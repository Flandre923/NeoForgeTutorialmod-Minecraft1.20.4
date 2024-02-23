package net.flandre923.examplemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ObsidianObj extends Block {
    public ObsidianObj() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion());
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return super.getShadeBrightness(pState, pLevel, pPos);
    }
}
