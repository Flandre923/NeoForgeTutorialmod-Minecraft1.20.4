package net.flandre923.examplemod.network;

import com.mojang.logging.LogUtils;
import net.flandre923.examplemod.capability.ModCapabilities;
import net.flandre923.examplemod.network.packet.MyData;
import net.flandre923.examplemod.network.packet.ThirstData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.slf4j.Logger;

import java.util.Optional;

public class ServerPayloadHandler {
    private static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ServerPayloadHandler getInstance() {
        return INSTANCE;
    }

    public static void handleData(final MyData data, final IPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    LOGGER.info(data.message());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleThirstData(final ThirstData data, final IPayloadContext context){
        context.enqueueWork(()->{
            Player player = context.player();

            if (player instanceof ServerPlayer serverPlayer){
                ServerLevel level = (ServerLevel) player.level();
                if(hasWaterAroundThem(player,level,2)){

                    level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                            0.5F, level.random.nextFloat() * 0.1F + 0.9F);

                    Optional.ofNullable(player.getCapability(ModCapabilities.PLAYER_THIRST_HANDLER)).ifPresent(thirst->{
                        thirst.addThirst(1);

                        player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst())
                                .withStyle(ChatFormatting.AQUA));

                        PacketDistributor.sendToPlayer(serverPlayer,new ThirstData(thirst.getThirst()));
                    });

                }else{
                    Optional.ofNullable(player.getCapability(ModCapabilities.PLAYER_THIRST_HANDLER)).ifPresent(thirst -> {
                        player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst())
                                .withStyle(ChatFormatting.AQUA));
                        // 添加这个代码，当玩家按下o建的时候同步数据
//                            ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()),player);
                        PacketDistributor.sendToPlayer(serverPlayer,new ThirstData(thirst.getThirst()));
                    });
                }
            }
        }).exceptionally(e->{
            context.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
            return null;
        });
    }

    private static boolean hasWaterAroundThem(Player player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }
}
