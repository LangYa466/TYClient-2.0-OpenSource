/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network;

public class ThreadQuickExitException
extends RuntimeException {
    public static final ThreadQuickExitException field_179886_a = new ThreadQuickExitException();
    public static final ThreadQuickExitException INSTANCE = new ThreadQuickExitException();

    protected ThreadQuickExitException() {
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        this.setStackTrace(new StackTraceElement[0]);
        return this;
    }
}

