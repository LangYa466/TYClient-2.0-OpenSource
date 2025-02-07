/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl;

import cn.tianyu.client.event.impl.Cancellable;
import cn.tianyu.client.event.impl.Event;

public abstract class CancellableEvent
implements Event,
Cancellable {
    private boolean cancelled;

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
}

