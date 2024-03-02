package net.flandre923.examplemod.entity.ai;

import net.flandre923.examplemod.entity.FirstAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MyGoal extends Goal {
    public final FirstAnimal firstAnimal;
    public MyGoal(FirstAnimal firstAnimal){
        this.firstAnimal = firstAnimal;
    }
    @Override
    public boolean canUse() {
        Level level = this.firstAnimal.level();
        if(!level.isClientSide){
            Player nearestPlayer = level.getNearestPlayer(this.firstAnimal, 10);
            if(nearestPlayer!=null){
                nearestPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 3 * 20, 3));
            }

        }
        return true;
    }
}
