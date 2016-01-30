package exter.substratum.config;

import java.util.EnumMap;
import java.util.Map;

import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraftforge.common.config.Configuration;


public class SubstratumConfig
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
  
  static public class MaterialRecipeConfig
  {
    public final boolean dust_bucket;
    public final boolean gear_crafting;
    public final boolean plate_crafting;
    public final boolean dust_from_ingot;
    public final boolean rod_crafting;
    
    public final boolean ingot_from_plate;
    public final boolean ingots_from_gear;
    public final boolean ingot_from_dust;
    public final boolean dust_from_rod;
    
    static private boolean hasIngot(EnumMaterial material)
    {
      return EnumMaterialItem.INGOT.materials.contains(material) || material == EnumMaterial.IRON || material == EnumMaterial.GOLD;
    }
    
    public MaterialRecipeConfig(Configuration config,EnumMaterial material)
    {
      String name = material.suffix.toLowerCase();
      String category = "recipes." + name;
      if(EnumMaterialItem.DUST.materials.contains(material) && material != EnumMaterial.SULFUR && material != EnumMaterial.NITER)
      {
        String property = hasIngot(material) ? "dust_from_ingot" : "dust_from_item";
        config.renameProperty(category, "dust", property);
        dust_from_ingot = config.getBoolean(property, category, true, "Enable/disable " + name + " dust with mortar crafting recipe.");
      } else
      {
        dust_from_ingot = false;
      }

      if(EnumMaterialItem.BUCKET_DUST.materials.contains(material))
      {
        config.renameProperty(category,"bucket","dust_bucket");
        dust_bucket = config.getBoolean("dust_bucket", category, true, "Enable/disable " + name + " dust bucket recipes.");
      } else
      {
        dust_bucket = false;
      }

      if(hasIngot(material))
      {
        ingot_from_dust = config.getBoolean("ingot_from_dust", "recipes." + name, true, "Enable/disable " + name + " dust to ingot smelting recipe.");
      } else
      {
        ingot_from_dust = false;
      }
      
      if(EnumMaterialItem.PLATE.materials.contains(material))
      {
        config.renameProperty(category,"plate","plate_crafting");
        plate_crafting = config.getBoolean("plate_crafting", "recipes." + name, true, "Enable/disable " + name + " plate crafting recipe.");
        ingot_from_plate = config.getBoolean("ingot_from_plate", "recipes." + name, true, "Enable/disable " + name + " plate to ingot smelting recipe.");
      } else
      {
        plate_crafting = false;
        ingot_from_plate = false;
      }
      
      if(EnumMaterialItem.GEAR.materials.contains(material))
      {
        config.renameProperty(category,"gear","gear_crafting");
        gear_crafting = config.getBoolean("gear_crafting", "recipes." + name, true, "Enable/disable " + name + " gear crafting recipe.");
        ingots_from_gear = config.getBoolean("ingots_from_gear", "recipes." + name, true, "Enable/disable " + name + " gear to ingot smelting recipe.");
      } else
      {
        gear_crafting = false;
        ingots_from_gear = false;
      }
      
      if(EnumMaterialItem.ROD.materials.contains(material))
      {
        rod_crafting = config.getBoolean("rod_crafting", "recipes." + name, true, "Enable/disable " + name + " rod crafting recipe.");
        dust_from_rod = config.getBoolean("dust_from_rod", "recipes." + name, true, "Enable/disable " + name + " rod to dusts mortar crafting recipe.");
      } else
      {
        rod_crafting = false;
        dust_from_rod = false;
      }
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

  public static boolean blend_bronze_enable;
  public static boolean blend_brass_enable;
  public static boolean blend_invar_enable;
  public static boolean blend_electrum_enable;
  public static boolean blend_cupronickel_enable;

  public static boolean blend_steel_enable;
  public static boolean blend_signalum_enable;
  public static boolean blend_lumium_enable;
  public static boolean blend_enderium_enable;

  public static boolean blend_gunpowder_enable;

  public static boolean cheaper_plate_recipes;
  public static boolean cheaper_rod_recipes;
  public static boolean cheaper_gear_recipes;
  
  public static Map<EnumMaterial,MaterialRecipeConfig> material_recipes = new EnumMap<EnumMaterial,MaterialRecipeConfig>(EnumMaterial.class);

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
    
    worldgen_sulfur = new WorldgenConfig(config, "sulfur", 5, 123, 15, 20);
    worldgen_niter = new WorldgenConfig(config, "niter", 5, 123, 10, 15);

    blend_bronze_enable = config.getBoolean("blend", "recipes.bronze", true, "Enable/disable bronze dust blending recipe.");
    blend_brass_enable = config.getBoolean("blend", "recipes.brass", true, "Enable/disable brass dust blending recipe.");
    blend_invar_enable = config.getBoolean("blend", "recipes.invar", true, "Enable/disable invar dust blending recipe.");
    blend_electrum_enable = config.getBoolean("blend", "recipes.electrum", true, "Enable/disable electrum dust blending recipe.");
    blend_cupronickel_enable = config.getBoolean("blend", "recipes.cupronickel", true, "Enable/disable cupronickel dust blending recipe.");

    blend_steel_enable = config.getBoolean("blend", "recipes.steel", false, "Enable/disable steel dust blending recipe.");
    blend_signalum_enable = config.getBoolean("blend", "recipes.signalum", false, "Enable/disable signalum dust blending recipe.");
    blend_lumium_enable = config.getBoolean("blend", "recipes.lumium", false, "Enable/disable lumium dust blending recipe.");
    blend_enderium_enable = config.getBoolean("blend", "recipes.enderium", false, "Enable/disable enderium dust blending recipe.");

    blend_gunpowder_enable = config.getBoolean("blend", "recipes.gunpowder", true, "Enable/disable gunpowder dust blending recipe.");

    misc_mortar_uses = config.getInt("mortar_uses", "misc", 20, 0, 1000, "How many uses the mortar has unti it breaks. Setting this to 0 disables the item.");

    cheaper_plate_recipes = config.getBoolean("cheaper_plate_recipes", "recipes.balance", false, "Require less materials to make plates.");
    cheaper_rod_recipes = config.getBoolean("cheaper_rod_recipes", "recipes.balance", false, "Require less materials to make rods.");
    cheaper_gear_recipes = config.getBoolean("cheaper_gear_recipes", "recipes.balance", false, "Require less materials to make gears.");

    for(EnumMaterial mat:EnumMaterial.values())
    {
      material_recipes.put(mat, new MaterialRecipeConfig(config,mat));
    }
  }
}
