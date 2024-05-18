package net.flandre923.examplemod.particle.provider;

import net.flandre923.examplemod.particle.ExampleParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class ExampleParticleProvider implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet sprites;


    public ExampleParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }

    @Nullable
    @Override
    public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        ExampleParticle exampleParticle = new ExampleParticle(pLevel, pX, pY, pZ);
        exampleParticle.pickSprite(this.sprites);
        return exampleParticle;
    }
}
