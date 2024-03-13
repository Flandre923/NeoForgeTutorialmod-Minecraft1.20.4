package net.flandre923.examplemod.client.util;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerThirstData {
    private static int playerThirst;

    // 设置饥渴值
    public static void set(int thirst) {
        ClientPlayerThirstData.playerThirst = thirst;
    }
    // 获得饥渴值的数值
    public static int getPlayerThirst() {
        return playerThirst;
    }
}
