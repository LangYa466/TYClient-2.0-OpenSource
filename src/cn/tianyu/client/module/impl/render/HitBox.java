/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.render;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class HitBox
extends Mod {
    public HitBox() {
        super("HitBox", "\u663e\u793a\u78b0\u649e\u7bb1", Category.render);
    }

    @Override
    public void render(float partialTicks) {
        Minecraft minecraft = Minecraft.getMinecraft();
        double renderPosX = minecraft.getRenderManager().renderPosX;
        double renderPosY = minecraft.getRenderManager().renderPosY;
        double renderPosZ = minecraft.getRenderManager().renderPosZ;
        for (Entity entity : Minecraft.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityLivingBase) || entity instanceof EntityPlayerSP) continue;
            AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
            AxisAlignedBB adjustedBoundingBox = new AxisAlignedBB(entityBoundingBox.minX - renderPosX, entityBoundingBox.minY - renderPosY, entityBoundingBox.minZ - renderPosZ, entityBoundingBox.maxX - renderPosX, entityBoundingBox.maxY - renderPosY, entityBoundingBox.maxZ - renderPosZ);
            this.drawESP(adjustedBoundingBox, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    private void drawESP(AxisAlignedBB boundingBox, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(3.0f);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(1);
        this.drawLine(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        this.drawLine(boundingBox.maxX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        this.drawLine(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ, boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        this.drawLine(boundingBox.minX, boundingBox.minY, boundingBox.maxZ, boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        this.drawLine(boundingBox.minX, boundingBox.maxY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        this.drawLine(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        this.drawLine(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ, boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        this.drawLine(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ, boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        this.drawLine(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        this.drawLine(boundingBox.maxX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        this.drawLine(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        this.drawLine(boundingBox.minX, boundingBox.minY, boundingBox.maxZ, boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glEnd();
        GlStateManager.popMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    private void drawLine(double x1, double y1, double z1, double x2, double y2, double z2) {
        GL11.glVertex3d(x1, y1, z1);
        GL11.glVertex3d(x2, y2, z2);
    }
}

