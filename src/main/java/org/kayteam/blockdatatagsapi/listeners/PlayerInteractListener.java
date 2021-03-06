package org.kayteam.blockdatatagsapi.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.json.simple.JSONObject;
import org.kayteam.blockdatatagsapi.BlockDataTagsAPI;
import org.kayteam.blockdatatagsapi.BlockDataTagsManager;
import org.kayteam.blockdatatagsapi.events.PlayerInteractBlockDataTagsEvent;
import org.kayteam.blockdatatagsapi.util.BlockDataTagsUtil;

public class PlayerInteractListener implements Listener
{
    private final BlockDataTagsAPI blockDataTagsAPI;

    public PlayerInteractListener(BlockDataTagsAPI blockDataTagsAPI)
    {
        this.blockDataTagsAPI = blockDataTagsAPI;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Action action = event.getAction();
        switch (action)
        {
            case LEFT_CLICK_BLOCK:
            case RIGHT_CLICK_BLOCK:
            {
                Player player = event.getPlayer();
                Block block = event.getClickedBlock();
                String id = BlockDataTagsUtil.getId(block);
                BlockDataTagsManager blockDataTagsManager = blockDataTagsAPI.getBlockDataTagsManager();
                if (blockDataTagsManager.containBlockDataTags(id))
                {
                    JSONObject jsonObject = blockDataTagsManager.getBlockDataTags(id);
                    PlayerInteractBlockDataTagsEvent playerInteractBlockDataTagsEvent = new PlayerInteractBlockDataTagsEvent(player, block, action, id, jsonObject);
                    blockDataTagsAPI.getServer().getPluginManager().callEvent(playerInteractBlockDataTagsEvent);
                    if (playerInteractBlockDataTagsEvent.isCancelled())
                    {
                        event.setCancelled(true);
                    }
                    else
                    {
                        if (playerInteractBlockDataTagsEvent.isCancelInteract())
                        {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}