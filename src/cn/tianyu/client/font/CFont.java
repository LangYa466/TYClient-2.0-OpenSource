/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.font;

import cn.tianyu.client.font.FontData;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;

public class CFont {
    private int fontHeight;
    public final DynamicTexture texture;
    private final BufferedImage bufTexture;
    public final HashMap<Character, FontData> charMap = new HashMap();

    public CFont(Font font, int charSize) {
        for (int i = 0; i < charSize; ++i) {
            this.charMap.put(Character.valueOf((char)i), new FontData());
        }
        this.fontHeight = -1;
        this.bufTexture = this.getFontTexture(font, charSize >= 65535 ? 8192 : 512);
        this.texture = new DynamicTexture(this.bufTexture);
    }

    public void drawChar(FontData data, float x, float y) {
        try {
            this.drawQuad(x, y, data.width, data.height, data.storedX, data.storedY, this.bufTexture.getWidth(), data.width, data.height);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }

    public int getWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            if (c >= this.charMap.size()) continue;
            width += this.charMap.get((Object)Character.valueOf((char)c)).width - 8;
        }
        return width / 2;
    }

    private BufferedImage getFontTexture(Font font, int imgSize) {
        BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, 2);
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setFont(font);
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, imgSize, imgSize);
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fontMetrics = g.getFontMetrics();
        int charHeight = 0;
        int positionX = 0;
        int positionY = 1;
        for (Map.Entry<Character, FontData> data : this.charMap.entrySet()) {
            Character c = data.getKey();
            FontData charData = data.getValue();
            Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(c), g);
            charData.width = dimensions.getBounds().width + 8;
            charData.height = dimensions.getBounds().height;
            if (positionX + charData.width >= imgSize) {
                positionX = 0;
                positionY += charHeight;
                charHeight = 0;
            }
            if (charData.height > charHeight) {
                charHeight = charData.height;
            }
            charData.storedX = positionX;
            charData.storedY = positionY;
            if (charData.height > this.fontHeight) {
                this.fontHeight = charData.height;
            }
            g.drawString(String.valueOf(c), positionX + 2, positionY + fontMetrics.getAscent());
            positionX += charData.width;
        }
        return bufferedImage;
    }

    public void drawQuad(float x, float y, float width, float height, float srcX, float srcY, int imgSize, float srcWidth, float srcHeight) {
        float renderSRCX = srcX / (float)imgSize;
        float renderSRCY = srcY / (float)imgSize;
        float renderSRCWidth = srcWidth / (float)imgSize;
        float renderSRCHeight = srcHeight / (float)imgSize;
        GL11.glBegin(4);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + width, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x + width, y + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + width, y);
        GL11.glEnd();
    }
}

