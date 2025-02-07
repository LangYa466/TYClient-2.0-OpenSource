/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.util.impl.render;

import cn.tianyu.client.util.annotations.Animation;

public class DecelerateAnimation
extends Animation {
    public DecelerateAnimation(int ms, double endPoint) {
        super(ms, endPoint);
    }

    @Override
    protected double getEquation(double x) {
        return 1.0 - (x - 1.0) * (x - 1.0);
    }
}

