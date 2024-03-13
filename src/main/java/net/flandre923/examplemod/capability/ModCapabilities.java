package net.flandre923.examplemod.capability;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.capability.impl.thirst.PlayerThirst;
import net.flandre923.examplemod.enchantment.ExampleModReference;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.EntityCapability;

import javax.annotation.Nullable;


public class ModCapabilities {
    public static final BlockCapability<ISimpleCapability,Void> SIMPLE_CAPABILITY_HANDLER =
            BlockCapability.createVoid(
                    new ResourceLocation(ExampleMod.MODID,"simple_capability_handler"),
                    ISimpleCapability.class
            );

    public static final EntityCapability<ISpeedUpCapability,Void> SPEED_CAPABILITY_HANDLER =
            EntityCapability.createVoid(new ResourceLocation(ExampleMod.MODID,"speed_capability_handler"),
                    ISpeedUpCapability.class);

    public static final EntityCapability<PlayerThirst,Void> PLAYER_THIRST_HANDLER =
            EntityCapability.createVoid(new ResourceLocation(ExampleMod.MODID,"player_thirst_handler"),
                    PlayerThirst.class);

}
