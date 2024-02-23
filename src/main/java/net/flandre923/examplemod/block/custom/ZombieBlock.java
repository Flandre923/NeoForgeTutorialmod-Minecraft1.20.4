package net.flandre923.examplemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.block.blockentity.custom.HelloBlockEntity;
import net.flandre923.examplemod.block.blockentity.custom.ZombieBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

public class ZombieBlock extends BaseEntityBlock {
    public ZombieBlock() {
        super(Properties.ofFullCopy(Blocks.STONE));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ZombieBlockEntity(pPos,pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, ModBlockEntities.ZOMBIE_BLOCK_ENTITY.get(),ZombieBlockEntity::clientTick) : createTickerHelper(pBlockEntityType, ModBlockEntities.ZOMBIE_BLOCK_ENTITY.get(), ZombieBlockEntity::serverTick);

    }


}
