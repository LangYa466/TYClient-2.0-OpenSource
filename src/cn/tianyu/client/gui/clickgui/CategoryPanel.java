/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.gui.clickgui;

import cn.tianyu.client.TYClient;
import cn.tianyu.client.font.FontManager;
import cn.tianyu.client.gui.clickgui.ModPanel;
import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.util.DrawUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel {
    private int x;
    private int y;
    private Category category;
    private final int width = 80;
    private final int height = 20;
    private int prevX;
    private int prevY;
    private boolean press;
    private boolean hovered;
    private boolean displayMod = true;
    private final List<ModPanel> modPanels = new ArrayList<ModPanel>();

    public CategoryPanel(int x, int y, Category category) {
        this.x = x;
        this.y = y;
        this.category = category;
        for (Mod mod : TYClient.modManager.getByCategory(category)) {
            this.modPanels.add(new ModPanel(mod));
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        boolean bl = this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        if (this.hovered && this.press) {
            this.x = mouseX + this.prevX;
            this.y = mouseY + this.prevY;
        }
        int background = new Color(0, 0, 0, 190).getRGB();
        int select = new Color(166, 166, 166, 128).getRGB();
        DrawUtil.drawRect(this.x, this.y, 80, 20, this.hovered ? select : background);
        FontManager.F22.drawString(this.category.name(), this.x + 40, this.y + 5, -1);
        int index = 0;
        if (this.displayMod) {
            for (ModPanel it : this.modPanels) {
                it.x = this.x;
                it.y = this.y + 20 + index;
                it.drawScreen(mouseX, mouseY, partialTicks);
                index += 20;
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            this.press = true;
            this.prevX = this.x - mouseX;
            this.prevY = this.y - mouseY;
        } else if (mouseButton == 1 && this.hovered) {
            boolean bl = this.displayMod = !this.displayMod;
        }
        if (this.displayMod) {
            this.modPanels.forEach(it -> it.mouseClicked(mouseX, mouseY, mouseButton));
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.press = false;
        if (this.displayMod) {
            this.modPanels.forEach(it -> it.mouseReleased(mouseX, mouseY, state));
        }
    }
}

