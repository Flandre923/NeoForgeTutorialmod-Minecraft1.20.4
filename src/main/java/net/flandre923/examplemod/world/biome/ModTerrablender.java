package net.flandre923.examplemod.world.biome;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiome(){
        Regions.register(new ModOverworldRegion(new ResourceLocation(ExampleMod.MODID,"overworld"),5));
    }
}
