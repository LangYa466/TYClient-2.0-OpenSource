/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.command.commands;

import cn.tianyu.client.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class bindCommand
extends Command {
    public bindCommand() {
        super(new String[]{"bind"});
    }

    @Override
    public void run(String[] args) {
        if (args.length == 2) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("\u529f\u80fd\u6682\u672a\u5b8c\u6210"));
        } else {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("\u7528\u6cd5: .bind <modName> <key>"));
        }
    }
}

