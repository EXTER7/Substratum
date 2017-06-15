package exter.substratum.block;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.material.EnumMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOre extends Block implements IBlockVariants
{

  public enum EnumVariant implements IStringSerializable
  {
    COPPER(EnumMaterial.COPPER,1),
    TIN(EnumMaterial.TIN,1),
    NICKEL(EnumMaterial.NICKEL,2),
    ZINC(EnumMaterial.ZINC,1),
    SILVER(EnumMaterial.SILVER,2),
    LEAD(EnumMaterial.LEAD,2),
    PLATINUM(EnumMaterial.PLATINUM,2),
    ALUMINA(EnumMaterial.ALUMINA,2),
    CHROMIUM(EnumMaterial.CHROMIUM,2);
    
    public final EnumMaterial material;
    public final int harvest_level;

    private EnumVariant(EnumMaterial material,int harvest_level)
    {
      this.material = material;
      this.harvest_level = harvest_level;
    }

    @Override
    public String getName()
    {
      return material.name;
    }

    @Override
    public String toString()
    {
      return getName();
    }
  }

  public static final PropertyEnum<EnumVariant> VARIANT = PropertyEnum.create("variant", EnumVariant.class);

  public BlockOre()
  {
    super(Material.ROCK);
    setHardness(3.0F);
    setResistance(5.0F);
    setSoundType(SoundType.STONE);
    setUnlocalizedName("substratum.ore");
    setCreativeTab(TabMaterials.tab);
    setRegistryName("ore");
    for(EnumVariant variant:EnumVariant.values())
    {
      setHarvestLevel("pickaxe", variant.harvest_level, getDefaultState().withProperty(VARIANT, variant));
    }
  }

  @Override
  protected BlockStateContainer createBlockState()
  {
    return new BlockStateContainer(this, VARIANT);
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

  @Override
  public int damageDropped(IBlockState state)
  {
    return getMetaFromState(state);
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
  {
    for(EnumVariant ore:EnumVariant.values())
    {
      list.add(new ItemStack(this, 1, ore.ordinal()));
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
    return String.format("%s_%s", getUnlocalizedName(), getStateFromMeta(meta).getValue(VARIANT).material.name);
  }
}
