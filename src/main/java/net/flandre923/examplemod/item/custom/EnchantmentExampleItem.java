package net.flandre923.examplemod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnchantmentExampleItem extends Item {
    public EnchantmentExampleItem() {
        super(new Properties());
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }
}
