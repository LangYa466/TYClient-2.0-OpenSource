/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.client;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.module.impl.client.Notifications;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Clientrender
extends Mod {
    public Clientrender() {
        super("Clientrender", "\u663e\u793a\u5ba2\u6237\u7aef\u540d\u79f0", Category.client);
    }

    @Override
    public void draw() {
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontRenderer = minecraft.fontRendererObj;
        EntityPlayerSP player = Minecraft.thePlayer;
        int xPosition = 10;
        int yPosition = 10;
        int boxWidth = 150;
        int boxHeight = 40;
        GuiButton.drawAdvancedRoundedRect(xPosition, yPosition += 20, xPosition + boxWidth, yPosition + boxHeight, 1, new Color(0, 0, 0, 150).getRGB());
        ResourceLocation skin = this.getPlayerSkin(player);
        minecraft.getTextureManager().bindTexture(skin);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        this.drawModalRectWithCustomSizedTexture(xPosition + 5, yPosition + 5, 8, 8, 8, 8, 64.0f, 64.0f);
        String playerName = player.getName();
        fontRenderer.drawString(playerName, xPosition + 50, yPosition + 5, new Color(255, 255, 255).getRGB());
        String health = "Health: " + (int)player.getHealth();
        fontRenderer.drawString(health, xPosition + 50, yPosition + 20, new Color(255, 255, 255).getRGB());
    }

    private ResourceLocation getPlayerSkin(EntityPlayer player) {
        NetworkPlayerInfo info = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(player.getUniqueID());
        return info != null ? info.getLocationSkin() : new ResourceLocation("textures/entity/steve.png");
    }

    @Override
    public void enable() {
        super.enable();
        Notifications.addNotification("\u5f00\u542frender", true);
    }

    @Override
    public void disable() {
        super.disable();
        Notifications.addNotification("\u5173\u95edrender", false);
    }

    private void drawModalRectWithCustomSizedTexture(int x, int y, int u, int v, int width, int height, float textureWidth, float textureHeight) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        GL11.glBegin(7);
        GL11.glTexCoord2f((float)u * f, (float)(v + height) * f1);
        GL11.glVertex3f(x, y + height, 0.0f);
        GL11.glTexCoord2f((float)(u + width) * f, (float)(v + height) * f1);
        GL11.glVertex3f(x + width, y + height, 0.0f);
        GL11.glTexCoord2f((float)(u + width) * f, (float)v * f1);
        GL11.glVertex3f(x + width, y, 0.0f);
        GL11.glTexCoord2f((float)u * f, (float)v * f1);
        GL11.glVertex3f(x, y, 0.0f);
        GL11.glEnd();
    }
}

