/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.lwjgl.opengl.GL11;

public class WorldVertexBufferUploader {
    public void func_181679_a(WorldRenderer p_181679_1_) {
        if (p_181679_1_.getVertexCount() > 0) {
            VertexFormat vertexformat = p_181679_1_.getVertexFormat();
            int i = vertexformat.getNextOffset();
            ByteBuffer bytebuffer = p_181679_1_.getByteBuffer();
            List<VertexFormatElement> list = vertexformat.getElements();
            block12: for (int j = 0; j < list.size(); ++j) {
                VertexFormatElement vertexformatelement = list.get(j);
                VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.getUsage();
                int k = vertexformatelement.getType().getGlConstant();
                int l = vertexformatelement.getIndex();
                bytebuffer.position(vertexformat.func_181720_d(j));
                switch (vertexformatelement$enumusage) {
                    case POSITION: {
                        GL11.glVertexPointer(vertexformatelement.getElementCount(), k, i, bytebuffer);
                        GL11.glEnableClientState(32884);
                        continue block12;
                    }
                    case UV: {
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + l);
                        GL11.glTexCoordPointer(vertexformatelement.getElementCount(), k, i, bytebuffer);
                        GL11.glEnableClientState(32888);
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                        continue block12;
                    }
                    case COLOR: {
                        GL11.glColorPointer(vertexformatelement.getElementCount(), k, i, bytebuffer);
                        GL11.glEnableClientState(32886);
                        continue block12;
                    }
                    case NORMAL: {
                        GL11.glNormalPointer(k, i, bytebuffer);
                        GL11.glEnableClientState(32885);
                    }
                }
            }
            GL11.glDrawArrays(p_181679_1_.getDrawMode(), 0, p_181679_1_.getVertexCount());
            int j1 = list.size();
            block13: for (int i1 = 0; i1 < j1; ++i1) {
                VertexFormatElement vertexformatelement1 = list.get(i1);
                VertexFormatElement.EnumUsage vertexformatelement$enumusage1 = vertexformatelement1.getUsage();
                int k1 = vertexformatelement1.getIndex();
                switch (vertexformatelement$enumusage1) {
                    case POSITION: {
                        GL11.glDisableClientState(32884);
                        continue block13;
                    }
                    case UV: {
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + k1);
                        GL11.glDisableClientState(32888);
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                        continue block13;
                    }
                    case COLOR: {
                        GL11.glDisableClientState(32886);
                        GlStateManager.resetColor();
                        continue block13;
                    }
                    case NORMAL: {
                        GL11.glDisableClientState(32885);
                    }
                }
            }
        }
        p_181679_1_.reset();
    }
}

