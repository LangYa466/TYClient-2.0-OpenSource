/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.useful;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import net.minecraft.client.Minecraft;

public class Light
extends Mod {
    private static final float NIGHT_VISION_GAMMA = 100.0f;
    private static final float DEFAULT_GAMMA = 1.0f;

    public Light() {
        super("Light", "\u6c38\u4e45\u591c\u89c6", Category.useful);
    }

    @Override
    public void enable() {
        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.gameSettings.gammaSetting = 100.0f;
    }

    @Override
    public void disable() {
        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.gameSettings.gammaSetting = 1.0f;
    }
}

