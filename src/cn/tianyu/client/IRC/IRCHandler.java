/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC;

public interface IRCHandler {
    public void onMessage(String var1, String var2);

    public void onDisconnected(String var1);

    public void onConnected();

    public String getInGameUsername();
}

