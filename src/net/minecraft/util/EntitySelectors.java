/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public final class EntitySelectors {
    public static final Predicate<Entity> selectAnything = new Predicate<Entity>(){

        @Override
        public boolean apply(Entity p_apply_1_) {
            return p_apply_1_.isEntityAlive();
        }
    };
    public static final Predicate<Entity> IS_STANDALONE = new Predicate<Entity>(){

        @Override
        public boolean apply(Entity p_apply_1_) {
            return p_apply_1_.isEntityAlive() && p_apply_1_.riddenByEntity == null && p_apply_1_.ridingEntity == null;
        }
    };
    public static final Predicate<Entity> selectInventories = new Predicate<Entity>(){

        @Override
        public boolean apply(Entity p_apply_1_) {
            return p_apply_1_ instanceof IInventory && p_apply_1_.isEntityAlive();
        }
    };
    public static final Predicate<Entity> NOT_SPECTATING = new Predicate<Entity>(){

        @Override
        public boolean apply(Entity p_apply_1_) {
            return !(p_apply_1_ instanceof EntityPlayer) || !((EntityPlayer)p_apply_1_).isSpectator();
        }
    };

    public static class ArmoredMob
    implements Predicate<Entity> {
        private final ItemStack armor;

        public ArmoredMob(ItemStack armor) {
            this.armor = armor;
        }

        @Override
        public boolean apply(Entity p_apply_1_) {
            if (!p_apply_1_.isEntityAlive()) {
                return false;
            }
            if (!(p_apply_1_ instanceof EntityLivingBase)) {
                return false;
            }
            EntityLivingBase entitylivingbase = (EntityLivingBase)p_apply_1_;
            return entitylivingbase.getEquipmentInSlot(EntityLiving.getArmorPosition(this.armor)) == null && (entitylivingbase instanceof EntityLiving ? ((EntityLiving)entitylivingbase).canPickUpLoot() : entitylivingbase instanceof EntityArmorStand || entitylivingbase instanceof EntityPlayer);
        }
    }
}

