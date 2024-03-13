package net.flandre923.examplemod.network;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.network.packet.MyData;
import net.flandre923.examplemod.network.packet.ThirstData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class Networking {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(ExampleMod.MODID);
        registrar.play(MyData.ID,MyData::new, handler ->
                handler.client(ClientPayloadHandler.getInstance()::handleData)
                        .server(ServerPayloadHandler.getInstance()::handleData));

        registrar.play(ThirstData.ID,ThirstData::new, handler ->
                handler.client(ClientPayloadHandler.getInstance()::handleThirstData)
                        .server(ServerPayloadHandler.getInstance()::handleThirstData));
    }

}
