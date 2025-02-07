/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.render;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import java.util.Collection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class Effect
extends Mod {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public Effect() {
        super("Effect", "\u663e\u793a\u836f\u6c34", Category.render);
    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }

    public static void onRenderGameOverlay() {
        block6: {
            block5: {
                if (Minecraft.thePlayer == null) break block5;
                if (Minecraft.theWorld != null) break block6;
            }
            return;
        }
        Collection<PotionEffect> activePotionEffects = Minecraft.thePlayer.getActivePotionEffects();
        if (activePotionEffects.isEmpty()) {
            return;
        }
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();
        int startX = 10;
        int startY = screenHeight / 2 - activePotionEffects.size() * 20 / 2;
        for (PotionEffect effect : activePotionEffects) {
            int potionID = effect.getPotionID();
            ResourceLocation potionTexture = new ResourceLocation("client/potion/" + potionID + ".png");
            mc.getTextureManager().bindTexture(potionTexture);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            Gui.drawModalRectWithCustomSizedTexture(startX, startY, 0.0f, 0.0f, 16, 16, 16.0f, 16.0f);
            String effectName = Potion.potionTypes[potionID].getName();
            effectName = effectName.replace("potion.", "");
            int duration = effect.getDuration() / 20;
            String displayText = effectName + " " + duration + "s";
            Effect.mc.fontRendererObj.drawStringWithShadow(displayText, startX + 20, startY + 4, 0xFFFFFF);
            startY += 20;
        }
    }
}

