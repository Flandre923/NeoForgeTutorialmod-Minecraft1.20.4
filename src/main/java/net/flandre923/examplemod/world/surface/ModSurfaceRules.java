package net.flandre923.examplemod.world.surface;

import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.world.biome.ModBiomeData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;


public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource RUBY_BLOCK = makeStateRule(ModBlocks.RUBY_BLOCK.get());
    private static final SurfaceRules.RuleSource DIAMOND_ORE = makeStateRule(Blocks.DIAMOND_ORE);


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomeData.EXAMPLE_BIOME),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, RUBY_BLOCK)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, DIAMOND_ORE)),


                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }



}
