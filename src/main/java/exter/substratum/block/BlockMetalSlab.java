package exter.substratum.block;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.common.base.Optional;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.material.EnumMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyHelper;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockMetalSlab extends BlockSlab implements IBlockVariants
{

  static public class Variant implements Comparable<Variant>,IBlockVariants.IMaterialProperty
  {
    public final EnumMaterial material;
    public int id;
    
    public Variant(EnumMaterial material)
    {
      this.material = material;
      this.id = -1;
    }

    @Override
    public String toString()
    {
      return getName();
    }

    @Override
    public int compareTo(Variant other)
    {
      return id - other.id;
    }

    @Override
    public EnumMaterial getMaterial()
    {
      return material;
    }
  }
  
  private class PropertyVariant extends PropertyHelper<Variant>
  {
    private Map<String,Variant> variants;
    
    public PropertyVariant(Variant[] variants)
    {
      super("variant",Variant.class);
      int i = 0;
      this.variants = new HashMap<String,Variant>();
      for (Variant v : variants)
      {
        v.id = i++;
        this.variants.put(v.getName(),v);
      }
    }

    @Override
    public Collection<Variant> getAllowedValues()
    {
      return variants.values();
    }

    @Override
    public String getName(Variant value)
    {
      return value.getName();
    }

    @Override
    public Optional<Variant> parseValue(String value)
    {
      return Optional.fromNullable(variants.get(value));
    }
  }

  private PropertyVariant property_variant;
  private BlockSlab single;

  public abstract Variant[] getVariants();
  
  
  public BlockMetalSlab(BlockSlab single,String name)
  {
    super(Material.IRON);
    this.single = single;
    setCreativeTab(TabMaterials.tab);
    setHardness(5.0F);
    setResistance(10.0F);
    setSoundType(SoundType.METAL);
    setUnlocalizedName("substratum.slab" + (single != null?"_double":""));
    setRegistryName(name);
    useNeighborBrightness = true;
  }
  

  @Override
  public final int damageDropped(IBlockState state)
  {
    return state.getValue(getVariantProperty()).id;
  }

  @Override
  public final Item getItemDropped(IBlockState blockState, Random random, int unused)
  {
    if(single != null)
    {
      return Item.getItemFromBlock(single);
    } else
    {
      return Item.getItemFromBlock(this);
    }
  }

  @SideOnly(Side.CLIENT)
  @Override
  public final ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
  {
    int amount = (single == null)?1:2;
    return new ItemStack(getItemDropped(state,null,0),amount,damageDropped(state));
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> items)
  {
    if(!isDouble())
    {
      for(Variant v:getVariants())
      {
        items.add(new ItemStack(this, 1, v.id));
      }
    }
  }

  @SideOnly(Side.CLIENT)
  protected static boolean isSlab(Block b)
  {
    if(b instanceof BlockSlab)
    {
      return !((BlockSlab)b).isDouble();
    }
    return false;
  }

  @Override
  public boolean canSilkHarvest()
  {
    return false;
  }
    
  @Override
  public String getUnlocalizedName(int meta)
  {
    return String.format("%s_%s",getUnlocalizedName(),getStateFromMeta(meta).getValue(getVariantProperty()).material.name);
  }

  @Override
  public boolean isDouble()
  {
    return single != null;
  }

  @Override
  public IProperty<Variant> getVariantProperty()
  {
    if(property_variant == null)
    {
      property_variant = new PropertyVariant(getVariants());
    }
    return property_variant;
  }

  @Override
  public Comparable<?> getTypeForItem(ItemStack stack)
  {
    return getVariants()[stack.getMetadata()];
  }
  
  @Override
  protected BlockStateContainer createBlockState()
  {
    return new BlockStateContainer(this, new IProperty[] { HALF, getVariantProperty() });
  }

  @Override
  public IBlockState getStateFromMeta(int meta)
  {
    return getDefaultState()
        .withProperty(getVariantProperty(), getVariants()[meta & 7])
        .withProperty(HALF, ((meta >>> 3) & 1) == 1 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
  }

  @Override
  public int getMetaFromState(IBlockState state)
  {
    Variant var = (Variant) state.getValue(getVariantProperty());
    EnumBlockHalf half = (EnumBlockHalf) state.getValue(HALF);
    return var.id & 7 | ((half == EnumBlockHalf.TOP ? 1 : 0) << 3);
  }

  public IBlockState getBottomVariant(Variant v)
  {
    return this.getDefaultState()
        .withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM)
        .withProperty(getVariantProperty(), v);
  }

  public int getBottomVariantMeta(Variant v)
  {
    return getMetaFromState(getBottomVariant(v));
  }
}
