/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.combat;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.module.impl.client.Notifications;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0APacketAnimation;

public class Killaura
extends Mod
implements Runnable {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final float range = 3.1f;
    private volatile boolean running = false;

    public Killaura() {
        super("Killaura", "\u6740\u622e\u5149\u73af", Category.combat);
        this.setKey(34);
    }

    @Override
    public void enable() {
        super.enable();
        this.running = true;
        new Thread(this).start();
        Notifications.addNotification("Killaura Enabled", true);
    }

    @Override
    public void disable() {
        super.disable();
        this.running = false;
        Notifications.addNotification("Killaura Disabled", false);
        this.mc.gameSettings.keyBindUseItem.pressed = false;
    }

    @Override
    public void run() {
        while (this.running) {
            try {
                this.onUpdate();
                Thread.sleep(50L);
            }
            catch (InterruptedException var2) {
                InterruptedException e = var2;
                e.printStackTrace();
            }
        }
    }

    private void onUpdate() {
        if (Minecraft.thePlayer != null) {
            if (Minecraft.theWorld != null) {
                EntityLivingBase target = this.getClosestEntity();
                if (target != null) {
                    this.fakeRotate(target);
                    this.attackEntity(target);
                    if (Minecraft.thePlayer.getCurrentEquippedItem() != null) {
                        if (Minecraft.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) {
                            this.mc.gameSettings.keyBindUseItem.pressed = true;
                        }
                    }
                } else {
                    this.mc.gameSettings.keyBindUseItem.pressed = false;
                }
            }
        }
    }

    private EntityLivingBase getClosestEntity() {
        EntityLivingBase closestEntity = null;
        Iterator var2 = Minecraft.theWorld.loadedEntityList.iterator();
        while (var2.hasNext()) {
            Object object = var2.next();
            if (!(object instanceof EntityLivingBase)) continue;
            EntityLivingBase entity = (EntityLivingBase)object;
            if (entity == Minecraft.thePlayer || !(entity instanceof EntityPlayer)) continue;
            if (!(Minecraft.thePlayer.getDistanceToEntity(entity) <= 3.1f)) continue;
            if (closestEntity != null) {
                if (!(Minecraft.thePlayer.getDistanceToEntity(entity) < Minecraft.thePlayer.getDistanceToEntity(closestEntity))) continue;
            }
            closestEntity = entity;
        }
        return closestEntity;
    }

    private void fakeRotate(EntityLivingBase entity) {
        double diffX = entity.posX - Minecraft.thePlayer.posX;
        double var10000 = entity.posY + (double)entity.getEyeHeight() - (Minecraft.thePlayer.posY + (double)Minecraft.thePlayer.getEyeHeight());
        double diffZ = entity.posZ - Minecraft.thePlayer.posZ;
        Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        float fakeYaw = yaw + (float)(Math.random() * 10.0 - 5.0);
        Minecraft.thePlayer.renderYawOffset = fakeYaw;
        Minecraft.thePlayer.rotationYawHead = fakeYaw;
    }

    private void attackEntity(EntityLivingBase entity) {
        if (Minecraft.thePlayer.getDistanceToEntity(entity) <= 3.1f) {
            Minecraft.thePlayer.swingItem();
            Minecraft.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity((Entity)entity, C02PacketUseEntity.Action.ATTACK));
            Minecraft.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
        }
    }
}

