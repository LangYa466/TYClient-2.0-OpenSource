/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;
import org.lwjgl.util.vector.Vector2f;

public class EventLook
implements Event {
    private Vector2f rotation;

    public EventLook(Vector2f rotation) {
        this.rotation = rotation;
    }

    public Vector2f getRotation() {
        return this.rotation;
    }

    public void setRotation(Vector2f rotation) {
        this.rotation = rotation;
    }
}

