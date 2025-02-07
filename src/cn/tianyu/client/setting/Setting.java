/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.setting;

public class Setting<T> {
    String name;
    private T value;

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

