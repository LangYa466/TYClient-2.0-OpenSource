/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.font;

import cn.tianyu.client.font.CFontRenderer;
import java.awt.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FontManager {
    public static CFontRenderer F22 = new CFontRenderer(FontManager.getFont(new ResourceLocation("client/font/1.ttf"), 22.0f));
    public static CFontRenderer F16 = new CFontRenderer(FontManager.getFont(new ResourceLocation("client/font/1.ttf"), 16.0f));
    public static CFontRenderer F48 = new CFontRenderer(FontManager.getFont(new ResourceLocation("client/font/2.ttf"), 48.0f));
    public static CFontRenderer font40 = new CFontRenderer(FontManager.getFont(new ResourceLocation("client/font/font.ttf"), 40.0f));

    private static Font getFont(ResourceLocation resourceLocation, float fontSize) {
        try {
            Font output = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream());
            output = output.deriveFont(fontSize);
            return output;
        }
        catch (Exception e) {
            return null;
        }
    }
}

