/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.client;

import cn.tianyu.client.event.annotations.EventTarget;
import cn.tianyu.client.event.impl.events.EventTick;
import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.util.impl.TimerUtil;

public class MemoryFix
extends Mod {
    private int delay = 150;
    private int limit = 110;
    public TimerUtil timer = new TimerUtil();

    public MemoryFix() {
        super("MemoryFix", "\u5185\u5b58\u4f18\u5316", Category.client);
    }

    @EventTarget
    public void onTick(EventTick event) {
        long maxMem = Runtime.getRuntime().maxMemory();
        long totalMem = Runtime.getRuntime().totalMemory();
        long freeMem = Runtime.getRuntime().freeMemory();
        long usedMem = totalMem - freeMem;
        float pct = usedMem * 100L / maxMem;
        if (this.timer.hasReached((double)this.delay * 1000.0) && (double)this.limit <= (double)pct) {
            Runtime.getRuntime().gc();
            this.timer.reset();
        }
    }
}

