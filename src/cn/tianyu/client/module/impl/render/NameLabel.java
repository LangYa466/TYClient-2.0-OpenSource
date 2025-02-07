/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.render;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class NameLabel
extends Mod {
    public NameLabel() {
        super("NameLabel", "\u540d\u5b57\u6807\u7b7e", Category.render);
    }

    @Override
    public void render(float partialTicks) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = Minecraft.thePlayer;
        if (mc.currentScreen != null && mc.currentScreen instanceof GuiInventory) {
            this.renderPlayerName(player, partialTicks);
        } else if (mc.gameSettings.thirdPersonView == 0 || mc.gameSettings.thirdPersonView == 1) {
            this.renderPlayerName(player, partialTicks);
        }
        for (Entity entity : Minecraft.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityLivingBase) || entity instanceof EntityPlayerSP) continue;
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            String displayName = entityLivingBase.getDisplayName().getFormattedText();
            RenderUtil.renderLabel(entityLivingBase, displayName, 64, partialTicks);
        }
    }

    private void renderPlayerName(EntityPlayerSP player, float partialTicks) {
        if (player != null) {
            String displayName = player.getDisplayName().getFormattedText();
            RenderUtil.renderLabel(player, displayName, 64, partialTicks);
        }
    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}

