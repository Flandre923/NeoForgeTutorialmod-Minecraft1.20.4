package net.flandre923.examplemod.event.client;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.client.model.entity.FlyingSwordModel;
import net.flandre923.examplemod.client.render.RubyFrameBlockEntityRender;
import net.flandre923.examplemod.client.render.entity.FlyingSwordEntityRenderer;
import net.flandre923.examplemod.entity.ModEntityTypes;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            BlockEntityRenderers.register(ModBlockEntities.RUBY_FRAME_BLOCK_ENTITY.get(), RubyFrameBlockEntityRender::new);

            EntityRenderers.register(ModEntityTypes.FLYING_SWORD_ENTITY.get(), FlyingSwordEntityRenderer::new);
        });

    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(FlyingSwordModel.LAYER_LOCATION, FlyingSwordModel::createBodyLayer);
    }

}
