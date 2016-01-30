package exter.substratum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exter.substratum.block.SubstratumBlocks;
import exter.substratum.block.BlockDustOre;
import exter.substratum.block.BlockOre;
import exter.substratum.config.SubstratumConfig;
import exter.substratum.item.SubstratumItems;
import exter.substratum.proxy.CommonProxy;
import exter.substratum.recipes.SubstratumRecipes;
import exter.substratum.worldgen.BMWorldGenerator;
import exter.substratum.worldgen.WorldGenOre;
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
  modid = ModSubstratum.MODID,
  name = ModSubstratum.MODNAME,
  version = ModSubstratum.MODVERSION,
  dependencies = "required-after:Forge@[11.15.1.1724,)"
)
public class ModSubstratum
{
  public static final String MODID = "substratum";
  public static final String MODNAME = "Substratum";
  public static final String MODVERSION = "1.0.0.1";

  @Instance(MODID)
  public static ModSubstratum instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(
    clientSide = "exter.substratum.proxy.ClientProxy",
    serverSide = "exter.substratum.proxy.CommonProxy"
  )
  public static CommonProxy proxy;

  
  public static Logger log = LogManager.getLogger(MODNAME);

  
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    config.load();

    SubstratumConfig.load(config);
    SubstratumItems.registerItems(config);
    SubstratumBlocks.registerBlocks(config);

    SubstratumRecipes.preInit();

    config.save();

    proxy.preInit();
  }
  
 
  @EventHandler
  public void load(FMLInitializationEvent event)
  {
   

    SubstratumRecipes.init();

    WorldGenOre.registerOre(SubstratumConfig.worldgen_copper, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.COPPER), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_tin, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.TIN), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_zinc, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.ZINC), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_nickel, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.NICKEL), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_silver, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.SILVER), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_lead, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.LEAD), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_platinum, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.PLATINUM), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_sulfur, SubstratumBlocks.block_ore_dust.asState(BlockDustOre.EnumVariant.SULFUR), true);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_niter, SubstratumBlocks.block_ore_dust.asState(BlockDustOre.EnumVariant.NITER), true);

    GameRegistry.registerWorldGenerator(new BMWorldGenerator(),0);

    proxy.init();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    SubstratumRecipes.postInit();
    proxy.postInit();
  }
}