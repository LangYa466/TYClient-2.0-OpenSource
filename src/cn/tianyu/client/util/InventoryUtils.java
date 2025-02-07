/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class InventoryUtils {
    static Minecraft mc = Minecraft.getMinecraft();

    public static int getItemSlot(Item item) {
        for (int i = 0; i < 9; ++i) {
            ItemStack is = Minecraft.thePlayer.inventory.mainInventory[i];
            if (is == null || is.getItem() != item) continue;
            return i;
        }
        return -1;
    }

    public static int getBlockSlot(Block block) {
        for (int i = 0; i < 9; ++i) {
            ItemStack is = Minecraft.thePlayer.inventory.mainInventory[i];
            if (is == null || !(is.getItem() instanceof ItemBlock) || ((ItemBlock)is.getItem()).getBlock() != block) continue;
            return i;
        }
        return -1;
    }

    public static Item getHeldItem() {
        block3: {
            block2: {
                if (Minecraft.thePlayer == null) break block2;
                if (Minecraft.thePlayer.getCurrentEquippedItem() != null) break block3;
            }
            return null;
        }
        return Minecraft.thePlayer.getCurrentEquippedItem().getItem();
    }

    public static boolean isHoldingSword() {
        return InventoryUtils.getHeldItem() instanceof ItemSword;
    }

    public static void click(int slot, int mouseButton, boolean shiftClick) {
        InventoryUtils.mc.playerController.windowClick(Minecraft.thePlayer.inventoryContainer.windowId, slot, mouseButton, shiftClick ? 1 : 0, Minecraft.thePlayer);
    }

    public static void drop(int slot) {
        InventoryUtils.mc.playerController.windowClick(0, slot, 1, 4, Minecraft.thePlayer);
    }

    public static void swap(int slot, int hSlot) {
        InventoryUtils.mc.playerController.windowClick(Minecraft.thePlayer.inventoryContainer.windowId, slot, hSlot, 2, Minecraft.thePlayer);
    }

    public static float getSwordStrength(ItemStack stack) {
        if (stack.getItem() instanceof ItemSword) {
            ItemSword sword = (ItemSword)stack.getItem();
            float sharpness = (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25f;
            float fireAspect = (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) * 1.5f;
            return sword.getDamageVsEntity() + sharpness + fireAspect;
        }
        return 0.0f;
    }

    public static boolean isItemEmpty(Item item) {
        return item == null || Item.getIdFromItem(item) == 0;
    }
}

