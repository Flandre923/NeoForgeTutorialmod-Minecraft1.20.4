package net.flandre923.examplemod.effect;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, ExampleMod.MODID);

    public static final Holder<MobEffect> EXAMPLE_EFFECT = register("example_effect", ()->new ExampleEffect(MobEffectCategory.BENEFICIAL, 16262179));

    public static <T extends MobEffect> DeferredHolder<MobEffect, T> register(String name, Supplier<T> effect){
        return MOD_EFFECTS.register(name, effect);
    }
    public static void register(IEventBus eventBus){
        MOD_EFFECTS.register(eventBus);
    }
}
