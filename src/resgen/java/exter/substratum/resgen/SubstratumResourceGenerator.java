package exter.substratum.resgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import exter.substratum.block.BlockMetal;
import exter.substratum.block.BlockMetalSlab;
import exter.substratum.block.SubstratumBlocks;
import exter.substratum.material.EnumMaterial;

public class SubstratumResourceGenerator
{
  static class StairState
  {
    static public final StairState[] STATES =
    {
      new StairState("east",  "bottom", "inner_left",  0,   270),
      new StairState("east",  "bottom", "inner_right", 0,   0),
      new StairState("east",  "bottom", "outer_left",  0,   270),
      new StairState("east",  "bottom", "outer_right", 0,   0),
      new StairState("east",  "bottom", "straight",    0,   0),
      new StairState("east",  "top",    "inner_left",  180, 90),
      new StairState("east",  "top",    "inner_right", 180, 0),
      new StairState("east",  "top",    "outer_left",  180, 90),
      new StairState("east",  "top",    "outer_right", 180, 0),
      new StairState("east",  "top",    "straight",    180, 0),
      new StairState("north", "bottom", "inner_left",  0,   180),
      new StairState("north", "bottom", "inner_right", 0,   270),
      new StairState("north", "bottom", "outer_left",  0,   180),
      new StairState("north", "bottom", "outer_right", 0,   270),
      new StairState("north", "bottom", "straight",    0,   270),
      new StairState("north", "top",    "inner_left",  180, 0),
      new StairState("north", "top",    "inner_right", 180, 270),
      new StairState("north", "top",    "outer_left",  180, 0),
      new StairState("north", "top",    "outer_right", 180, 270),
      new StairState("north", "top",    "straight",    180, 270),
      new StairState("south", "bottom", "inner_left",  0,   0),
      new StairState("south", "bottom", "inner_right", 0,   90),
      new StairState("south", "bottom", "outer_left",  0,   0),
      new StairState("south", "bottom", "outer_right", 0,   90),
      new StairState("south", "bottom", "straight",    0,   90),
      new StairState("south", "top",    "inner_left",  180, 180),
      new StairState("south", "top",    "inner_right", 180, 90),
      new StairState("south", "top",    "outer_left",  180, 180),
      new StairState("south", "top",    "outer_right", 180, 90),
      new StairState("south", "top",    "straight",    180, 90),
      new StairState("west",  "bottom", "inner_left",  0,   90),
      new StairState("west",  "bottom", "inner_right", 0,   180),
      new StairState("west",  "bottom", "outer_left",  0,   90),
      new StairState("west",  "bottom", "outer_right", 0,   180),
      new StairState("west",  "bottom", "straight",    0,   180),
      new StairState("west",  "top",    "inner_left",  180, 270),
      new StairState("west",  "top",    "inner_right", 180, 180),
      new StairState("west",  "top",    "outer_left",  180, 270),
      new StairState("west",  "top",    "outer_right", 180, 180),
      new StairState("west",  "top",    "straight",    180, 180)
    };
    
    private final int x;
    private final int y;
    private final String facing;
    private final String half;
    private final String shape;
    
    private StairState(String facing,String half,String shape,int x,int y)
    {
      this.facing = facing;
      this.half = half;
      this.shape = shape;
      this.x = x;
      this.y = y;
    }
    
    public void writeVariant(JsonWriter json,EnumMaterial mat) throws IOException
    {
      json.name(String.format("facing=%s,half=%s,shape=%s", facing, half,shape)).beginObject();
      String prefix = shape.replace("_left", "").replace("_right", "");
      json.name("model").value(String.format("substratum:stairs_%s_%s", prefix, mat.name));
      if(x != 0 || y != 0)
      {
        json.name("uvlock").value(true);
      }
      if(x != 0)
      {
        json.name("x").value(x);
      }
      if(y != 0)
      {
        json.name("y").value(y);
      }
      json.endObject();
    }
    
    static void writeState(String output,EnumMaterial mat) throws IOException
    {

      FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {PATH_BLOCKSTATES, output})));
      JsonWriter json = new JsonWriter(writer);
      json.beginObject();
      json.setIndent("  ");
      json.name("variants").beginObject();
      for(StairState state:STATES)
      {
        state.writeVariant(json,mat);
      }
      json.endObject();
      json.endObject();
      json.close();
      writer.close();
    }
  }
  
  static private final String PATH_RESOURCES = String.join(File.separator, new String[] {"src","main","resources","assets","substratum"});
  static private final String PATH_BLOCKSTATES = String.join(File.separator, new String[] {PATH_RESOURCES,"blockstates"});
  
  private static void writeMetalBlockState(String output,BlockMetal.Variant[] variants) throws IOException
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

  private static void writeMetalSlabState(String output,BlockMetalSlab.Variant[] variants,boolean double_slab) throws IOException
  {
    String top_prefix = double_slab?"substratum:slab_double_":"substratum:slab_top_";
    String bottom_prefix = double_slab?"substratum:slab_double_":"substratum:slab_bottom_";
    
    if(variants.length > 8)
    {
      throw new IllegalArgumentException("Metal block cannot have more than 8 variants.");
    }
    FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {PATH_BLOCKSTATES, output})));
    JsonWriter json = new JsonWriter(writer);
    json.beginObject();
    json.setIndent("  ");
    json.name("variants").beginObject();
    for(BlockMetalSlab.Variant v:variants)
    {
      json.name("half=top,variant=" + v.getName()).beginObject();
      json.name("model").value(top_prefix + v.material.name);
      json.endObject();
      json.name("half=bottom,variant=" + v.getName()).beginObject();
      json.name("model").value(bottom_prefix + v.material.name);
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
      writeMetalBlockState("block_metal_1.json", SubstratumBlocks.BLOCK1_METALS);
      writeMetalBlockState("block_metal_2.json", SubstratumBlocks.BLOCK2_METALS);

      writeMetalSlabState("slab_1.json", SubstratumBlocks.SLAB1_METALS, false);
      writeMetalSlabState("slab_2.json", SubstratumBlocks.SLAB2_METALS, false);
      writeMetalSlabState("slab_3.json", SubstratumBlocks.SLAB3_METALS, false);
      writeMetalSlabState("slab_double_1.json", SubstratumBlocks.SLAB1_METALS, true);
      writeMetalSlabState("slab_double_2.json", SubstratumBlocks.SLAB2_METALS, true);
      writeMetalSlabState("slab_double_3.json", SubstratumBlocks.SLAB3_METALS, true);
      
      for(BlockMetal.Variant v:SubstratumBlocks.BLOCK1_METALS)
      {
        EnumMaterial mat = v.material;
        StairState.writeState(String.format("stairs_%s.json", mat.name), mat);
      }
      for(BlockMetal.Variant v:SubstratumBlocks.BLOCK2_METALS)
      {
        EnumMaterial mat = v.material;
        StairState.writeState(String.format("stairs_%s.json", mat.name), mat);
      }
      StairState.writeState("stairs_iron.json", EnumMaterial.IRON);
      StairState.writeState("stairs_iron.json", EnumMaterial.GOLD);
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}