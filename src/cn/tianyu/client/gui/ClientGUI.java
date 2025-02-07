/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.gui;

import cn.tianyu.client.font.FontManager;
import cn.tianyu.client.gui.MainMenuGui.MainMenuGui;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClientGUI
extends GuiScreen {
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 21;
    private static final int BUTTON_SPACING = 10;
    private static final ResourceLocation BACKGROUND = new ResourceLocation("client/bg/114514.png");

    @Override
    public void initGui() {
        int startY = this.height - 21 - 30;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 60, startY, 120, 21, "Back"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glPushMatrix();
        this.mc.getTextureManager().bindTexture(BACKGROUND);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0f, 0.0f, this.width, this.height, this.width, this.height);
        GL11.glPopMatrix();
        super.drawScreen1(mouseX, mouseY, partialTicks);
        FontManager.font40.drawString2("Dev group", 0.0, 0.0, new Color(255, 255, 255).getRGB());
        this.a1("[\u4e3b\u8981\u5f00\u53d1\u8005]CN_tianyu", 100, 30, new Color(255, 255, 255).getRGB(), 20);
        this.a1("[\u63d0\u4f9b\u5b57\u4f53\u6e32\u67d3\u4ee3\u7801]Yvonneyu ", 100, 50, new Color(255, 255, 255).getRGB(), 20);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new MainMenuGui());
        }
    }

    private void a1(String text, int x, int y, int color, int fontSize) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0.0f);
        GL11.glScalef((float)fontSize / 10.0f, (float)fontSize / 10.0f, 1.0f);
        int textWidth = this.mc.fontRendererObj.getStringWidth(text);
        this.mc.fontRendererObj.drawString(text, -textWidth / 2, 0, color);
        GL11.glPopMatrix();
    }
}

