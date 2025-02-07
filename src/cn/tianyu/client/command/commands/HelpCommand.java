/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.command.commands;

import cn.tianyu.client.command.Command;
import java.util.Arrays;

public class HelpCommand
extends Command {
    public HelpCommand() {
        super(new String[]{"help"});
    }

    @Override
    public void run(String[] args) {
        System.out.println(Arrays.toString(args));
    }
}

