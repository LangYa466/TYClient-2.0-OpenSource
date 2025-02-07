/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class EventPacket
extends CancellableEvent {
    private final EventState eventType;
    public Packet packet;
    private final INetHandler netHandler;

    public EventPacket(EventState eventType, Packet packet, INetHandler netHandler) {
        this.eventType = eventType;
        this.packet = packet;
        this.netHandler = netHandler;
    }

    public EventState getEventType() {
        return this.eventType;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public INetHandler getNetHandler() {
        return this.netHandler;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public static enum EventState {
        SEND,
        RECEIVE;

    }
}

