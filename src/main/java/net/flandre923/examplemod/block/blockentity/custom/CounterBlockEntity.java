package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
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
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        counter = pTag.getInt("counter");
        super.loadAdditional(pTag, pRegistries);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.putInt("counter",counter);

    }

}
