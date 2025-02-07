/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.value;

import cn.tianyu.client.util.annotations.EaseBackIn;
import cn.tianyu.client.util.annotations.OutputAnimation;
import cn.tianyu.client.util.impl.render.DecelerateAnimation;

public abstract class Value<V> {
    public final OutputAnimation numberAnim = new OutputAnimation(0);
    public final EaseBackIn easeBackIn = new EaseBackIn(200, (double)0.2f, 1.0f);
    public final DecelerateAnimation decelerateAnimation = new DecelerateAnimation(200, 1.0);
    public float animation = 0.0f;
    public float height = 22.0f;
    protected final Dependency dependency;
    public V value;
    String name;

    public Value(String name, Dependency dependenc) {
        this.name = name;
        this.dependency = dependenc;
    }

    public Value(String name) {
        this.name = name;
        this.dependency = () -> Boolean.TRUE;
    }

    public String getName() {
        return this.name;
    }

    public V getValue() {
        return this.value;
    }

    public V get() {
        return this.value;
    }

    public void setValue(V val2) {
        this.value = val2;
    }

    public void set(V val2) {
        this.value = val2;
    }

    public abstract <T> T getConfigValue();

    public boolean isHidden() {
        return !this.isAvailable();
    }

    public boolean isAvailable() {
        return this.dependency != null && this.dependency.check();
    }

    public float getHeight() {
        return this.height;
    }

    @FunctionalInterface
    public static interface Dependency {
        public boolean check();
    }
}

