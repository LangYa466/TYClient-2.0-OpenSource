/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.Event;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public final class EventClickBlock
implements Event {
    private final BlockPos clickedBlock;
    private final EnumFacing enumFacing;

    public EventClickBlock(BlockPos clickedBlock, EnumFacing enumFacing) {
        this.clickedBlock = clickedBlock;
        this.enumFacing = enumFacing;
    }

    public BlockPos getClickedBlock() {
        return this.clickedBlock;
    }

    public EnumFacing getEnumFacing() {
        return this.enumFacing;
    }
}

