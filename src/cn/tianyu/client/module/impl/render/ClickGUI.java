/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.render;

import cn.tianyu.client.TYClient;
import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import net.minecraft.client.Minecraft;

public class ClickGUI
extends Mod {
    public ClickGUI() {
        super("ClickGUI", "\u5c31\u662f\u4f60\u73b0\u5728\u770b\u5230\u7684\u754c\u9762", Category.render);
        this.setKey(54);
    }

    @Override
    public void enable() {
        Minecraft.getMinecraft().displayGuiScreen(TYClient.clickGui);
        this.setEnable(false);
    }
}

