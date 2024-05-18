package net.flandre923.examplemod.network.packet;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.units.qual.C;

public record ThirstData(int thirst) implements CustomPacketPayload {

    public static final StreamCodec<FriendlyByteBuf,ThirstData> STREAM_CODEC =
            CustomPacketPayload.codec(ThirstData::write,ThirstData::new);
    public static final CustomPacketPayload.Type<ThirstData> TYPE =
            new CustomPacketPayload.Type<>(new ResourceLocation(ExampleMod.MODID,"thirst_data"));

    public ThirstData(final FriendlyByteBuf buf){
        this(buf.readInt());
    }

    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(thirst());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
