package net.flandre923.examplemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.flandre923.examplemod.block.blockentity.custom.CounterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.css.Counter;

public class RubyCounter extends BaseEntityBlock {
    public RubyCounter() {
        super(Properties.ofFullCopy(Blocks.STONE));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CounterBlockEntity(pPos,pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND){
            var rubyBlockEntity = (CounterBlockEntity) pLevel.getBlockEntity(pPos);
            int counter = rubyBlockEntity.increase();
            pPlayer.sendSystemMessage(Component.literal("counter:" + counter));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }
}
