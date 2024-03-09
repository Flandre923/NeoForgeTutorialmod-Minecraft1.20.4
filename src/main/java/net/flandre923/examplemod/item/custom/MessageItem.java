package net.flandre923.examplemod.item.custom;

import net.flandre923.examplemod.network.packet.MyData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class MessageItem extends Item {
    public MessageItem() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide){
//            PacketDistributor.PLAYER.with(pPlayer).send(new );
            PacketDistributor.SERVER.noArg().send(new MyData("From client",2));
        }
        if(!pLevel.isClientSide){
            PacketDistributor.PLAYER.with((ServerPlayer) pPlayer).send(new MyData("From server",2));
        }
        return super.use(pLevel,pPlayer,pUsedHand);
    }
}
