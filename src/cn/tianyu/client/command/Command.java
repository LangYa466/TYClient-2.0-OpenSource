/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.command;

public abstract class Command {
    private final String[] key;

    public Command(String[] key) {
        this.key = key;
    }

    public abstract void run(String[] var1);

    public String[] getKey() {
        return this.key;
    }
}

