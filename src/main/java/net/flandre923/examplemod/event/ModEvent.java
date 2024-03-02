package net.flandre923.examplemod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.client.model.HiddenBlockModel;
import net.flandre923.examplemod.client.model.WrenchBakeModel;
import net.flandre923.examplemod.entity.FirstAnimal;
import net.flandre923.examplemod.entity.ModEntityTypes;
import net.flandre923.examplemod.item.ModItems;
import net.flandre923.examplemod.villager.ModVillagers;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;
import java.util.Map;

public class ModEvent {
    @Mod.EventBusSubscriber(modid = ExampleMod.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
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

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBus{
        @SubscribeEvent
        public static void setupAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.FIRST_ANIMAL.get(), FirstAnimal.createAttributes().build());
        }

    }
}
