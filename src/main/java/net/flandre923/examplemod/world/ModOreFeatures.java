package net.flandre923.examplemod.world;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = createKey("ruby");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> pContext) {
        RuleTest stoneOreReplaceRuleTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepSlateOreReplaceRuleTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> list = List.of(
                OreConfiguration.target(stoneOreReplaceRuleTest, ModBlocks.RUBY_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepSlateOreReplaceRuleTest, ModBlocks.RUBY_BLOCK.get().defaultBlockState())
        );

        FeatureUtils.register(pContext, ORE_RUBY, Feature.ORE, new OreConfiguration(list, 9));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ExampleMod.MODID,pName));
    }

}
