package net.flandre923.examplemod.entity;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;


public class FlyingSwordEntity extends Entity {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final EntityDataAccessor<Integer> COUNTER = SynchedEntityData.defineId(FlyingSwordEntity.class, EntityDataSerializers.INT);
    @Override
    public void tick() {
        if(this.level().isClientSide){
            Integer i = this.entityData.get(COUNTER);
            LOGGER.info(i.toString());
        }
        if(!this.level().isClientSide){
            LOGGER.info(this.entityData.get(COUNTER).toString());
            this.entityData.set(COUNTER,this.entityData.get(COUNTER)+1);
        }
        super.tick();
    }

    public FlyingSwordEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(COUNTER,0);
    }


    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.entityData.set(COUNTER,pCompound.getInt("counter"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("counter",this.entityData.get(COUNTER));
    }

}
