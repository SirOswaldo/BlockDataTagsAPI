package org.kayteam.blockdatatagsapi.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.json.simple.JSONObject;

public class PlayerInteractBlockDataTagsEvent extends Event implements Cancellable
{
    private static final HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private final Player player;
    private final Block block;
    private final Action action;
    private final String id;
    private final JSONObject jsonObject;
    private boolean cancelInteract = false;

    public PlayerInteractBlockDataTagsEvent(Player player, Block block, Action action, String id, JSONObject jsonObject)
    {
        this.player = player;
        this.block = block;
        this.action = action;
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

    public Action getAction()
    {
        return action;
    }

    public String getId()
    {
        return id;
    }

    public JSONObject getJsonObject()
    {
        return jsonObject;
    }

    public boolean isCancelInteract()
    {
        return cancelInteract;
    }

    public void setCancelInteract(boolean cancelInteract)
    {
        this.cancelInteract = cancelInteract;
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