/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.util;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeReflect {
    public static final Unsafe theUnsafe;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            theUnsafe = (Unsafe)f.get(null);
        }
        catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}

