package net.flandre923.examplemod.world.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

public class ExampleOverworldBiomes{

    protected static int calculateSkyColor(float pTemperature) {
        float $$1 = pTemperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome ExampleOverworldBiomes(
            boolean pHasPercipitation,
            float pTemperature,
            float pDownfall,
            MobSpawnSettings.Builder pMobSpawnSettings,
            BiomeGenerationSettings.Builder pGenerationSettings,
            @Nullable Music pBackgroundMusic
    ) {
        return ExampleOverworldBiomes(pHasPercipitation, pTemperature, pDownfall, 4159204, 329011, null, null, pMobSpawnSettings, pGenerationSettings, pBackgroundMusic);
    }

    private static Biome ExampleOverworldBiomes(
            boolean pHasPrecipitation,
            float pTemperature,
            float pDownfall,
            int pWaterColor,
            int pWaterFogColor,
            @Nullable Integer pGrassColorOverride,
            @Nullable Integer pFoliageColorOverride,
            MobSpawnSettings.Builder pMobSpawnSettings,
            BiomeGenerationSettings.Builder pGenerationSettings,
            @Nullable Music pBackgroundMusic
    ) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder()
                .waterColor(pWaterColor)
                .waterFogColor(pWaterFogColor)
                .fogColor(12638463)
                .skyColor(calculateSkyColor(pTemperature))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(pBackgroundMusic);
        if (pGrassColorOverride != null) {
            biomespecialeffects$builder.grassColorOverride(pGrassColorOverride);
        }

        if (pFoliageColorOverride != null) {
            biomespecialeffects$builder.foliageColorOverride(pFoliageColorOverride);
        }

        return new Biome.BiomeBuilder()
                .hasPrecipitation(pHasPrecipitation)
                .temperature(pTemperature)
                .downfall(pDownfall)
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(pMobSpawnSettings.build())
                .generationSettings(pGenerationSettings.build())
                .build();
    }

    public static Biome exampleBiome(HolderGetter<PlacedFeature> pPlacedFeatures, HolderGetter<ConfiguredWorldCarver<?>> pWorldCarvers) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(mobspawnsettings$builder);
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder(pPlacedFeatures, pWorldCarvers);
        BiomeDefaultFeatures.addFossilDecoration(biomegenerationsettings$builder);
        globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultGrass(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDesertVegetation(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDesertExtraDecoration(biomegenerationsettings$builder);
        return ExampleOverworldBiomes(false, 2.0F, 0.0F, mobspawnsettings$builder, biomegenerationsettings$builder, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));
    }




    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder pGenerationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultSprings(pGenerationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(pGenerationSettings);
    }

}
