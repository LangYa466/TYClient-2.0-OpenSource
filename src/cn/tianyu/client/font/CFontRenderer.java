/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.font;

import cn.tianyu.client.font.CFont;
import cn.tianyu.client.font.FontData;
import java.awt.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class CFontRenderer
extends CFont {
    private final int[] colorCode = new int[32];

    public CFontRenderer(Font font, int cs) {
        super(font, cs);
        for (int index = 0; index < 32; ++index) {
            int noClue = (index >> 3 & 1) * 85;
            int red = (index >> 2 & 1) * 170 + noClue;
            int green = (index >> 1 & 1) * 170 + noClue;
            int blue = (index & 1) * 170 + noClue;
            if (index == 6) {
                red += 85;
            }
            if (index >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }
            this.colorCode[index] = (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
        }
    }

    public CFontRenderer(Font font) {
        super(font, 256);
        for (int index = 0; index < 32; ++index) {
            int noClue = (index >> 3 & 1) * 85;
            int red = (index >> 2 & 1) * 170 + noClue;
            int green = (index >> 1 & 1) * 170 + noClue;
            int blue = (index & 1) * 170 + noClue;
            if (index == 6) {
                red += 85;
            }
            if (index >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }
            this.colorCode[index] = (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
        }
    }

    public float drawString(String text, double x, double y, int color) {
        return this.drawString(text, x, y, color, false);
    }

    public float drawString2(String text, double x, double y, int color) {
        return this.drawString1(text, x, y, color, false);
    }

    public float drawStringWithShadow(String text, double x, double y, int color) {
        float shadowWidth = this.drawString(text, x + (double)0.7f, y + (double)0.7f, color, true);
        return Math.max(shadowWidth, this.drawString(text, x, y, color, false));
    }

    public float drawCenteredString(String text, double x, double y, int color) {
        return this.drawString(text, x - (double)((float)this.getWidth(text) / 2.0f), y, color);
    }

    public float drawCenteredStringWithShadow(String text, double x, double y, int color) {
        return this.drawStringWithShadow(text, x - (double)((float)this.getWidth(text) / 2.0f), y, color);
    }

    public void drawOutlinedString(String str, float x, float y, int internalCol, int externalCol) {
        this.drawString(str, x - 0.5f, y, externalCol);
        this.drawString(str, x + 0.5f, y, externalCol);
        this.drawString(str, x, y - 0.5f, externalCol);
        this.drawString(str, x, y + 0.5f, externalCol);
        this.drawString(str, x, y, internalCol);
    }

    private float drawString(String text, double x, double y, int color, boolean shadow) {
        char character;
        int i;
        y -= 1.0;
        GlStateManager.resetColor();
        if (text == null) {
            return 0.0f;
        }
        text = this.processString(text);
        int textLength = text.length();
        x *= 2.0;
        y = (y - 1.0) * 2.0;
        if ((color & 0xFC000000) == 0) {
            color |= 0xFF000000;
        }
        if (shadow) {
            color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
        }
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(red, green, blue, alpha);
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.texture.getGlTextureId());
        float totalWidth = 0.0f;
        for (i = 0; i < textLength; ++i) {
            character = text.charAt(i);
            if (character >= this.charMap.size()) continue;
            totalWidth += (float)(((FontData)this.charMap.get((Object)Character.valueOf((char)character))).width - 8);
        }
        x -= (double)(totalWidth / 2.0f);
        for (i = 0; i < textLength; ++i) {
            character = text.charAt(i);
            if (character == '\u00a7') {
                int colorIndex = 21;
                try {
                    colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (colorIndex < 16) {
                    GlStateManager.bindTexture(this.texture.getGlTextureId());
                    if (colorIndex < 0) {
                        colorIndex = 15;
                    }
                    if (shadow) {
                        colorIndex += 16;
                    }
                    int realColor = this.colorCode[colorIndex];
                    GlStateManager.color((float)(realColor >> 16 & 0xFF) / 255.0f, (float)(realColor >> 8 & 0xFF) / 255.0f, (float)(realColor & 0xFF) / 255.0f, alpha);
                } else if (colorIndex == 21) {
                    GlStateManager.color(red, green, blue, alpha);
                    GlStateManager.bindTexture(this.texture.getGlTextureId());
                }
                ++i;
                continue;
            }
            if (character >= this.charMap.size()) continue;
            this.drawChar((FontData)this.charMap.get(Character.valueOf(character)), (float)x, (float)y);
            x += (double)(((FontData)this.charMap.get((Object)Character.valueOf((char)character))).width - 8);
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        return (float)(x / 2.0);
    }

    private float drawString1(String text, double x, double y, int color, boolean shadow) {
        y -= 1.0;
        GlStateManager.resetColor();
        if (text == null) {
            return 0.0f;
        }
        text = this.processString(text);
        int textLength = text.length();
        x *= 2.0;
        y = (y - 1.0) * 2.0;
        if ((color & 0xFC000000) == 0) {
            color |= 0xFF000000;
        }
        if (shadow) {
            color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
        }
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(red, green, blue, alpha);
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.texture.getGlTextureId());
        GL11.glBindTexture(3553, this.texture.getGlTextureId());
        for (int i = 0; i < textLength; ++i) {
            char character = text.charAt(i);
            if (character == '\u00a7') {
                int colorIndex = 21;
                try {
                    colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (colorIndex < 16) {
                    GlStateManager.bindTexture(this.texture.getGlTextureId());
                    if (colorIndex < 0) {
                        colorIndex = 15;
                    }
                    if (shadow) {
                        colorIndex += 16;
                    }
                    int realColor = this.colorCode[colorIndex];
                    GlStateManager.color((float)(realColor >> 16 & 0xFF) / 255.0f, (float)(realColor >> 8 & 0xFF) / 255.0f, (float)(realColor & 0xFF) / 255.0f, alpha);
                } else if (colorIndex == 21) {
                    GlStateManager.color(red, green, blue, alpha);
                    GlStateManager.bindTexture(this.texture.getGlTextureId());
                }
                ++i;
                continue;
            }
            if (character >= this.charMap.size()) continue;
            this.drawChar((FontData)this.charMap.get(Character.valueOf(character)), (float)x, (float)y);
            x += (double)(((FontData)this.charMap.get((Object)Character.valueOf((char)character))).width - 8);
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        return (float)(x / 2.0);
    }

    @Override
    public int getWidth(String text) {
        if (text == null) {
            return 0;
        }
        text = this.processString(text);
        int width = 0;
        for (int i = 0; i < text.length(); ++i) {
            char character = text.charAt(i);
            if (character == '\u00a7') {
                ++i;
                continue;
            }
            if (character >= this.charMap.size()) continue;
            width += ((FontData)this.charMap.get((Object)Character.valueOf((char)character))).width - 8;
        }
        return width / 2;
    }

    private String processString(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= '\uc350' && c <= '\uea60' || c == '\u26bd') continue;
            sb.append(c);
        }
        return sb.toString();
    }

    public void drawCenteredTextScaled(String text, int givenX, int givenY, int color, double givenScale) {
        GL11.glPushMatrix();
        GL11.glTranslated(givenX, givenY, 0.0);
        GL11.glScaled(givenScale, givenScale, givenScale);
        this.drawCenteredString(text, 0.0, 0.0, color);
        GL11.glPopMatrix();
    }

    public static class MCFont
    extends CFontRenderer {
        public MCFont() {
            super(null);
        }

        @Override
        public float drawString(String text, double x, double y, int color) {
            return Minecraft.getMinecraft().fontRendererObj.drawString(text, (int)x + 2, (int)y, color);
        }

        @Override
        public int getWidth(String text) {
            return Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
        }

        @Override
        public float drawStringWithShadow(String text, double x, double y, int color) {
            return Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, (float)x + 2.0f, (float)y, color);
        }

        @Override
        public int getHeight() {
            return Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
        }
    }
}

