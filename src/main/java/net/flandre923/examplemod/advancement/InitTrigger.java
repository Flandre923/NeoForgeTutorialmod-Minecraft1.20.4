package net.flandre923.examplemod.advancement;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class InitTrigger {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, ExampleMod.MODID);
    public static final Supplier<GiveAppleTrigger> GIVE_RUBY_APPLE = register("give_ruby_apple", GiveAppleTrigger::new);

    private static <T extends CriterionTrigger<?>> DeferredHolder<CriterionTrigger<?>, T> register(String id, Supplier<T> trigger) {
        return TRIGGERS.register(id, trigger);
    }
    public static void register(IEventBus eventBus) {
        TRIGGERS.register(eventBus);
    }
}
