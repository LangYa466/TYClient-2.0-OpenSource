/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.value.impl;

import cn.tianyu.client.value.Value;

public class ModeValue
extends Value<String> {
    private final String[] modes;

    public ModeValue(String name, String[] modes, String value) {
        super(name);
        this.modes = modes;
        this.setValue(value);
    }

    public ModeValue(String name, String[] modes, String value, Value.Dependency dependenc) {
        super(name, dependenc);
        this.modes = modes;
        this.setValue(value);
    }

    public boolean is(String sb) {
        return ((String)this.getValue()).equalsIgnoreCase(sb);
    }

    public String[] getModes() {
        return this.modes;
    }

    public String getModeAsString() {
        return (String)this.getValue();
    }

    public void setMode(String mode) {
        for (String e : this.modes) {
            if (e == null) {
                return;
            }
            if (!e.equalsIgnoreCase(mode)) continue;
            this.setValue(e);
        }
    }

    @Override
    public String getConfigValue() {
        return (String)this.value;
    }
}

