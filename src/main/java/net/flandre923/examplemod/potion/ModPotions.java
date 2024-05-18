package net.flandre923.examplemod.potion;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.effect.ModEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, ExampleMod.MODID);

    public static final DeferredHolder<Potion,Potion> EXAMPLE_POTION = register("example_potion", () -> new Potion("example_potion",new MobEffectInstance(ModEffects.EXAMPLE_EFFECT,1200,1)));

    public static <T extends Potion> DeferredHolder<Potion,T> register(String name, Supplier<T> supplier){
        return POTIONS.register(name, supplier);
    }

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
