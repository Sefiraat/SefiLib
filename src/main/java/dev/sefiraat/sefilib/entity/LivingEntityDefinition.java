package dev.sefiraat.sefilib.entity;

import com.google.common.collect.Sets;
import dev.sefiraat.sefilib.version.Versions;
import io.github.bakedlibs.dough.versions.SemanticVersion;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public enum LivingEntityDefinition {

    ALLAY(EntityType.ALLAY, Versions.VERSION_1_19,
          LivingEntityCategory.FLYING,
          LivingEntityCategory.PASSIVE,
          LivingEntityCategory.TAMEABLE
    ),

    AXOLOTL(EntityType.AXOLOTL, Versions.VERSION_1_17,
            LivingEntityCategory.ANIMAL,
            LivingEntityCategory.AQUATIC,
            LivingEntityCategory.BREEDABLE,
            LivingEntityCategory.PASSIVE
    ),

    BAT(EntityType.BAT, Versions.VERSION_1_14,
        LivingEntityCategory.ANIMAL,
        LivingEntityCategory.FLYING,
        LivingEntityCategory.PASSIVE
    ),

    BEE(EntityType.BEE, Versions.VERSION_1_15,
        LivingEntityCategory.ANIMAL,
        LivingEntityCategory.ARTHROPOD,
        LivingEntityCategory.BREEDABLE,
        LivingEntityCategory.FLYING,
        LivingEntityCategory.NEUTRAL
    ),

    BLAZE(EntityType.BLAZE, Versions.VERSION_1_14,
          LivingEntityCategory.FLYING,
          LivingEntityCategory.HOSTILE,
          LivingEntityCategory.NETHER
    ),

    CAT(EntityType.CAT, Versions.VERSION_1_14,
        LivingEntityCategory.ANIMAL,
        LivingEntityCategory.BREEDABLE,
        LivingEntityCategory.PASSIVE,
        LivingEntityCategory.TAMEABLE
    ),

    CAVE_SPIDER(EntityType.CAVE_SPIDER, Versions.VERSION_1_14,
                LivingEntityCategory.ARTHROPOD,
                LivingEntityCategory.NEUTRAL
    ),

    CHICKEN(EntityType.CHICKEN, Versions.VERSION_1_14,
            LivingEntityCategory.ANIMAL,
            LivingEntityCategory.BREEDABLE,
            LivingEntityCategory.FLYING,
            LivingEntityCategory.PASSIVE
    ),

    COD(EntityType.COD, Versions.VERSION_1_14,
        LivingEntityCategory.ANIMAL,
        LivingEntityCategory.AQUATIC,
        LivingEntityCategory.FISH,
        LivingEntityCategory.PASSIVE
    ),

    COW(EntityType.COW, Versions.VERSION_1_14,
        LivingEntityCategory.ANIMAL,
        LivingEntityCategory.BREEDABLE,
        LivingEntityCategory.PASSIVE
    ),

    CREEPER(EntityType.CREEPER, Versions.VERSION_1_14,
            LivingEntityCategory.HOSTILE
    ),

    DOLPHIN(EntityType.DOLPHIN, Versions.VERSION_1_14,
            LivingEntityCategory.ANIMAL,
            LivingEntityCategory.AQUATIC,
            LivingEntityCategory.NEUTRAL
    ),

    DONKEY(EntityType.DONKEY, Versions.VERSION_1_14,
           LivingEntityCategory.ANIMAL,
           LivingEntityCategory.BREEDABLE,
           LivingEntityCategory.PASSIVE,
           LivingEntityCategory.RIDEABLE,
           LivingEntityCategory.TAMEABLE
    ),

    DROWNED(EntityType.DROWNED, Versions.VERSION_1_14,
            LivingEntityCategory.HOSTILE,
            LivingEntityCategory.UNDEAD
    ),

    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, Versions.VERSION_1_14,
                   LivingEntityCategory.HOSTILE,
                   LivingEntityCategory.AQUATIC,
                   LivingEntityCategory.BOSS
    ),

    ENDER_DRAGON(EntityType.ENDER_DRAGON, Versions.VERSION_1_14,
                 LivingEntityCategory.HOSTILE,
                 LivingEntityCategory.FLYING,
                 LivingEntityCategory.END,
                 LivingEntityCategory.BOSS
    ),

    ENDERMAN(EntityType.ENDERMAN, Versions.VERSION_1_14,
             LivingEntityCategory.NEUTRAL,
             LivingEntityCategory.END
    ),

    ENDERMITE(EntityType.ENDERMITE, Versions.VERSION_1_14,
              LivingEntityCategory.ARTHROPOD,
              LivingEntityCategory.HOSTILE
    ),

    EVOKER(EntityType.EVOKER, Versions.VERSION_1_14,
           LivingEntityCategory.HOSTILE,
           LivingEntityCategory.RAID,
           LivingEntityCategory.ILLAGER
    ),

    FOX(EntityType.FOX, Versions.VERSION_1_14,
        LivingEntityCategory.ANIMAL,
        LivingEntityCategory.BREEDABLE,
        LivingEntityCategory.PASSIVE,
        LivingEntityCategory.TAMEABLE
    ),

    FROG(EntityType.FROG, Versions.VERSION_1_19,
         LivingEntityCategory.ANIMAL,
         LivingEntityCategory.AQUATIC,
         LivingEntityCategory.BREEDABLE,
         LivingEntityCategory.PASSIVE
    ),

    GHAST(EntityType.GHAST, Versions.VERSION_1_14,
          LivingEntityCategory.HOSTILE,
          LivingEntityCategory.FLYING,
          LivingEntityCategory.NETHER
    ),

    GIANT(EntityType.GIANT, Versions.VERSION_1_14,
          LivingEntityCategory.HOSTILE,
          LivingEntityCategory.UNDEAD
    ),

    GLOW_SQUID(EntityType.GLOW_SQUID, Versions.VERSION_1_17,
               LivingEntityCategory.ANIMAL,
               LivingEntityCategory.AQUATIC,
               LivingEntityCategory.PASSIVE
    ),

    GOAT(EntityType.GOAT, Versions.VERSION_1_17,
         LivingEntityCategory.ANIMAL,
         LivingEntityCategory.BREEDABLE,
         LivingEntityCategory.NEUTRAL
    ),

    GUARDIAN(EntityType.GUARDIAN, Versions.VERSION_1_14,
             LivingEntityCategory.AQUATIC,
             LivingEntityCategory.HOSTILE
    ),

    HOGLIN(EntityType.HOGLIN, Versions.VERSION_1_16,
           LivingEntityCategory.BREEDABLE,
           LivingEntityCategory.HOSTILE,
           LivingEntityCategory.NETHER,
           LivingEntityCategory.PIGLIN
    ),

    HORSE(EntityType.HORSE, Versions.VERSION_1_14,
          LivingEntityCategory.ANIMAL,
          LivingEntityCategory.BREEDABLE,
          LivingEntityCategory.PASSIVE,
          LivingEntityCategory.RIDEABLE,
          LivingEntityCategory.TAMEABLE
    ),

    HUSK(EntityType.HUSK, Versions.VERSION_1_14,
         LivingEntityCategory.HOSTILE,
         LivingEntityCategory.UNDEAD
    ),

    ILLUSIONER(EntityType.ILLUSIONER, Versions.VERSION_1_14,
               LivingEntityCategory.ILLAGER
    ),

    IRON_GOLEM(EntityType.IRON_GOLEM, Versions.VERSION_1_14,
               LivingEntityCategory.GOLEM,
               LivingEntityCategory.NEUTRAL
    ),

    LLAMA(EntityType.LLAMA, Versions.VERSION_1_14,
          LivingEntityCategory.ANIMAL,
          LivingEntityCategory.BREEDABLE,
          LivingEntityCategory.NEUTRAL,
          LivingEntityCategory.TAMEABLE
    ),

    MAGMA_CUBE(EntityType.MAGMA_CUBE, Versions.VERSION_1_14,
               LivingEntityCategory.HOSTILE,
               LivingEntityCategory.NETHER
    ),

    MOOSHROOM(EntityType.MUSHROOM_COW, Versions.VERSION_1_14,
              LivingEntityCategory.ANIMAL,
              LivingEntityCategory.BREEDABLE,
              LivingEntityCategory.PASSIVE
    ),

    MULE(EntityType.MULE, Versions.VERSION_1_14,
         LivingEntityCategory.ANIMAL,
         LivingEntityCategory.PASSIVE,
         LivingEntityCategory.RIDEABLE,
         LivingEntityCategory.TAMEABLE
    ),

    OCELOT(EntityType.OCELOT, Versions.VERSION_1_14,
           LivingEntityCategory.ANIMAL,
           LivingEntityCategory.BREEDABLE,
           LivingEntityCategory.PASSIVE,
           LivingEntityCategory.TAMEABLE
    ),

    PANDA(EntityType.PANDA, Versions.VERSION_1_14,
          LivingEntityCategory.ANIMAL,
          LivingEntityCategory.BREEDABLE,
          LivingEntityCategory.NEUTRAL
    ),

    PARROT(EntityType.PARROT, Versions.VERSION_1_14,
           LivingEntityCategory.ANIMAL,
           LivingEntityCategory.FLYING,
           LivingEntityCategory.PASSIVE,
           LivingEntityCategory.TAMEABLE
    ),

    PHANTOM(EntityType.PHANTOM, Versions.VERSION_1_14,
            LivingEntityCategory.FLYING,
            LivingEntityCategory.HOSTILE,
            LivingEntityCategory.UNDEAD
    ),

    PIG(EntityType.PIG, Versions.VERSION_1_14,
        LivingEntityCategory.ANIMAL,
        LivingEntityCategory.BREEDABLE,
        LivingEntityCategory.PASSIVE,
        LivingEntityCategory.RIDEABLE
    ),

    PIGLIN(EntityType.PIGLIN, Versions.VERSION_1_16,
           LivingEntityCategory.NETHER,
           LivingEntityCategory.NEUTRAL,
           LivingEntityCategory.PIGLIN
    ),

    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, Versions.VERSION_1_16,
                 LivingEntityCategory.HOSTILE,
                 LivingEntityCategory.NETHER,
                 LivingEntityCategory.PIGLIN
    ),

    PILLAGER(EntityType.PILLAGER, Versions.VERSION_1_14,
             LivingEntityCategory.HOSTILE,
             LivingEntityCategory.RAID,
             LivingEntityCategory.ILLAGER
    ),

    POLAR_BEAR(EntityType.POLAR_BEAR, Versions.VERSION_1_14,
               LivingEntityCategory.ANIMAL,
               LivingEntityCategory.BREEDABLE,
               LivingEntityCategory.NEUTRAL
    ),

    PUFFERFISH(EntityType.PUFFERFISH, Versions.VERSION_1_14,
               LivingEntityCategory.ANIMAL,
               LivingEntityCategory.AQUATIC,
               LivingEntityCategory.FISH,
               LivingEntityCategory.PASSIVE
    ),

    RABBIT(EntityType.RABBIT, Versions.VERSION_1_14,
           LivingEntityCategory.ANIMAL,
           LivingEntityCategory.BREEDABLE,
           LivingEntityCategory.PASSIVE
    ),

    RAVAGER(EntityType.RAVAGER, Versions.VERSION_1_14,
            LivingEntityCategory.HOSTILE,
            LivingEntityCategory.RAID,
            LivingEntityCategory.ILLAGER
    ),

    SALMON(EntityType.SALMON, Versions.VERSION_1_14,
           LivingEntityCategory.ANIMAL,
           LivingEntityCategory.AQUATIC,
           LivingEntityCategory.FISH,
           LivingEntityCategory.PASSIVE
    ),

    SHEEP(EntityType.SHEEP, Versions.VERSION_1_14,
          LivingEntityCategory.ANIMAL,
          LivingEntityCategory.BREEDABLE,
          LivingEntityCategory.PASSIVE
    ),

    SHULKER(EntityType.SHULKER, Versions.VERSION_1_14,
            LivingEntityCategory.HOSTILE,
            LivingEntityCategory.END
    ),

    SILVERFISH(EntityType.SILVERFISH, Versions.VERSION_1_14,
               LivingEntityCategory.ARTHROPOD,
               LivingEntityCategory.HOSTILE
    ),

    SKELETON_HORSE(EntityType.SKELETON_HORSE, Versions.VERSION_1_14,
                   LivingEntityCategory.PASSIVE,
                   LivingEntityCategory.RIDEABLE,
                   LivingEntityCategory.TAMEABLE,
                   LivingEntityCategory.UNDEAD
    ),

    SLIME(EntityType.SLIME, Versions.VERSION_1_14,
          LivingEntityCategory.HOSTILE
    ),

    SNOW_GOLEM(EntityType.SNOWMAN, Versions.VERSION_1_14,
               LivingEntityCategory.GOLEM,
               LivingEntityCategory.PASSIVE
    ),

    SPIDER(EntityType.SPIDER, Versions.VERSION_1_14,
           LivingEntityCategory.ARTHROPOD,
           LivingEntityCategory.NEUTRAL
    ),

    SQUID(EntityType.SQUID, Versions.VERSION_1_14,
          LivingEntityCategory.ANIMAL,
          LivingEntityCategory.AQUATIC,
          LivingEntityCategory.PASSIVE
    ),

    STRAY(EntityType.STRAY, Versions.VERSION_1_14,
          LivingEntityCategory.HOSTILE,
          LivingEntityCategory.UNDEAD
    ),

    STRIDER(EntityType.STRIDER, Versions.VERSION_1_16,
            LivingEntityCategory.BREEDABLE,
            LivingEntityCategory.NETHER,
            LivingEntityCategory.PASSIVE,
            LivingEntityCategory.RIDEABLE
    ),

    TADPOLE(EntityType.TADPOLE, Versions.VERSION_1_19,
            LivingEntityCategory.ANIMAL,
            LivingEntityCategory.AQUATIC,
            LivingEntityCategory.PASSIVE
    ),

    TRADER_LLAMA(EntityType.TRADER_LLAMA, Versions.VERSION_1_14,
                 LivingEntityCategory.ANIMAL,
                 LivingEntityCategory.TAMEABLE
    ),

    TROPICAL_FISH(EntityType.TROPICAL_FISH, Versions.VERSION_1_14,
                  LivingEntityCategory.ANIMAL,
                  LivingEntityCategory.AQUATIC,
                  LivingEntityCategory.FISH,
                  LivingEntityCategory.PASSIVE
    ),

    TURTLE(EntityType.TURTLE, Versions.VERSION_1_14,
           LivingEntityCategory.ANIMAL,
           LivingEntityCategory.AQUATIC,
           LivingEntityCategory.BREEDABLE,
           LivingEntityCategory.PASSIVE
    ),

    VEX(EntityType.VEX, Versions.VERSION_1_14,
        LivingEntityCategory.FLYING,
        LivingEntityCategory.HOSTILE,
        LivingEntityCategory.ILLAGER
    ),

    VILLAGER(EntityType.VILLAGER, Versions.VERSION_1_14,
             LivingEntityCategory.BREEDABLE,
             LivingEntityCategory.PASSIVE,
             LivingEntityCategory.TRADER,
             LivingEntityCategory.VILLAGER
    ),

    VINDICATOR(EntityType.VINDICATOR, Versions.VERSION_1_14,
               LivingEntityCategory.HOSTILE,
               LivingEntityCategory.RAID,
               LivingEntityCategory.ILLAGER
    ),

    WANDERING_TRADER(EntityType.WANDERING_TRADER, Versions.VERSION_1_14,
                     LivingEntityCategory.TRADER,
                     LivingEntityCategory.VILLAGER,
                     LivingEntityCategory.PASSIVE
    ),

    WARDEN(EntityType.WARDEN, Versions.VERSION_1_19,
           LivingEntityCategory.HOSTILE
    ),

    WITCH(EntityType.WITCH, Versions.VERSION_1_14,
          LivingEntityCategory.HOSTILE,
          LivingEntityCategory.RAID
    ),

    WITHER(EntityType.WITHER, Versions.VERSION_1_14,
           LivingEntityCategory.BOSS,
           LivingEntityCategory.FLYING,
           LivingEntityCategory.HOSTILE,
           LivingEntityCategory.UNDEAD
    ),

    WITHER_SKELETON(EntityType.WITHER_SKELETON, Versions.VERSION_1_14,
                    LivingEntityCategory.HOSTILE,
                    LivingEntityCategory.NETHER,
                    LivingEntityCategory.UNDEAD
    ),

    WOLF(EntityType.WOLF, Versions.VERSION_1_14,
         LivingEntityCategory.ANIMAL,
         LivingEntityCategory.BREEDABLE,
         LivingEntityCategory.NEUTRAL,
         LivingEntityCategory.TAMEABLE
    ),

    ZOGLIN(EntityType.ZOGLIN, Versions.VERSION_1_16,
           LivingEntityCategory.HOSTILE,
           LivingEntityCategory.NETHER,
           LivingEntityCategory.PIGLIN,
           LivingEntityCategory.UNDEAD
    ),

    ZOMBIE(EntityType.ZOMBIE, Versions.VERSION_1_14,
           LivingEntityCategory.HOSTILE,
           LivingEntityCategory.UNDEAD
    ),

    ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE, Versions.VERSION_1_14,
                 LivingEntityCategory.NEUTRAL,
                 LivingEntityCategory.UNDEAD
    ),

    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, Versions.VERSION_1_14,
                    LivingEntityCategory.HOSTILE,
                    LivingEntityCategory.VILLAGER,
                    LivingEntityCategory.UNDEAD
    ),

    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, Versions.VERSION_1_16,
                     LivingEntityCategory.NETHER,
                     LivingEntityCategory.NEUTRAL,
                     LivingEntityCategory.PIGLIN,
                     LivingEntityCategory.UNDEAD
    );

    private static final LivingEntityDefinition[] VALUES = values();

    private final EntityType type;
    private final SemanticVersion lowestVersion;
    private final Set<LivingEntityCategory> categories;
    private boolean isFlying;
    private boolean isPassive;
    private boolean isHostile;
    private boolean isNeutral;
    private boolean isTameable;
    private boolean isAnimal;
    private boolean isAquatic;
    private boolean isBreedable;
    private boolean isNether;
    private boolean isArthropod;
    private boolean isFish;
    private boolean isRideable;
    private boolean isUndead;
    private boolean isEnd;
    private boolean isBoss;
    private boolean isRaid;
    private boolean isIllager;
    private boolean isPiglin;
    private boolean isGolem;
    private boolean isTrader;
    private boolean isVillager;

    @ParametersAreNonnullByDefault
    LivingEntityDefinition(EntityType type, SemanticVersion lowestVersion, LivingEntityCategory... categories) {
        this.type = type;
        this.lowestVersion = lowestVersion;
        this.categories = Sets.newHashSet(categories);
        for (LivingEntityCategory category : categories) {
            switch (category) {
                case ANIMAL -> this.isAnimal = true;
                case AQUATIC -> this.isAquatic = true;
                case ARTHROPOD -> this.isArthropod = true;
                case BOSS -> this.isBoss = true;
                case BREEDABLE -> this.isBreedable = true;
                case END -> this.isEnd = true;
                case FISH -> this.isFish = true;
                case FLYING -> this.isFlying = true;
                case GOLEM -> this.isGolem = true;
                case HOSTILE -> this.isHostile = true;
                case ILLAGER -> this.isIllager = true;
                case NETHER -> this.isNether = true;
                case NEUTRAL -> this.isNeutral = true;
                case PASSIVE -> this.isPassive = true;
                case PIGLIN -> this.isPiglin = true;
                case RAID -> this.isRaid = true;
                case RIDEABLE -> this.isRideable = true;
                case TAMEABLE -> this.isTameable = true;
                case TRADER -> this.isTrader = true;
                case UNDEAD -> this.isUndead = true;
                case VILLAGER -> this.isVillager = true;
            }
        }
    }

    public EntityType getType() {
        return type;
    }

    public SemanticVersion getLowestVersion() {
        return lowestVersion;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public boolean isHostile() {
        return isHostile;
    }

    public boolean isNeutral() {
        return isNeutral;
    }

    public boolean isTameable() {
        return isTameable;
    }

    public boolean isAnimal() {
        return isAnimal;
    }

    public boolean isAquatic() {
        return isAquatic;
    }

    public boolean isBreedable() {
        return isBreedable;
    }

    public boolean isNether() {
        return isNether;
    }

    public boolean isArthropod() {
        return isArthropod;
    }

    public boolean isFish() {
        return isFish;
    }

    public boolean isRideable() {
        return isRideable;
    }

    public boolean isUndead() {
        return isUndead;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public boolean isRaid() {
        return isRaid;
    }

    public boolean isIllager() {
        return isIllager;
    }

    public boolean isPiglin() {
        return isPiglin;
    }

    public boolean isGolem() {
        return isGolem;
    }

    public boolean isVillager() {
        return isVillager;
    }

    public boolean isTrader() {
        return isTrader;
    }

    public boolean existsIn(SemanticVersion version) {
        return this.lowestVersion.isAtLeast(version);
    }

    public boolean isCategorized(LivingEntityCategory category) {
        return categories.contains(category);
    }

    public boolean isCategorized(LivingEntityCategory... categories) {
        return isCategorized(Sets.newHashSet(categories));
    }

    public boolean isCategorized(Collection<LivingEntityCategory> categories) {
        for (LivingEntityCategory category : categories) {
            if (!isCategorized(category)) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public static LivingEntityDefinition fromType(EntityType type) {
        for (LivingEntityDefinition value : VALUES) {
            if (value.type == type) {
                return value;
            }
        }
        return null;
    }

    public static LivingEntityDefinition[] getValues() {
        return Arrays.copyOf(VALUES, VALUES.length);
    }

    public static Set<LivingEntityDefinition> getByCategory(LivingEntityCategory category) {
        return getByCategory(category, Versions.VERSION_EARLIEST);
    }

    public static Set<LivingEntityDefinition> getByCategory(LivingEntityCategory category, SemanticVersion version) {
        Set<LivingEntityDefinition> livingEntityDefinitions = new HashSet<>();
        for (LivingEntityDefinition definition : getValues()) {
            if (definition.isCategorized(category) && definition.existsIn(version)) {
                livingEntityDefinitions.add(definition);
            }
        }
        return livingEntityDefinitions;
    }
}
