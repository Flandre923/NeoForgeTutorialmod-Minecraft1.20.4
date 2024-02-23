package net.flandre923.examplemod.item.custom.tool;

import net.flandre923.examplemod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {

    RUBY("ruby", 33, Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 6);
        map.put(ArmorItem.Type.CHESTPLATE, 8);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(ModItems.RUBY.get()));

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 13);
        map.put(ArmorItem.Type.LEGGINGS, 15);
        map.put(ArmorItem.Type.CHESTPLATE, 16);
        map.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterial(
            String pName,
            int pDurabilityMultiplier,
            EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType,
            int pEnchantmentValue,
            SoundEvent pSound,
            float pToughness,
            float pKnockbackResistance,
            Supplier<Ingredient> pRepairIngredient
    ) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = pProtectionFunctionForType;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
