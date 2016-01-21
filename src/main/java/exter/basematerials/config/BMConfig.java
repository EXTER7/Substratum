package exter.basematerials.config;

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
      this.enabled = config.getBoolean("enabled", "worldgen." + ore, true, null);
      this.min_y = config.getInt("minY", "worldgen." + ore, min_y, 0, 256, null);
      this.max_y = config.getInt("maxY", "worldgen." + ore, max_y, min_y, 256, null);
      this.frequency = config.getInt("frequency", "worldgen." + ore, frequency, 1, 100, null);
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
  
  static public void load(Configuration config)
  {
    worldgen_copper = new WorldgenConfig(config, "copper", 16, 80, 12);
    worldgen_tin = new WorldgenConfig(config, "tin", 16, 52, 8);
    worldgen_zinc = new WorldgenConfig(config, "zinc", 8, 48, 6);
    worldgen_nickel = new WorldgenConfig(config, "nickel", 8, 36, 5);
    worldgen_silver = new WorldgenConfig(config, "silver", 8, 30, 3);
    worldgen_lead = new WorldgenConfig(config, "lead", 8, 48, 5);
    worldgen_platinum = new WorldgenConfig(config, "platinum", 2, 12, 1);
    
    recipe_bronze_enable = config.getBoolean("recipes", "crafting.bronze", true, null);
    recipe_brass_enable = config.getBoolean("recipes", "crafting.brass", true, null);
    recipe_invar_enable = config.getBoolean("recipes", "crafting.invar", true, null);
    recipe_electrum_enable = config.getBoolean("recipes", "crafting.electrum", true, null);
    recipe_cupronickel_enable = config.getBoolean("recipes", "crafting.cupronickel", true, null);

    recipe_steel_enable = config.getBoolean("recipes", "crafting.steel", false, null);
    recipe_signalum_enable = config.getBoolean("recipes", "crafting.signalum", false, null);
    recipe_lumium_enable = config.getBoolean("recipes", "crafting.lumium", false, null);
    recipe_enderium_enable = config.getBoolean("recipes", "crafting.enderium", false, null);
    recipe_coaldust_enable = config.getBoolean("recipes", "crafting.coaldust", false, null);
    recipe_enderpearldust_enable = config.getBoolean("recipes", "crafting.enderpearldust", false, null);
  }
}
