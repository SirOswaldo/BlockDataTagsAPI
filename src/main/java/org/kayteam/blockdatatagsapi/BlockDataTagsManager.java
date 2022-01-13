package org.kayteam.blockdatatagsapi;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kayteam.blockdatatagsapi.util.JSONUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BlockDataTagsManager
{

    private final BlockDataTagsAPI blockDataTagsAPI;
    private final HashMap<String, JSONObject> blocks = new HashMap<>();


    public BlockDataTagsManager(BlockDataTagsAPI blockDataTagsAPI)
    {
        this.blockDataTagsAPI = blockDataTagsAPI;
    }

    public void loadBlocksDataTags()
    {
        blocks.clear();
        File folder = new File(blockDataTagsAPI.getDataFolder() + File.separator + "blockDataTags");
        if (folder.isDirectory())
        {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null)
            {
                for (File file:files)
                {
                    blocks.put(file.getName().replaceAll(".json", ""), JSONUtil.readFile(file));
                }
            }
        }
    }

    public void saveBlocksDataTags()
    {
        for (String id:blocks.keySet())
        {
            saveBlockDataTags(id);
        }
    }

    public void saveBlockDataTags(String id)
    {
        JSONObject block = blocks.get(id);
        JSONUtil.writeFile(blockDataTagsAPI.getDataFolder() + File.separator + "blockDataTags", id + ".json", block);
    }

    public void removeBlockDataTags(String id)
    {
        File file = new File(blockDataTagsAPI.getDataFolder() + File.separator + "blockDataTags", id + ".json");
        if (file.exists())
        {
            if (file.delete())
            {
                blockDataTagsAPI.getLogger().info("Deleted: " + id);
            }
        }
        blocks.remove(id);
    }

    public void addBlockDataTags(String id, String[][] dataTags)
    {
        JSONParser parser = new JSONParser();
        try
        {
            JSONObject jsonObject = (JSONObject) parser.parse(JSONUtil.getJSONString(dataTags));
            addBlockDataTag(id, jsonObject);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public void addBlockDataTags(String id, Map<String, String> dataTags)
    {
        JSONParser parser = new JSONParser();
        try
        {
            JSONObject jsonObject = (JSONObject) parser.parse(JSONUtil.getJSONString(dataTags));
            addBlockDataTag(id, jsonObject);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public void addBlockDataTag(String id, JSONObject jsonObject)
    {
        blocks.put(id, jsonObject);
        saveBlockDataTags(id);
    }

    public JSONObject getBlockDataTags(String id)
    {
        if (containBlockDataTags(id))
        {
            return blocks.get(id);
        }
        else
        {
            return new JSONObject();
        }
    }

    public boolean containBlockDataTags(String id)
    {
        return blocks.containsKey(id);
    }

    public void updateBlockDataTags(String id, JSONObject jsonObject)
    {
        blocks.put(id, jsonObject);
        saveBlockDataTags(id);
    }

}