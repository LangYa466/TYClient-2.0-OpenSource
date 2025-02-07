/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.util.impl;

import org.apache.commons.lang3.RandomUtils;

public class TimerUtil {
    public long lastMS = System.currentTimeMillis();

    public final long getDifference() {
        return this.getCurrentMS() - this.lastMS;
    }

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public void reset() {
        this.lastMS = System.currentTimeMillis();
    }

    public boolean hasReached(double milliseconds) {
        return (double)(this.getCurrentMS() - this.lastMS) >= milliseconds;
    }

    public static long randomDelay(int minDelay, int maxDelay) {
        return RandomUtils.nextInt(minDelay, maxDelay);
    }

    public boolean hasTimeElapsed(long time, boolean reset) {
        if (System.currentTimeMillis() - this.lastMS > time) {
            if (reset) {
                this.reset();
            }
            return true;
        }
        return false;
    }

    public boolean hasTimeElapsed(long time) {
        return System.currentTimeMillis() - this.lastMS > time;
    }

    public boolean delay(float time) {
        return (float)(System.currentTimeMillis() - this.lastMS) >= time;
    }

    public boolean hasTimeElapsed(double time) {
        return this.hasTimeElapsed((long)time);
    }

    public long getTime() {
        return System.currentTimeMillis() - this.lastMS;
    }

    public void setTime(long time) {
        this.lastMS = time;
    }
}

