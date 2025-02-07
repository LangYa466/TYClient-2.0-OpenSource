/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.render;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.util.DrawUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BetterTab
extends Mod {
    public BetterTab() {
        super("BetterTab", "\u66f4\u7f8e\u4e3d\u5feb\u6377\u680f", Category.render);
    }

    @Override
    public void draw() {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        EntityPlayer entityplayer = (EntityPlayer)mc.getRenderViewEntity();
        int i = sr.getScaledWidth() / 2;
        DrawUtil.drawRect(0, sr.getScaledHeight() - 22, sr.getScaledWidth(), 22, new Color(0, 0, 0, 120).getRGB());
        DrawUtil.drawRect(i - 91 + entityplayer.inventory.currentItem * 20, sr.getScaledHeight() - 22, 22, 22, new Color(0, 0, 0, 90).getRGB());
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();
        for (int j = 0; j < 9; ++j) {
            int k = sr.getScaledWidth() / 2 - 90 + j * 20 + 2;
            int l = sr.getScaledHeight() - 16 - 3;
            this.renderHotbarItem(j, k, l, 0.0f, entityplayer);
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        this.info();
    }

    public void info() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        int fps2 = Minecraft.getDebugFPS();
        font.drawString("FPS: " + fps2, sr.getScaledWidth() / 2 - font.getStringWidth("FPS: " + fps2) / 2 - 100, sr.getScaledHeight() - 20, -1);
    }

    private void renderHotbarItem(int index, int xPos, int yPos, float partialTicks, EntityPlayer p_175184_5_) {
        ItemStack itemstack = p_175184_5_.inventory.mainInventory[index];
        if (itemstack != null) {
            float f = (float)itemstack.animationsToGo - partialTicks;
            if (f > 0.0f) {
                GlStateManager.pushMatrix();
                float f1 = 1.0f + f / 5.0f;
                GlStateManager.translate(xPos + 8, yPos + 12, 0.0f);
                GlStateManager.scale(1.0f / f1, (f1 + 1.0f) / 2.0f, 1.0f);
                GlStateManager.translate(-(xPos + 8), -(yPos + 12), 0.0f);
            }
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemstack, xPos, yPos);
            if (f > 0.0f) {
                GlStateManager.popMatrix();
            }
            Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRendererObj, itemstack, xPos, yPos);
        }
    }
}

