package cn.tianyu.client.command;

import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import cn.tianyu.client.command.commands.IRCCommand;
import cn.tianyu.client.command.commands.bindCommand;
import cn.tianyu.client.command.commands.OpenCommand;
import cn.tianyu.client.command.commands.HelpCommand;
import java.util.HashMap;
import java.util.Map;

public class CommandManager
{
    private final Map<String[], Command> commandMap;

    public CommandManager() {
        this.commandMap = new HashMap<String[], Command>();
    }

    public void load() {
        final HelpCommand helpCommand = new HelpCommand();
        this.commandMap.put(helpCommand.getKey(), helpCommand);
        final OpenCommand OpenCommand = new OpenCommand();
        this.commandMap.put(OpenCommand.getKey(), OpenCommand);
        final bindCommand bindCommand = new bindCommand();
        this.commandMap.put(bindCommand.getKey(), bindCommand);
        final IRCCommand IRCCommand = new IRCCommand();
        this.commandMap.put(IRCCommand.getKey(), IRCCommand);
    }

    public boolean run(final String message) {
        if ('.' == message.charAt(0)) {
            final String substring = message.substring(1);
            final String[] s = substring.split(" ");
            final String key = s[0];
            final Command command = this.getCommand(key);
            if (command != null) {
                final List<String> args = new ArrayList<String>();
                Collections.addAll(args, s);
                args.remove(0);
                command.run(args.toArray(new String[0]));
            }
            return true;
        }
        return false;
    }

    public Command getCommand(final String key) {
        for (final Map.Entry<String[], Command> commandEntry : this.commandMap.entrySet()) {
            for (final String k : commandEntry.getKey()) {
                if (k.equals(key)) {
                    return commandEntry.getValue();
                }
            }
        }
        return null;
    }
}
