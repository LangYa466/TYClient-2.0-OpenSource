/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.useful;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.module.impl.client.Notifications;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;

public class FastPlace
extends Mod {
    public static final String NAME = "FastPlace";

    public FastPlace() {
        super(NAME, "\u5feb\u901f\u653e\u7f6e(\u4ec5\u5efa\u7b51\u65f6\u4f7f\u7528)", Category.useful);
    }

    @Override
    public void update() {
        Minecraft.getMinecraft();
        if (Minecraft.thePlayer.inventory.getCurrentItem() != null) {
            Minecraft.getMinecraft();
            Minecraft.getMinecraft().rightClickDelayTimer = Minecraft.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock ? 0 : 4;
        }
    }

    @Override
    public void enable() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        Notifications.addNotification("\u5f00\u542fFastPlace", true);
    }

    @Override
    public void disable() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        Notifications.addNotification("\u5173\u95edFastPlace", false);
    }
}

