/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.combat;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class Velocity
extends Mod {
    public Velocity() {
        super("Velocity", "\u53cd\u51fb\u9000(\u6ca1\u5b8c\u6210)", Category.combat);
    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }

    public void onPacketReceived(Packet<?> packet) {
        if (packet instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity velocityPacket = (S12PacketEntityVelocity)packet;
            int n = velocityPacket.getEntityID();
            Minecraft.getMinecraft();
            if (n == Minecraft.thePlayer.getEntityId()) {
                Minecraft.getMinecraft();
                EntityPlayerSP player = Minecraft.thePlayer;
                if (this.shouldJumpRest()) {
                    player.jump();
                }
                Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer(true));
            }
        }
    }

    private boolean shouldJumpRest() {
        return true;
    }
}

