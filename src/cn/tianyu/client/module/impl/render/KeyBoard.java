/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.render;

import cn.tianyu.client.font.FontManager;
import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.util.DrawUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.GameSettings;

public class KeyBoard
extends Mod {
    public KeyBoard() {
        super("KeyBoard", "\u6309\u952e\u663e\u793a", Category.render);
    }

    @Override
    public void draw() {
        this.drawKey(0, 100);
    }

    public void drawKey(int x, int y) {
        int backgroundColor = new Color(0, 0, 0, 100).getRGB();
        int pressedColor = new Color(255, 255, 255, 190).getRGB();
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings gameSettings = minecraft.gameSettings;
        FontRenderer fontRenderer = minecraft.fontRendererObj;
        this.drawKeyButton(x + 25, y + 1, gameSettings.keyBindForward.isKeyDown(), "W", fontRenderer, pressedColor, backgroundColor);
        this.drawKeyButton(x + 25, y + 26, gameSettings.keyBindBack.isKeyDown(), "S", fontRenderer, pressedColor, backgroundColor);
        this.drawKeyButton(x, y + 26, gameSettings.keyBindLeft.isKeyDown(), "A", fontRenderer, pressedColor, backgroundColor);
        this.drawKeyButton(x + 50, y + 26, gameSettings.keyBindRight.isKeyDown(), "D", fontRenderer, pressedColor, backgroundColor);
        DrawUtil.drawRect(x, y + 51, 75, 20, gameSettings.keyBindJump.isKeyDown() ? pressedColor : backgroundColor);
        FontManager.F22.drawString("SPACE", x + 35, y + 56, -1);
    }

    private void drawKeyButton(int x, int y, boolean isPressed, String label, FontRenderer fontRenderer, int pressedColor, int backgroundColor) {
        DrawUtil.drawRect(x, y, 25, 25, isPressed ? pressedColor : backgroundColor);
        FontManager.F22.drawString(label, x + 10, y + 10, -1);
    }
}

