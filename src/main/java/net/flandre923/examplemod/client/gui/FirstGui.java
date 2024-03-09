package net.flandre923.examplemod.client.gui;

import net.flandre923.examplemod.ExampleMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.widget.ExtendedSlider;

public class FirstGui extends Screen {
    EditBox editBox;
    Button button;
    ResourceLocation FIRST_GUI_TEXTURE = new ResourceLocation(ExampleMod.MODID, "textures/gui/first_gui.png");
    Component content = Component.translatable("gui." + ExampleMod.MODID + ".first_gui_title");
    ExtendedSlider sliderBar;

    public FirstGui(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {
        this.editBox = new EditBox(this.font, this.width / 2 - 100, 66, 200, 20, Component.translatable("gui." + ExampleMod.MODID + ".first_gui"));
        this.addWidget(this.editBox);
        this.button = new Button.Builder(Component.translatable("gui." + ExampleMod.MODID + ".first_gui.save"), pButton -> {
        }).pos(this.width / 2 - 40, 96).size(80, 20).build();
        this.addWidget(this.button);
        this.sliderBar = new ExtendedSlider(this.width / 2 - 100, 120, 200, 10, Component.translatable("gui." + ExampleMod.MODID + ".first_gui.slider"), Component.empty(), 0, 100, 0, true);
        this.addWidget(this.sliderBar);
        super.init();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.setColor(1, 1, 1, 1);
        int textureWidth = 208;
        int textureHeight = 156;
        pGuiGraphics.blit(FIRST_GUI_TEXTURE, this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
        pGuiGraphics.drawCenteredString(this.font, content, this.width / 2 - 10, 30, 0xeb0505);
        this.editBox.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.button.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.sliderBar.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
