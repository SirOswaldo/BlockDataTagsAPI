package org.kayteam.blockdatatagsapi.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.kayteam.blockdatatagsapi.BlockDataTagsAPI;
import org.kayteam.blockdatatagsapi.events.BlockDataTagsRemoveEvent;
import org.kayteam.blockdatatagsapi.util.BlockDataTagsUtil;

public class BlockBreakListener implements Listener
{
    private final BlockDataTagsAPI blockDataTagsAPI;

    public BlockBreakListener(BlockDataTagsAPI blockDataTagsAPI)
    {
        this.blockDataTagsAPI = blockDataTagsAPI;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        BlockDataTagsRemoveEvent blockDataTagsRemoveEvent = new BlockDataTagsRemoveEvent(player, block);
        blockDataTagsAPI.getServer().getPluginManager().callEvent(blockDataTagsRemoveEvent);
        if (blockDataTagsRemoveEvent.isCancelled())
        {
            event.setCancelled(true);
        }
        else
        {
            blockDataTagsAPI.getBlockDataTagsManager().removeBlockDataTags(BlockDataTagsUtil.getId(block));
        }
    }
}