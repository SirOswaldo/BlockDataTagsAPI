package org.kayteam.blockdatatagsapi.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.json.simple.JSONObject;

public class BlockDataTagsRemoveEvent extends Event implements Cancellable
{
    private static final HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private final Player player;
    private final Block block;
    private final String id;
    private final JSONObject jsonObject;
    private boolean breakBlock = true;

    public BlockDataTagsRemoveEvent(Player player, Block block, String id, JSONObject jsonObject)
    {
        this.player = player;
        this.block = block;
        this.id = id;
        this.jsonObject = jsonObject;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Block getBlock()
    {
        return block;
    }

    public String getId()
    {
        return id;
    }

    public JSONObject getJsonObject()
    {
        return jsonObject;
    }

    public boolean isBreakBlock()
    {
        return breakBlock;
    }

    public void setBreakBlock(boolean breakBlock)
    {
        this.breakBlock = breakBlock;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlerList;
    }

    public static HandlerList getHandlerList()
    {
        return handlerList;
    }

    @Override
    public boolean isCancelled()
    {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel)
    {
        this.cancel = cancel;
    }
}