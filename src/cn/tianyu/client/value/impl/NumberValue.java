/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.value.impl;

import cn.tianyu.client.value.Value;

public class NumberValue
extends Value<Double> {
    public float animatedPercentage;
    public boolean sliding;
    double max;
    double min;
    double inc;

    public NumberValue(String name, double val2, double min, double max, double inc) {
        super(name);
        this.setValue(val2);
        this.max = max;
        this.min = min;
        this.inc = inc;
    }

    public NumberValue(String name, double val2, double min, double max, double inc, Value.Dependency dependenc) {
        super(name, dependenc);
        this.setValue(val2);
        this.max = max;
        this.min = min;
        this.inc = inc;
    }

    public Double getMax() {
        return this.max;
    }

    public Double getMin() {
        return this.min;
    }

    public Double getInc() {
        return this.inc;
    }

    @Override
    public Double getConfigValue() {
        return (Double)this.getValue();
    }
}

