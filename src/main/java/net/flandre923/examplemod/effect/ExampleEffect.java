package net.flandre923.examplemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ExampleEffect extends MobEffect {
    protected ExampleEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pEntity, int pAmplifier) {
        super.applyEffectTick(pEntity, pAmplifier);
        if (pEntity.getHealth() < pEntity.getMaxHealth()) {
            pEntity.heal(2.0F);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tick, int amp) {
        if (tick % 5 == 0) {
            return true;
        }else{
            return false;
        }
    }


}
