package org.kayteam.blockdatatagsapi.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kayteam.blockdatatagsapi.BlockDataTagsAPI;
import org.kayteam.blockdatatagsapi.events.BlockDataTagsCreateEvent;
import org.kayteam.blockdatatagsapi.util.JSONUtil;

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
        NBTItem nbtItem = new NBTItem(itemStack);
        if (!nbtItem.getKeys().isEmpty())
        {
            try
            {
                JSONParser jsonParser = new JSONParser();
                JSONObject dataTags = (JSONObject) jsonParser.parse(JSONUtil.getJSONString(nbtItem));
                BlockDataTagsCreateEvent blockDataTagsCreateEvent = new BlockDataTagsCreateEvent(player, block, itemStack, dataTags);
                blockDataTagsAPI.getServer().getPluginManager().callEvent(blockDataTagsCreateEvent);
                if (blockDataTagsCreateEvent.isCancelled())
                {
                    event.setCancelled(true);
                }
                else
                {
                    blockDataTagsAPI.getBlockDataTagsManager().addBlockDataTag(blockDataTagsCreateEvent.getId(), blockDataTagsCreateEvent.getDataTags());
                }
            }
            catch (ParseException ignored)
            {

            }
        }
    }
}