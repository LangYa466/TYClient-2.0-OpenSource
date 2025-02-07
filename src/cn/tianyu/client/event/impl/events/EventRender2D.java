/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;
import net.minecraft.client.gui.ScaledResolution;

public class EventRender2D
implements Event {
    private float partialTicks;
    private final ScaledResolution scaledResolution;

    public EventRender2D(float partialTicks, ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
}

