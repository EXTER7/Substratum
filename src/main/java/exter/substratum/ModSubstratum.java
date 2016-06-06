package exter.substratum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exter.substratum.block.SubstratumBlocks;
import exter.substratum.block.BlockDustOre;
import exter.substratum.block.BlockOre;
import exter.substratum.config.SubstratumConfig;
import exter.substratum.fluid.SubstratumFluids;
import exter.substratum.handler.SubstratumBucketHandler;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumMaterialItem;
import exter.substratum.proxy.CommonProxy;
import exter.substratum.recipes.SubstratumRecipes;
import exter.substratum.worldgen.SubstratumWorldGenerator;
import exter.substratum.worldgen.WorldGenOre;
import net.minecraftforge.common.MinecraftForge;
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
  dependencies = "required-after:Forge@[12.17.0.1939,)"
)
public class ModSubstratum
{
  public static final String MODID = "substratum";
  public static final String MODNAME = "Substratum";
  public static final String MODVERSION = "1.3.2.0";

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

    SubstratumBlocks.registerBlocks();
    SubstratumConfig.load(config);
    SubstratumItems.registerItems(config);
    SubstratumFluids.registerFluids();


    SubstratumBucketHandler bucket_handler = new SubstratumBucketHandler();
    MinecraftForge.EVENT_BUS.register(bucket_handler);
    SubstratumItems.item_materials.get(EnumMaterialItem.BUCKET_LIQUID).setRightClickHandler(bucket_handler);

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
    WorldGenOre.registerOre(SubstratumConfig.worldgen_alumina, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.ALUMINA), false);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_chromium, SubstratumBlocks.block_ore.asState(BlockOre.EnumVariant.CHROMIUM), false);

    WorldGenOre.registerOre(SubstratumConfig.worldgen_sulfur, SubstratumBlocks.block_ore_dust.asState(BlockDustOre.EnumVariant.SULFUR), true);
    WorldGenOre.registerOre(SubstratumConfig.worldgen_niter, SubstratumBlocks.block_ore_dust.asState(BlockDustOre.EnumVariant.NITER), true);

    GameRegistry.registerWorldGenerator(new SubstratumWorldGenerator(),0);

    proxy.init();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    proxy.postInit();
  }
}
