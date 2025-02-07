/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.gui.clickgui;

import cn.tianyu.client.font.FontManager;
import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.util.DrawUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ModPanel {
    private final Mod mod;
    public int x;
    public int y;
    private final int width = 80;
    private final int height = 20;
    private boolean hovered;
    private Category category;
    private boolean enabled;
    private boolean showText;
    private static final int GAP = 10;

    public ModPanel(Mod mod) {
        this.mod = mod;
        this.enabled = mod.isEnable();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        int background = this.mod.isEnable() ? new Color(0, 174, 248, 87).getRGB() : new Color(0, 0, 0, 131).getRGB();
        DrawUtil.drawRect(this.x, this.y, this.width, this.height, background);
        FontManager.F22.drawString(this.mod.getName(), this.x + 40, this.y + 5, -1);
        if (this.showText) {
            DrawUtil.drawRect(this.x + 80 + 5, this.y + 5, this.width, this.height, Color.BLACK.getRGB());
            FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
            String description = this.mod.getVersion();
            fontRendererObj.drawString(description, this.x + 80 + 6, this.y + 5, Color.WHITE.getRGB());
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.hovered) {
            this.mod.setEnable(!this.mod.isEnable());
            this.enabled = !this.enabled;
        } else if (mouseButton == 1 && this.hovered) {
            this.showText = !this.showText;
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public void setYWithGap(int baseY) {
        this.y = baseY + 10;
    }
}

