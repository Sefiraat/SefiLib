package dev.sefiraat.sefilib.version;

import io.github.bakedlibs.dough.versions.SemanticVersion;

public class Versions {

    private Versions() {
        throw new IllegalStateException("Utility class");
    }

    public static final SemanticVersion VERSION_1_14 = new SemanticVersion(1, 14);
    public static final SemanticVersion VERSION_1_15 = new SemanticVersion(1, 15);
    public static final SemanticVersion VERSION_1_16 = new SemanticVersion(1, 16);
    public static final SemanticVersion VERSION_1_17 = new SemanticVersion(1, 17);
    public static final SemanticVersion VERSION_1_18 = new SemanticVersion(1, 18);
    public static final SemanticVersion VERSION_1_19 = new SemanticVersion(1, 19);
    public static final SemanticVersion VERSION_1_20 = new SemanticVersion(1, 20);
    public static final SemanticVersion VERSION_LATEST = VERSION_1_20;
    public static final SemanticVersion VERSION_EARLIEST = VERSION_1_14;
}
