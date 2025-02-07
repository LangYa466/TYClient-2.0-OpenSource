/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.gui;

import cn.tianyu.client.font.FontManager;
import cn.tianyu.client.gui.MainMenuGui.MainMenuGui;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CustomLoginScreen
extends GuiScreen {
    public static boolean ClientLogin = false;
    private GuiTextField usernameField;
    private GuiTextField passwordField;
    private String statusMessage;
    private ResourceLocation backgroundTexture;
    public static String loggedInUsername = null;
    private static final String CREDENTIALS_FILE = "login_credentials.txt";

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.usernameField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 4 - 20, 200, 20);
        this.usernameField.setMaxStringLength(30);
        this.usernameField.setFocused(true);
        this.passwordField = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, this.height / 4 + 20, 200, 20);
        this.passwordField.setMaxStringLength(30);
        this.passwordField.setEnableBackgroundDrawing(true);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 60, "Login"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 100, "Exit"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 140, "Reg"));
        this.backgroundTexture = new ResourceLocation("client/Q.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.backgroundTexture);
        this.loadCredentials();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            String username = this.usernameField.getText();
            String password = this.passwordField.getText();
            new Thread(() -> {
                String response = this.checkAccountOnServer(username, password);
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    if ("Login successful".equals(response)) {
                        loggedInUsername = username;
                        this.saveCredentials(username, password);
                        Minecraft.getMinecraft().displayGuiScreen(new MainMenuGui());
                        ClientLogin = true;
                    } else {
                        this.statusMessage = "Login failed: Account does not exist or password is incorrect.";
                    }
                });
            }).start();
        } else if (button.id == 1) {
            Minecraft.getMinecraft().shutdown();
        } else if (button.id == 2) {
            this.statusMessage = "The registration function has not yet been implemented.";
        }
    }

    /*
     * Exception decompiling
     */
    private String checkAccountOnServer(String username, String password) {
        return "Login successful";
    }

    private void saveCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE));){
            writer.write(username + "\n" + password);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCredentials() {
        File file = new File(CREDENTIALS_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file));){
                String username = reader.readLine();
                String password = reader.readLine();
                if (username != null && password != null) {
                    this.usernameField.setText(username);
                    this.passwordField.setText(password);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawBackground();
        this.drawDynamicBackground(partialTicks);
        FontManager.font40.drawString("Welcome", this.width / 2, this.height / 4 - 60, Color.WHITE.getRGB());
        this.usernameField.drawTextBox114514();
        this.passwordField.drawTextBox114514();
        FontManager.F22.drawString("UserName:", this.width / 2 - 100, this.height / 4 - 40, Color.WHITE.getRGB());
        FontManager.F22.drawString("Password:", this.width / 2 - 100, this.height / 4 + 0, Color.WHITE.getRGB());
        if (this.statusMessage != null) {
            FontManager.F22.drawString(this.statusMessage, this.width / 2 - 100, this.height / 4 + 120, new Color(255, 255, 255).getRGB());
        }
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        super.drawScreen1(mouseX, mouseY, partialTicks);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    private void drawBackground() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.backgroundTexture);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(0.0f, this.height);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(this.width, this.height);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(this.width, 0.0f);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glEnd();
    }

    private void drawDynamicBackground(float partialTicks) {
        int width = this.width;
        int height = this.height;
        GL11.glPushMatrix();
        GL11.glBegin(7);
        float time = (float)(Minecraft.getSystemTime() % 10000L) / 10000.0f;
        float red = 0.5f + 0.5f * (float)Math.sin((double)(time * 2.0f) * Math.PI);
        float green = 0.5f + 0.5f * (float)Math.sin((double)(time * 2.0f) * Math.PI + 1.0471975511965976);
        float blue = 0.5f + 0.5f * (float)Math.sin((double)(time * 2.0f) * Math.PI + 2.0943951023931953);
        GL11.glColor3f(red, green, blue);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glColor3f(green, blue, red);
        GL11.glVertex2f(width, 0.0f);
        GL11.glColor3f(blue, red, green);
        GL11.glVertex2f(width, height);
        GL11.glColor3f(red, green, blue);
        GL11.glVertex2f(0.0f, height);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public void updateScreen() {
        this.usernameField.updateCursorCounter();
        this.passwordField.updateCursorCounter();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException {
        super.mouseClicked(mouseX, mouseY, button);
        this.usernameField.mouseClicked(mouseX, mouseY, button);
        this.passwordField.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        this.usernameField.textboxKeyTyped(typedChar, keyCode);
        this.passwordField.textboxKeyTyped(typedChar, keyCode);
    }

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
}

