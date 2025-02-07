/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.combat;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import cn.tianyu.client.util.InventoryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

public class AutoGapple
extends Mod {
    private final Minecraft mc = Minecraft.getMinecraft();
    private int minHealHP = 12;
    int slot;
    private int oldSlot = -1;
    private int eatingTicks = 0;

    public AutoGapple() {
        super("AutoGapple", "\u81ea\u52a8\u5403\u91d1\u82f9\u679c", Category.combat);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void update() {
        if (this.eatingTicks == 0) {
            if (Minecraft.thePlayer.getHealth() < (float)this.minHealHP) {
                this.slot = this.getAppleFromInventory();
                if (this.slot == -1) return;
                this.slot -= 36;
                this.oldSlot = Minecraft.thePlayer.inventory.currentItem;
                Minecraft.thePlayer.inventory.currentItem = this.slot;
                Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
                Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(this.slot + 1 >= 9 ? 0 : this.slot + 1));
                Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(this.slot));
                this.eatingTicks = 40;
                return;
            }
        }
        if (this.eatingTicks <= 0) return;
        --this.eatingTicks;
        if (this.eatingTicks != 0) return;
        if (this.oldSlot == -1) return;
        this.mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(this.oldSlot));
    }

    private int getAppleFromInventory() {
        Item item;
        ItemStack stack;
        int i;
        for (i = 36; i < 45; ++i) {
            stack = Minecraft.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack == null || (item = stack.getItem()) == null || InventoryUtils.isItemEmpty(item) || item != Items.golden_apple) continue;
            return i;
        }
        for (i = 9; i < 36; ++i) {
            stack = Minecraft.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack == null || (item = stack.getItem()) == null || InventoryUtils.isItemEmpty(item) || item != Items.golden_apple) continue;
            this.mc.playerController.windowClick(Minecraft.thePlayer.openContainer.windowId, i, this.slot, 2, Minecraft.thePlayer);
        }
        return -1;
    }
}

