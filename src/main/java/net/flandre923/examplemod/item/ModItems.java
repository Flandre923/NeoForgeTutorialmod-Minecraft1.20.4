package net.flandre923.examplemod.item;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.client.gui.FirstGui;
import net.flandre923.examplemod.fluid.ModFluids;
import net.flandre923.examplemod.item.custom.*;
import net.flandre923.examplemod.item.custom.tool.ModArmorMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final List<Supplier<Item>> ITEMS_SUPPLIER = new ArrayList<>();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ExampleMod.MODID);
    public static final Supplier<Item> RUBY = register("ruby",() -> new Item(new Item.Properties()));
    public static final Supplier<Item> MAGIC_INGOT = register("magic_ingot", MagicIngot::new);
    public static final Supplier<Item> RUBY_APPLE = register("ruby_apple", RubyApple::new);
    public static final Supplier<Item> RUBY_SWORD = register("ruby_sword", RubySword::new);
    public static final Supplier<Item> RUBY_PICKAXE = register("ruby_pickaxe", RubyPickaxe::new);
    public static final Supplier<Item> RUBY_HELMET = register("ruby_helmet", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final Supplier<Item> RUBY_CHESTPLATE = register("ruby_chestplate", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final Supplier<Item> RUBY_LEGGINGS =register("ruby_leggings", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final Supplier<Item> RUBY_BOOTS =register("ruby_boots", () -> new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.BOOTS, (new Item.Properties())));
    public static final Supplier<Item> RUBY_WAND = register("ruby_wand", RubyWand::new);
    public static final Supplier<Item> WRENCH_ITEM = register("wrench_item",WrenchItem::new);
    public static final Supplier<Item> MESSAGE_ITEM = register("message_item", MessageItem::new);
    public static final Supplier<Item> ENCHANTMENT_EXAMPLE_ITEM = register("enchantment_example_item",EnchantmentExampleItem::new);
    public static final Supplier<Item> FIRST_GUI_ITEM = register("first_gui_item", OpenFirstGuiItem::new);
    public static final Supplier<Item> SPEED_UP_SHOW_ITEM = register("speed_up_show_item",SpeedUpShowItem::new);
    public static final Supplier<Item> MY_FLUID_BUCKET = register("my_fluid_bucket", ()->new BucketItem(ModFluids.MY_SOURCE_FLUID_BLOCK,new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final Supplier<Item> EXAMPLE_SOUND_TEST = register("example_sound_test", ExampleSoundTestItem::new);
    public static Supplier<Item> register(String name, Supplier<Item> supplier){
        Supplier<Item> supplierItem =  ITEMS.register(name,supplier);
        ITEMS_SUPPLIER.add(supplierItem);
        return supplierItem;
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
