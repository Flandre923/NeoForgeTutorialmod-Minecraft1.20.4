package net.flandre923.examplemod.painting;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(Registries.PAINTING_VARIANT, ExampleMod.MODID);

    public static final Supplier<PaintingVariant> XI_GUA = PAINTING_VARIANTS.register("xi_gua",()->new PaintingVariant(16,32));

    public static final Supplier<PaintingVariant> JIU = PAINTING_VARIANTS.register("jiu",()->new PaintingVariant(16,16));


    public static void register(IEventBus eventBus){
        PAINTING_VARIANTS.register(eventBus);
    }
}
