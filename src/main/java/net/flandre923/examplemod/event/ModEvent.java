package net.flandre923.examplemod.event;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.advancement.InitTrigger;
import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.capability.ModCapabilities;
import net.flandre923.examplemod.capability.impl.SimpleCapability;
import net.flandre923.examplemod.capability.impl.thirst.PlayerThirst;
import net.flandre923.examplemod.capability.provider.SpeedUpCapabilityProvider;
import net.flandre923.examplemod.capability.provider.thirst.PlayerThirstProvider;
import net.flandre923.examplemod.command.ExampleCommand;
import net.flandre923.examplemod.entity.FirstAnimal;
import net.flandre923.examplemod.entity.ModEntityTypes;
import net.flandre923.examplemod.item.ModItems;
import net.flandre923.examplemod.network.packet.ThirstData;
import net.flandre923.examplemod.villager.ModVillagers;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

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


        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                Optional<PlayerThirst> optionalPlayerThirst = Optional.ofNullable(event.player.getCapability(ModCapabilities.PLAYER_THIRST_HANDLER));
                optionalPlayerThirst .ifPresent(thirst -> {
                    if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                        thirst.subThirst(1);
                        // TODO 同步数据
//                        ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()),(ServerPlayer) event.player);
                        PacketDistributor.PLAYER.with((ServerPlayer) event.player).send(new ThirstData(thirst.getThirst()));
                        event.player.sendSystemMessage(Component.literal("Subtracted Thirst"));
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if(!event.getLevel().isClientSide()) {
                if(event.getEntity() instanceof ServerPlayer player) {
                    Optional<PlayerThirst> optionalPlayerThirst = Optional.ofNullable(player.getCapability(ModCapabilities.PLAYER_THIRST_HANDLER));
                    optionalPlayerThirst.ifPresent(thirst -> {
                        // TODO 同步数据
                        PacketDistributor.PLAYER.with((ServerPlayer) player).send(new ThirstData(thirst.getThirst()));
//                        ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                    });
                }

            }
        }

        @SubscribeEvent
        public static void onServerStaring(RegisterCommandsEvent event) {
            CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
            LiteralCommandNode<CommandSourceStack> cmd = dispatcher.register(
                    Commands.literal(ExampleMod.MODID).then(
                            Commands.literal("test")
                                    .requires((commandSourceStack -> commandSourceStack.hasPermission(2)))
                                    .executes(ExampleCommand.INSTANCE)
                    )
            );
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
                    (myBlockEntity, side) -> {
                        if (side == Direction.UP) {
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
                                    if (!this.isItemValid(slot, stack)) return stack;
                                    if (simulate) {
                                        return ItemStack.EMPTY;
                                    } else {
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
                        } else {
                            return null;
                        }
                    }
            );

            event.registerEntity(ModCapabilities.SPEED_CAPABILITY_HANDLER,
                    EntityType.PLAYER,
                    new SpeedUpCapabilityProvider());

            event.registerEntity(ModCapabilities.PLAYER_THIRST_HANDLER,
                    EntityType.PLAYER,
                    new PlayerThirstProvider());
        }
    }



}
