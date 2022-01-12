package org.kayteam.blockdatatagsapi.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.kayteam.blockdatatagsapi.BlockDataTagsAPI;
import org.kayteam.blockdatatagsapi.events.BlockDataTagsCreateEvent;

public class BlockPlaceListener implements Listener
{

    private final BlockDataTagsAPI blockDataTagsAPI;

    public BlockPlaceListener(BlockDataTagsAPI blockDataTagsAPI)
    {
        this.blockDataTagsAPI = blockDataTagsAPI;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemStack = event.getItemInHand();
        BlockDataTagsCreateEvent blockDataTagsCreateEvent = new BlockDataTagsCreateEvent(player, block, itemStack);
        blockDataTagsAPI.getServer().getPluginManager().callEvent(blockDataTagsCreateEvent);
        if (blockDataTagsCreateEvent.isCancelled())
        {
            event.setCancelled(true);
        }
        else
        {
            blockDataTagsAPI.getBlockDataTagsManager().addBlockDataTags(blockDataTagsCreateEvent.getId(), blockDataTagsCreateEvent.getDataTags());
        }
    }

}