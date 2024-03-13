package net.flandre923.examplemod.network;

import com.mojang.logging.LogUtils;
import net.flandre923.examplemod.client.util.ClientPlayerThirstData;
import net.flandre923.examplemod.network.packet.MyData;
import net.flandre923.examplemod.network.packet.ThirstData;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.slf4j.Logger;


public class ClientPayloadHandler {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

    public static ClientPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final MyData data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                 LOGGER.info(data.message());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
                    return null;
                });
    }

    public void handleThirstData(final ThirstData data,final PlayPayloadContext context){
        context.workHandler().submitAsync(()->{
            ClientPlayerThirstData.set(data.thirst());
        }).exceptionally(e->{
            context.packetHandler().disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
            return null;
        });
    }

}
