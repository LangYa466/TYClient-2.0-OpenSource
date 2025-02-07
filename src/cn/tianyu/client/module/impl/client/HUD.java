/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.client;

import cn.tianyu.client.TYClient;
import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class HUD
extends Mod {
    private float hue = 0.005f;
    private static final int FONT_SCALE = 2;
    private float I = 0.005f;

    public HUD() {
        super("HUD", "\u4eff\u5916\u6302\u754c\u9762", Category.client);
    }

    @Override
    public void draw() {
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontRenderer = minecraft.fontRendererObj;
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        int width = scaledResolution.getScaledWidth();
        this.hue += this.I;
        if (this.hue > 1.0f) {
            this.hue -= 1.0f;
        }
        int color = Color.getColorFromHue(this.hue);
        List<Mod> enabledMods = TYClient.modManager.getEnableMods();
        enabledMods.sort((o1, o2) -> fontRenderer.getStringWidth(o2.getName()) - fontRenderer.getStringWidth(o1.getName()));
        int yPosition = 0;
        GL11.glPushMatrix();
        GL11.glScalef(2.0f, 2.0f, 1.0f);
        for (Mod mod : enabledMods) {
            String modName = mod.getName();
            int stringWidth = fontRenderer.getStringWidth(modName);
            fontRenderer.drawString(modName, (width - stringWidth * 2) / 2, yPosition / 2, color);
            yPosition += fontRenderer.FONT_HEIGHT * 2 + 5;
        }
        GL11.glPopMatrix();
    }

    private static class Color {
        private Color() {
        }

        public static int getColorFromHue(float hue) {
            int rgb = java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f);
            return rgb & 0xFFFFFF | 0xFF000000;
        }
    }
}

