/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.useful;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.value.impl.NumberValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;

public class MotionBlur
extends Mod {
    private static final int NUM_SAMPLES = 10;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static Framebuffer framebuffer;
    public final NumberValue blurAmount = new NumberValue("Amount", 7.0, 0.0, 10.0, 0.1);

    public MotionBlur() {
        super("MotionBlur", "\u52a8\u6001\u6a21\u7cca", Category.useful);
    }

    @Override
    public void enable() {
        framebuffer = new Framebuffer(MotionBlur.mc.displayWidth, MotionBlur.mc.displayHeight, true);
    }

    @Override
    public void disable() {
        if (framebuffer != null) {
            framebuffer.deleteFramebuffer();
        }
    }

    public static void onRenderGameOverlay() {
        if (framebuffer == null) {
            return;
        }
        if (MotionBlur.mc.entityRenderer == null || MotionBlur.mc.timer == null) {
            return;
        }
        float partialTicks = MotionBlur.mc.timer.renderPartialTicks;
        framebuffer.bindFramebuffer(true);
        MotionBlur.mc.entityRenderer.setupCameraTransform(partialTicks, 0);
        MotionBlur.mc.entityRenderer.renderWorld(partialTicks, 0L);
        framebuffer.unbindFramebuffer();
        MotionBlur.applyMotionBlur();
        mc.getFramebuffer().bindFramebuffer(true);
        MotionBlur.drawFramebuffer(framebuffer);
    }

    private static void applyMotionBlur() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 0.1f);
        for (int i = 0; i < 10; ++i) {
            MotionBlur.drawFramebuffer(framebuffer);
        }
        GlStateManager.disableBlend();
    }

    private static void drawFramebuffer(Framebuffer framebuffer) {
        int screenWidth = MotionBlur.mc.displayWidth;
        int screenHeight = MotionBlur.mc.displayHeight;
        GlStateManager.bindTexture(framebuffer.framebufferTexture);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(screenWidth, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(screenWidth, screenHeight);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(0.0f, screenHeight);
        GL11.glEnd();
    }
}

