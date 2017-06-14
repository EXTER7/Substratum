package exter.substratum.resgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import exter.substratum.block.BlockMetal;
import exter.substratum.block.SubstratumBlocks;

public class SubstratumResourceGenerator
{
  static private final String PATH_RESOURCES = String.join(File.separator, new String[] {"src","main","resources","assets","substratum"});
  static private final String PATH_BLOCKSTATES = String.join(File.separator, new String[] {PATH_RESOURCES,"blockstates"});
  
  private static void writeMetaBlockState(String output,BlockMetal.Variant[] variants) throws IOException
  {
    if(variants.length > 16)
    {
      throw new IllegalArgumentException("Metal block cannot have more than 16 variants.");
    }
    FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {PATH_BLOCKSTATES, output})));
    JsonWriter json = new JsonWriter(writer);
    json.beginObject();
    json.setIndent("  ");
    json.name("variants").beginObject();
    for(BlockMetal.Variant v:variants)
    {
      json.name("variant=" + v.getName()).beginObject();
      json.name("model").value("substratum:block_" + v.material.name);
      json.endObject();
    }
    json.endObject();
    json.endObject();
    json.close();
    writer.close();
  }
  
  public static void main(String[] args)
  {
    try
    {
      writeMetaBlockState("block_metal_1.json", SubstratumBlocks.BLOCK1_METALS);
      writeMetaBlockState("block_metal_2.json", SubstratumBlocks.BLOCK2_METALS);
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}