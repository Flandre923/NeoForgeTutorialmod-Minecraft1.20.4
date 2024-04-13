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
    public ExampleParticle(ClientLevel pLevel, double pX, double pY, double pZ, Vector3d speed, Vector4i color, float diameter) {
        super(pLevel, pX, pY, pZ, speed.x,speed.y,speed.z);
        this.lifetime = 100;
        this.xd = speed.x;
        this.yd = speed.y;
        this.zd = speed.z;
        this.setColor(color.x/255F, color.y/255F, color.z/255F);
        this.setAlpha(color.w/255F);
        final float PARTICLE_SCALE_FOR_ONE_METRE = 0.5F;
        this.quadSize = PARTICLE_SCALE_FOR_ONE_METRE * diameter;
        this.hasPhysics = true;

    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}
