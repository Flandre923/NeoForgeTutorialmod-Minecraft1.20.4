package net.flandre923.examplemod.particle.type;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.flandre923.examplemod.particle.ExampleParticle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.FriendlyByteBuf;
import org.joml.*;
import org.joml.Math;

import java.util.Locale;

public class ExampleParticleType extends ParticleType implements ParticleOptions {
    private final Vector3d speed;
    private final Vector4i color;
    private final float diameter;
    public static final ParticleOptions.Deserializer<ExampleParticleType> DESERIALIZER = new ParticleOptions.Deserializer<ExampleParticleType>() {

        @Override
        public ExampleParticleType fromCommand(ParticleType<ExampleParticleType> pParticleType, StringReader pReader) throws CommandSyntaxException {
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            pReader.expect(' ');
            double speedX = pReader.readDouble();
            pReader.expect(' ');
            double speedY = pReader.readDouble();
            pReader.expect(' ');
            double speedZ = pReader.readDouble();
            pReader.expect(' ');
            int red = Math.clamp(pReader.readInt(), MIN_COLOUR, MAX_COLOUR);
            pReader.expect(' ');
            int green = Math.clamp(pReader.readInt(), MIN_COLOUR, MAX_COLOUR);
            pReader.expect(' ');
            int blue = Math.clamp(pReader.readInt(), MIN_COLOUR, MAX_COLOUR);
            pReader.expect(' ');
            int alpha = Math.clamp(pReader.readInt(), 1, MAX_COLOUR);
            pReader.expect(' ');
            float diameter = pReader.readFloat();
            return new ExampleParticleType(new Vector3d(speedX, speedY, speedZ), new Vector4i(red, green, blue, alpha), diameter);
        }

        @Override
        public ExampleParticleType fromNetwork(ParticleType<ExampleParticleType> pParticleType, FriendlyByteBuf pBuffer) {
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            double speedX = pBuffer.readDouble();
            double speedY = pBuffer.readDouble();
            double speedZ = pBuffer.readDouble();
            int red = Math.clamp(pBuffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int green = Math.clamp(pBuffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int blue = Math.clamp(pBuffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int alpha = Math.clamp(pBuffer.readInt(), 1, MAX_COLOUR);
            float diameter = pBuffer.readFloat();
            return new ExampleParticleType(new Vector3d(speedX, speedY, speedZ), new Vector4i(red, green, blue, alpha), diameter);
        }
    };

    public ExampleParticleType(Vector3d speed, Vector4i color, float diameter) {
        super(false, ExampleParticleType.DESERIALIZER);
        this.speed = speed;
        this.color = color;
        this.diameter = diameter;
    }



    @Override
    public ExampleParticleType getType() {
        return ModParticleType.EXAMPLE_PARTICLE_TYPE.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeDouble(this.speed.x);
        pBuffer.writeDouble(this.speed.y);
        pBuffer.writeDouble(this.speed.z);
        pBuffer.writeInt(this.color.x());
        pBuffer.writeInt(this.color.y());
        pBuffer.writeInt(this.color.z());
        pBuffer.writeInt(this.color.w());
        pBuffer.writeFloat(this.diameter);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f %i %i %i %i %.2d %.2d %.2d",
                this.getType().toString(), diameter, color.x(), color.y(), color.z(), color.w(), speed.x(), speed.y(), speed.z());
    }

    @Override
    public Codec<ExampleParticleType> codec() {
        return Codec.unit(new ExampleParticleType(new Vector3d(0, 0, 0), new Vector4i(0,0,0,0), 0));
    }

    public Vector3d getSpeed() {
        return speed;
    }

    public Vector4i getColor() {
        return color;
    }

    public float getDiameter() {
        return diameter;
    }
}
