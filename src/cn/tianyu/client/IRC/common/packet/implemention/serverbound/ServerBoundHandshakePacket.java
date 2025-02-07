/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet.implemention.serverbound;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.annotations.ProtocolField;

public class ServerBoundHandshakePacket
implements IRCPacket {
    @ProtocolField(value="u")
    private final String username;
    @ProtocolField(value="t")
    private final String token;

    public ServerBoundHandshakePacket(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return this.username;
    }

    public String getToken() {
        return this.token;
    }
}

