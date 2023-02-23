package dev.sefiraat.sefilib.entity;

import dev.sefiraat.sefilib.version.Versions;
import io.github.bakedlibs.dough.versions.SemanticVersion;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LivingEntitySelector {
    private final Set<LivingEntityCategory> categoryInclusions = new HashSet<>();
    private final Set<LivingEntityCategory> categoryExclusions = new HashSet<>();
    private final Set<LivingEntityDefinition> typeInclusions = new HashSet<>();
    private final Set<LivingEntityDefinition> typeExclusions = new HashSet<>();
    private SemanticVersion version = Versions.VERSION_EARLIEST;

    public LivingEntitySelector includeCategories(LivingEntityCategory... categories) {
        Collections.addAll(categoryInclusions, categories);
        return this;
    }

    public LivingEntitySelector excludeCategories(LivingEntityCategory... categories) {
        Collections.addAll(categoryExclusions, categories);
        return this;
    }

    public LivingEntitySelector includeTypes(LivingEntityDefinition... categories) {
        Collections.addAll(typeInclusions, categories);
        return this;
    }

    public LivingEntitySelector excludeTypes(LivingEntityDefinition... categories) {
        Collections.addAll(typeExclusions, categories);
        return this;
    }

    public LivingEntitySelector setVersion(SemanticVersion version) {
        this.version = version;
        return this;
    }

    public Set<LivingEntityDefinition> process(MatchType matchType) {
        Set<LivingEntityDefinition> livingEntityDefinitions = new HashSet<>();

        // Categories
        addCatInclusions(livingEntityDefinitions, matchType);
        removeCatExclusions(livingEntityDefinitions, matchType);

        // Specific Type overrides
        livingEntityDefinitions.addAll(typeInclusions);
        livingEntityDefinitions.removeAll(typeExclusions);

        return livingEntityDefinitions;
    }

    private void addCatInclusions(Set<LivingEntityDefinition> set, MatchType matchType) {
        if (matchType == MatchType.MATCH_ANY) {
            for (LivingEntityCategory category : categoryInclusions) {
                for (LivingEntityDefinition definition : LivingEntityDefinition.getValues()) {
                    if (definition.isCategorized(category) && definition.existsIn(version)) {
                        set.add(definition);
                    }
                }
            }
        } else if (matchType == MatchType.MATCH_ALL) {
            for (LivingEntityDefinition definition : LivingEntityDefinition.getValues()) {
                if (definition.isCategorized(categoryInclusions) && definition.existsIn(version)) {
                    set.add(definition);
                }
            }
        }
    }

    private void removeCatExclusions(Set<LivingEntityDefinition> set, MatchType matchType) {
        if (matchType == MatchType.MATCH_ANY) {
            for (LivingEntityCategory category : categoryExclusions) {
                set.removeIf(definition -> definition.isCategorized(category) && definition.existsIn(version));
            }
        } else if (matchType == MatchType.MATCH_ALL) {
            set.removeIf(definition -> definition.isCategorized(categoryExclusions) && definition.existsIn(version));
        }
    }

    public static LivingEntitySelector start() {
        return new LivingEntitySelector();
    }

    public enum MatchType {
        MATCH_ALL,
        MATCH_ANY
    }
}
