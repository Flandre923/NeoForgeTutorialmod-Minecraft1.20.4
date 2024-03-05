package net.flandre923.examplemod.block;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.custom.*;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.flandre923.examplemod.item.ModItems.ITEMS;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, ExampleMod.MODID);

    public static final Supplier<Block> RUBY_BLOCK = registerBlock("ruby_block", RubyBlock::new);
    public static final Supplier<Block> LAMP_BLOCK = registerBlock("lamp_block",()->new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f).requiresCorrectToolForDrops()
            .lightLevel(state->state.getValue(LampBlock.LIT)?15:0)));

    public static final Supplier<Block> RUBY_FRAME = registerBlock("ruby_frame", RubyFrame::new);
    public static final Supplier<Block> GLASS_JAR = registerBlock("glass_jar", GlassJar::new);
    public static final Supplier<Block> RUBY_ORE = registerBlock("ruby_ore",()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));

    public static final Supplier<Block> OBSIDIAN_OBJ = registerBlock("obsidian_obj", ObsidianObj::new);
    public static final Supplier<Block> RUBY_COUNTER = registerBlock("ruby_counter",RubyCounter::new);
    public static final Supplier<Block> HELLO_BLOCK = registerBlock("hello_block",HelloBlock::new);
    public static final Supplier<Block> ZOMBIE_BLOCK = registerBlock("zombie_block",ZombieBlock::new);
    public static final Supplier<Block> HIDDEN_BLOCK = registerBlock("hidden_block",HiddenBlock::new);
    public static final Supplier<Block> DOWN_BLOCK = registerBlock("down_block",DownBlock::new);
    public static final Supplier<Block> UP_BLOCK = registerBlock("up_block",UpBlock::new);
    public static final Supplier<Block> TRASH_BLOCK = registerBlock("trash_block",TrashBlock::new);


    public static Supplier<Block> registerBlock(String name,Supplier<Block> block){
        Supplier<Block> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    public static void registerBlockItem(String name, Supplier<Block> block){
        registerBlockItem(name, block, new Item.Properties());
    }
    public static void registerBlockItem(String name, Supplier<Block> block, Item.Properties properties){
        ModItems.register(name, () -> new BlockItem(block.get(), properties));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
