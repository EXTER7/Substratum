package exter.substratum.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import exter.substratum.block.SubstratumBlocks;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialEquipment;
import exter.substratum.material.EnumMaterialItem;
import net.minecraftforge.common.config.Configuration;


public class SubstratumConfig
{
  static public class WorldgenConfig
  {
    public final boolean enabled;
    public final int min_y;
    public final int max_y;
    public final int min_frequency;
    public final int max_frequency;
    public final int cluster_size;

    public WorldgenConfig(Configuration config, String ore, int min_y, int max_y, int min_frequency, int max_frequency, int cluster_size)
    {
      String category = "worldgen." + ore;
      this.enabled = config.getBoolean("enabled", "worldgen." + ore, true, "Enable/disable worldgen for this ore.");
      this.min_y = config.getInt("minY", category, min_y, 0, 256, "Lowest Y level the ore is generated.");
      this.max_y = config.getInt("maxY", category, max_y, 0, 256, "Highest Y level the ore is generated.");

      int minfreq = min_frequency;
      int maxfreq = max_frequency;
      
      if(config.hasKey(category, "min_clusters") && config.hasKey(category, "max_clusters"))
      {
        // Convert old cluster config to frequency config
        int minc = config.get(category, "min_clusters", min_frequency).getInt();
        int maxc = config.get(category, "max_clusters", max_frequency).getInt();
        minfreq =  (int)Math.round((double)(minc * 1000) / (double)Math.abs(this.max_y - this.min_y));
        maxfreq =  (int)Math.round((double)(maxc * 1000) / (double)Math.abs(this.max_y - this.min_y));
      }
      config.getCategory(category).remove("min_clusters");
      config.getCategory(category).remove("max_clusters");
      
      this.min_frequency = config.getInt("min_frequency", category, minfreq, 1, 10000, "Minimum ore frequency.");
      this.max_frequency = config.getInt("max_frequency", category, maxfreq, 1, 10000, "Maximum ore frequency.");
      
      this.cluster_size = config.getInt("cluster_size", category, cluster_size, 1, 100, "Size of ore cluster.");
    }
  }
  
  static public class MaterialRecipeConfig
  {
    public final boolean dust_bottle;
    public final boolean gear_crafting;
    public final boolean plate_crafting;
    public final boolean dust_from_ingot;
    public final boolean rod_crafting;
    
    public final boolean ingot_from_plate;
    public final boolean ingots_from_gear;
    public final boolean ingot_from_dust;
    public final boolean dust_from_rod;
    public final boolean slab_from_blocks;
    public final boolean stairs_from_blocks;
    
    
    public final boolean tool_pickaxe;
    public final boolean tool_axe;
    public final boolean tool_shovel;
    public final boolean tool_hoe;
    public final boolean tool_sword;

    public final boolean armor_helmet;
    public final boolean armor_chestplate;
    public final boolean armor_leggings;
    public final boolean armor_boots;

    
    static private boolean hasIngot(EnumMaterial material)
    {
      return EnumMaterialItem.INGOT.materials.contains(material) || material == EnumMaterial.IRON || material == EnumMaterial.GOLD;
    }
    
    public MaterialRecipeConfig(Configuration config,EnumMaterial material)
    {
      String name = material.name;
      String category = "recipes." + name;
      if(EnumMaterialItem.DUST.materials.contains(material) && material != EnumMaterial.SULFUR && material != EnumMaterial.NITER)
      {
        String property = hasIngot(material) ? "dust_from_ingot" : "dust_from_item";
        dust_from_ingot = config.getBoolean(property, category, true, "Enable/disable " + name + " dust with mortar crafting recipe.");
      } else
      {
        dust_from_ingot = false;
      }

      if(EnumMaterialItem.BOTTLE_DUST.materials.contains(material))
      {
        config.renameProperty(category, "dust_bucket", "dust_bottle");
        dust_bottle = config.getBoolean("dust_bottle", category, true, "Enable/disable " + name + " dust bottle recipes.");
      } else
      {
        dust_bottle = false;
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
        plate_crafting = config.getBoolean("plate_crafting", "recipes." + name, true, "Enable/disable " + name + " plate crafting recipe.");
        ingot_from_plate = config.getBoolean("ingot_from_plate", "recipes." + name, true, "Enable/disable " + name + " plate to ingot smelting recipe.");
      } else
      {
        plate_crafting = false;
        ingot_from_plate = false;
      }
      
      if(EnumMaterialItem.GEAR.materials.contains(material))
      {
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

      if(SubstratumBlocks.slab_stacks.containsKey(material))
      {
        slab_from_blocks = config.getBoolean("slab_from_blocks", "recipes." + name, true, "Enable/disable " + name + " slab crafting recipe.");
      } else
      {
        slab_from_blocks = false;
      }

      if(SubstratumBlocks.stairs_stacks.containsKey(material))
      {
        stairs_from_blocks = config.getBoolean("stairs_from_blocks", "recipes." + name, true, "Enable/disable " + name + " stairs crafting recipe.");
      } else
      {
        stairs_from_blocks = false;
      }

      Set<EnumMaterial> equipment_materials = EnumMaterialEquipment.getMaterials();
      
      if(equipment_materials.contains(material))
      {
        tool_pickaxe = config.getBoolean("pickaxe", "equipment." + name, true, "Enable/disable " + name + " pickaxe.");
        tool_axe = config.getBoolean("axe", "equipment." + name, true, "Enable/disable " + name + " axe.");
        tool_shovel = config.getBoolean("shovel", "equipment." + name, true, "Enable/disable " + name + " shovel.");
        tool_hoe = config.getBoolean("hoe", "equipment." + name, true, "Enable/disable " + name + " hoe.");
        tool_sword = config.getBoolean("sword", "equipment." + name, true, "Enable/disable " + name + " sword.");

        armor_helmet = config.getBoolean("helmet", "equipment." + name, true, "Enable/disable " + name + " helmet.");
        armor_chestplate = config.getBoolean("chestplate", "equipment." + name, true, "Enable/disable " + name + " chestplate.");
        armor_leggings = config.getBoolean("leggings", "equipment." + name, true, "Enable/disable " + name + " leggings.");
        armor_boots = config.getBoolean("boots", "equipment." + name, true, "Enable/disable " + name + " boots.");
      } else
      {
        tool_pickaxe = false;
        tool_axe = false;
        tool_shovel = false;
        tool_hoe = false;
        tool_sword = false;

        armor_helmet = false;
        armor_chestplate = false;
        armor_leggings = false;
        armor_boots = false;
      }
    }
  }
  
  static public enum AluminiumRecipe
  {
    NONE,
    NORMAL,
    CHEAP
  }
  
  public static WorldgenConfig worldgen_copper;
  public static WorldgenConfig worldgen_tin;
  public static WorldgenConfig worldgen_zinc;
  public static WorldgenConfig worldgen_nickel;
  public static WorldgenConfig worldgen_silver;
  public static WorldgenConfig worldgen_lead;
  public static WorldgenConfig worldgen_platinum;
  public static WorldgenConfig worldgen_alumina;
  public static WorldgenConfig worldgen_chromium;

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
  
  public static AluminiumRecipe aluminium_recipe;
  
  public static Map<EnumMaterial,MaterialRecipeConfig> material_recipes = new EnumMap<EnumMaterial,MaterialRecipeConfig>(EnumMaterial.class);

  public static int misc_mortar_uses;

  public static boolean dye_enabled;

  static public void load(Configuration config)
  {
    worldgen_copper = new WorldgenConfig(config, "copper", 20, 80, 167, 333, 7);    
    worldgen_tin = new WorldgenConfig(config, "tin", 20, 52, 188, 375, 7);
    worldgen_zinc = new WorldgenConfig(config, "zinc", 8, 48, 175, 175, 7);    
    worldgen_nickel = new WorldgenConfig(config, "nickel", 8, 36, 107, 214, 7);
    worldgen_silver = new WorldgenConfig(config, "silver", 8, 30, 136, 182, 7);
    worldgen_lead = new WorldgenConfig(config, "lead", 8, 48, 125, 150, 7);
    worldgen_platinum = new WorldgenConfig(config, "platinum", 2, 12, 100, 150, 3);
    worldgen_alumina = new WorldgenConfig(config, "alumina", 16, 32, 125, 312, 7);
    worldgen_chromium = new WorldgenConfig(config, "chromium", 8, 24, 100, 150, 5);
    
    worldgen_sulfur = new WorldgenConfig(config, "sulfur", 5, 123, 127, 169, 7);
    worldgen_niter = new WorldgenConfig(config, "niter", 5, 123, 85, 127, 7);

    blend_bronze_enable = config.getBoolean("blend", "recipes.bronze", true, "Enable/disable bronze dust blending recipe.");
    blend_brass_enable = config.getBoolean("blend", "recipes.brass", true, "Enable/disable brass dust blending recipe.");
    blend_invar_enable = config.getBoolean("blend", "recipes.invar", true, "Enable/disable invar dust blending recipe.");
    blend_electrum_enable = config.getBoolean("blend", "recipes.electrum", true, "Enable/disable electrum dust blending recipe.");
    blend_cupronickel_enable = config.getBoolean("blend", "recipes.cupronickel", true, "Enable/disable cupronickel dust blending recipe.");

    blend_steel_enable = config.getBoolean("blend", "recipes.steel", false, "Enable/disable steel dust blending recipe.");
    blend_signalum_enable = config.getBoolean("blend", "recipes.signalum", true, "Enable/disable signalum dust blending recipe.");
    blend_lumium_enable = config.getBoolean("blend", "recipes.lumium", true, "Enable/disable lumium dust blending recipe.");
    blend_enderium_enable = config.getBoolean("blend", "recipes.enderium", true, "Enable/disable enderium dust blending recipe.");

    blend_gunpowder_enable = config.getBoolean("blend", "recipes.gunpowder", true, "Enable/disable gunpowder dust blending recipe.");

    config.renameProperty("recipes.aluminium", "ingot_from_alumina", "nugget_from_alumina");
    boolean alumina_nugget_smelting = config.getBoolean("nugget_from_alumina", "recipes.aluminium", true, "");
    config.getCategory("recipes.aluminium").remove("nugget_from_alumina");
    
    aluminium_recipe = AluminiumRecipe.values()[config.getInt("recipe_from_alumina", "recipes.aluminium", alumina_nugget_smelting?1:0,0,2, "Recipe for making aluminium from alumina:\n"
        + "0 = No recipe (aluminium cannot be made without another mod adding a way to make it).\n"
        + "1 = Normal recipe (1 alumina dust/ore -> 3 aluminium nuggets in a furnace).\n"
        + "2 = Cheaper recipe (1 alumina dust/ore -> 1 aluminium ingot in a furnace).\n")];
    
    misc_mortar_uses = config.getInt("mortar_uses", "misc", 20, 0, 1000, "How many uses the mortar has unti it breaks. Setting this to 0 disables the item.");
    dye_enabled = config.getBoolean("enabled", "dyes", true, "Enable/disable dye powders.");

    cheaper_plate_recipes = config.getBoolean("cheaper_plate_recipes", "recipes.balance", false, "Require less materials to make plates.");
    cheaper_rod_recipes = config.getBoolean("cheaper_rod_recipes", "recipes.balance", false, "Require less materials to make rods.");
    cheaper_gear_recipes = config.getBoolean("cheaper_gear_recipes", "recipes.balance", false, "Require less materials to make gears.");

    for(EnumMaterial mat:EnumMaterial.values())
    {
      if(mat != EnumMaterial.NULL)
      {
        material_recipes.put(mat, new MaterialRecipeConfig(config,mat));
      }
    }
  }
}
