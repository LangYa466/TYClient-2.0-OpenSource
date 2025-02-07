/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module;

import cn.tianyu.client.TYClient;
import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.module.impl.client.CapeMod;
import cn.tianyu.client.module.impl.client.Clientrender;
import cn.tianyu.client.module.impl.client.HUD;
import cn.tianyu.client.module.impl.client.IRC;
import cn.tianyu.client.module.impl.client.MemoryFix;
import cn.tianyu.client.module.impl.client.Notifications;
import cn.tianyu.client.module.impl.combat.AimAssist;
import cn.tianyu.client.module.impl.combat.AutoGapple;
import cn.tianyu.client.module.impl.combat.BrightPlayers;
import cn.tianyu.client.module.impl.combat.Killaura;
import cn.tianyu.client.module.impl.combat.Reach;
import cn.tianyu.client.module.impl.combat.Velocity;
import cn.tianyu.client.module.impl.movement.SprintMod;
import cn.tianyu.client.module.impl.player.acdsda;
import cn.tianyu.client.module.impl.render.BetterTab;
import cn.tianyu.client.module.impl.render.ClickGUI;
import cn.tianyu.client.module.impl.render.Effect;
import cn.tianyu.client.module.impl.render.HitBox;
import cn.tianyu.client.module.impl.render.KeyBoard;
import cn.tianyu.client.module.impl.render.NameLabel;
import cn.tianyu.client.module.impl.render.NoHurtCamera;
import cn.tianyu.client.module.impl.render.SelectBlock;
import cn.tianyu.client.module.impl.render.TargetHUD;
import cn.tianyu.client.module.impl.useful.AutoRespawn;
import cn.tianyu.client.module.impl.useful.FastPlace;
import cn.tianyu.client.module.impl.useful.ItemPhysics;
import cn.tianyu.client.module.impl.useful.Light;
import cn.tianyu.client.module.impl.useful.MotionBlur;
import cn.tianyu.client.module.impl.useful.fps;
import cn.tianyu.client.module.impl.world.BetterSky;
import cn.tianyu.client.module.impl.world.Particles;
import cn.tianyu.client.module.impl.world.XRay;
import cn.tianyu.client.viamcp.ViaMCP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModManager {
    private final List<Mod> mods = new ArrayList<Mod>();
    private final Map<String, Mod> moduleMap = new HashMap<String, Mod>();

    public List<Mod> getEnableMods() {
        return this.mods.stream().filter(Mod::isEnable).collect(Collectors.toList());
    }

    public void onKey(int key) {
        for (Mod enableMod : this.mods) {
            if (enableMod.getKey() != key) continue;
            enableMod.setEnable(!enableMod.isEnable());
        }
    }

    public <T extends Mod> T getModule(Class<T> cls) {
        return (T)((Mod)cls.cast(this.moduleMap.get(cls.getSimpleName())));
    }

    public void load() {
        this.mods.add(new AimAssist());
        this.mods.add(new Reach());
        this.mods.add(new AutoGapple());
        this.mods.add(new BrightPlayers());
        this.mods.add(new Killaura());
        this.mods.add(new Velocity());
        this.mods.add(new SprintMod());
        this.mods.add(new acdsda());
        this.mods.add(new BetterTab());
        this.mods.add(new ClickGUI());
        this.mods.add(new HitBox());
        this.mods.add(new KeyBoard());
        this.mods.add(new NameLabel());
        this.mods.add(new NoHurtCamera());
        this.mods.add(new SelectBlock());
        this.mods.add(new TargetHUD());
        this.mods.add(new Effect());
        this.mods.add(new Light());
        this.mods.add(new fps());
        this.mods.add(new FastPlace());
        this.mods.add(new ItemPhysics());
        this.mods.add(new MotionBlur());
        this.mods.add(new AutoRespawn());
        this.mods.add(new XRay());
        this.mods.add(new Particles());
        this.mods.add(new BetterSky());
        this.mods.add(new CapeMod());
        this.mods.add(new Clientrender());
        this.mods.add(new HUD());
        this.mods.add(new IRC());
        this.mods.add(new Notifications());
        this.mods.add(new MemoryFix());
        try {
            TYClient.LOGGER.info("Starting ViaMCP...");
            ViaMCP viaMCP = ViaMCP.getInstance();
            viaMCP.start();
            viaMCP.initAsyncSlider(5, 5, 110, 20);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mod getByName(String name) {
        for (Mod mod : this.mods) {
            if (!name.equalsIgnoreCase(mod.getName())) continue;
            return mod;
        }
        return null;
    }

    public Mod getByClass(Class<? extends Mod> modClass) {
        for (Mod mod : this.mods) {
            if (mod.getClass() != modClass) continue;
            return mod;
        }
        return null;
    }

    public List<Mod> getByCategory(Category category) {
        return this.mods.stream().filter(m -> m.getCategory() == category).collect(Collectors.toList());
    }

    public List<Mod> getMods() {
        return this.mods;
    }
}

