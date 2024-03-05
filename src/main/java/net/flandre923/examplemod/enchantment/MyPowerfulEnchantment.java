package net.flandre923.examplemod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class MyPowerfulEnchantment extends Enchantment {
    protected MyPowerfulEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, ExampleModReference.MY_POWER, pApplicableSlots);
    }

    /**
     *返回所传递附魔等级所需的最小附魔能力值。
     */
    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 1 + (pEnchantmentLevel - 1) * 10;
    }
    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 15;
    }

    @Override
    public int getMinLevel() {
        return super.getMinLevel();
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }


}
