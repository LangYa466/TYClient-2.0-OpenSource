/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.gui;

import cn.tianyu.client.font.FontManager;
import cn.tianyu.client.gui.CustomLoginScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SplashScreen
extends GuiScreen {
    private long startTime;
    private static final String BACKGROUND_TEXTURE = "client/bg/CN_tianyuu.png";

    @Override
    public void initGui() {
        super.initGui();
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawBackgroundImage();
        this.drawLoadingAnimation(this.width / 2, this.height / 2 + 50, partialTicks);
        FontManager.F48.drawString("TYClient", this.width / 2, this.height / 4 - 50, 0xFFFFFF);
        long elapsedTime = System.currentTimeMillis() - this.startTime;
        if (elapsedTime >= 3000L) {
            this.mc.displayGuiScreen(new CustomLoginScreen());
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawBackgroundImage() {
        try {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(BACKGROUND_TEXTURE));
            GL11.glBegin(7);
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex2f(0.0f, this.height);
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex2f(this.width, this.height);
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex2f(this.width, 0.0f);
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex2f(0.0f, 0.0f);
            GL11.glEnd();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawLoadingAnimation(int x, int y, float partialTicks) {
        long elapsedTime = System.currentTimeMillis() - this.startTime;
        float rotationAngle = (float)elapsedTime / 1000.0f * 360.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0.0f);
        GL11.glRotatef(rotationAngle, 0.0f, 0.0f, 1.0f);
        GL11.glLineWidth(8.0f);
        GL11.glBegin(1);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        for (int i = 0; i < 360; ++i) {
            double angle = Math.toRadians(i);
            double x1 = Math.cos(angle) * 20.0;
            double y1 = Math.sin(angle) * 20.0;
            GL11.glVertex2d(x1, y1);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    public static void init() {
        SplashScreen splashScreen = new SplashScreen();
        Minecraft.getMinecraft().displayGuiScreen(splashScreen);
    }
}

