/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.management;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundConnectedPacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundDisconnectPacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundMessagePacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundUpdateUserListPacket;
import cn.tianyu.client.IRC.common.packet.implemention.serverbound.ServerBoundHandshakePacket;
import cn.tianyu.client.IRC.common.packet.implemention.serverbound.ServerBoundMessagePacket;
import cn.tianyu.client.IRC.common.packet.implemention.serverbound.ServerBoundUpdateIgnPacket;
import cn.tianyu.client.IRC.common.util.UnsafeReflect;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class PacketManager {
    private final Map<Integer, Class<? extends IRCPacket>> idToPacketMap = new HashMap<Integer, Class<? extends IRCPacket>>();
    private final Map<Class<? extends IRCPacket>, Integer> packetToIdMap = new HashMap<Class<? extends IRCPacket>, Integer>();
    private int id;

    public PacketManager() {
        this.register(ClientBoundDisconnectPacket.class, ClientBoundConnectedPacket.class, ClientBoundUpdateUserListPacket.class, ClientBoundMessagePacket.class);
        this.register(ServerBoundHandshakePacket.class, ServerBoundUpdateIgnPacket.class, ServerBoundMessagePacket.class);
    }

    @SafeVarargs
    private final void register(Class<? extends IRCPacket> ... classes) {
        for (Class<? extends IRCPacket> clazz : classes) {
            this.idToPacketMap.put(this.id, clazz);
            this.packetToIdMap.put(clazz, this.id);
            ++this.id;
        }
    }

    public IRCPacket readPacket(JsonObject object) {
        if (object.has("id") && object.has("cxt")) {
            int id = object.get("id").getAsInt();
            IRCPacket packet = this.create(id);
            packet.readPacket(object.get("cxt").getAsJsonObject());
            return packet;
        }
        throw new RuntimeException("Unknown packet");
    }

    public JsonObject writePacket(IRCPacket packet) {
        JsonObject jsonObject = new JsonObject();
        JsonObject packetJson = packet.writePacket();
        jsonObject.addProperty("id", this.packetToIdMap.get(packet.getClass()));
        jsonObject.add("cxt", packetJson);
        return jsonObject;
    }

    public IRCPacket create(int id) {
        if (!this.idToPacketMap.containsKey(id)) {
            throw new IllegalArgumentException("Unknown packet: " + id);
        }
        Class<? extends IRCPacket> clazz = this.idToPacketMap.get(id);
        return this.create(clazz);
    }

    public IRCPacket create(Class<? extends IRCPacket> clazz) {
        try {
            return (IRCPacket)UnsafeReflect.theUnsafe.allocateInstance(clazz);
        }
        catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}

