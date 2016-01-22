package exter.basematerials.block;

import java.util.EnumMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import exter.basematerials.item.ItemBlockMulti;
import exter.basematerials.item.ItemBlockSlab;
import exter.basematerials.material.EnumMaterial;
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
      new BlockMetal.Variant(EnumMaterial.COPPER),
      new BlockMetal.Variant(EnumMaterial.TIN),
      new BlockMetal.Variant(EnumMaterial.BRONZE),
      new BlockMetal.Variant(EnumMaterial.ELECTRUM),
      new BlockMetal.Variant(EnumMaterial.INVAR),
      new BlockMetal.Variant(EnumMaterial.NICKEL),
      new BlockMetal.Variant(EnumMaterial.ZINC),
      new BlockMetal.Variant(EnumMaterial.BRASS),
      new BlockMetal.Variant(EnumMaterial.SILVER),
      new BlockMetal.Variant(EnumMaterial.STEEL),
      new BlockMetal.Variant(EnumMaterial.LEAD),
      new BlockMetal.Variant(EnumMaterial.PLATINUM),
      new BlockMetal.Variant(EnumMaterial.CUPRONICKEL),
      new BlockMetal.Variant(EnumMaterial.SIGNALUM),
      new BlockMetal.Variant(EnumMaterial.LUMIUM),
  };

  private static final BlockMetal.Variant[] BLOCK2_METALS = 
  {
      new BlockMetal.Variant(EnumMaterial.ENDERIUM)
  };

  private static final BlockMetalSlab.Variant[] SLAB1_METALS = 
  {
    new BlockMetalSlab.Variant(EnumMaterial.IRON),
    new BlockMetalSlab.Variant(EnumMaterial.GOLD),
    new BlockMetalSlab.Variant(EnumMaterial.COPPER),
    new BlockMetalSlab.Variant(EnumMaterial.TIN),
    new BlockMetalSlab.Variant(EnumMaterial.BRONZE),
    new BlockMetalSlab.Variant(EnumMaterial.ELECTRUM),
    new BlockMetalSlab.Variant(EnumMaterial.INVAR),
    new BlockMetalSlab.Variant(EnumMaterial.NICKEL)
  };
  
  private static final BlockMetalSlab.Variant[] SLAB2_METALS = 
  {
    new BlockMetalSlab.Variant(EnumMaterial.ZINC),
    new BlockMetalSlab.Variant(EnumMaterial.BRASS),
    new BlockMetalSlab.Variant(EnumMaterial.SILVER),
    new BlockMetalSlab.Variant(EnumMaterial.STEEL),
    new BlockMetalSlab.Variant(EnumMaterial.LEAD),
    new BlockMetalSlab.Variant(EnumMaterial.PLATINUM),
    new BlockMetalSlab.Variant(EnumMaterial.CUPRONICKEL),
    new BlockMetalSlab.Variant(EnumMaterial.SIGNALUM)
  };

  private static final BlockMetalSlab.Variant[] SLAB3_METALS = 
  {
    new BlockMetalSlab.Variant(EnumMaterial.LUMIUM),
    new BlockMetalSlab.Variant(EnumMaterial.ENDERIUM)
  };

  public static BlockMetal[] block_metal;
  public static BlockOre block_ore;
  
  public static BlockMetalSlab[] block_slab;

  public static BlockMetalSlab[] block_slabdouble;
  
//  public static final Map<EnumMaterial,BlockMetalStairs> stairs_blocks = new EnumMap<String,BlockMetalStairs>();

  //All ores mapped by the metal name.
  public static final Map<EnumMaterial,ItemStack> ore_stacks = new EnumMap<EnumMaterial,ItemStack>(EnumMaterial.class);

  //All blocks mapped by the metal name.
  public static final Map<EnumMaterial,ItemStack> block_stacks = new EnumMap<EnumMaterial,ItemStack>(EnumMaterial.class);
  
  //All slabs mapped by the metal name.
  public static final Map<EnumMaterial,ItemStack> slab_stacks = new EnumMap<EnumMaterial,ItemStack>(EnumMaterial.class);

  //All stairs mapped by the metal name.
  public static final Map<EnumMaterial,ItemStack> stairs_stacks = new EnumMap<EnumMaterial,ItemStack>(EnumMaterial.class);

  
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
      GameRegistry.registerBlock(slab, ItemBlockSlab.class, "slab" + (i + 1), slabdouble);
      GameRegistry.registerBlock(slabdouble.left, ItemBlockSlab.class, "slabDouble" + (i + 1), slabdouble);
      for(BlockMetalSlab.Variant v:block_slab[i].getVariants())
      {
        IBlockState state = block_slab[i].getBottomVariant(v);
        ItemStack item = new ItemStack(block_slab[i],1,block_slab[i].getMetaFromState(state));
        slab_stacks.put(v.material, item);
        OreDictionary.registerOre("slab" + v.material.suffix, item);
      }
    }
  }
  
  static private void registerStairs(EnumMaterial material,IBlockState model_state)
  {
    BlockMetalStairs block = new BlockMetalStairs(model_state,material);
    GameRegistry.registerBlock(block, "stairs" + material.suffix);
    ItemStack item = new ItemStack(block);
    stairs_stacks.put(material,item);
    OreDictionary.registerOre("stairs" + material.suffix, item);
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
        ItemStack item = new ItemStack(block_metal[i],1,block_metal[i].getMetaFromState(state));
        block_stacks.put(v.material, item);
        OreDictionary.registerOre("block" + v.material.suffix, item);
        registerStairs(v.material,state);
      }
    }

    GameRegistry.registerBlock(block_ore, ItemBlockMulti.class, "ore");
    for(BlockOre.EnumVariant ore:BlockOre.EnumVariant.values())
    {
      ItemStack item = block_ore.asItemStack(ore);
      ore_stacks.put(ore.material, item);
      OreDictionary.registerOre("ore" + ore.material.suffix, item);
    }

    registerHalfSlabs(config);
    
    registerStairs(EnumMaterial.IRON,Blocks.iron_block.getDefaultState());
    registerStairs(EnumMaterial.GOLD,Blocks.gold_block.getDefaultState());
  }
}
