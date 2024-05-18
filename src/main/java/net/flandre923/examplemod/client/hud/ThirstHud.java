package net.flandre923.examplemod.client.hud;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.client.util.ClientPlayerThirstData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;

public class ThirstHud {

    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(ExampleMod.MODID,
            "textures/gui/filled_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(ExampleMod.MODID,
            "textures/gui/empty_thirst.png");
    public static final LayeredDraw.Layer HUD_THIRST =  (guiGraphics, partialTick) -> {
        int screenWidth = Minecraft.getInstance().getWindow().getScreenWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getScreenHeight();
        int x = screenWidth / 2;
        int y = screenHeight;
        for(int i = 0; i < 10; i++) {
            guiGraphics.blit(EMPTY_THIRST,x - 94 + (i * 9), y - 54,90,0,0,12,12,
                    12,12);
        }

        for(int i = 0; i < 10; i++) {
            if(ClientPlayerThirstData.getPlayerThirst() > i) {
                guiGraphics.blit(FILLED_THIRST,x - 94 + (i * 9),y - 54,90,0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    };
}
