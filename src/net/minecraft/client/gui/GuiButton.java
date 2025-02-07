/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import cn.tianyu.client.font.FontManager;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButton
extends Gui {
    protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
    protected int width;
    protected int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled = true;
    public boolean visible = true;
    protected boolean hovered;

    public GuiButton(int buttonId, int x, int y, String buttonText) {
        this(buttonId, x, y, 200, 20, buttonText);
    }

    public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }

    protected int getHoverState(boolean mouseOver) {
        int i = 1;
        if (!this.enabled) {
            i = 0;
        } else if (mouseOver) {
            i = 2;
        }
        return i;
    }

    public static void drawAdvancedRoundedRect(int x, int y, int width, int height, int radius, int color) {
        GuiButton.drawCircle(x + radius, y + radius, radius, color);
        GuiButton.drawCircle(x + width - radius, y + radius, radius, color);
        GuiButton.drawCircle(x + radius, y + height - radius, radius, color);
        GuiButton.drawCircle(x + width - radius, y + height - radius, radius, color);
        GuiButton.drawRect(x + radius, y, x + width - radius, y + height, color);
        GuiButton.drawRect(x, y + radius, x + width, y + height - radius, color);
    }

    private static void drawCircle(int x, int y, int radius, int color) {
        for (int i = -radius; i <= radius; ++i) {
            for (int j = -radius; j <= radius; ++j) {
                if (i * i + j * j > radius * radius) continue;
                GuiButton.drawRect(x + i, y + j, x + i + 1, y + j + 1, color);
            }
        }
    }

    public void drawButton1(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            boolean bl = this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            if (this.hovered) {
                Gui.drawRect(this.xPosition + 1, this.yPosition, this.xPosition + this.width - 1, this.yPosition + this.height, new Color(5, 5, 5, 255).getRGB());
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glEnable(2848);
                GL11.glBlendFunc(770, 771);
                GL11.glDisable(3553);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glTranslatef(this.xPosition, this.yPosition, 0.0f);
                GL11.glLineWidth(3.0f);
                GL11.glBegin(2);
                GL11.glVertex2f(0.0f, 0.0f);
                GL11.glVertex2f(this.width, 0.0f);
                GL11.glVertex2f(this.width, this.height);
                GL11.glVertex2f(0.0f, this.height);
                GL11.glEnd();
                GL11.glEnable(3553);
                GL11.glDisable(2848);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            } else {
                Gui.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 0, 0, 68).getRGB());
            }
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 0xE0E0E0;
            if (!this.enabled) {
                j = 0xA0A0A0;
            } else if (this.hovered) {
                j = 0xFFFFA0;
            }
            FontManager.F22.drawString(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
        }
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 0xE0E0E0;
            if (!this.enabled) {
                j = 0xA0A0A0;
            } else if (this.hovered) {
                j = 0xFFFFA0;
            }
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
        }
    }

    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
    }

    public void mouseReleased(int mouseX, int mouseY) {
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    public boolean isMouseOver() {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
    }

    public void playPressSound(SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0f));
    }

    public int getButtonWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

