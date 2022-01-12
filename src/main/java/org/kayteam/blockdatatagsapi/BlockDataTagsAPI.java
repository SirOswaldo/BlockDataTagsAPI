package org.kayteam.blockdatatagsapi;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.blockdatatagsapi.listeners.BlockBreakListener;
import org.kayteam.blockdatatagsapi.listeners.BlockPlaceListener;
import org.kayteam.blockdatatagsapi.listeners.PlayerInteractListener;
import org.kayteam.kayteamapi.BrandSender;

public final class BlockDataTagsAPI extends JavaPlugin
{
    private final BlockDataTagsManager blockDataTagsManager = new BlockDataTagsManager(this);

    @Override
    public void onEnable()
    {
        blockDataTagsManager.loadBlocksDataTags();
        registerListeners();
        BrandSender.sendBrandMessage(this, "&aEnabled");
    }

    @Override
    public void onDisable()
    {
        blockDataTagsManager.saveBlocksDataTags();
        BrandSender.sendBrandMessage(this, "&cDisabled");
    }

    private void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockPlaceListener(this), this);
        pluginManager.registerEvents(new BlockBreakListener(this), this);
        pluginManager.registerEvents(new PlayerInteractListener(this), this);
    }

    public BlockDataTagsManager getBlockDataTagsManager()
    {
        return blockDataTagsManager;
    }
}