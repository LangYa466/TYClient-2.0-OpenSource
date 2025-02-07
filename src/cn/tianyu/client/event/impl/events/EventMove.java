/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;
import net.minecraft.client.Minecraft;

public class EventMove
extends CancellableEvent {
    public double x;
    public double y;
    public double z;
    private final double motionX;
    private final double motionY;
    private final double motionZ;

    public EventMove(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setMoveSpeed(double speed) {
        Minecraft.getMinecraft();
        double forward = Minecraft.thePlayer.movementInput.moveForward;
        Minecraft.getMinecraft();
        double strafe = Minecraft.thePlayer.movementInput.moveStrafe;
        Minecraft.getMinecraft();
        float yaw = Minecraft.thePlayer.rotationYaw;
        if (forward == 0.0 && strafe == 0.0) {
            this.setX(0.0);
            this.setZ(0.0);
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (float)(forward > 0.0 ? -45 : 45);
                } else if (strafe < 0.0) {
                    yaw += (float)(forward > 0.0 ? 45 : -45);
                }
                strafe = 0.0;
                forward = forward > 0.0 ? 1.0 : -1.0;
            }
            double sin = Math.sin(Math.toRadians(yaw + 90.0f));
            double cos = Math.cos(Math.toRadians(yaw + 90.0f));
            this.setX(forward * speed * cos + strafe * speed * sin);
            this.setZ(forward * speed * sin - strafe * speed * cos);
        }
    }
}

