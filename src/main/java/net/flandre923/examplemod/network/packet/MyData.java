package net.flandre923.examplemod.network.packet;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MyData(String message, int age) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf,MyData> STREAM_CODEC =
            CustomPacketPayload.codec(MyData::write,MyData::new);
    public static final Type<MyData> TYPE = new Type<>(new ResourceLocation(ExampleMod.MODID,"my_data"));

    public MyData(final FriendlyByteBuf buf){
        this(buf.readUtf(),buf.readInt());
    }

    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeUtf(message());
        pBuffer.writeInt(age());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
