/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Set;
import net.minecraft.client.renderer.chunk.SetVisibility;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IntegerCache;

public class VisGraph {
    private static final int field_178616_a = (int)Math.pow(16.0, 0.0);
    private static final int field_178614_b = (int)Math.pow(16.0, 1.0);
    private static final int field_178615_c = (int)Math.pow(16.0, 2.0);
    private final BitSet field_178612_d = new BitSet(4096);
    private static final int[] field_178613_e = new int[1352];
    private int field_178611_f = 4096;

    public void func_178606_a(BlockPos pos) {
        this.field_178612_d.set(VisGraph.getIndex(pos), true);
        --this.field_178611_f;
    }

    private static int getIndex(BlockPos pos) {
        return VisGraph.getIndex(pos.getX() & 0xF, pos.getY() & 0xF, pos.getZ() & 0xF);
    }

    private static int getIndex(int x, int y, int z) {
        return x << 0 | y << 8 | z << 4;
    }

    public SetVisibility computeVisibility() {
        SetVisibility setvisibility = new SetVisibility();
        if (4096 - this.field_178611_f < 256) {
            setvisibility.setAllVisible(true);
        } else if (this.field_178611_f == 0) {
            setvisibility.setAllVisible(false);
        } else {
            for (int i : field_178613_e) {
                if (this.field_178612_d.get(i)) continue;
                setvisibility.setManyVisible(this.func_178604_a(i));
            }
        }
        return setvisibility;
    }

    public Set<EnumFacing> func_178609_b(BlockPos pos) {
        return this.func_178604_a(VisGraph.getIndex(pos));
    }

    private Set<EnumFacing> func_178604_a(int p_178604_1_) {
        EnumSet<EnumFacing> set = EnumSet.noneOf(EnumFacing.class);
        LinkedList<Integer> queue = Lists.newLinkedList();
        queue.add(IntegerCache.func_181756_a(p_178604_1_));
        this.field_178612_d.set(p_178604_1_, true);
        while (!queue.isEmpty()) {
            int i = (Integer)queue.poll();
            this.func_178610_a(i, set);
            for (EnumFacing enumfacing : EnumFacing.values()) {
                int j = this.func_178603_a(i, enumfacing);
                if (j < 0 || this.field_178612_d.get(j)) continue;
                this.field_178612_d.set(j, true);
                queue.add(IntegerCache.func_181756_a(j));
            }
        }
        return set;
    }

    private void func_178610_a(int p_178610_1_, Set<EnumFacing> p_178610_2_) {
        int i = p_178610_1_ >> 0 & 0xF;
        if (i == 0) {
            p_178610_2_.add(EnumFacing.WEST);
        } else if (i == 15) {
            p_178610_2_.add(EnumFacing.EAST);
        }
        int j = p_178610_1_ >> 8 & 0xF;
        if (j == 0) {
            p_178610_2_.add(EnumFacing.DOWN);
        } else if (j == 15) {
            p_178610_2_.add(EnumFacing.UP);
        }
        int k = p_178610_1_ >> 4 & 0xF;
        if (k == 0) {
            p_178610_2_.add(EnumFacing.NORTH);
        } else if (k == 15) {
            p_178610_2_.add(EnumFacing.SOUTH);
        }
    }

    private int func_178603_a(int p_178603_1_, EnumFacing p_178603_2_) {
        switch (p_178603_2_) {
            case DOWN: {
                if ((p_178603_1_ >> 8 & 0xF) == 0) {
                    return -1;
                }
                return p_178603_1_ - field_178615_c;
            }
            case UP: {
                if ((p_178603_1_ >> 8 & 0xF) == 15) {
                    return -1;
                }
                return p_178603_1_ + field_178615_c;
            }
            case NORTH: {
                if ((p_178603_1_ >> 4 & 0xF) == 0) {
                    return -1;
                }
                return p_178603_1_ - field_178614_b;
            }
            case SOUTH: {
                if ((p_178603_1_ >> 4 & 0xF) == 15) {
                    return -1;
                }
                return p_178603_1_ + field_178614_b;
            }
            case WEST: {
                if ((p_178603_1_ >> 0 & 0xF) == 0) {
                    return -1;
                }
                return p_178603_1_ - field_178616_a;
            }
            case EAST: {
                if ((p_178603_1_ >> 0 & 0xF) == 15) {
                    return -1;
                }
                return p_178603_1_ + field_178616_a;
            }
        }
        return -1;
    }

    static {
        boolean i = false;
        int j = 15;
        int k = 0;
        for (int l = 0; l < 16; ++l) {
            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j1 = 0; j1 < 16; ++j1) {
                    if (l != 0 && l != 15 && i1 != 0 && i1 != 15 && j1 != 0 && j1 != 15) continue;
                    VisGraph.field_178613_e[k++] = VisGraph.getIndex(l, i1, j1);
                }
            }
        }
    }
}

