package net.flandre923.examplemod.item.custom;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.client.gui.FirstGui;
import net.flandre923.examplemod.client.gui.GuiOpenWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;

public class OpenFirstGuiItem extends Item {
    public OpenFirstGuiItem() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide){
            GuiOpenWrapper.openFirstGui();
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
