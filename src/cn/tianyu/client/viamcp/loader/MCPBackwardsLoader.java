package cn.tianyu.client.viamcp.loader;

import com.viaversion.viabackwards.api.ViaBackwardsPlatform;
import cn.tianyu.client.viamcp.ViaMCP;

import java.io.File;
import java.util.logging.Logger;

public class MCPBackwardsLoader implements ViaBackwardsPlatform
{
    private final File file;

    public MCPBackwardsLoader(final File file)
    {
        this.init(this.file = new File(file, "ViaBackwards"));
    }

    @Override
    public Logger getLogger()
    {
        return cn.tianyu.client.viamcp.ViaMCP.getInstance().getjLogger();
    }

    @Override
    public void disable() { }

    @Override
    public boolean isOutdated()
    {
        return false;
    }

    @Override
    public File getDataFolder()
    {
        return new File(this.file, "config.yml");
    }
}
