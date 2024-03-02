package net.flandre923.examplemod.client.render.entity;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.client.model.entity.FirstAnimalModel;
import net.flandre923.examplemod.entity.FirstAnimal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FirstAnimalRenderer extends MobRenderer<FirstAnimal, FirstAnimalModel> {
    public FirstAnimalRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FirstAnimalModel(pContext.bakeLayer(FirstAnimalModel.LAYER_LOCATION)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(FirstAnimal pEntity) {
        return new ResourceLocation(ExampleMod.MODID,"textures/entity/first_animal.png");
    }
}
