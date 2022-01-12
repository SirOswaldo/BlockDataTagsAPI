package org.kayteam.blockdatatagsapi.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.kayteam.blockdatatagsapi.util.BlockDataTagsUtil;

import java.util.HashMap;

public class BlockDataTagsCreateEvent extends Event implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private final Player player;
    private final Block block;
    private final ItemStack itemStack;
    private final String id;
    private final HashMap<String, Object> dataTags = new HashMap<>();

    public BlockDataTagsCreateEvent(Player player, Block block, ItemStack itemStack) {
        this.player = player;
        this.block = block;
        this.itemStack = itemStack;
        id = BlockDataTagsUtil.getId(block);
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getId() {
        return id;
    }

    public HashMap<String, Object> getDataTags() {
        return dataTags;
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