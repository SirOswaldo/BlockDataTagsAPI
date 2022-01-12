package org.kayteam.blockdatatagsapi.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BlockDataTagsRemoveEvent extends Event implements Cancellable
{

    private static final HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private final Player player;
    private final Block block;

    public BlockDataTagsRemoveEvent(Player player, Block block)
    {
        this.player = player;
        this.block = block;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Block getBlock()
    {
        return block;
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