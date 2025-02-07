/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.setting.Setting;
import cn.tianyu.client.value.Value;
import java.util.ArrayList;
import java.util.List;

public abstract class Mod {
    private final String name;
    private final Category category;
    public boolean state = false;
    public final String version;
    private boolean enable;
    private boolean disable;
    private int key;
    private final List<Value<?>> values = new ArrayList();
    private final List<Setting<?>> setting = new ArrayList();

    public Mod(String name, String version, Category category) {
        this.name = name;
        this.version = version;
        this.category = category;
    }

    public boolean getState() {
        return this.state;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        if (enable) {
            this.enable();
        } else {
            this.disable();
        }
    }

    public Setting<?> getSettingByName(String name) {
        for (Setting<?> setting : this.setting) {
            if (!setting.getName().equalsIgnoreCase(name)) continue;
            return setting;
        }
        return null;
    }

    public void enable() {
    }

    public void disable() {
    }

    public void draw() {
    }

    public void update() {
    }

    public void key(int key) {
    }

    public void render(float partialTicks) {
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public boolean isDisable() {
        return this.disable;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    public List<Value<?>> getValues() {
        return this.values;
    }

    public List<Setting<?>> getSetting() {
        return this.setting;
    }
}

