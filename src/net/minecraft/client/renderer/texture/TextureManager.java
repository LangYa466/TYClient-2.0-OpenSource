package net.minecraft.client.renderer.texture;

import org.apache.logging.log4j.LogManager;
import java.util.Iterator;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import java.io.IOException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.resources.IResourceManager;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.resources.IResourceManagerReloadListener;

public class TextureManager implements ITickable, IResourceManagerReloadListener
{
    private static final Logger logger;
    private final Map<ResourceLocation, ITextureObject> mapTextureObjects;
    private final List<ITickable> listTickables;
    private final Map<String, Integer> mapTextureCounters;
    private final IResourceManager theResourceManager;

    public TextureManager(final IResourceManager resourceManager) {
        this.mapTextureObjects = Maps.newHashMap();
        this.listTickables = Lists.newArrayList();
        this.mapTextureCounters = Maps.newHashMap();
        this.theResourceManager = resourceManager;
    }

    public void bindTexture(final ResourceLocation resource) {
        ITextureObject itextureobject = this.mapTextureObjects.get(resource);
        if (itextureobject == null) {
            itextureobject = new SimpleTexture(resource);
            this.loadTexture(resource, itextureobject);
        }
        TextureUtil.bindTexture(itextureobject.getGlTextureId());
    }

    public boolean loadTickableTexture(final ResourceLocation textureLocation, final ITickableTextureObject textureObj) {
        if (this.loadTexture(textureLocation, textureObj)) {
            this.listTickables.add(textureObj);
            return true;
        }
        return false;
    }

    public boolean loadTexture(final ResourceLocation textureLocation, ITextureObject textureObj) {
        boolean flag = true;
        try {
            textureObj.loadTexture(this.theResourceManager);
        }
        catch (final IOException ioexception) {
            TextureManager.logger.warn("Failed to load texture: " + textureLocation, ioexception);
            textureObj = TextureUtil.missingTexture;
            this.mapTextureObjects.put(textureLocation, textureObj);
            flag = false;
        }
        catch (final Throwable throwable) {
            final ITextureObject textureObjf = textureObj;
            final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Registering texture");
            final CrashReportCategory crashreportcategory = crashreport.makeCategory("Resource location being registered");
            crashreportcategory.addCrashSection("Resource location", textureLocation);
            crashreportcategory.addCrashSectionCallable("Texture object class", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return textureObjf.getClass().getName();
                }
            });
            throw new ReportedException(crashreport);
        }
        this.mapTextureObjects.put(textureLocation, textureObj);
        return flag;
    }

    public ITextureObject getTexture(final ResourceLocation textureLocation) {
        return this.mapTextureObjects.get(textureLocation);
    }

    public ResourceLocation getDynamicTextureLocation(final String name, final DynamicTexture texture) {
        Integer integer = this.mapTextureCounters.get(name);
        if (integer == null) {
            integer = 1;
        }
        else {
            ++integer;
        }
        this.mapTextureCounters.put(name, integer);
        final ResourceLocation resourcelocation = new ResourceLocation(String.format("dynamic/%s_%d", name, integer));
        this.loadTexture(resourcelocation, texture);
        return resourcelocation;
    }

    @Override
    public void tick() {
        for (final ITickable itickable : this.listTickables) {
            itickable.tick();
        }
    }

    public void deleteTexture(final ResourceLocation textureLocation) {
        final ITextureObject itextureobject = this.getTexture(textureLocation);
        if (itextureobject != null) {
            TextureUtil.deleteTexture(itextureobject.getGlTextureId());
        }
    }

    @Override
    public void onResourceManagerReload(final IResourceManager resourceManager) {
        for (final Map.Entry<ResourceLocation, ITextureObject> entry : this.mapTextureObjects.entrySet()) {
            this.loadTexture(entry.getKey(), entry.getValue());
        }
    }

    static {
        logger = LogManager.getLogger();
    }
}
