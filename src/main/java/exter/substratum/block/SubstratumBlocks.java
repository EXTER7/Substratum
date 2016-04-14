package exter.substratum.block;

import java.util.EnumMap;
import java.util.Map;

import exter.substratum.item.ItemBlockMulti;
import exter.substratum.material.EnumMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class SubstratumBlocks
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
  public static BlockDustOre block_ore_dust;
  
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


  static private void register(Block block)
  {
    GameRegistry.register(block);
    GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
   
  }

  static private <T extends Block & IBlockVariants>void registerMulti(T block)
  {
    GameRegistry.register(block);
    GameRegistry.register(new ItemBlockMulti(block).setRegistryName(block.getRegistryName()));
  }

  static private void registerSlab(BlockSlab block,BlockSlab slabdouble)
  {
    GameRegistry.register(block);
    GameRegistry.register(new ItemSlab(block,block,slabdouble).setRegistryName(block.getRegistryName()));   
  }

  static private void registerHalfSlabs()
  {
    int i;
    block_slab = new BlockMetalSlab[3];
    block_slab[0] = new BlockMetalSlab(null,"slab1") { @Override public Variant[] getVariants() { return SLAB1_METALS; } };
    block_slab[1] = new BlockMetalSlab(null,"slab2") { @Override public Variant[] getVariants() { return SLAB2_METALS; } };
    block_slab[2] = new BlockMetalSlab(null,"slab3") { @Override public Variant[] getVariants() { return SLAB3_METALS; } };

    block_slabdouble = new BlockMetalSlab[3];
    block_slabdouble[0] = new BlockMetalSlab(block_slab[0],"slabDouble1") {
      @Override public Variant[] getVariants() { return SLAB1_METALS; }
      @Override public IProperty<BlockMetalSlab.Variant> getVariantProperty() { return block_slab[0].getVariantProperty(); } };
    block_slabdouble[1] = new BlockMetalSlab(block_slab[1],"slabDouble2") {
      @Override public Variant[] getVariants() { return SLAB2_METALS; }
      @Override public IProperty<BlockMetalSlab.Variant> getVariantProperty() { return block_slab[1].getVariantProperty(); } };
    block_slabdouble[2] = new BlockMetalSlab(block_slab[2],"slabDouble3") {
      @Override public Variant[] getVariants() { return SLAB3_METALS; }
      @Override public IProperty<BlockMetalSlab.Variant> getVariantProperty() { return block_slab[2].getVariantProperty(); } };

    for(i = 0; i < block_slab.length; i++)
    {
      BlockSlab slab = block_slab[i];
      BlockSlab slabdouble = block_slabdouble[i];
      registerSlab(slab, slabdouble);
      registerSlab(slabdouble, slabdouble);
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
    register(block);
    ItemStack item = new ItemStack(block);
    stairs_stacks.put(material,item);
    OreDictionary.registerOre("stairs" + material.suffix, item);
  }
  
  static public void registerBlocks()
  {
    int i;
    block_metal = new BlockMetal[2];
    block_metal[0] = new BlockMetal("blockMetal1") { public Variant[] getVariants() { return BLOCK1_METALS; } };
    block_metal[1] = new BlockMetal("blockMetal2") { public Variant[] getVariants() { return BLOCK2_METALS; } };
    block_ore = new BlockOre();
    block_ore_dust = new BlockDustOre();

    for(i = 0; i < block_metal.length; i++)
    {
      
      registerMulti(block_metal[i]);
      for(BlockMetal.Variant v:block_metal[i].getVariants())
      {
        IBlockState state = block_metal[i].getVariantState(v);
        ItemStack item = new ItemStack(block_metal[i],1,block_metal[i].getMetaFromState(state));
        block_stacks.put(v.material, item);
        OreDictionary.registerOre("block" + v.material.suffix, item);
        registerStairs(v.material,state);
      }
    }

    registerMulti(block_ore);
    for(BlockOre.EnumVariant ore:BlockOre.EnumVariant.values())
    {
      ItemStack item = block_ore.asItemStack(ore);
      ore_stacks.put(ore.material, item);
      OreDictionary.registerOre("ore" + ore.material.suffix, item);
    }

    registerMulti(block_ore_dust);
    for(BlockDustOre.EnumVariant ore:BlockDustOre.EnumVariant.values())
    {
      ItemStack item = block_ore_dust.asItemStack(ore);
      ore_stacks.put(ore.material, item);
      OreDictionary.registerOre("ore" + ore.material.suffix, item);
    }

    registerHalfSlabs();
    
    registerStairs(EnumMaterial.IRON,Blocks.IRON_BLOCK.getDefaultState());
    registerStairs(EnumMaterial.GOLD,Blocks.GOLD_BLOCK.getDefaultState());
  }
}
