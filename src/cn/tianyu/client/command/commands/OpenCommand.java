/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.command.commands;

import cn.tianyu.client.TYClient;
import cn.tianyu.client.command.Command;
import cn.tianyu.client.module.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class OpenCommand
extends Command {
    public OpenCommand() {
        super(new String[]{"open"});
    }

    @Override
    public void run(String[] args) {
        if (args.length == 1) {
            Mod byName = TYClient.modManager.getByName(args[0]);
            if (byName != null) {
                byName.setEnable(!byName.isEnable());
            } else {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Mod " + args[0] + " not found"));
            }
        }
    }
}

