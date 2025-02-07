/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;

public class EventText
implements Event {
    public String string;

    public EventText(String string) {
        this.string = string;
    }

    public String getText() {
        return this.string;
    }

    public void setText(String pass) {
        this.string = pass;
    }
}

