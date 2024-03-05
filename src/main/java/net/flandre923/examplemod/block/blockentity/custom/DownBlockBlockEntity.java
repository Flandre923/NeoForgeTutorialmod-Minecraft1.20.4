package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DownBlockBlockEntity extends BlockEntity {
    public DownBlockBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.DOWN_BLOCK_BLOCK_ENTITY.get(), pPos, pBlockState);
    }


}
