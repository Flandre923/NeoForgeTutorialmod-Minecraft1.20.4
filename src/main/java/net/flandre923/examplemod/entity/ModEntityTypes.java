package net.flandre923.examplemod.entity;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, ExampleMod.MODID);
    public static final Supplier<EntityType<FlyingSwordEntity>> FLYING_SWORD_ENTITY = ENTITY_TYPES.register("flying_sword_entity", () -> EntityType.Builder.of(FlyingSwordEntity::new, MobCategory.MISC).sized(2, 0.5F).build("flying_sword_entity"));
    public static final Supplier<EntityType<FirstAnimal>> FIRST_ANIMAL = ENTITY_TYPES.register("first_animal", () -> EntityType.Builder.of(FirstAnimal::new, MobCategory.MISC).sized(2, 0.5F).build("first_animal"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
