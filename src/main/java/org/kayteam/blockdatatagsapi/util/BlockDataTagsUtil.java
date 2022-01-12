package org.kayteam.blockdatatagsapi.util;

import org.bukkit.block.Block;

public class BlockDataTagsUtil
{

    public static String getId(Block block)
    {
        return block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ();
    }

}