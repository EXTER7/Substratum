package exter.substratum.resgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.stream.JsonWriter;

import exter.substratum.block.BlockDustOre;
import exter.substratum.block.BlockMetal;
import exter.substratum.block.BlockMetalSlab;
import exter.substratum.block.BlockOre;
import exter.substratum.block.IBlockVariants;
import exter.substratum.block.SubstratumBlocks;
import exter.substratum.material.EnumMaterial;

public class BlockResources
{

  private static class JsonBlockModel
  {
    private Map<String,String> textures;
    
    private String parent;
    
    public JsonBlockModel(String parent)
    {
      this.parent = parent;
      this.textures = new HashMap<>();
    }
    
    public void setTexture(String side,String texture)
    {
      textures.put(side, texture);
    }
    
    public void writeToJson(String output) throws IOException
    {
      FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {ResourcePaths.MODELS, "block", output + ".json"})));
      JsonWriter json = new JsonWriter(writer);
      json.beginObject();
      json.setIndent("  ");
      json.name("parent").value(parent);
      json.name("textures").beginObject();
      for(Map.Entry<String, String> tex:textures.entrySet())
      {
        json.name(tex.getKey()).value(tex.getValue());
      }
      json.endObject();
      json.endObject();
      json.close();
      writer.close();

      writer = new FileWriter(new File(String.join(File.separator, new String[] {ResourcePaths.MODELS, "item", output + ".json"})));
      json = new JsonWriter(writer);
      json.beginObject();
      json.setIndent("  ");
      json.name("parent").value("substratum:block/" + output);
      json.endObject();
      json.close();
      writer.close();
    
    }
  }

  private static class StairState
  {
    static public enum Shape
    {
      STRAIGHT("straight", "straight"),
      INNER_LEFT("inner_left", "inner"),
      INNER_RIGHT("inner_right", "inner"),
      OUTER_LEFT("outer_left", "outer"),
      OUTER_RIGHT("outer_right", "outer");
      
      final String variant_name;
      final String model_name;

      Shape(String variant_name, String model_name)
      {
        this.variant_name = variant_name;
        this.model_name = model_name;
      }
    }
    
    static public final StairState[] STATES =
    {
      new StairState("east",  "bottom", Shape.INNER_LEFT,  0,   270),
      new StairState("east",  "bottom", Shape.INNER_RIGHT, 0,   0),
      new StairState("east",  "bottom", Shape.OUTER_LEFT,  0,   270),
      new StairState("east",  "bottom", Shape.OUTER_RIGHT, 0,   0),
      new StairState("east",  "bottom", Shape.STRAIGHT,    0,   0),
      new StairState("east",  "top",    Shape.INNER_LEFT,  180, 90),
      new StairState("east",  "top",    Shape.INNER_RIGHT, 180, 0),
      new StairState("east",  "top",    Shape.OUTER_LEFT,  180, 90),
      new StairState("east",  "top",    Shape.OUTER_RIGHT, 180, 0),
      new StairState("east",  "top",    Shape.STRAIGHT,    180, 0),
      new StairState("north", "bottom", Shape.INNER_LEFT,  0,   180),
      new StairState("north", "bottom", Shape.INNER_RIGHT, 0,   270),
      new StairState("north", "bottom", Shape.OUTER_LEFT,  0,   180),
      new StairState("north", "bottom", Shape.OUTER_RIGHT, 0,   270),
      new StairState("north", "bottom", Shape.STRAIGHT,    0,   270),
      new StairState("north", "top",    Shape.INNER_LEFT,  180, 0),
      new StairState("north", "top",    Shape.INNER_RIGHT, 180, 270),
      new StairState("north", "top",    Shape.OUTER_LEFT,  180, 0),
      new StairState("north", "top",    Shape.OUTER_RIGHT, 180, 270),
      new StairState("north", "top",    Shape.STRAIGHT,    180, 270),
      new StairState("south", "bottom", Shape.INNER_LEFT,  0,   0),
      new StairState("south", "bottom", Shape.INNER_RIGHT, 0,   90),
      new StairState("south", "bottom", Shape.OUTER_LEFT,  0,   0),
      new StairState("south", "bottom", Shape.OUTER_RIGHT, 0,   90),
      new StairState("south", "bottom", Shape.STRAIGHT,    0,   90),
      new StairState("south", "top",    Shape.INNER_LEFT,  180, 180),
      new StairState("south", "top",    Shape.INNER_RIGHT, 180, 90),
      new StairState("south", "top",    Shape.OUTER_LEFT,  180, 180),
      new StairState("south", "top",    Shape.OUTER_RIGHT, 180, 90),
      new StairState("south", "top",    Shape.STRAIGHT,    180, 90),
      new StairState("west",  "bottom", Shape.INNER_LEFT,  0,   90),
      new StairState("west",  "bottom", Shape.INNER_RIGHT, 0,   180),
      new StairState("west",  "bottom", Shape.OUTER_LEFT,  0,   90),
      new StairState("west",  "bottom", Shape.OUTER_RIGHT, 0,   180),
      new StairState("west",  "bottom", Shape.STRAIGHT,    0,   180),
      new StairState("west",  "top",    Shape.INNER_LEFT,  180, 270),
      new StairState("west",  "top",    Shape.INNER_RIGHT, 180, 180),
      new StairState("west",  "top",    Shape.OUTER_LEFT,  180, 270),
      new StairState("west",  "top",    Shape.OUTER_RIGHT, 180, 180),
      new StairState("west",  "top",    Shape.STRAIGHT,    180, 180)
    };
    
    private final int x;
    private final int y;
    private final String facing;
    private final String half;
    private final Shape shape;
    
    private StairState(String facing,String half,Shape shape,int x,int y)
    {
      this.facing = facing;
      this.half = half;
      this.shape = shape;
      this.x = x;
      this.y = y;
    }
    
    public void writeVariant(JsonWriter json,EnumMaterial mat) throws IOException
    {
      json.name(String.format("facing=%s,half=%s,shape=%s", facing, half, shape.variant_name)).beginObject();
      json.name("model").value(String.format("substratum:stairs_%s_%s", shape.model_name, mat.name));
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
    
    static void writeToJson(String output,EnumMaterial mat) throws IOException
    {

      FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {ResourcePaths.BLOCKSTATES, output + ".json"})));
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
      
      String block_tex = blockTexture(mat);

      JsonBlockModel block_model = new JsonBlockModel("minecraft:block/stairs");
      block_model.setTexture("bottom", block_tex);
      block_model.setTexture("top", block_tex);
      block_model.setTexture("side", block_tex);
      block_model.writeToJson(String.format("stairs_straight_%s",mat.name));

      block_model = new JsonBlockModel("minecraft:block/inner_stairs");
      block_model.setTexture("bottom", block_tex);
      block_model.setTexture("top", block_tex);
      block_model.setTexture("side", block_tex);
      block_model.writeToJson(String.format("stairs_inner_%s",mat.name));

      block_model = new JsonBlockModel("minecraft:block/outer_stairs");
      block_model.setTexture("bottom", block_tex);
      block_model.setTexture("top", block_tex);
      block_model.setTexture("side", block_tex);
      block_model.writeToJson(String.format("stairs_outer_%s",mat.name));
    }
  }
  
  
  private static String blockTexture(EnumMaterial mat)
  {
    switch(mat)
    {
      case IRON:
        return "minecraft:blocks/iron_block";
      case GOLD:
        return "minecraft:blocks/gold_block";
      default:
        return "substratum:blocks/block_" + mat.name; 
    }
  }
  
  private static void writeVariantBlockState(String output,String prefix,IBlockVariants.IMaterialProperty[] variants) throws IOException
  {
    if(variants.length > 16)
    {
      throw new IllegalArgumentException("Metal block cannot have more than 16 variants.");
    }
    FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {ResourcePaths.BLOCKSTATES, output + ".json"})));
    JsonWriter json = new JsonWriter(writer);
    json.beginObject();
    json.setIndent("  ");
    json.name("variants").beginObject();
    for(IBlockVariants.IMaterialProperty v:variants)
    {
      String model = String.format("%s_%s", prefix,v.getMaterial().name);
      json.name("variant=" + v.getName()).beginObject();
      json.name("model").value("substratum:" + model);
      json.endObject();
      JsonBlockModel block_model = new JsonBlockModel("minecraft:block/cube_all");
      block_model.setTexture("all", "substratum:blocks/" + model);
      block_model.writeToJson(model);
    }
    json.endObject();
    json.endObject();
    json.close();
    writer.close();
  }

  private static void writeMetalSlabState(String output,BlockMetalSlab.Variant[] variants,boolean double_slab) throws IOException
  {
    
    if(variants.length > 8)
    {
      throw new IllegalArgumentException("Metal block cannot have more than 8 variants.");
    }
    FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {ResourcePaths.BLOCKSTATES, output + ".json"})));
    JsonWriter json = new JsonWriter(writer);
    json.beginObject();
    json.setIndent("  ");
    json.name("variants").beginObject();
    for(BlockMetalSlab.Variant v:variants)
    {
      String block_tex = blockTexture(v.getMaterial());
      if(double_slab)
      {
        String model = "slab_double_" + v.material.name;
        json.name("half=top,variant=" + v.getName()).beginObject();
        json.name("model").value("substratum:" + model);
        json.endObject();
        json.name("half=bottom,variant=" + v.getName()).beginObject();
        json.name("model").value("substratum:" + model);
        json.endObject();

        JsonBlockModel block_model = new JsonBlockModel("block/cube_column");
        block_model.setTexture("side", "substratum:blocks/slab_" + v.getName());
        block_model.setTexture("end", block_tex);
        block_model.writeToJson(model);
      } else
      {
        String model_top = "slab_top_" + v.material.name;
        String model_bottom = "slab_bottom_" + v.material.name;
        json.name("half=top,variant=" + v.getName()).beginObject();
        json.name("model").value("substratum:" + model_top);
        json.endObject();
        json.name("half=bottom,variant=" + v.getName()).beginObject();
        json.name("model").value("substratum:" + model_bottom);
        json.endObject();

        JsonBlockModel block_model = new JsonBlockModel("minecraft:block/upper_slab");
        block_model.setTexture("side", "substratum:blocks/slab_" + v.getName());
        block_model.setTexture("top", block_tex);
        block_model.setTexture("bottom", block_tex);
        block_model.writeToJson(model_top);
        block_model = new JsonBlockModel("minecraft:block/half_slab");
        block_model.setTexture("side", "substratum:blocks/slab_" + v.getName());
        block_model.setTexture("top", block_tex);
        block_model.setTexture("bottom", block_tex);
        block_model.writeToJson(model_bottom);
      }
    }
    json.endObject();
    json.endObject();
    json.close();
    writer.close();
  }

  public static void generate()
  {
    try
    {
      writeVariantBlockState("ore", "ore", BlockOre.EnumVariant.values());
      writeVariantBlockState("ore_dust", "ore", BlockDustOre.EnumVariant.values());

      
      writeVariantBlockState("block_metal_1", "block", SubstratumBlocks.BLOCK1_METALS);
      writeVariantBlockState("block_metal_2", "block", SubstratumBlocks.BLOCK2_METALS);

      writeMetalSlabState("slab_1", SubstratumBlocks.SLAB1_METALS, false);
      writeMetalSlabState("slab_2", SubstratumBlocks.SLAB2_METALS, false);
      writeMetalSlabState("slab_3", SubstratumBlocks.SLAB3_METALS, false);
      writeMetalSlabState("slab_double_1", SubstratumBlocks.SLAB1_METALS, true);
      writeMetalSlabState("slab_double_2", SubstratumBlocks.SLAB2_METALS, true);
      writeMetalSlabState("slab_double_3", SubstratumBlocks.SLAB3_METALS, true);
      
      for(BlockMetal.Variant v:SubstratumBlocks.BLOCK1_METALS)
      {
        EnumMaterial mat = v.material;
        StairState.writeToJson(String.format("stairs_%s", mat.name), mat);
      }
      for(BlockMetal.Variant v:SubstratumBlocks.BLOCK2_METALS)
      {
        EnumMaterial mat = v.material;
        StairState.writeToJson(String.format("stairs_%s", mat.name), mat);
      }
      StairState.writeToJson("stairs_iron", EnumMaterial.IRON);
      StairState.writeToJson("stairs_iron", EnumMaterial.GOLD);
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
