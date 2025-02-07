/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;
import net.minecraft.client.gui.GuiScreen;

public class EventScreen
implements Event {
    private final GuiScreen guiScreen;

    public EventScreen(GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }

    public GuiScreen getGuiScreen() {
        return this.guiScreen;
    }
}

