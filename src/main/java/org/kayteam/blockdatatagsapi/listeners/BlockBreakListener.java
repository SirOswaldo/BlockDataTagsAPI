package org.kayteam.blockdatatagsapi.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
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
            event.setDropItems(false);
            JSONObject jsonObject = blockDataTagsAPI.getBlockDataTagsManager().getBlockDataTags(BlockDataTagsUtil.getId(block));
            for (ItemStack itemStack:block.getDrops())
            {
                NBTItem nbtItem = new NBTItem(itemStack);
                for (Object key:jsonObject.keySet())
                {
                    String keyString = (String) key;
                    nbtItem.setString(keyString, (String) jsonObject.get(key));
                }
                block.getLocation().getWorld().dropItem(block.getLocation(), nbtItem.getItem());
            }
            blockDataTagsAPI.getBlockDataTagsManager().removeBlockDataTags(BlockDataTagsUtil.getId(block));
        }
    }

}