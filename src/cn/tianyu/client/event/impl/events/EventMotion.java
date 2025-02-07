/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;

public class EventMotion
implements Event {
    public EventState eventState;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean ground;

    public EventMotion(double x, double y, double z, float yaw, float pitch, boolean ground, EventState eventState) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.ground = ground;
        this.eventState = eventState;
    }

    public boolean isPre() {
        return this.eventState == EventState.PRE;
    }

    public boolean isPost() {
        return this.eventState == EventState.POST;
    }

    public EventState getEventState() {
        return this.eventState;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public boolean isGround() {
        return this.ground;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setGround(boolean ground) {
        this.ground = ground;
    }

    public static enum EventState {
        PRE,
        POST;

    }
}

