package net.flandre923.examplemod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class RubyApple extends Item {
    private static final FoodProperties FOOD_PROPERTIES = new FoodProperties.Builder()
            .saturationModifier(10)
            .nutrition(20)
            .effect(()-> new MobEffectInstance(MobEffects.POISON,3*20,1),1)
            .build();
    public RubyApple() {
        super(new Properties().food(FOOD_PROPERTIES));
    }


}
