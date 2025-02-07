/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.world;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.module.impl.client.Notifications;
import net.minecraft.client.Minecraft;

public class XRay
extends Mod {
    public XRay() {
        super("XRay", "\u900f\u77ff", Category.world);
        this.setKey(45);
    }

    @Override
    public void enable() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        Notifications.addNotification("\u5f00\u542fXray", true);
    }

    @Override
    public void disable() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        Notifications.addNotification("\u5173\u95edXray", false);
    }
}

