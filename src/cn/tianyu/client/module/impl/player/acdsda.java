/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.module.impl.player;

import cn.tianyu.client.module.Category;
import cn.tianyu.client.module.Mod;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class acdsda
extends Mod {
    private final Minecraft mc = Minecraft.getMinecraft();

    public acdsda() {
        super("\u7bb1\u5b50\u5c0f\u5077", "\u81ea\u52a8\u62ff\u8d70\u7bb1\u5b50", Category.player);
    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }

    @Override
    public void update() {
        block7: {
            block6: {
                if (Minecraft.thePlayer == null) break block6;
                if (Minecraft.theWorld != null) break block7;
            }
            return;
        }
        if (this.mc.currentScreen instanceof GuiChest) {
            GuiChest guiChest = (GuiChest)this.mc.currentScreen;
            List<Slot> chestSlots = guiChest.inventorySlots.inventorySlots;
            for (int i = 0; i < chestSlots.size(); ++i) {
                Slot slot = chestSlots.get(i);
                ItemStack stackInSlot = slot.getStack();
                if (stackInSlot == null || stackInSlot.stackSize <= 0 || !this.canTakeItem()) continue;
                for (int j = 9; j < 36; ++j) {
                    Slot playerSlot = Minecraft.thePlayer.inventoryContainer.getSlot(j);
                    if (playerSlot.getStack() != null && playerSlot.getStack().stackSize != 0) continue;
                    this.mc.playerController.windowClick(guiChest.inventorySlots.windowId, i, 0, 1, Minecraft.thePlayer);
                    return;
                }
            }
        }
    }

    private boolean canTakeItem() {
        List<Slot> playerSlots = Minecraft.thePlayer.inventoryContainer.inventorySlots;
        for (Slot slot : playerSlots) {
            if (slot.getStack() != null && slot.getStack().stackSize > 0) continue;
            return true;
        }
        return false;
    }
}

