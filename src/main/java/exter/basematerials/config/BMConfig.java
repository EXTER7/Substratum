package exter.basematerials.config;

import java.util.EnumMap;
import java.util.Map;

import exter.basematerials.material.EnumMaterial;
import exter.basematerials.material.EnumMaterialItem;
import net.minecraftforge.common.config.Configuration;


public class BMConfig
{
  static public class WorldgenConfig
  {
    public final boolean enabled;
    public final int min_y;
    public final int max_y;
    public final int frequency;

    public WorldgenConfig(Configuration config, String ore, int min_y,int max_y,int frequency)
    {
      this.enabled = config.getBoolean("enabled", "worldgen." + ore, true, "Enable/disable worldgen for this ore.");
      this.min_y = config.getInt("minY", "worldgen." + ore, min_y, 0, 256, "Lowest Y level the ore is generated.");
      this.max_y = config.getInt("maxY", "worldgen." + ore, max_y, 0, 256, "Highest Y level the ore is generated.");
      this.frequency = config.getInt("frequency", "worldgen." + ore, frequency, 1, 100, "Amount ore clusters per chunk.");
    }
  }
  
  public static WorldgenConfig worldgen_copper;
  public static WorldgenConfig worldgen_tin;
  public static WorldgenConfig worldgen_zinc;
  public static WorldgenConfig worldgen_nickel;
  public static WorldgenConfig worldgen_silver;
  public static WorldgenConfig worldgen_lead;
  public static WorldgenConfig worldgen_platinum;

  public static boolean recipe_bronze_enable;
  public static boolean recipe_brass_enable;
  public static boolean recipe_invar_enable;
  public static boolean recipe_electrum_enable;
  public static boolean recipe_cupronickel_enable;

  public static boolean recipe_steel_enable;
  public static boolean recipe_signalum_enable;
  public static boolean recipe_lumium_enable;
  public static boolean recipe_enderium_enable;
  public static boolean recipe_enderpearldust_enable;
  public static boolean recipe_coaldust_enable;

  public static Map<EnumMaterial,Boolean> recipe_buckets_enable = new EnumMap<EnumMaterial,Boolean>(EnumMaterial.class);
  public static Map<EnumMaterial,Boolean> recipe_gears_enable = new EnumMap<EnumMaterial,Boolean>(EnumMaterial.class);
  public static Map<EnumMaterial,Boolean> recipe_plates_enable = new EnumMap<EnumMaterial,Boolean>(EnumMaterial.class);

  static public void load(Configuration config)
  {
    worldgen_copper = new WorldgenConfig(config, "copper", 16, 80, 12);
    worldgen_tin = new WorldgenConfig(config, "tin", 16, 52, 8);
    worldgen_zinc = new WorldgenConfig(config, "zinc", 8, 48, 6);
    worldgen_nickel = new WorldgenConfig(config, "nickel", 8, 36, 5);
    worldgen_silver = new WorldgenConfig(config, "silver", 8, 30, 3);
    worldgen_lead = new WorldgenConfig(config, "lead", 8, 48, 5);
    worldgen_platinum = new WorldgenConfig(config, "platinum", 2, 12, 1);
    
    recipe_bronze_enable = config.getBoolean("dust", "recipes.bronze", true, "");
    recipe_brass_enable = config.getBoolean("dust", "recipes.brass", true, "");
    recipe_invar_enable = config.getBoolean("dust", "recipes.invar", true, "");
    recipe_electrum_enable = config.getBoolean("dust", "recipes.electrum", true, "");
    recipe_cupronickel_enable = config.getBoolean("dust", "recipes.cupronickel", true, "");

    recipe_steel_enable = config.getBoolean("dust", "recipes.steel", false, null);
    recipe_signalum_enable = config.getBoolean("dust", "recipes.signalum", false, "");
    recipe_lumium_enable = config.getBoolean("dust", "recipes.lumium", false, null);
    recipe_enderium_enable = config.getBoolean("dust", "recipes.enderium", false, "");
    recipe_coaldust_enable = config.getBoolean("dust", "recipes.coal", false, "");
    recipe_enderpearldust_enable = config.getBoolean("dust", "recipes.enderpearl", false, "");

    for(EnumMaterial mat:EnumMaterialItem.BUCKET_LIQUID.materials)
    {
      recipe_buckets_enable.put(mat,config.getBoolean("bucket", "recipes." + mat.suffix.toLowerCase(), true, ""));
    }

    for(EnumMaterial mat:EnumMaterialItem.GEAR.materials)
    {
      recipe_gears_enable.put(mat,config.getBoolean("gear", "recipes." + mat.suffix.toLowerCase(), true, ""));
    }
    for(EnumMaterial mat:EnumMaterialItem.PLATE.materials)
    {
      recipe_plates_enable.put(mat,config.getBoolean("plate", "recipes." + mat.suffix.toLowerCase(), true, ""));
    }
  }
}
