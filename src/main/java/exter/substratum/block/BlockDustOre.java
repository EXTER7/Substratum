package exter.substratum.block;

import java.util.Random;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
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
      return material.name;
    }

    @Override
    public String toString()
    {
      return getName();
    }
  }

  public static final PropertyEnum<EnumVariant> VARIANT = PropertyEnum.create("variant", EnumVariant.class);


  public BlockDustOre()
  {
    super(Material.ROCK);
    setHardness(3.0F);
    setResistance(5.0F);
    setSoundType(SoundType.STONE);
    setUnlocalizedName("substratum.ore");
    setRegistryName("ore_dust");
    setCreativeTab(TabMaterials.tab);
    setHarvestLevel("pickaxe", 1);
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
  
  public Item getItemDropped(IBlockState state, Random rand, int fortune)
  {
    return SubstratumItems.item_materials.get(EnumMaterialItem.DUST);
  }

  @Override
  public int damageDropped(IBlockState state)
  {
    return SubstratumItems.item_materials.get(EnumMaterialItem.DUST).getMaterialMeta(state.getValue(VARIANT).material);
  }
  
  @Override
  public int quantityDroppedWithBonus(int fortune, Random random)
  {
    return this.quantityDropped(random) + random.nextInt(fortune + 1);
  }

  @Override
  public int quantityDropped(Random random)
  {
    return 2 + random.nextInt(2);
  }
  
  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
  {
    return asItemStack(state.getValue(VARIANT));
  }
}
