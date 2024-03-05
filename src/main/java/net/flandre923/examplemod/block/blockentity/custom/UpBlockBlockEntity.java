package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.capability.ISimpleCapability;
import net.flandre923.examplemod.capability.ModCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class UpBlockBlockEntity extends BlockEntity {
    public UpBlockBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.UP_BLOCK_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, UpBlockBlockEntity pBlockEntity) {
        if(pLevel!=null && !pLevel.isClientSide){
            BlockPos pos = pPos.below();
            BlockEntity blockEntity = pLevel.getBlockEntity(pos);
            if(blockEntity instanceof DownBlockBlockEntity){
                ISimpleCapability simpleCapability = pLevel.getCapability(ModCapabilities.SIMPLE_CAPABILITY_HANDLER,pos,null);
                if(simpleCapability!=null){
                    simpleCapability.getString(pBlockEntity.getBlockPos());
                }
            }
        }
    }

}
