package net.flandre923.examplemod.client.render;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            BlockEntityRenderers.register(ModBlockEntities.RUBY_FRAME_BLOCK_ENTITY.get(),RubyFrameBlockEntityRender::new);
        });
    }

}
