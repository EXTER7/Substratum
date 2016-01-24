package exter.basematerials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exter.basematerials.block.BMBlocks;
import exter.basematerials.block.BlockOre;
import exter.basematerials.block.BlockDustOre;
import exter.basematerials.config.BMConfig;
import exter.basematerials.item.BMItems;
import exter.basematerials.proxy.CommonProxy;
import exter.basematerials.recipes.BMRecipes;
import exter.basematerials.worldgen.BMWorldGenerator;
import exter.basematerials.worldgen.WorldGenOre;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod(
  modid = ModBaseMaterials.MODID,
  name = ModBaseMaterials.MODNAME,
  version = ModBaseMaterials.MODVERSION,
  dependencies = "required-after:Forge@[11.15.0.1699,)"
)
public class ModBaseMaterials
{
  public static final String MODID = "basematerials";
  public static final String MODNAME = "BaseMaterials";
  public static final String MODVERSION = "1.0.0.0";

  @Instance(MODID)
  public static ModBaseMaterials instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(
    clientSide = "exter.basematerials.proxy.ClientProxy",
    serverSide = "exter.basematerials.proxy.CommonProxy"
  )
  public static CommonProxy proxy;

  
  public static Logger log = LogManager.getLogger(MODNAME);

  
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    config.load();

    BMConfig.load(config);
    BMItems.registerItems(config);
    BMBlocks.registerBlocks(config);

    BMRecipes.preInit();

    config.save();

    proxy.preInit();
  }
  
 
  @EventHandler
  public void load(FMLInitializationEvent event)
  {
   

    BMRecipes.init();

    WorldGenOre.registerOre(BMConfig.worldgen_copper, BMBlocks.block_ore.asState(BlockOre.EnumVariant.COPPER), false);
    WorldGenOre.registerOre(BMConfig.worldgen_tin, BMBlocks.block_ore.asState(BlockOre.EnumVariant.TIN), false);
    WorldGenOre.registerOre(BMConfig.worldgen_zinc, BMBlocks.block_ore.asState(BlockOre.EnumVariant.ZINC), false);
    WorldGenOre.registerOre(BMConfig.worldgen_nickel, BMBlocks.block_ore.asState(BlockOre.EnumVariant.NICKEL), false);
    WorldGenOre.registerOre(BMConfig.worldgen_silver, BMBlocks.block_ore.asState(BlockOre.EnumVariant.SILVER), false);
    WorldGenOre.registerOre(BMConfig.worldgen_lead, BMBlocks.block_ore.asState(BlockOre.EnumVariant.LEAD), false);
    WorldGenOre.registerOre(BMConfig.worldgen_platinum, BMBlocks.block_ore.asState(BlockOre.EnumVariant.PLATINUM), false);
    WorldGenOre.registerOre(BMConfig.worldgen_sulfur, BMBlocks.block_ore_dust.asState(BlockDustOre.EnumVariant.SULFUR), true);
    WorldGenOre.registerOre(BMConfig.worldgen_niter, BMBlocks.block_ore_dust.asState(BlockDustOre.EnumVariant.NITER), true);

    GameRegistry.registerWorldGenerator(new BMWorldGenerator(),0);

    proxy.init();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    BMRecipes.postInit();
    proxy.postInit();
  }
}
