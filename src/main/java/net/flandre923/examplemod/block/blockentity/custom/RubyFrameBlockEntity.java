package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class RubyFrameBlockEntity extends BlockEntity {
    public RubyFrameBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RUBY_FRAME_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
}
