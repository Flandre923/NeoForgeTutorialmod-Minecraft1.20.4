package net.flandre923.examplemod.client.model;

import net.flandre923.examplemod.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelDataManager;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class HiddenBlockModel implements BakedModel {
    BakedModel defaultModel;
    public static ModelProperty<BlockState> COPIED_BLOCK = new ModelProperty<>();

    public HiddenBlockModel(BakedModel existingModel){
        this.defaultModel = existingModel;
    }
    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData data, @Nullable RenderType renderType) {
        BakedModel renderModel = defaultModel;
        if (data.has(COPIED_BLOCK)) {
            BlockState copiedBlock = data.get(COPIED_BLOCK);
            if (copiedBlock != null) {
                Minecraft mc = Minecraft.getInstance();
                BlockRenderDispatcher blockRendererDispatcher = mc.getBlockRenderer();
                renderModel = blockRendererDispatcher.getBlockModel(copiedBlock);
            }
        }
        return renderModel.getQuads(state,side,rand,data,renderType);
    }

    @Override
    public @NotNull ModelData getModelData(@NotNull BlockAndTintGetter level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull ModelData modelData) {
        BlockState downBlockState = level.getBlockState(pos.below());
        ModelData modelDataMap = modelData.derive().with(COPIED_BLOCK,null).build();

        if(downBlockState.getBlock() == Blocks.AIR || downBlockState.getBlock() == ModBlocks.HIDDEN_BLOCK.get()){
            return modelDataMap;
        }

        return modelDataMap.derive().with(COPIED_BLOCK,downBlockState).build();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState pState, @Nullable Direction pDirection, RandomSource pRandom) {
        throw new AssertionError("IBakedModel::getQuads should never be called, only IForgeBakedModel::getQuads");
    }

    @Override
    public boolean useAmbientOcclusion() {
        return defaultModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return defaultModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return defaultModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return defaultModel.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return defaultModel.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return defaultModel.getOverrides();
    }
}
