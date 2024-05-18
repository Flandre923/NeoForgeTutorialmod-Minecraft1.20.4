package net.flandre923.examplemod.event.client;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.client.key.KeyBinding;
import net.flandre923.examplemod.client.util.ClientPlayerThirstData;
import net.flandre923.examplemod.network.packet.ThirstData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = ExampleMod.MODID,value = Dist.CLIENT)
public class ForgeClientEventHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if(KeyBinding.DRINKING_KEY.consumeClick()){
            PacketDistributor.sendToServer(new ThirstData(ClientPlayerThirstData.getPlayerThirst()));
        }
    }

}
