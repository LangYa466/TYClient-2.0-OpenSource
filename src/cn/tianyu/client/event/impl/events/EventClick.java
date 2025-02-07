/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;

public class EventClick
extends CancellableEvent {
    private int key;

    public EventClick(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}

