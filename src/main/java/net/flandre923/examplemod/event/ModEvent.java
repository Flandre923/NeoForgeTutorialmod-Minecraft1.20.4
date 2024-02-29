package net.flandre923.examplemod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.client.model.HiddenBlockModel;
import net.flandre923.examplemod.client.model.WrenchBakeModel;
import net.flandre923.examplemod.item.ModItems;
import net.flandre923.examplemod.villager.ModVillagers;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;
import java.util.Map;

public class ModEvent {
    @Mod.EventBusSubscriber(modid = ExampleMod.MODID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event) {
            if(event.getType() == VillagerProfession.TOOLSMITH) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.MAGIC_INGOT.get(), 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 2),
                        stack,10,8,0.02F));
            }

            if(event.getType() == ModVillagers.RUBY_MASTER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.RUBY.get(), 15);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 5),
                        stack,10,8,0.02F));
            }
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
    public static class ModEventBus{
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
    }


}
