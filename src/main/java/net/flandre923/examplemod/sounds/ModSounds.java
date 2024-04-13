package net.flandre923.examplemod.sounds;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, ExampleMod.MODID);

    public static final Supplier<SoundEvent> EXAMPLE_SOUND = register("example_sound", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(ExampleMod.MODID, "example_sound"),16));

    public static DeferredHolder<SoundEvent,SoundEvent> register(String name, Supplier<SoundEvent> supplier){
        return SOUNDS.register(name, supplier);
    }

    public static void register(IEventBus modBus){
        SOUNDS.register(modBus);
    }
}
