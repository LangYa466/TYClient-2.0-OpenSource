/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;

public class EventSlowDown
extends CancellableEvent {
    private float strafeMultiplier;
    private float forwardMultiplier;

    public EventSlowDown(float strafeMultiplier, float forwardMultiplier) {
        this.strafeMultiplier = strafeMultiplier;
        this.forwardMultiplier = forwardMultiplier;
    }

    public float getStrafeMultiplier() {
        return this.strafeMultiplier;
    }

    public float getForwardMultiplier() {
        return this.forwardMultiplier;
    }

    public void setStrafeMultiplier(float strafeMultiplier) {
        this.strafeMultiplier = strafeMultiplier;
    }

    public void setForwardMultiplier(float forwardMultiplier) {
        this.forwardMultiplier = forwardMultiplier;
    }
}

