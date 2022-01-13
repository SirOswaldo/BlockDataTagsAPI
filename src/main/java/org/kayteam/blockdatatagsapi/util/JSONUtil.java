package org.kayteam.blockdatatagsapi.util;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public class JSONUtil
{

    public static String getJSONString(String[][] datas)
    {
        StringBuilder json = new StringBuilder("{");
        for (Object[] data:datas)
        {
            json.append("\"");
            json.append(data[0]);
            json.append("\"");
            json.append(":");
            json.append("\"");
            json.append(data[1]);
            json.append("\"");
        }
        json.append("}");
        return json.toString();
    }

    public static String getJSONString(Map<String, String> datas)
    {
        StringBuilder json = new StringBuilder("{");
        for (String key:datas.keySet())
        {
            json.append("\"");
            json.append(key);
            json.append("\"");
            json.append(":");
            json.append("\"");
            json.append(datas.get(key));
            json.append("\"");
        }
        json.append("}");
        return json.toString();
    }

    public static String getJSONString(NBTItem nbtItem)
    {
        StringBuilder json = new StringBuilder("{");
        for (String key:nbtItem.getKeys())
        {
            json.append("\"");
            json.append(key);
            json.append("\"");
            json.append(":");
            json.append("\"");
            json.append(nbtItem.getString(key));
            json.append("\"");
        }
        json.append("}");
        return json.toString();
    }

    public static void writeFile(String dir, String name, JSONObject jsonObject)
    {
        File folder = new File(dir);
        File file = new File(dir + File.separator + name);
        if (!folder.exists())
        {
            if (folder.mkdirs())
            {
                writeFile(file, jsonObject);
            }
        }
        else
        {
            writeFile(file, jsonObject);
        }
    }

    public static void writeFile(File file, JSONObject jsonObject)
    {
        try
        {
            if (!file.exists())
            {
                if (file.createNewFile())
                {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.close();
                }
                else
                {
                    System.out.println("Error saving block data tags!");
                }
            }
            else
            {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Error saving block data tags!");
        }
    }

    public static JSONObject readFile(String dir, String name)
    {
        JSONObject jsonObject = new JSONObject();
        File folder = new File(dir);
        File file = new File(dir + File.separator + name);
        if (folder.isDirectory())
        {
            jsonObject = readFile(file);
        }
        return jsonObject;
    }

    public static JSONObject readFile(File file)
    {
        JSONObject jsonObject = new JSONObject();
        if (file.exists())
        {
            JSONParser parser = new JSONParser();
            try
            {
                Reader reader = new FileReader(file);
                jsonObject = (JSONObject) parser.parse(reader);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

}