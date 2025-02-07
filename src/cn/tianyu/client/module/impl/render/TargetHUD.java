/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.render;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.module.impl.client.Notifications;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Mouse;

public class TargetHUD
extends Mod {
    private int posX = 110;
    private int posY = 100;
    private int width = 100;
    private int height = 50;
    private boolean isDragging = false;
    private int dragOffsetX;
    private int dragOffsetY;

    public TargetHUD() {
        super("TargetHUD", "\u653b\u51fb\u76ee\u6807\u4fe1\u606f\u663e\u793a", Category.render);
        Notifications.addNotification("\u5f00\u542f", true);
    }

    @Override
    public void draw() {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entityHit = mc.objectMouseOver.entityHit;
        EntityPlayerSP player = Minecraft.thePlayer;
        if (mc.currentScreen instanceof GuiChat) {
            if (Mouse.isButtonDown(0)) {
                if (this.isDragging) {
                    this.posX = Mouse.getX() * mc.displayWidth / mc.displayWidth - this.dragOffsetX;
                    this.posY = mc.displayHeight - Mouse.getY() * mc.displayHeight / mc.displayHeight - this.dragOffsetY;
                    this.posX = Math.max(0, Math.min(this.posX, mc.displayWidth - this.width));
                    this.posY = Math.max(0, Math.min(this.posY, mc.displayHeight - this.height));
                } else if (this.isMouseOver()) {
                    this.isDragging = true;
                    this.dragOffsetX = Mouse.getX() * mc.displayWidth / mc.displayWidth - this.posX;
                    this.dragOffsetY = mc.displayHeight - Mouse.getY() * mc.displayHeight / mc.displayHeight - this.posY;
                }
            } else {
                this.isDragging = false;
            }
            GuiInventory.drawEntityOnScreen(this.posX + 20, this.posY + this.height / 4, 30, 0.0f, 0.0f, player);
            mc.fontRendererObj.drawString(player.getName(), this.posX + 70, this.posY + 10, -1);
            mc.fontRendererObj.drawString(String.format("HP: %s/%s", (int)player.getHealth(), (int)player.getMaxHealth()), this.posX + 70, this.posY + 30, -1);
            this.drawRect(this.posX, this.posY, this.posX + this.width, this.posY + this.height, new Color(0, 0, 0, 150).getRGB());
            if (entityHit instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase)entityHit;
                GuiInventory.drawEntityOnScreen(this.posX + 20, this.posY + this.height / 2 + 20, 30, 0.0f, 0.0f, entity);
                mc.fontRendererObj.drawString(entity.getName(), this.posX + 70, this.posY + this.height / 2 + 30, -1);
                mc.fontRendererObj.drawString(String.format("HP: %s/%s", (int)entity.getHealth(), (int)entity.getMaxHealth()), this.posX + 70, this.posY + this.height / 2 + 50, -1);
            }
        } else if (entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)entityHit;
            this.drawRect(this.posX, this.posY, this.posX + this.width, this.posY + this.height, new Color(0, 0, 0, 150).getRGB());
            GuiInventory.drawEntityOnScreen(this.posX + 20, this.posY + this.height / 2 + 20, 30, 0.0f, 0.0f, entity);
            mc.fontRendererObj.drawString(entity.getName(), this.posX + 70, this.posY + this.height / 2 + 30, -1);
            mc.fontRendererObj.drawString(String.format("HP: %s/%s", (int)entity.getHealth(), (int)entity.getMaxHealth()), this.posX + 70, this.posY + this.height / 2 + 50, -1);
        }
    }

    private boolean isMouseOver() {
        int mouseX = Mouse.getX() * Minecraft.getMinecraft().displayWidth / Minecraft.getMinecraft().displayWidth;
        int mouseY = Minecraft.getMinecraft().displayHeight - Mouse.getY() * Minecraft.getMinecraft().displayHeight / Minecraft.getMinecraft().displayHeight;
        return mouseX >= this.posX && mouseX <= this.posX + this.width && mouseY >= this.posY && mouseY <= this.posY + this.height;
    }

    private void drawRect(int left, int top, int right, int bottom, int color) {
        GuiIngame cfr_ignored_0 = Minecraft.getMinecraft().ingameGUI;
        GuiIngame.drawRect(left, top, right, bottom, color);
    }
}

