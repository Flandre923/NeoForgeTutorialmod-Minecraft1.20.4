package net.flandre923.examplemod.network.packet;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ThirstData(int thirst) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation(ExampleMod.MODID,"thirst_data");


    public ThirstData(final FriendlyByteBuf buf){
        this(buf.readInt());
    }

    @Override
    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(thirst());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
