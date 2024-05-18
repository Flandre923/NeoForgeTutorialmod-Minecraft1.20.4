package net.flandre923.examplemod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.joml.Vector4i;

import java.util.Vector;

public class ExampleParticle extends TextureSheetParticle {
    public ExampleParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
        this.lifetime = 100;
        this.hasPhysics = true;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}
