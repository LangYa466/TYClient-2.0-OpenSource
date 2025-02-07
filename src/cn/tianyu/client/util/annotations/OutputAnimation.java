/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.util.annotations;

import cn.tianyu.client.util.annotations.AnimationUtils;

public class OutputAnimation {
    private double now;

    public OutputAnimation(int now) {
        this.now = now;
    }

    public void animate(double target, float speed) {
        this.now = AnimationUtils.animate(target, this.now, (double)speed);
    }

    public double getOutput() {
        return this.now;
    }

    public void setNow(int now) {
        this.now = now;
    }
}

