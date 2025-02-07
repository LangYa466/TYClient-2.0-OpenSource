/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet.implemention.clientbound;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.annotations.ProtocolField;
import java.util.Map;

public class ClientBoundUpdateUserListPacket
implements IRCPacket {
    @ProtocolField(value="u")
    private final Map<String, String> userMap;

    public ClientBoundUpdateUserListPacket(Map<String, String> userMap) {
        this.userMap = userMap;
    }

    public Map<String, String> getUserMap() {
        return this.userMap;
    }
}

