package net.flandre923.examplemod.item;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.item.custom.*;
import net.flandre923.examplemod.item.custom.tool.ModArmorMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ExampleMod.MODID);
    public static final Supplier<Item> RUBY = ITEMS.register("ruby",() -> new Item(new Item.Properties()));
    public static final Supplier<Item> MAGIC_INGOT = ITEMS.register("magic_ingot", MagicIngot::new);
    public static final Supplier<Item> RUBY_APPLE = ITEMS.register("ruby_apple", RubyApple::new);
    public static final Supplier<Item> RUBY_SWORD = ITEMS.register("ruby_sword", RubySword::new);
    public static final Supplier<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", RubyPickaxe::new);
    public static final Supplier<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final Supplier<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final Supplier<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final Supplier<Item> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.BOOTS, (new Item.Properties())));
    public static final Supplier<Item> RUBY_WAND = ITEMS.register("ruby_wand", RubyWand::new);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
