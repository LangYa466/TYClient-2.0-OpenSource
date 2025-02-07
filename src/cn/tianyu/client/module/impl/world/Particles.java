/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.world;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.value.impl.BoolValue;

public class Particles
extends Mod {
    BoolValue normal = new BoolValue("Example", true);
    private final boolean enchantment = true;
    private int amount = 5;

    public Particles() {
        super("Particles", "\u6253\u4e1c\u897f\u5c31\u4f1a\u5192\u51fa\u950b\u5229\u7c92\u5b50", Category.world);
    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}

