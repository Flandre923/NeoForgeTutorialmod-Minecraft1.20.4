package net.flandre923.examplemod.block.blockentity;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.block.blockentity.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ExampleMod.MODID);

    public static final Supplier<BlockEntityType<CounterBlockEntity>> RUBY_COUNTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("ruby_counter_block_entity", () ->
                    BlockEntityType.Builder.of(CounterBlockEntity::new,
                            ModBlocks.RUBY_COUNTER.get()).build(null));

    public static final Supplier<BlockEntityType<HelloBlockEntity>> HELLO_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("hello_block_entity", () ->
                    BlockEntityType.Builder.of(HelloBlockEntity::new,
                            ModBlocks.HELLO_BLOCK.get()).build(null));

    public static final Supplier<BlockEntityType<ZombieBlockEntity>> ZOMBIE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("zombie_block_entity", () ->
                    BlockEntityType.Builder.of(ZombieBlockEntity::new,
                            ModBlocks.ZOMBIE_BLOCK.get()).build(null));
    public static final Supplier<BlockEntityType<RubyFrameBlockEntity>> RUBY_FRAME_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("ruby_frame_block_entity", () ->
                    BlockEntityType.Builder.of(RubyFrameBlockEntity::new,
                            ModBlocks.RUBY_FRAME.get()).build(null));

    public static final Supplier<BlockEntityType<DownBlockBlockEntity>> DOWN_BLOCK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("down_block_entity", () ->
                    BlockEntityType.Builder.of(DownBlockBlockEntity::new,
                            ModBlocks.DOWN_BLOCK.get()).build(null));
    public static final Supplier<BlockEntityType<UpBlockBlockEntity>> UP_BLOCK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("up_block_entity", () ->
                    BlockEntityType.Builder.of(UpBlockBlockEntity::new,
                            ModBlocks.UP_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
