package net.flandre923.examplemod.client.gui;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.PacketSendListener;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, ExampleMod.MODID);

    public static final Supplier<MenuType<FirstMenu>> FIRST_MENU =registerMenuType(FirstMenu::new,"first_menu");
    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                            String name) {
        return MENU_TYPES.register(name, () -> IMenuTypeExtension.create(factory));
    }
    public static void register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }
}
