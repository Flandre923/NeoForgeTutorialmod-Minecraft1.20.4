package net.flandre923.examplemod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.capability.ModCapabilities;
import net.flandre923.examplemod.capability.impl.SimpleCapability;
import net.flandre923.examplemod.entity.FirstAnimal;
import net.flandre923.examplemod.entity.ModEntityTypes;
import net.flandre923.examplemod.item.ModItems;
import net.flandre923.examplemod.villager.ModVillagers;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    @Mod.EventBusSubscriber(modid = ExampleMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBus{
        @SubscribeEvent
        public static void setupAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.FIRST_ANIMAL.get(), FirstAnimal.createAttributes().build());
        }

        @SubscribeEvent
        private static void registerCapabilities(RegisterCapabilitiesEvent event) {
            event.registerBlockEntity(
                    ModCapabilities.SIMPLE_CAPABILITY_HANDLER,
                    ModBlockEntities.DOWN_BLOCK_BLOCK_ENTITY.get(),
                    (myBlockEntity, side) -> new SimpleCapability("hello")
            );

            //item handler
            event.registerBlockEntity(
                    Capabilities.ItemHandler.BLOCK,
                    ModBlockEntities.TRASH_BLOCK_ENTITY.get(),
                    (myBlockEntity,side)->{
                        if(side == Direction.UP){
                            return new IItemHandler() {
                                @Override
                                public int getSlots() {
                                    return 1;
                                }

                                @Override
                                public @NotNull ItemStack getStackInSlot(int slot) {
                                    return ItemStack.EMPTY;
                                }

                                @Override
                                public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                                    if (!this.isItemValid(slot,stack))return stack;
                                    if (simulate){
                                        return ItemStack.EMPTY;
                                    }else{
                                        stack = stack.copy();
                                        stack.shrink(1);
                                        return stack;
                                    }
                                }

                                @Override
                                public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                                    return ItemStack.EMPTY;
                                }

                                @Override
                                public int getSlotLimit(int slot) {
                                    return slot;
                                }

                                @Override
                                public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                                    return stack.getItem() == Items.COBBLESTONE;
                                }
                            };
                        }else{
                            return null;
                        }
                    }
            );
        }
    }



}
