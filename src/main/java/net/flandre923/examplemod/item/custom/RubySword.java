package net.flandre923.examplemod.item.custom;

import net.flandre923.examplemod.item.custom.tool.ModItemTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class RubySword extends SwordItem {

    public RubySword(){
        super(ModItemTiers.RUBY,new Item.Properties());
    }
}
