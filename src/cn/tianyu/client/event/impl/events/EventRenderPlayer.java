/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;

public class EventRenderPlayer
implements Event {
    public REventState eventState;

    public EventRenderPlayer(REventState eventState) {
        this.eventState = eventState;
    }

    public static enum REventState {
        PRE,
        POST;

    }
}

