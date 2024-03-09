package net.flandre923.examplemod.enchantment;

import net.flandre923.examplemod.item.custom.EnchantmentExampleItem;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ExampleModReference {
    public static final EnchantmentCategory MY_POWER = EnchantmentCategory.create("MY_POWER", EnchantmentExampleItem.class::isInstance);
}