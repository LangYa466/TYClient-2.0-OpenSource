/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;
import net.minecraft.client.multiplayer.WorldClient;

public class EventWorldLoad
implements Event {
    private final WorldClient world;

    public EventWorldLoad(WorldClient world) {
        this.world = world;
    }

    public WorldClient getWorld() {
        return this.world;
    }
}

