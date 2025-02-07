/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.gui.MainMenuGui;

import cn.tianyu.client.font.FontManager;
import cn.tianyu.client.gui.ClientGUI;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MainMenuGui
extends GuiScreen {
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 21;
    private static final int BUTTON_SPACING = 10;
    private static final ResourceLocation[] BACKGROUNDS = new ResourceLocation[]{new ResourceLocation("client/bg/4.png"), new ResourceLocation("client/bg/bg.png")};
    private int currentBackgroundIndex = 0;
    private float alpha = 0.0f;
    private long startTime;
    private boolean verticalLayout = true;

    @Override
    public void initGui() {
        this.buttonList.clear();
        if (this.verticalLayout) {
            int startY = this.height / 4;
            this.buttonList.add(new GuiButton(1, this.width / 2 - 60, startY, 120, 21, "Singleplayer"));
            this.buttonList.add(new GuiButton(2, this.width / 2 - 60, startY + 21 + 10, 120, 21, "Multiplayer"));
            this.buttonList.add(new GuiButton(6, this.width / 2 - 60, startY + 62, 120, 21, "Client"));
            this.buttonList.add(new GuiButton(0, this.width / 2 - 60, startY + 93, 120, 21, "Settings"));
            this.buttonList.add(new GuiButton(4, this.width / 2 - 60, startY + 124, 120, 21, "Exit"));
        } else {
            int startX = this.width / 2 - 260;
            int startY = this.height / 2 - 10;
            this.buttonList.add(new GuiButton(1, startX, startY, 120, 21, "Singleplayer"));
            this.buttonList.add(new GuiButton(2, startX + 130, startY, 120, 21, "Multiplayer"));
            this.buttonList.add(new GuiButton(6, startX + 260, startY, 120, 21, "Client"));
            this.buttonList.add(new GuiButton(0, startX + 390, startY, 120, 21, "Settings"));
            this.buttonList.add(new GuiButton(4, startX + 520, startY, 120, 21, "Exit"));
        }
        this.buttonList.add(new GuiButton(5, this.width - 120 - 10, this.height - 21 - 10, 120, 21, "Change Styles"));
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        long elapsed = System.currentTimeMillis() - this.startTime;
        float transitionDuration = 2000.0f;
        this.alpha = Math.min(1.0f, (float)elapsed / transitionDuration);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, this.alpha);
        this.mc.getTextureManager().bindTexture(BACKGROUNDS[this.currentBackgroundIndex]);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0f, 0.0f, this.width, this.height, this.width, this.height);
        int titleY = 30;
        if (this.verticalLayout) {
            FontManager.F48.drawString("TYClient", this.width / 2, titleY + 41, new Color(255, 255, 255, 255).getRGB());
        } else {
            FontManager.F48.drawString("TYClient", this.width / 2, titleY + 41, new Color(0, 0, 0, 255).getRGB());
        }
        this.drawCustomFontString1("\u8d5e\u52a9\u6211+QQ:3808257263", this.width / 2, titleY + 320, new Color(255, 255, 255).getRGB(), 11);
        this.drawCustomFontString1("2025 January 15", this.width / 2, titleY + 328, new Color(255, 255, 255).getRGB(), 10);
        for (GuiButton button : this.buttonList) {
            button.drawButton1(this.mc, mouseX, mouseY);
        }
        GL11.glPopMatrix();
        super.drawScreen1(mouseX, mouseY, partialTicks);
    }

    private void drawCustomFontString1(String text, int x, int y, int color, int fontSize) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0.0f);
        GL11.glScalef((float)fontSize / 10.0f, (float)fontSize / 10.0f, 1.0f);
        int textWidth = this.mc.fontRendererObj.getStringWidth(text);
        this.mc.fontRendererObj.drawString(text, -textWidth / 2, 0, color);
        GL11.glPopMatrix();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 4) {
            this.mc.shutdown();
        }
        if (button.id == 5) {
            this.currentBackgroundIndex = (this.currentBackgroundIndex + 1) % BACKGROUNDS.length;
            this.verticalLayout = !this.verticalLayout;
            this.initGui();
        }
        if (button.id == 6) {
            this.mc.displayGuiScreen(new ClientGUI());
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }
}

