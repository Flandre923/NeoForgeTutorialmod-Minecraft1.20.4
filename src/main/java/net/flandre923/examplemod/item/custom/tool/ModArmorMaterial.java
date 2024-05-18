package net.flandre923.examplemod.item.custom.tool;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.command.ExampleCommand;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.A;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterial  {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIAL = DeferredRegister.create(Registries.ARMOR_MATERIAL, ExampleMod.MODID);

    public static final DeferredHolder<ArmorMaterial,ArmorMaterial> RUBY = register(
            "ruby",
            Util.make(new EnumMap<>(ArmorItem.Type.class),typeObjectEnumMap -> {
                typeObjectEnumMap.put(ArmorItem.Type.BOOTS,1);
                typeObjectEnumMap.put(ArmorItem.Type.LEGGINGS,2);
                typeObjectEnumMap.put(ArmorItem.Type.CHESTPLATE,3);
                typeObjectEnumMap.put(ArmorItem.Type.HELMET,1);
                typeObjectEnumMap.put(ArmorItem.Type.BODY,3);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0F,
            0F,
            ()-> Ingredient.of(ModItems.RUBY.get()),
            List.of(new ArmorMaterial.Layer(new ResourceLocation(ExampleMod.MODID,"ruby"),"",true),new ArmorMaterial.Layer(new ResourceLocation(ExampleMod.MODID,"ruby"),"_overlay",false))
    );

    public static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name,
                                                                                 EnumMap<ArmorItem.Type,Integer> pDefense,
                                                                                 int pEnchantmentValue,
                                                                                 Holder<SoundEvent> pEquipSound,
                                                                                 float pToughness,
                                                                                 float pKnockResistance,
                                                                                 Supplier<Ingredient> pRepairIngridient,
                                                                                 List<ArmorMaterial.Layer> pLayers){

        EnumMap<ArmorItem.Type,Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for(ArmorItem.Type armorMaterial:ArmorItem.Type.values()){
            enummap.put(armorMaterial,pDefense.get(armorMaterial));
        }

        return ARMOR_MATERIAL.register(name,()->new ArmorMaterial(enummap,pEnchantmentValue,pEquipSound,pRepairIngridient,pLayers,pToughness,pKnockResistance));
    }
    public static void register(IEventBus eventBus){
        ARMOR_MATERIAL.register(eventBus);
    }

}
