/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;

public class EventMoveInput
extends CancellableEvent {
    private float forward;
    private float strafe;
    private boolean jump;
    private boolean sneak;
    private double sneakSlowDownMultiplier;

    public EventMoveInput(float forward, float strafe, boolean jump, boolean sneak, double sneakSlowDownMultiplier) {
        this.forward = forward;
        this.strafe = strafe;
        this.jump = jump;
        this.sneak = sneak;
        this.sneakSlowDownMultiplier = sneakSlowDownMultiplier;
    }

    public float getForward() {
        return this.forward;
    }

    public float getStrafe() {
        return this.strafe;
    }

    public boolean isJump() {
        return this.jump;
    }

    public boolean isSneak() {
        return this.sneak;
    }

    public double getSneakSlowDownMultiplier() {
        return this.sneakSlowDownMultiplier;
    }

    public void setForward(float forward) {
        this.forward = forward;
    }

    public void setStrafe(float strafe) {
        this.strafe = strafe;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setSneak(boolean sneak) {
        this.sneak = sneak;
    }

    public void setSneakSlowDownMultiplier(double sneakSlowDownMultiplier) {
        this.sneakSlowDownMultiplier = sneakSlowDownMultiplier;
    }
}

