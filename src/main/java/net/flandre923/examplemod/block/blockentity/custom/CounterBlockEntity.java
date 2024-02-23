package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CounterBlockEntity extends BlockEntity {
    private int counter = 0;

    public CounterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RUBY_COUNTER_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public int increase(){
        counter ++;
        setChanged();
        return counter;
    }

    @Override
    public void load(CompoundTag pTag) {
        counter = pTag.getInt("counter");
        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("counter",counter);
    }
}
