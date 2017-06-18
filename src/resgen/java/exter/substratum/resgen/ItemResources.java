package exter.substratum.resgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import exter.substratum.material.EnumDyePowderColor;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialEquipment;
import exter.substratum.material.EnumMaterialItem;

public class ItemResources
{
  private static void writeItemJson(String output, String parent, String texture) throws IOException
  {
    FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] { ResourcePaths.MODELS, "item", output + ".json" })));
    JsonWriter json = new JsonWriter(writer);
    json.beginObject();
    json.setIndent("  ");
    json.name("parent").value(parent);
    json.name("textures").beginObject();
    json.name("layer0").value(texture);
    json.endObject();
    json.endObject();
    json.close();
    writer.close();
  }

  public static void generate()
  {
    try
    {
      for(EnumMaterialItem item : EnumMaterialItem.values())
      {
        for(EnumMaterial mat : item.materials)
        {
          if(mat != EnumMaterial.NULL)
          {
            String model = String.format("%s_%s", item.name, mat.name);
            writeItemJson(model, "minecraft:item/generated", "substratum:items/" + model);
          }
        }
      }
      
      for(EnumMaterialEquipment equipment:EnumMaterialEquipment.values())
      {
        if(equipment.material != EnumMaterial.NULL)
        {
          String model = "helmet_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/generated", "substratum:items/" + model);
          model = "chestplate_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/generated", "substratum:items/" + model);
          model = "leggings_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/generated", "substratum:items/" + model);
          model = "boots_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/generated", "substratum:items/" + model);
          model = "pickaxe_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/handheld", "substratum:items/" + model);
          model = "axe_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/handheld", "substratum:items/" + model);
          model = "sword_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/handheld", "substratum:items/" + model);
          model = "shovel_" + equipment.material.name;
          writeItemJson(model, "iminecraft:tem/handheld", "substratum:items/" + model);
          model = "hoe_" + equipment.material.name;
          writeItemJson(model, "minecraft:item/handheld", "substratum:items/" + model);
        }
      }

      for(EnumDyePowderColor color : EnumDyePowderColor.values())
      {
        String model = "dye_" + color.name;
        writeItemJson(model, "minecraft:item/generated", "substratum:items/" + model);
        model = "dye_small_" + color.name;
        writeItemJson(model, "minecraft:item/generated", "substratum:items/" + model);
      }
      
      writeItemJson("mortar.json", "minecraft:item/generated", "substratum:items/mortar");

    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
