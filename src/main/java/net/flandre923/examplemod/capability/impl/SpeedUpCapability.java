package net.flandre923.examplemod.capability.impl;

import net.flandre923.examplemod.capability.ISpeedUpCapability;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.UnknownNullability;

/**
 SpeedUpCapability类实现了ISpeedUpCapability接口，用于表示加速能力。
 该类包含一个整数类型的级别（level）属性，表示加速能力的级别。
 */
public class SpeedUpCapability implements ISpeedUpCapability {
    private int level;
    /**
     构造函数，用于创建SpeedUpCapability对象。
     @param level 加速能力的级别
     */
    public SpeedUpCapability(int level) {
        this.level = level;
    }

    /**
     获取加速能力的级别。
     @return 加速能力的级别
     */
    @Override
    public int getLevel() {
        return level;
    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putInt("level", this.level);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.level = nbt.getInt("level");
    }
}
