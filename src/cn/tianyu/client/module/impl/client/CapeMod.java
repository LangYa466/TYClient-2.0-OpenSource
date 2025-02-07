/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.client;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class CapeMod
extends Mod {
    public CapeMod() {
        super("Cape", "\u62ab\u98ce", Category.client);
    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("\u4f60\u4ee5\u4e3a\u4f60\u5173\u6389\u4e86\uff1f\u60f3\u4ec0\u4e48\u80fd,\u4f60\u522b\u8bf4\uff0c\u4f60\u8fd8\u771f\u5173\u4e86\uff0c\u4f46\u6ca1\u7528\u7684\u54c8\u54c8\u54c8"));
    }
}

