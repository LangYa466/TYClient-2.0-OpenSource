/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.movement;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.setting.EnableSetting;
import net.minecraft.client.Minecraft;

public class SprintMod
extends Mod {
    private final EnableSetting setting = new EnableSetting("setting1", true);

    public SprintMod() {
        super("Sprint", "\u81ea\u52a8\u75be\u8dd1", Category.movement);
        this.getSetting().add(this.setting);
    }

    @Override
    public void enable() {
        System.out.println(this.getSettingByName("setting1").getValue());
    }

    @Override
    public void update() {
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
            Minecraft.getMinecraft();
            if (!Minecraft.thePlayer.isInWater()) {
                Minecraft.getMinecraft();
                Minecraft.thePlayer.setSprinting(true);
            }
        }
    }
}

