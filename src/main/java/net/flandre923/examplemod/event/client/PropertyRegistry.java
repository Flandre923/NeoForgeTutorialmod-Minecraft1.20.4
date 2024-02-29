package net.flandre923.examplemod.event.client;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class PropertyRegistry {

    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ItemProperties.register(ModItems.MAGIC_INGOT.get(),new ResourceLocation(ExampleMod.MODID,"size"),(itemStack,level,livingEntity,int_num)->{
                return itemStack.getCount();
            });
        });
    }



}
