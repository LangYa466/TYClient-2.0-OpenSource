/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.config;

import cn.tianyu.client.config.Config;
import cn.tianyu.client.config.configs.ModConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private final List<Config> configs = new ArrayList<Config>(){
        {
            this.add(new ModConfig());
        }
    };

    public void load() {
        for (Config config : this.configs) {
            if (!config.getPath().toFile().exists()) continue;
            config.load();
        }
    }

    public void save() {
        for (Config config : this.configs) {
            if (!config.getPath().toFile().exists()) {
                try {
                    Files.createDirectories(config.getPath().getParent(), new FileAttribute[0]);
                    config.getPath().toFile().createNewFile();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            config.save();
        }
    }
}

