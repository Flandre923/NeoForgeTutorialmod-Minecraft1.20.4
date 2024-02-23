package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HelloBlockEntity extends BlockEntity  {
    private static final int MAX_TIME = 5 * 20;
    private int timer = 0;
    public HelloBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.HELLO_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, HelloBlockEntity pBlockEntity) {
        if(pLevel!=null && !pLevel.isClientSide){
            if(pBlockEntity.timer == HelloBlockEntity.MAX_TIME){
                Player nearestPlayer = pLevel.getNearestPlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 10, false);
                Component component = Component.literal("hello");
                if(nearestPlayer!=null){
                    nearestPlayer.sendSystemMessage(component);
                }
                pBlockEntity.timer = 0;
            }
            pBlockEntity.timer++;
        }
    }

}
