package net.flandre923.examplemod.client.hud;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.IGuiGraphicsExtension;

public class ExampleHud implements LayeredDraw.Layer {
    private static final ExampleHud hud = new ExampleHud();
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(ExampleMod.MODID, "textures/gui/hud.png");

    public ExampleHud() {
        this.width = Minecraft.getInstance().getWindow().getScreenWidth();
        this.height = Minecraft.getInstance().getWindow().getScreenHeight();
        this.minecraft = Minecraft.getInstance();
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, float pPartialTick) {
        if (minecraft.player.getMainHandItem().getItem()!= ModItems.RUBY_APPLE.get())
            return;
        pGuiGraphics.setColor(1,1,1,1);
        pGuiGraphics.blit(HUD,width/2-16,height/2-64,0,0,32,32,32,32);
    }

    public static ExampleHud getInstance() {
        return hud;
    }
}
