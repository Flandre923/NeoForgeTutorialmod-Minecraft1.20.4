package net.flandre923.examplemod.network;

import com.mojang.logging.LogUtils;
import net.flandre923.examplemod.client.util.ClientPlayerThirstData;
import net.flandre923.examplemod.network.packet.MyData;
import net.flandre923.examplemod.network.packet.ThirstData;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.slf4j.Logger;


public class ClientPayloadHandler {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

    public static ClientPayloadHandler getInstance() {
        return INSTANCE;
    }

    public static void handleData(final MyData data, final IPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                 LOGGER.info(data.message());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleThirstData(final ThirstData data, final IPayloadContext context){
        context.enqueueWork(()->{
            ClientPlayerThirstData.set(data.thirst());
        }).exceptionally(e->{
            context.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
            return null;
        });
    }

}
