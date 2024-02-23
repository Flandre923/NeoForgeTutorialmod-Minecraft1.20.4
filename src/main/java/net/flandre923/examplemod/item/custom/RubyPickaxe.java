package net.flandre923.examplemod.item.custom;

import net.flandre923.examplemod.item.custom.tool.ModItemTiers;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class RubyPickaxe extends PickaxeItem {
    public RubyPickaxe() {
        super(ModItemTiers.RUBY,2,-3f,new Properties());
    }
}
