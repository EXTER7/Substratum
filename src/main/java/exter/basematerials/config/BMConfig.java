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
    public final int min_clusters;
    public final int max_clusters;

    public WorldgenConfig(Configuration config, String ore, int min_y, int max_y, int min_clusters, int max_clusters)
    {
      this.enabled = config.getBoolean("enabled", "worldgen." + ore, true, "Enable/disable worldgen for this ore.");
      this.min_y = config.getInt("minY", "worldgen." + ore, min_y, 0, 256, "Lowest Y level the ore is generated.");
      this.max_y = config.getInt("maxY", "worldgen." + ore, max_y, 0, 256, "Highest Y level the ore is generated.");
      this.min_clusters = config.getInt("min_clusters", "worldgen." + ore, min_clusters, 1, 100, "Minimum amount of ore clusters per chunk.");
      this.max_clusters = config.getInt("max_clusters", "worldgen." + ore, max_clusters, 1, 100, "Maximum amount pf ore clusters per chunk.");
    }
  }
  
  public static WorldgenConfig worldgen_copper;
  public static WorldgenConfig worldgen_tin;
  public static WorldgenConfig worldgen_zinc;
  public static WorldgenConfig worldgen_nickel;
  public static WorldgenConfig worldgen_silver;
  public static WorldgenConfig worldgen_lead;
  public static WorldgenConfig worldgen_platinum;

  public static WorldgenConfig worldgen_sulfur;
  public static WorldgenConfig worldgen_niter;

  public static boolean recipe_bronze_enable;
  public static boolean recipe_brass_enable;
  public static boolean recipe_invar_enable;
  public static boolean recipe_electrum_enable;
  public static boolean recipe_cupronickel_enable;

  public static boolean recipe_steel_enable;
  public static boolean recipe_signalum_enable;
  public static boolean recipe_lumium_enable;
  public static boolean recipe_enderium_enable;

  public static boolean recipe_gunpowder_enable;

  public static Map<EnumMaterial,Boolean> recipe_buckets_enable = new EnumMap<EnumMaterial,Boolean>(EnumMaterial.class);
  public static Map<EnumMaterial,Boolean> recipe_gears_enable = new EnumMap<EnumMaterial,Boolean>(EnumMaterial.class);
  public static Map<EnumMaterial,Boolean> recipe_plates_enable = new EnumMap<EnumMaterial,Boolean>(EnumMaterial.class);
  public static Map<EnumMaterial,Boolean> recipe_dusts_enable = new EnumMap<EnumMaterial,Boolean>(EnumMaterial.class);

  public static int misc_mortar_uses;
  
  static public void load(Configuration config)
  {
    worldgen_copper = new WorldgenConfig(config, "copper", 20, 80, 10, 20);
    worldgen_tin = new WorldgenConfig(config, "tin", 20, 52, 6, 12);
    worldgen_zinc = new WorldgenConfig(config, "zinc", 8, 48, 5, 7);
    worldgen_nickel = new WorldgenConfig(config, "nickel", 8, 36, 3, 6);
    worldgen_silver = new WorldgenConfig(config, "silver", 8, 30, 3, 4);
    worldgen_lead = new WorldgenConfig(config, "lead", 8, 48, 5, 6);
    worldgen_platinum = new WorldgenConfig(config, "platinum", 2, 12, 0, 1);
    
    worldgen_sulfur = new WorldgenConfig(config, "sulfur", 5, 123, 20, 15);
    worldgen_niter = new WorldgenConfig(config, "niter", 5, 123, 15, 10);

    recipe_bronze_enable = config.getBoolean("blend", "recipes.bronze", true, "Enable/disable bronze dust blending recipe.");
    recipe_brass_enable = config.getBoolean("blend", "recipes.brass", true, "Enable/disable brass dust blending recipe.");
    recipe_invar_enable = config.getBoolean("blend", "recipes.invar", true, "Enable/disable invar dust blending recipe.");
    recipe_electrum_enable = config.getBoolean("blend", "recipes.electrum", true, "Enable/disable electrum dust blending recipe.");
    recipe_cupronickel_enable = config.getBoolean("blend", "recipes.cupronickel", true, "Enable/disable cupronickel dust blending recipe.");

    recipe_steel_enable = config.getBoolean("blend", "recipes.steel", false, "Enable/disable steel dust blending recipe.");
    recipe_signalum_enable = config.getBoolean("blend", "recipes.signalum", false, "Enable/disable signalum dust blending recipe.");
    recipe_lumium_enable = config.getBoolean("blend", "recipes.lumium", false, "Enable/disable lumium dust blending recipe.");
    recipe_enderium_enable = config.getBoolean("blend", "recipes.enderium", false, "Enable/disable enderium dust blending recipe.");

    recipe_gunpowder_enable = config.getBoolean("blend", "recipes.gunpowder", true, "Enable/disable gunpowder dust blending recipe.");

    misc_mortar_uses = config.getInt("mortar_uses", "misc", 20, 0, 1000, "How many uses the mortar has unti it breaks. Setting this to 0 disables the item.");

    for(EnumMaterial mat:EnumMaterialItem.DUST.materials)
    {
      if(mat != EnumMaterial.SULFUR && mat != EnumMaterial.NITER)
      {
        String name = mat.suffix.toLowerCase();
        recipe_dusts_enable.put(mat,config.getBoolean("dust", "recipes." + name, true, "Enable/disable " + name + " dust mortar recipe."));
      }
    }

    for(EnumMaterial mat:EnumMaterialItem.BUCKET_LIQUID.materials)
    {
      String name = mat.suffix.toLowerCase();
      recipe_buckets_enable.put(mat,config.getBoolean("bucket", "recipes." + name, true, "Enable/disable " + name + " bucket recipe."));
    }

    for(EnumMaterial mat:EnumMaterialItem.GEAR.materials)
    {
      String name = mat.suffix.toLowerCase();
      recipe_gears_enable.put(mat,config.getBoolean("gear", "recipes." + name, true, "Enable/disable " + name + " gear recipe."));
    }
    
    for(EnumMaterial mat:EnumMaterialItem.PLATE.materials)
    {
      String name = mat.suffix.toLowerCase();
      recipe_plates_enable.put(mat,config.getBoolean("plate", "recipes." + name, true, "Enable/disable " + name + " plate recipe."));
    }
  }
}
