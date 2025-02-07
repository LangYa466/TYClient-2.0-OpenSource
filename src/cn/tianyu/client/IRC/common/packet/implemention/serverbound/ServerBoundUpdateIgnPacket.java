/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet.implemention.serverbound;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.annotations.ProtocolField;

public class ServerBoundUpdateIgnPacket
implements IRCPacket {
    @ProtocolField(value="n")
    private final String name;

    public ServerBoundUpdateIgnPacket(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

