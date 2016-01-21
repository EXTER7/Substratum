package exter.basematerials.block;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import exter.basematerials.item.ItemBlockMulti;
import exter.basematerials.item.ItemBlockSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BMBlocks
{
  private static final BlockMetal.Variant[] BLOCK1_METALS = 
  {
      new BlockMetal.Variant("copper", "blockCopper", "stairsCopper"),
      new BlockMetal.Variant("tin", "blockTin", "stairsTin"),
      new BlockMetal.Variant("bronze", "blockBronze", "stairsBronze"),
      new BlockMetal.Variant("electrum", "blockElectrum", "stairsElectrum"),
      new BlockMetal.Variant("invar", "blockInvar", "stairsInvar"),
      new BlockMetal.Variant("nickel", "blockNickel", "stairsNickel"),
      new BlockMetal.Variant("zinc", "blockZinc", "stairsZinc"),
      new BlockMetal.Variant("brass", "blockBrass", "stairsBrass"),
      new BlockMetal.Variant("silver", "blockSilver", "stairsSilver"),
      new BlockMetal.Variant("steel", "blockSteel", "stairsSteel"),
      new BlockMetal.Variant("lead", "blockLead", "stairsLead"),
      new BlockMetal.Variant("platinum", "blockPlatinum", "stairsPlatinum"),
      new BlockMetal.Variant("cupronickel", "blockCupronickel", "stairsCupronickel"),
      new BlockMetal.Variant("signalum", "blockSignalum", "stairsSignalum"),
      new BlockMetal.Variant("lumium", "blockLumium", "stairsLumium"),
  };

  private static final BlockMetal.Variant[] BLOCK2_METALS = 
  {
      new BlockMetal.Variant("enderium", "blockEnderium", "stairsEnderium")
  };

  private static final BlockMetalSlab.Variant[] SLAB1_METALS = 
  {
    new BlockMetalSlab.Variant("iron", "slabIron"),
    new BlockMetalSlab.Variant("gold", "slabGold"),
    new BlockMetalSlab.Variant("copper", "slabCopper"),
    new BlockMetalSlab.Variant("tin", "slabTin"),
    new BlockMetalSlab.Variant("bronze", "slabBronze"),
    new BlockMetalSlab.Variant("electrum", "slabElectrum"),
    new BlockMetalSlab.Variant("invar", "slabInvar"),
    new BlockMetalSlab.Variant("nickel", "slabNickel")
  };
  
  private static final BlockMetalSlab.Variant[] SLAB2_METALS = 
  {
    new BlockMetalSlab.Variant("zinc", "slabZinc"),
    new BlockMetalSlab.Variant("brass", "slabBrass"),
    new BlockMetalSlab.Variant("silver", "slabSilver"),
    new BlockMetalSlab.Variant("steel", "slabSteel"),
    new BlockMetalSlab.Variant("lead", "slabLead"),
    new BlockMetalSlab.Variant("platinum", "slabPlatinum"),
    new BlockMetalSlab.Variant("cupronickel", "slabCupronickel"),
    new BlockMetalSlab.Variant("signalum", "slabSignalum")
  };

  private static final BlockMetalSlab.Variant[] SLAB3_METALS = 
  {
    new BlockMetalSlab.Variant("lumium", "slabLumium"),
    new BlockMetalSlab.Variant("enderium", "slabEnderium")
  };

  public static BlockMetal[] block_metal;
  public static BlockOre block_ore;
  
  public static BlockMetalSlab[] block_slab;

  public static BlockMetalSlab[] block_slabdouble;
  
  public static final Map<String,BlockMetalStairs> stairs_blocks = new HashMap<String,BlockMetalStairs>();

  //All ores mapped by the metal name.
  public static final Map<String,ItemStack> ore_stacks = new HashMap<String,ItemStack>();

  //All blocks mapped by the metal name.
  public static final Map<String,ItemStack> block_stacks = new HashMap<String,ItemStack>();
  
  //All slabs mapped by the metal name.
  public static final Map<String,ItemStack> slab_stacks = new HashMap<String,ItemStack>();

  //All stairs mapped by the metal name.
  public static final Map<String,ItemStack> stair_stacks = new HashMap<String,ItemStack>();

  
  static private void registerHalfSlabs(Configuration config)
  {
    int i;
    block_slab = new BlockMetalSlab[3];
    block_slab[0] = new BlockMetalSlab(null) { @Override public Variant[] getVariants() { return SLAB1_METALS; } };
    block_slab[1] = new BlockMetalSlab(null) { @Override public Variant[] getVariants() { return SLAB2_METALS; } };
    block_slab[2] = new BlockMetalSlab(null) { @Override public Variant[] getVariants() { return SLAB3_METALS; } };

    block_slabdouble = new BlockMetalSlab[3];
    block_slabdouble[0] = new BlockMetalSlab(block_slab[0]) {
      @Override public Variant[] getVariants() { return SLAB1_METALS; }
      @Override public IProperty<BlockMetalSlab.Variant> getVariantProperty() { return block_slab[0].getVariantProperty(); } };
    block_slabdouble[1] = new BlockMetalSlab(block_slab[1]) {
      @Override public Variant[] getVariants() { return SLAB2_METALS; }
      @Override public IProperty<BlockMetalSlab.Variant> getVariantProperty() { return block_slab[1].getVariantProperty(); } };
    block_slabdouble[2] = new BlockMetalSlab(block_slab[2]) {
      @Override public Variant[] getVariants() { return SLAB3_METALS; }
      @Override public IProperty<BlockMetalSlab.Variant> getVariantProperty() { return block_slab[2].getVariantProperty(); } };

    for(i = 0; i < block_slab.length; i++)
    {
      BlockSlab slab = block_slab[i];
      ImmutablePair<BlockSlab,Object> slabdouble = new ImmutablePair<BlockSlab,Object>(block_slabdouble[i],null);
      GameRegistry.registerBlock(slab, ItemBlockSlab.class, "slabMetal" + (i + 1), slabdouble);
      GameRegistry.registerBlock(slabdouble.left, ItemBlockSlab.class, "slabMetalDouble" + (i + 1), slabdouble);
      for(BlockMetalSlab.Variant v:block_slab[i].getVariants())
      {
        IBlockState state = block_slab[i].getBottomVariant(v);
        ItemStack item = new ItemStack(block_slab[i],1,block_slab[i].getMetaFromState(state));
        slab_stacks.put(v.name, item);
        OreDictionary.registerOre(v.oredict, item);
      }
    }
  }
  
  static public void registerBlocks(Configuration config)
  {
    int i;
    block_metal = new BlockMetal[2];
    block_metal[0] = new BlockMetal() { public Variant[] getVariants() { return BLOCK1_METALS; } };
    block_metal[1] = new BlockMetal() { public Variant[] getVariants() { return BLOCK2_METALS; } };
    block_ore = new BlockOre();

    for(i = 0; i < block_metal.length; i++)
    {
      
      GameRegistry.registerBlock(block_metal[i], ItemBlockMulti.class, "blockMetal" + (i + 1));
      for(BlockMetal.Variant v:block_metal[i].getVariants())
      {
        IBlockState state = block_metal[i].getVariantState(v);
        stairs_blocks.put(v.name,new BlockMetalStairs(state,v.name,v.oredict_stairs));
        ItemStack item = new ItemStack(block_metal[i],1,block_metal[i].getMetaFromState(state));
        block_stacks.put(v.name, item);
        OreDictionary.registerOre(v.oredict, item);
      }
    }

    GameRegistry.registerBlock(block_ore, ItemBlockMulti.class, "ore");
    for(BlockOre.EnumMaterial ore:BlockOre.EnumMaterial.values())
    {
      ore_stacks.put(ore.name, block_ore.asItemStack(ore));
    }

    
    registerHalfSlabs(config);
    
    stairs_blocks.put("iron",new BlockMetalStairs(Blocks.iron_block.getDefaultState(),"iron","stairsIron"));
    stairs_blocks.put("gold",new BlockMetalStairs(Blocks.gold_block.getDefaultState(),"gold","stairsGold"));

    for(Map.Entry<String,BlockMetalStairs> e:stairs_blocks.entrySet())
    {
      BlockMetalStairs block = e.getValue();
      GameRegistry.registerBlock(block, block.oredict);
      OreDictionary.registerOre(block.oredict, new ItemStack(block));
    }

    block_stacks.put("iron", new ItemStack(Blocks.iron_block));
    block_stacks.put("gold", new ItemStack(Blocks.gold_block));

    for(BlockOre.EnumMaterial v:BlockOre.EnumMaterial.values())
    {
      IBlockState state = block_ore.getDefaultState().withProperty(BlockOre.VARIANT, v);
      ItemStack stack = new ItemStack(block_ore,  1, block_ore.getMetaFromState(state));
      OreDictionary.registerOre(v.oredict,stack);
    }

  }
}
