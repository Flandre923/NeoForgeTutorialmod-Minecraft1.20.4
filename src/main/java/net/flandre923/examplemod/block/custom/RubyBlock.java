package net.flandre923.examplemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class RubyBlock extends Block {
    public RubyBlock() {
        super(Properties.ofFullCopy(Blocks.STONE).strength(5f));
    }

}
