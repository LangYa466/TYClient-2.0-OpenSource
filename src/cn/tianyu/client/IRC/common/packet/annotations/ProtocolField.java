/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface ProtocolField {
    public String value();
}

