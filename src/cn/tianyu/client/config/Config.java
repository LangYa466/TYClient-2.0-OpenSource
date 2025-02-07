/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import net.minecraft.client.Minecraft;

public class Config {
    private final String name;

    public Config(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getCCC() {
        return this.name;
    }

    public Path getPath() {
        String[] stringArray = new String[3];
        stringArray[0] = "TYClient";
        stringArray[1] = "config";
        stringArray[2] = this.name + ".json";
        return Paths.get(Minecraft.getMinecraft().mcDataDir.getAbsolutePath(), stringArray);
    }

    public void load() {
    }

    public void save() {
    }
}

