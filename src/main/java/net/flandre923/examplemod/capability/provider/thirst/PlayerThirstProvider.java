package net.flandre923.examplemod.capability.provider.thirst;

import net.flandre923.examplemod.capability.impl.thirst.PlayerThirst;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

public class PlayerThirstProvider implements ICapabilityProvider<Player,Void, PlayerThirst>, INBTSerializable<CompoundTag> {
    private PlayerThirst thirst = null;

    private PlayerThirst createPlayerThirst() {
        if(this.thirst == null) {
            this.thirst = new PlayerThirst();
        }
        return this.thirst;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerThirst().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerThirst().loadNBTData(nbt);
    }

    @Override
    public @Nullable PlayerThirst getCapability(Player player, Void context) {
        return this.createPlayerThirst();
    }
}
