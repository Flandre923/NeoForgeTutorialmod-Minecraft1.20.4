package net.flandre923.examplemod.item.custom;

import net.flandre923.examplemod.capability.ISpeedUpCapability;
import net.flandre923.examplemod.capability.ModCapabilities;
import net.flandre923.examplemod.capability.impl.SpeedUpCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpeedUpShowItem extends Item {
    public SpeedUpShowItem() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && pUsedHand == InteractionHand.MAIN_HAND) {
            ISpeedUpCapability capability = pPlayer.getCapability(ModCapabilities.SPEED_CAPABILITY_HANDLER);
            if(capability!=null){
                int level = capability.getLevel();
                pPlayer.sendSystemMessage(Component.literal("speed level:" + level));
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
