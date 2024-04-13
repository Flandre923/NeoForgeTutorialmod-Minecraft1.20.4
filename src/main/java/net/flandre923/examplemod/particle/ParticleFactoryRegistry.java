package net.flandre923.examplemod.particle;

import net.flandre923.examplemod.particle.provider.ExampleParticleProvider;
import net.flandre923.examplemod.particle.type.ModParticleType;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoryRegistry {
    @SubscribeEvent
    public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleType.EXAMPLE_PARTICLE_TYPE.get(), ExampleParticleProvider::new);
//        Minecraft.getInstance().particleEngine.register(ModParticleType.EXAMPLE_PARTICLE_TYPE.get(), ExampleParticleProvider::new);
    }
}
