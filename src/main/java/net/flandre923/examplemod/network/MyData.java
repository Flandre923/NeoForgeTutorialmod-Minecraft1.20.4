package net.flandre923.examplemod.network;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MyData(String message, int age) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation(ExampleMod.MODID,"my_data");

    public MyData(final FriendlyByteBuf buf){
        this(buf.readUtf(),buf.readInt());
    }

    @Override
    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeUtf(message());
        pBuffer.writeInt(age());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
