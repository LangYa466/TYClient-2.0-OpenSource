/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.gui.clickgui;

import cn.tianyu.client.gui.clickgui.CategoryPanel;
import cn.tianyu.client.module.Category;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui
extends GuiScreen {
    private final List<CategoryPanel> categoryPanels = new ArrayList<CategoryPanel>();

    public ClickGui() {
        int x = 20;
        for (Category value : Category.values()) {
            this.categoryPanels.add(new CategoryPanel(x, 0, value));
            x += 100;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.categoryPanels.forEach(it -> it.drawScreen(mouseX, mouseY, partialTicks));
        super.drawScreen1(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.categoryPanels.forEach(it -> it.mouseClicked(mouseX, mouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        this.categoryPanels.forEach(it -> it.mouseReleased(mouseX, mouseY, state));
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}

