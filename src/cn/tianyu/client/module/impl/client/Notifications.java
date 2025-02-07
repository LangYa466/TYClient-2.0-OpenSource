/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.client;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class Notifications
extends Mod {
    private static final List<Notification> notifications = new ArrayList<Notification>();

    public Notifications() {
        super("Notifications", "\u901a\u77e5\u529f\u80fd", Category.client);
    }

    public static void addNotification(String message, boolean isEnabled) {
        final Notification notification = new Notification(message, isEnabled);
        notifications.add(notification);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                notifications.remove(notification);
            }
        }, 5000L);
    }

    @Override
    public void draw() {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRenderer = mc.fontRendererObj;
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenHeight = scaledResolution.getScaledHeight();
        int screenWidth = scaledResolution.getScaledWidth();
        int yOffset = screenHeight - 20;
        for (Notification notification : notifications) {
            int x;
            int textWidth = fontRenderer.getStringWidth(notification.message);
            int boxWidth = textWidth + 10;
            int boxHeight = 20;
            if (!notification.isDisplayed) {
                notification.startX = screenWidth + boxWidth;
                notification.isDisplayed = true;
            }
            if ((x = notification.startX - (int)((double)(System.currentTimeMillis() - notification.startTime) / 10.0)) < screenWidth - boxWidth - 10) {
                x = screenWidth - boxWidth - 10;
            }
            GL11.glPushMatrix();
            GL11.glPushAttrib(1048575);
            GL11.glDisable(3553);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.5f);
            GL11.glBegin(7);
            GL11.glVertex2f(x, yOffset - boxHeight);
            GL11.glVertex2f(x + boxWidth, yOffset - boxHeight);
            GL11.glVertex2f(x + boxWidth, yOffset);
            GL11.glVertex2f(x, yOffset);
            GL11.glEnd();
            GL11.glEnable(3553);
            GL11.glDisable(3553);
            if (notification.isEnabled) {
                GL11.glColor4f(0.0f, 1.0f, 0.0f, 0.5f);
            } else {
                GL11.glColor4f(1.0f, 0.0f, 0.0f, 0.5f);
            }
            GL11.glBegin(7);
            GL11.glVertex2f(x, yOffset - boxHeight);
            GL11.glVertex2f(x + boxWidth, yOffset - boxHeight);
            GL11.glVertex2f(x + boxWidth, yOffset);
            GL11.glVertex2f(x, yOffset);
            GL11.glEnd();
            GL11.glEnable(3553);
            fontRenderer.drawString(notification.message, x + 5, yOffset - 15, 0xFFFFFF);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
            yOffset -= 30;
        }
    }

    private static class Notification {
        private final String message;
        private final boolean isEnabled;
        private final long startTime;
        private boolean isDisplayed;
        private int startX;

        public Notification(String message, boolean isEnabled) {
            this.message = message;
            this.isEnabled = isEnabled;
            this.startTime = System.currentTimeMillis();
            this.isDisplayed = false;
        }
    }
}

