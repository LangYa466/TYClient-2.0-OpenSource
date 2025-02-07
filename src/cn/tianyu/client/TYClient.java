/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client;

import cn.tianyu.client.command.CommandManager;
import cn.tianyu.client.config.ConfigManager;
import cn.tianyu.client.event.EventManager;
import cn.tianyu.client.gui.clickgui.ClickGui;
import cn.tianyu.client.module.ModManager;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

public class TYClient {
    public static TYClient instance;
    public static final String NAME = "TYClient";
    public static final String VERSION = "2.0";
    public static ModManager modManager;
    public static final Logger LOGGER;
    public static ConfigManager configManager;
    public static CommandManager commandManager;
    public static EventManager eventManager;
    public static ClickGui clickGui;
    public static Minecraft mc;

    public static void start() {
        modManager = new ModManager();
        configManager = new ConfigManager();
        commandManager = new CommandManager();
        modManager.load();
        configManager.load();
        commandManager.load();
        clickGui = new ClickGui();
        eventManager = new EventManager();
        Display.setTitle("TYClient | 2.0  by CN_tianyu January 28 2025\u9664\u5915\u5feb\u4e50");
    }

    public static void stop() {
        configManager.save();
    }

    static {
        LOGGER = LogManager.getLogger(NAME);
        eventManager = new EventManager();
        mc = Minecraft.getMinecraft();
    }
}

