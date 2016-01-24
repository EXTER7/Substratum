package exter.substratum.block;

import java.util.List;
import java.util.Random;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDustOre extends Block implements IBlockVariants
{

  public enum EnumVariant implements IStringSerializable
  {
    SULFUR(EnumMaterial.SULFUR),
    NITER(EnumMaterial.NITER);
    
    public final EnumMaterial material;
    
    
    private EnumVariant(EnumMaterial material)
    {
      this.material = material;
      
    }

    @Override
    public String getName()
    {
      return material.suffix.toLowerCase();
    }

    @Override
    public String toString()
    {
      return getName();
    }
  }

  public static final PropertyEnum<EnumVariant> VARIANT = PropertyEnum.create("ore", EnumVariant.class);


  public BlockDustOre()
  {
    super(Material.rock);
    setHardness(3.0F);
    setResistance(5.0F);
    setStepSound(Block.soundTypeStone);
    setUnlocalizedName("substratum.ore");
    setCreativeTab(TabMaterials.tab);
    setHarvestLevel("pickaxe", 1);
  }
  

  @Override
  protected BlockState createBlockState()
  {
    return new BlockState(this, VARIANT);
  }

  @Override
  public IBlockState getStateFromMeta(int meta)
  {
    return getDefaultState().withProperty(VARIANT, EnumVariant.values()[meta]);
  }

  @Override
  public int getMetaFromState(IBlockState state)
  {
    return state.getValue(VARIANT).ordinal();
  }

  @SuppressWarnings("unchecked")
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list)
  {
    for(EnumVariant ore:EnumVariant.values())
    {
      list.add(new ItemStack(item, 1, ore.ordinal()));
    }
  }
  
  public ItemStack asItemStack(EnumVariant ore)
  {
    return new ItemStack(this,1,ore.ordinal());
  }

  public IBlockState asState(EnumVariant ore)
  {
    return getDefaultState().withProperty(VARIANT, ore);
  }

  @Override
  public String getUnlocalizedName(int meta)
  {
    return getUnlocalizedName() + getStateFromMeta(meta).getValue(VARIANT).material.suffix;
  }
  
  public Item getItemDropped(IBlockState state, Random rand, int fortune)
  {
    return SubstratumItems.item_materials.get(EnumMaterialItem.DUST);
  }

  @Override
  public int damageDropped(IBlockState state)
  {
    return SubstratumItems.item_materials.get(EnumMaterialItem.DUST).getMaterialMeta(state.getValue(VARIANT).material);
  }
  
  public int quantityDroppedWithBonus(int fortune, Random random)
  {
    return this.quantityDropped(random) + random.nextInt(fortune + 1);
  }

  public int quantityDropped(Random random)
  {
    return 2 + random.nextInt(2);
  }
}
