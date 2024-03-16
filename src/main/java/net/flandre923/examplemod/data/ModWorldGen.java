package net.flandre923.examplemod.data;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.world.ModBiomeModifiers;
import net.flandre923.examplemod.world.ModOreFeatures;
import net.flandre923.examplemod.world.ModOrePlacements;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGen extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModOreFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModOrePlacements::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER,Set.of(ExampleMod.MODID));
    }
}
