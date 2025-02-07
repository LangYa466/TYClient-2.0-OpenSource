/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.event.impl.events;

import cn.tianyu.client.event.impl.CancellableEvent;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public final class EventBlockCollision
extends CancellableEvent {
    private final Block block;
    private final BlockPos blockPos;
    private AxisAlignedBB boundingBox;

    public EventBlockCollision(Block block, BlockPos blockPos, AxisAlignedBB boundingBox) {
        this.block = block;
        this.blockPos = blockPos;
        this.boundingBox = boundingBox;
    }

    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(AxisAlignedBB boundingBox) {
        this.boundingBox = boundingBox;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public Block getBlock() {
        return this.block;
    }
}

