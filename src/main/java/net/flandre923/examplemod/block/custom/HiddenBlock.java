package net.flandre923.examplemod.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class HiddenBlock extends Block {
    public HiddenBlock(){
        super(Properties.ofFullCopy(Blocks.STONE).noOcclusion());
    }
}
