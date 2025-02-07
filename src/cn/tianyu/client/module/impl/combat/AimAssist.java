/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.combat;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class AimAssist
extends Mod {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final float SMOOTHNESS = 1.1f;

    public AimAssist() {
        super("AimAssist", "\u81ea\u52a8\u7784\u51c6", Category.combat);
    }

    @Override
    public void update() {
        block5: {
            block4: {
                if (Minecraft.thePlayer == null) break block4;
                if (Minecraft.theWorld != null) break block5;
            }
            return;
        }
        EntityPlayer target = this.getClosestEnemy();
        if (target == null) {
            return;
        }
        float[] angles = this.getAnglesToEntity(target);
        float currentYaw = Minecraft.thePlayer.rotationYaw;
        float currentPitch = Minecraft.thePlayer.rotationPitch;
        float newYaw = currentYaw + this.wrapDegrees(angles[0] - currentYaw);
        float newPitch = currentPitch + this.wrapDegrees(angles[1] - currentPitch);
        Minecraft.thePlayer.rotationYaw = currentYaw + (newYaw - currentYaw) * 1.1f;
        Minecraft.thePlayer.rotationPitch = currentPitch + (newPitch - currentPitch) * 1.1f;
    }

    private EntityPlayer getClosestEnemy() {
        List<EntityPlayer> players = Minecraft.theWorld.playerEntities;
        EntityPlayer closestPlayer = null;
        double closestDistance = Double.MAX_VALUE;
        for (EntityPlayer player : players) {
            if (player == Minecraft.thePlayer || player.isInvisible() || !player.isEntityAlive()) continue;
            double distance = player.getDistanceToEntity(Minecraft.thePlayer);
            if (!(distance < closestDistance)) continue;
            closestDistance = distance;
            closestPlayer = player;
        }
        return closestPlayer;
    }

    private float[] getAnglesToEntity(EntityPlayer target) {
        double deltaX = target.posX - Minecraft.thePlayer.posX;
        double deltaY = target.posY + (double)target.getEyeHeight() - (Minecraft.thePlayer.posY + (double)Minecraft.thePlayer.getEyeHeight());
        double deltaZ = target.posZ - Minecraft.thePlayer.posZ;
        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float)Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(deltaY, distance)));
        return new float[]{yaw, pitch};
    }

    private float wrapDegrees(float value) {
        if ((value %= 360.0f) >= 180.0f) {
            value -= 360.0f;
        }
        if (value < -180.0f) {
            value += 360.0f;
        }
        return value;
    }
}

