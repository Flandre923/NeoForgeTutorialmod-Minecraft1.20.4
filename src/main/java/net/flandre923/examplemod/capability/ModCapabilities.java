package net.flandre923.examplemod.capability;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.enchantment.ExampleModReference;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;

import javax.annotation.Nullable;


public class ModCapabilities {
    public static final BlockCapability<ISimpleCapability,Void> SIMPLE_CAPABILITY_HANDLER =
            BlockCapability.createVoid(
                    new ResourceLocation(ExampleMod.MODID,"simple_capability_handler"),
                    ISimpleCapability.class
            );

}
