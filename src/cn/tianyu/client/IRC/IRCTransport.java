/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC;

import cn.tianyu.client.IRC.IRCHandler;
import cn.tianyu.client.IRC.common.packet.IRCPacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundConnectedPacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundDisconnectPacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundMessagePacket;
import cn.tianyu.client.IRC.common.packet.implemention.clientbound.ClientBoundUpdateUserListPacket;
import cn.tianyu.client.IRC.common.packet.implemention.serverbound.ServerBoundHandshakePacket;
import cn.tianyu.client.IRC.common.packet.implemention.serverbound.ServerBoundMessagePacket;
import cn.tianyu.client.IRC.common.packet.implemention.serverbound.ServerBoundUpdateIgnPacket;
import cn.tianyu.client.IRC.common.processor.IRCProtocol;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.transport.AioQuickClient;
import org.smartboot.socket.transport.AioSession;

public class IRCTransport {
    private final IRCProtocol protocol = new IRCProtocol();
    private final AioSession session;
    private IRCHandler handler;
    private final Map<String, String> userToIgnMap = new ConcurrentHashMap<String, String>();
    private final Map<String, String> ignToUserMap = new ConcurrentHashMap<String, String>();
    private ScheduledExecutorService scheduler;

    public IRCTransport(String host, int port, IRCHandler handler) throws IOException {
        this.handler = handler;
        MessageProcessor<IRCPacket> processor = (session, msg) -> {
            if (msg instanceof ClientBoundDisconnectPacket) {
                handler.onDisconnected(((ClientBoundDisconnectPacket)msg).getReason());
            }
            if (msg instanceof ClientBoundConnectedPacket) {
                handler.onConnected();
                if (this.scheduler == null || this.scheduler.isShutdown()) {
                    this.scheduler = Executors.newScheduledThreadPool(1);
                    Runnable task = this::sendInGameUsername;
                    this.scheduler.scheduleAtFixedRate(task, 5L, 5L, TimeUnit.SECONDS);
                }
            }
            if (msg instanceof ClientBoundUpdateUserListPacket) {
                this.userToIgnMap.clear();
                this.userToIgnMap.putAll(((ClientBoundUpdateUserListPacket)msg).getUserMap());
                this.ignToUserMap.clear();
                this.userToIgnMap.forEach((user, ign) -> this.ignToUserMap.put((String)ign, (String)user));
            }
            if (msg instanceof ClientBoundMessagePacket) {
                handler.onMessage(((ClientBoundMessagePacket)msg).getSender(), ((ClientBoundMessagePacket)msg).getMessage());
            }
        };
        AioQuickClient client = new AioQuickClient(host, port, this.protocol, processor);
        this.session = client.start();
    }

    public void sendPacket(IRCPacket packet) {
        try {
            byte[] data = this.protocol.encode(packet);
            this.session.writeBuffer().writeInt(data.length);
            this.session.writeBuffer().write(data);
            this.session.writeBuffer().flush();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send packet", e);
        }
    }

    public boolean isUser(String name) {
        return this.ignToUserMap.containsKey(name);
    }

    public String getName(String ign) {
        return this.ignToUserMap.get(ign);
    }

    public String getIgn(String name) {
        return this.userToIgnMap.get(name);
    }

    public void sendChat(String message) {
        this.sendPacket(new ServerBoundMessagePacket(message));
    }

    public void sendInGameUsername(String username) {
        this.sendPacket(new ServerBoundUpdateIgnPacket(username));
    }

    public void sendInGameUsername() {
        this.sendInGameUsername(this.handler.getInGameUsername());
    }

    public void connect(String username, String token) {
        this.sendPacket(new ServerBoundHandshakePacket(username, token));
    }

    public void setHandler(IRCHandler handler) {
        this.handler = handler;
    }
}

