package exter.substratum;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exter.substratum.block.SubstratumBlocks;
import exter.substratum.block.BlockDustOre;
import exter.substratum.block.BlockOre;
import exter.substratum.config.SubstratumConfig;
import exter.substratum.fluid.SubstratumFluids;
import exter.substratum.init.InitRecipes;
import exter.substratum.item.SubstratumItems;
import exter.substratum.proxy.CommonProxy;
import exter.substratum.worldgen.SubstratumWorldGenerator;
import exter.substratum.worldgen.WorldGenOre;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;


@Mod(
  modid = ModSubstratum.MODID,
  name = ModSubstratum.MODNAME,
  version = ModSubstratum.MODVERSION,
  dependencies = "required-after:forge@[13.20.0.2206,)"
)
public class ModSubstratum
{
  public static final String MODID = "substratum";
  public static final String MODNAME = "Substratum";
  public static final String MODVERSION = "1.9.1.0";

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

    config.save();

    proxy.preInit();
  }
  
 
  @EventHandler
  public void load(FMLInitializationEvent event)
  {
    InitRecipes.init();

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

  // Remap old registry names in 1.10 worlds to the new names.
  @EventHandler
  public void remap(FMLMissingMappingsEvent event)
  {
    Map<String,String> registry_map = new HashMap<String,String>();
    IForgeRegistry<Item> item_registry = GameRegistry.findRegistry(Item.class);
    IForgeRegistry<Block> block_registry = GameRegistry.findRegistry(Block.class);
    
    for(Map.Entry<ResourceLocation,Item> entry:GameRegistry.findRegistry(Item.class).getEntries())
    {
      if(entry.getKey().getResourceDomain().equals(MODID))
      {
        String name = entry.getKey().getResourcePath();
        registry_map.put(name.replaceAll("_", ""), name);
      }
    }

    for(MissingMapping map:event.get())
    {
      String name = map.resourceLocation.getResourcePath();
      String new_name = registry_map.get(name);
      if(new_name != null)
      {
        switch(map.type)
        {
          case BLOCK:
            map.remap(block_registry.getValue(new ResourceLocation(MODID,new_name)));
            break;
          case ITEM:
            map.remap(item_registry.getValue(new ResourceLocation(MODID,new_name)));
            break;
          default:
            break;
        }
      } else
      {
        map.warn();
      }
    }
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    proxy.postInit();
  }
}
