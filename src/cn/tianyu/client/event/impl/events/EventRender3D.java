/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;
import net.minecraft.client.gui.ScaledResolution;

public class EventRender3D
implements Event {
    private float ticks;
    private float partialTicks;
    private ScaledResolution scaledResolution;

    public EventRender3D(float ticks) {
        this.ticks = ticks;
    }

    public void Render3DEvent(ScaledResolution scaledResolution, float partialTicks) {
        this.scaledResolution = scaledResolution;
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public float getTicks() {
        return this.ticks;
    }

    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
}

