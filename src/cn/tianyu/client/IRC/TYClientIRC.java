/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC;

import cn.tianyu.client.IRC.IRCHandler;
import cn.tianyu.client.IRC.IRCTransport;
import cn.tianyu.client.TYClient;
import cn.tianyu.client.command.commands.IRCCommand;
import cn.tianyu.client.gui.CustomLoginScreen;
import cn.tianyu.client.module.impl.client.IRC;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class TYClientIRC {
    private static String lastSentMessage = null;

    public static void start() {
        try {
            String IRCString = IRCCommand.ircString;
            if (CustomLoginScreen.ClientLogin && TYClient.modManager.getByClass(IRC.class).isEnable() && IRCString != null && (lastSentMessage == null || !lastSentMessage.equals(IRCString))) {
                IRCTransport transport = new IRCTransport("150.138.78.137", 20126, new IRCHandler(){

                    @Override
                    public void onMessage(String sender, String message) {
                        System.out.println(sender + ": " + message);
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[IRC] " + sender + ": " + message));
                    }

                    @Override
                    public void onDisconnected(String message) {
                        System.out.println("Disconnected: " + message);
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Disconnected: " + message));
                    }

                    @Override
                    public void onConnected() {
                        System.out.println("Connected");
                    }

                    @Override
                    public String getInGameUsername() {
                        return Minecraft.thePlayer.getName();
                    }
                });
                transport.connect(Minecraft.thePlayer.getName(), "123");
                transport.sendChat(IRCString);
                lastSentMessage = IRCString;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

