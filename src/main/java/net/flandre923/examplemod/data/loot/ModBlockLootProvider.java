package net.flandre923.examplemod.data.loot;

import net.flandre923.examplemod.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {

    public static final Set<Block> BLOCK = Set.of(
      ModBlocks.RUBY_ORE.get()
    );

    public ModBlockLootProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.RUBY_ORE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCK;
    }
}
