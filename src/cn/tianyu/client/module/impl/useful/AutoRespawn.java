/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.useful;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AutoRespawn
extends Mod {
    private final Minecraft mc = Minecraft.getMinecraft();
    private boolean isDead = false;

    public AutoRespawn() {
        super("AutoRespawn", "\u81ea\u52a8\u91cd\u751f(bug)", Category.useful);
    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }

    @Override
    public void update() {
        if (Minecraft.thePlayer == null) {
            return;
        }
        if (Minecraft.thePlayer.isDead && !this.isDead) {
            this.isDead = true;
            this.triggerRespawn();
        }
    }

    private void triggerRespawn() {
        if (Minecraft.theWorld != null) {
            if (Minecraft.thePlayer != null) {
                if (this.mc.currentScreen instanceof GuiGameOver) {
                    Minecraft.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                }
                this.isDead = false;
            }
        }
    }
}

