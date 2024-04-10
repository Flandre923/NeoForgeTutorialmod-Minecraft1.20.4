package net.flandre923.examplemod.block.custom;

import net.flandre923.examplemod.world.demension.ModDimensions;
import net.flandre923.examplemod.world.portal.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PortalBlock extends Block {

    public PortalBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noLootTable());
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.canChangeDimensions()){
            handlePortalTeleport(pLevel, pPos, pPlayer);
            return InteractionResult.SUCCESS;
        }else{
            return   InteractionResult.FAIL;
        }
    }

    private void handlePortalTeleport(Level pLevel, BlockPos pPos, Player pPlayer) {
        if(pPlayer.level() instanceof ServerLevel serverLevel){
            MinecraftServer minecraftServer = serverLevel.getServer();
            ResourceKey<Level> resourceKey = pPlayer.level().dimension() == ModDimensions.EXAMPLE_LEVEL_KEY ? Level.OVERWORLD :  ModDimensions.EXAMPLE_LEVEL_KEY;
            ServerLevel portalDimension = minecraftServer.getLevel(resourceKey);
            if (portalDimension != null && !pPlayer.isPassenger()) {
                if(resourceKey == ModDimensions.EXAMPLE_LEVEL_KEY){
                    pPlayer.changeDimension(portalDimension,new ModTeleporter(pPos,true));
                }else{
                    pPlayer.changeDimension(portalDimension,new ModTeleporter(pPos,false));
                }
            }
        }
    }


}
