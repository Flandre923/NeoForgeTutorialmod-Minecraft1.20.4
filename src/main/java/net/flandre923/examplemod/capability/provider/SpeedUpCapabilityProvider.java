package net.flandre923.examplemod.capability.provider;

import net.flandre923.examplemod.capability.ISpeedUpCapability;
import net.flandre923.examplemod.capability.impl.SpeedUpCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Random;

public class SpeedUpCapabilityProvider implements ICapabilityProvider<Player,Void,ISpeedUpCapability>, INBTSerializable<CompoundTag> {
    private ISpeedUpCapability speedUpCapability;

    @Nullable
    @Override
    public ISpeedUpCapability getCapability(Player Entity, Void context) {
        return this.getOrCreateCapability();
    }

    @Nonnull
    ISpeedUpCapability getOrCreateCapability() {
        if (speedUpCapability == null) {
            Random random = new Random();
            this.speedUpCapability = new SpeedUpCapability(random.nextInt(99) + 1);
        }
        return this.speedUpCapability;
    }


    @Override
    public CompoundTag serializeNBT() {
        return this.getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.getOrCreateCapability().deserializeNBT(nbt);
    }
}
