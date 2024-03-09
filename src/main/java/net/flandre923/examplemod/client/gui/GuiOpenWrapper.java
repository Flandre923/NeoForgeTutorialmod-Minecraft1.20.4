package net.flandre923.examplemod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class GuiOpenWrapper {
    public static void openFirstGui(){
        Minecraft.getInstance().setScreen(new FirstGui(Component.translatable("test")));
    }
}
