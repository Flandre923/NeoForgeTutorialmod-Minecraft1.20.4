package net.flandre923.examplemod.item.custom;

import net.flandre923.examplemod.client.render.WrenchItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class WrenchItem extends Item {
    public WrenchItem() {
        super(new Properties());
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WrenchItemRenderer();
            }
        });
    }
}
