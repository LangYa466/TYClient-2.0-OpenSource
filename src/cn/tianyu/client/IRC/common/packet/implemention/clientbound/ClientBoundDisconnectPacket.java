/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet.implemention.clientbound;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.annotations.ProtocolField;

public class ClientBoundDisconnectPacket
implements IRCPacket {
    @ProtocolField(value="r")
    private final String reason;

    public ClientBoundDisconnectPacket(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }
}

