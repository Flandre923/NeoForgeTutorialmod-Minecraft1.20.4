package net.flandre923.examplemod.capability.impl;

import com.mojang.logging.LogUtils;
import net.flandre923.examplemod.capability.ISimpleCapability;
import net.minecraft.core.BlockPos;
import org.slf4j.Logger;

public class SimpleCapability implements ISimpleCapability {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final String str;
    public SimpleCapability(final String str){
        this.str = str;
    }

    @Override
    public void getString(BlockPos pos) {
        LOGGER.info(pos.toString() + ":::" + str);
    }
}
