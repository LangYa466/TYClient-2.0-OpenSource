/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.processor;

import cn.tianyu.client.IRC.common.management.PacketManager;
import cn.tianyu.client.IRC.common.packet.IRCPacket;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;

public class IRCProtocol
implements Protocol<IRCPacket> {
    private final PacketManager packetManager = new PacketManager();
    private final Gson gson = new Gson();

    public byte[] encode(IRCPacket packet) {
        return this.gson.toJson(this.packetManager.writePacket(packet)).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public IRCPacket decode(ByteBuffer readBuffer, AioSession session) {
        int remaining = readBuffer.remaining();
        if (remaining < 4) {
            return null;
        }
        readBuffer.mark();
        int length = readBuffer.getInt();
        if (length > readBuffer.remaining()) {
            readBuffer.reset();
            return null;
        }
        byte[] b = new byte[length];
        readBuffer.get(b);
        readBuffer.mark();
        String text = new String(b);
        try {
            JsonObject object = JsonParser.parseString(text).getAsJsonObject();
            return this.packetManager.readPacket(object);
        }
        catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    public PacketManager getPacketManager() {
        return this.packetManager;
    }
}

