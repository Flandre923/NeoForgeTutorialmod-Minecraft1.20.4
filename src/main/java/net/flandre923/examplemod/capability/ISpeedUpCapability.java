package net.flandre923.examplemod.capability;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public interface ISpeedUpCapability extends INBTSerializable<CompoundTag> {
    int getLevel();
}
