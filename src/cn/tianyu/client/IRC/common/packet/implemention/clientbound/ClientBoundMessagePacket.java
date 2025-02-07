/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet.implemention.clientbound;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.annotations.ProtocolField;

public class ClientBoundMessagePacket
implements IRCPacket {
    @ProtocolField(value="s")
    private final String sender;
    @ProtocolField(value="m")
    private final String message;

    public ClientBoundMessagePacket(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return this.sender;
    }

    public String getMessage() {
        return this.message;
    }
}

