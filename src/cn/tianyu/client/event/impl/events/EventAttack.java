/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;
import net.minecraft.entity.Entity;

public class EventAttack
implements Event {
    private final boolean pre;
    private Entity target;

    public EventAttack(Entity entity, boolean pre) {
        this.target = entity;
        this.pre = pre;
    }

    public Entity getTarget() {
        return this.target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public boolean isPre() {
        return this.pre;
    }

    public boolean isPost() {
        return !this.pre;
    }
}

