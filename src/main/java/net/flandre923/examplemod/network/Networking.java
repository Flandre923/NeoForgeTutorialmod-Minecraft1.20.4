package net.flandre923.examplemod.network;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.network.packet.MyData;
import net.flandre923.examplemod.network.packet.ThirstData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ExampleMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class Networking {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(ExampleMod.MODID);
        registrar.playBidirectional(
                MyData.TYPE,
                MyData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientPayloadHandler::handleData,
                        ServerPayloadHandler::handleData
                )
        );

        registrar.playBidirectional(
                ThirstData.TYPE,
                ThirstData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientPayloadHandler::handleThirstData,
                        ServerPayloadHandler::handleThirstData
                )
        );
    }

}
