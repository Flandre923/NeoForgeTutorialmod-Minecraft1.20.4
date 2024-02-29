package net.flandre923.examplemod.network;

import com.mojang.logging.LogUtils;
import net.flandre923.examplemod.ExampleMod;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.slf4j.Logger;

import java.util.logging.LogManager;

public class ServerPayloadHandler {
    private static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ServerPayloadHandler getInstance() {
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


}
