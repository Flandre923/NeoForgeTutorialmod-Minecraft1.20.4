package net.flandre923.examplemod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.flandre923.examplemod.ExampleMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FistMenuGui extends AbstractContainerScreen<FirstMenu> {
    private final ResourceLocation OBSIDIAN_CONTAINER_RESOURCE = new ResourceLocation(ExampleMod.MODID, "textures/gui/container.png");
    private final int textureWidth = 176;
    private final int textureHeight = 166;

    public FistMenuGui(FirstMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        super.renderLabels(pGuiGraphics, pMouseX, pMouseY);
        pGuiGraphics.drawString(this.font,Component.literal(this.menu.getContainerData().get(0)+""),82,20,0xeb0505);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        pGuiGraphics.blit(OBSIDIAN_CONTAINER_RESOURCE,x,y,0,0,this.textureWidth,this.textureHeight,this.textureWidth,this.textureHeight);

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics,pMouseX,pMouseY,pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics,pMouseX,pMouseY);
    }
}
