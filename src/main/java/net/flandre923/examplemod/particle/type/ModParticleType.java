package net.flandre923.examplemod.particle.type;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.joml.Vector3d;
import org.joml.Vector4i;

import java.util.function.Supplier;

public class ModParticleType {
    public static final DeferredRegister <ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, ExampleMod.MODID);
    public static final Supplier<SimpleParticleType> EXAMPLE_PARTICLE_TYPE = register("example_particle_type", () -> new SimpleParticleType(true));

    public static <T extends ParticleType<?>> DeferredHolder<ParticleType<?>, T> register(String name, Supplier<T> particleType){
        return PARTICLE_TYPES.register(name, particleType);

    }
    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }


}
