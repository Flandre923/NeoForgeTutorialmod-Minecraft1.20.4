package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ZombieBlockEntity extends BlockEntity {
    private static final  int MAX_TIME = 5 * 20;
    private boolean flag = false;
    private int timer = 0;

    public ZombieBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ZOMBIE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
// getUpdatePacket是服务端发送数据包用的方法，
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    //而onDataPacket才是客户端接受数据包的方法。
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        handleUpdateTag(pkt.getTag(),lookupProvider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag compoundNBT = super.getUpdateTag(pRegistries);
        compoundNBT.putBoolean("flag", flag);
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        flag = tag.getBoolean("flag");
    }

    public static void clientTick(Level pLevel, BlockPos pPos, BlockState pState, ZombieBlockEntity pBlockEntity) {
        if(pLevel.isClientSide && pBlockEntity.flag){
            var player =  pLevel.getNearestPlayer(pPos.getX(),pPos.getY(),pPos.getZ(),10,false);
            pLevel.playSound(player,pPos, SoundEvents.ZOMBIE_AMBIENT, SoundSource.AMBIENT,1f,1f);
            pBlockEntity.flag = false;
        }
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, ZombieBlockEntity pBlockEntity) {
        if(!pLevel.isClientSide){
            if(pBlockEntity.timer >= MAX_TIME){
                pBlockEntity.flag = true;
                pLevel.sendBlockUpdated(pPos,pLevel.getBlockState(pPos),pLevel.getBlockState(pPos),3);
//                pBlockEntity.flag = true;
                pBlockEntity.timer = 0;
            }
            pBlockEntity.timer ++;
        }
    }
}
