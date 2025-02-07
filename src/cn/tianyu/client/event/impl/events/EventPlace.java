/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;

public class EventPlace
extends CancellableEvent {
    private boolean shouldRightClick;
    private int slot;

    public EventPlace(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return this.slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean isShouldRightClick() {
        return this.shouldRightClick;
    }

    public void setShouldRightClick(boolean shouldRightClick) {
        this.shouldRightClick = shouldRightClick;
    }
}

