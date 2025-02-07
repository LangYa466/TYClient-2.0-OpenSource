/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.world;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class BetterSky
extends Mod {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private List<SkyProperties> skyPropertiesList = new ArrayList<SkyProperties>();

    public BetterSky() {
        super("BetterSky", "\u66f4\u597d\u7684\u5929\u7a7a", Category.world);
    }

    @Override
    public void enable() {
        super.enable();
        this.loadAllSkies("client/sky");
    }

    @Override
    public void disable() {
        super.disable();
        this.resetSky();
    }

    private void loadAllSkies(String directoryPath) {
        File[] files;
        File skyDirectory = new File(directoryPath);
        if (skyDirectory.exists() && skyDirectory.isDirectory() && (files = skyDirectory.listFiles((dir, name) -> name.endsWith(".properties"))) != null) {
            for (File file : files) {
                SkyProperties properties = new SkyProperties();
                properties.loadFromFile(file);
                this.skyPropertiesList.add(properties);
            }
        }
    }

    private void resetSky() {
    }

    @Override
    public void update() {
        block4: {
            block3: {
                super.update();
                if (Minecraft.theWorld == null) break block3;
                if (Minecraft.thePlayer != null) break block4;
            }
            return;
        }
        long currentTime = Minecraft.theWorld.getWorldTime();
        for (SkyProperties properties : this.skyPropertiesList) {
            if (!this.shouldChangeSky(currentTime, properties)) continue;
            this.applySky(properties);
        }
    }

    private boolean shouldChangeSky(long currentTime, SkyProperties properties) {
        if (currentTime >= (long)properties.startFadeIn && currentTime <= (long)properties.endFadeIn) {
            return true;
        }
        return currentTime >= (long)properties.startFadeOut && currentTime <= (long)properties.endFadeOut;
    }

    private void applySky(SkyProperties properties) {
        if (properties.source == null || properties.source.isEmpty()) {
            return;
        }
        ResourceLocation texture = new ResourceLocation("client/sky", properties.source);
        TextureManager textureManager = mc.getTextureManager();
        textureManager.bindTexture(texture);
        GlStateManager.pushMatrix();
        if (properties.rotate) {
            GlStateManager.rotate(45.0f, properties.axisX, properties.axisY, properties.axisZ);
        }
        GlStateManager.popMatrix();
    }

    public static class SkyProperties {
        public int startFadeIn;
        public int endFadeIn;
        public int startFadeOut;
        public int endFadeOut;
        public String blend;
        public boolean rotate;
        public float axisX;
        public float axisY;
        public float axisZ;
        public String source;

        public void loadFromFile(File file) {
            Properties properties = new Properties();
            try (FileReader reader = new FileReader(file);){
                properties.load(reader);
                this.startFadeIn = this.parseTime(properties.getProperty("startFadeIn"));
                this.endFadeIn = this.parseTime(properties.getProperty("endFadeIn"));
                this.startFadeOut = this.parseTime(properties.getProperty("startFadeOut"));
                this.endFadeOut = this.parseTime(properties.getProperty("endFadeOut"));
                this.blend = properties.getProperty("blend");
                this.rotate = Boolean.parseBoolean(properties.getProperty("rotate"));
                String[] axisValues = properties.getProperty("axis", "0.0 -0.2 0.0").split(" ");
                this.axisX = Float.parseFloat(axisValues[0]);
                this.axisY = Float.parseFloat(axisValues[1]);
                this.axisZ = Float.parseFloat(axisValues[2]);
                this.source = properties.getProperty("source");
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }

        private int parseTime(String time) {
            if (time == null || time.isEmpty()) {
                return 0;
            }
            String[] parts = time.split(":");
            return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        }
    }
}

