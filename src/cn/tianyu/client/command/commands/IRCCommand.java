/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.command.commands;

import cn.tianyu.client.command.Command;

public class IRCCommand
extends Command {
    public static String ircString;

    public IRCCommand() {
        super(new String[]{"i"});
    }

    @Override
    public void run(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            sb.append(args[i]);
            if (i >= args.length - 1) continue;
            sb.append(" ");
        }
        ircString = sb.toString();
    }
}

