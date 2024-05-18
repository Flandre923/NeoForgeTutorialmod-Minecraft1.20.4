package net.flandre923.examplemod.world.biome;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomeData {


    public static final ResourceKey<Biome> EXAMPLE_BIOME = register("example_biome");

    private static ResourceKey<Biome> register(String pKey) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(ExampleMod.MODID,pKey));
    }


    public static void bootstrap(BootstrapContext<Biome> pContext) {

        HolderGetter<PlacedFeature> holdergetter = pContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = pContext.lookup(Registries.CONFIGURED_CARVER);
        pContext.register(ModBiomeData.EXAMPLE_BIOME, ExampleOverworldBiomes.exampleBiome(holdergetter, holdergetter1));

    }
}
