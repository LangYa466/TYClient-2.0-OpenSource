/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.culling;

public class ClippingHelper {
    public float[][] frustum = new float[6][4];
    public float[] projectionMatrix = new float[16];
    public float[] modelviewMatrix = new float[16];
    public float[] clippingMatrix = new float[16];

    private double dot(float[] p_178624_1_, double p_178624_2_, double p_178624_4_, double p_178624_6_) {
        return (double)p_178624_1_[0] * p_178624_2_ + (double)p_178624_1_[1] * p_178624_4_ + (double)p_178624_1_[2] * p_178624_6_ + (double)p_178624_1_[3];
    }

    public boolean isBoxInFrustum(double p_78553_1_, double p_78553_3_, double p_78553_5_, double p_78553_7_, double p_78553_9_, double p_78553_11_) {
        for (int i = 0; i < 6; ++i) {
            float[] afloat = this.frustum[i];
            if (!(this.dot(afloat, p_78553_1_, p_78553_3_, p_78553_5_) <= 0.0) || !(this.dot(afloat, p_78553_7_, p_78553_3_, p_78553_5_) <= 0.0) || !(this.dot(afloat, p_78553_1_, p_78553_9_, p_78553_5_) <= 0.0) || !(this.dot(afloat, p_78553_7_, p_78553_9_, p_78553_5_) <= 0.0) || !(this.dot(afloat, p_78553_1_, p_78553_3_, p_78553_11_) <= 0.0) || !(this.dot(afloat, p_78553_7_, p_78553_3_, p_78553_11_) <= 0.0) || !(this.dot(afloat, p_78553_1_, p_78553_9_, p_78553_11_) <= 0.0) || !(this.dot(afloat, p_78553_7_, p_78553_9_, p_78553_11_) <= 0.0)) continue;
            return false;
        }
        return true;
    }
}

