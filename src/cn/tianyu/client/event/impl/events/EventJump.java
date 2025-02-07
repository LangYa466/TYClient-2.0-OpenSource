/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;

public class EventJump
extends CancellableEvent {
    public float motion;
    public float yaw;

    public EventJump(float yaw, float motion) {
        this.yaw = yaw;
        this.motion = motion;
    }

    public float getMotion() {
        return this.motion;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setMotion(float motion) {
        this.motion = motion;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
}

