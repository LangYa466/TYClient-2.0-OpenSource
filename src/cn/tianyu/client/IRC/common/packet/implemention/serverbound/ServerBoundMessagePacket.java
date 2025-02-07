/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet.implemention.serverbound;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.annotations.ProtocolField;

public class ServerBoundMessagePacket
implements IRCPacket {
    @ProtocolField(value="m")
    private final String message;

    public ServerBoundMessagePacket(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

