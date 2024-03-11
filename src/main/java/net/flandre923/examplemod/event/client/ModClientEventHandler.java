package net.flandre923.examplemod.event.client;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.client.gui.ModMenuTypes;
import net.flandre923.examplemod.client.gui.FistMenuGui;
import net.flandre923.examplemod.client.hud.ExampleHud;
import net.flandre923.examplemod.client.key.KeyBinding;
import net.flandre923.examplemod.client.model.HiddenBlockModel;
import net.flandre923.examplemod.client.model.WrenchBakeModel;
import net.flandre923.examplemod.client.model.entity.FirstAnimalModel;
import net.flandre923.examplemod.client.model.entity.FlyingSwordModel;
import net.flandre923.examplemod.client.render.RubyFrameBlockEntityRender;
import net.flandre923.examplemod.client.render.entity.FirstAnimalRenderer;
import net.flandre923.examplemod.client.render.entity.FlyingSwordEntityRenderer;
import net.flandre923.examplemod.entity.ModEntityTypes;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ModClientEventHandler {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            BlockEntityRenderers.register(ModBlockEntities.RUBY_FRAME_BLOCK_ENTITY.get(), RubyFrameBlockEntityRender::new);

            EntityRenderers.register(ModEntityTypes.FLYING_SWORD_ENTITY.get(), FlyingSwordEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.FIRST_ANIMAL.get(), FirstAnimalRenderer::new);

            MenuScreens.register(ModMenuTypes.FIRST_MENU.get(),FistMenuGui::new);

        });

    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(FlyingSwordModel.LAYER_LOCATION, FlyingSwordModel::createBodyLayer);
        evt.registerLayerDefinition(FirstAnimalModel.LAYER_LOCATION,FirstAnimalModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onModelBaked(ModelEvent.ModifyBakingResult event){
        for(BlockState blockstate: ModBlocks.HIDDEN_BLOCK.get().getStateDefinition().getPossibleStates()){
            ModelResourceLocation modelResourceLocation = BlockModelShaper.stateToModelLocation(blockstate);
            BakedModel existingModel = event.getModels().get(modelResourceLocation);
            if(existingModel==null){
                throw new RuntimeException("Did not find Obsidian Hidden in registry");
            }else if (existingModel instanceof HiddenBlockModel) {
                throw new RuntimeException("Tried to replaceObsidian Hidden twice");
            }else {
                HiddenBlockModel obsidianHiddenBlockModel = new HiddenBlockModel(existingModel);
                event.getModels().put(modelResourceLocation, obsidianHiddenBlockModel);
            }
        }

        // wrench item model
        Map<ResourceLocation, BakedModel> modelRegistry = event.getModels();
        ModelResourceLocation location = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(ModItems.WRENCH_ITEM.get()), "inventory");
        BakedModel existingModel = modelRegistry.get(location);
        if (existingModel == null) {
            throw new RuntimeException("Did not find Obsidian Hidden in registry");
        } else if (existingModel instanceof WrenchBakeModel) {
            throw new RuntimeException("Tried to replaceObsidian Hidden twice");
        } else {
            WrenchBakeModel obsidianWrenchBakedModel = new WrenchBakeModel(existingModel);
            event.getModels().put(location, obsidianWrenchBakedModel);
        }
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(new ResourceLocation(ExampleMod.MODID,"example_hud"), ExampleHud.getInstance());
    }


    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.DRINKING_KEY);
    }


}
