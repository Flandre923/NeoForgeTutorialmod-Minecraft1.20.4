package net.flandre923.examplemod.enchantment;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;

public class MyPowerfulEnchantment extends Enchantment {
    protected MyPowerfulEnchantment() {
        super(Enchantment.definition(ItemTags.BOW_ENCHANTABLE,2,2,Enchantment.dynamicCost(10,20),Enchantment.dynamicCost(60,20),4,EquipmentSlot.MAINHAND));
    }


}
