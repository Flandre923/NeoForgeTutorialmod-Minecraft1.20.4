package net.flandre923.examplemod.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_EXAMPLE_MOD = "key.category.example_mod";
    public static final String KEY_DRINK_WATER = "key.example_mod.drink_water";

    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_CATEGORY_EXAMPLE_MOD, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O,KEY_DRINK_WATER);

}
