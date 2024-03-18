package net.flandre923.examplemod.world.structure;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class ModStructureSets {
    public static final ResourceKey<StructureSet> MY_STRUCTURE_SET = register("my_structure");

    public static void bootstrap(BootstapContext<StructureSet> pContext) {
        HolderGetter<Structure> holdergetter = pContext.lookup(Registries.STRUCTURE);

        pContext.register(
                ModStructureSets.MY_STRUCTURE_SET,
                new StructureSet(holdergetter.getOrThrow(ModStructures.MY_STRUCTURE), new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357619))
        );
    }

    private static ResourceKey<StructureSet> register(String pName) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(ExampleMod.MODID,pName));
    }

}
