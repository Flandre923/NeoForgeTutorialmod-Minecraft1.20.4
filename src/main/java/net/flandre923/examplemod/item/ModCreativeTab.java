package net.flandre923.examplemod.item;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MODID);

    public static final String EXAMPLE_MOD_TAB_STRING = "creativetab.example_tab";

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB  = CREATIVE_MODE_TABS.register("example_tab",() -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable(EXAMPLE_MOD_TAB_STRING))
            .icon(()->ModItems.RUBY.get().getDefaultInstance())
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.RUBY.get());
                pOutput.accept(ModItems.MAGIC_INGOT.get());
                pOutput.accept(ModItems.RUBY_APPLE.get());
                pOutput.accept(ModItems.RUBY_SWORD.get());
                pOutput.accept(ModItems.RUBY_PICKAXE.get());
                pOutput.accept(ModItems.RUBY_CHESTPLATE.get());
                pOutput.accept(ModItems.RUBY_HELMET.get());
                pOutput.accept(ModItems.RUBY_LEGGINGS.get());
                pOutput.accept(ModItems.RUBY_BOOTS.get());
                pOutput.accept(ModItems.RUBY_WAND.get());
                pOutput.accept(ModBlocks.RUBY_BLOCK.get());
                pOutput.accept(ModBlocks.LAMP_BLOCK.get());
                pOutput.accept(ModBlocks.RUBY_FRAME.get());
                pOutput.accept(ModBlocks.GLASS_JAR.get());
                pOutput.accept(ModBlocks.RUBY_ORE.get());
                pOutput.accept(ModBlocks.OBSIDIAN_OBJ.get());
                pOutput.accept(ModBlocks.HELLO_BLOCK.get());
                pOutput.accept(ModBlocks.ZOMBIE_BLOCK.get());
                pOutput.accept(ModBlocks.HIDDEN_BLOCK.get());
            })
            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
