package exter.substratum.block;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.material.EnumMaterial;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyHelper;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;


public abstract class BlockMetal extends Block implements IBlockVariants
{
  static public class Variant implements IStringSerializable,Comparable<Variant>
  {
    public final EnumMaterial material;
    public int id;
    
    public Variant(EnumMaterial material)
    {
      this.material = material;
      this.id = -1;
    }

    @Override
    public String getName()
    {
      return material.suffix_lc;
    }

    @Override
    public String toString()
    {
      return getName();
    }

    @Override
    public int compareTo(Variant o)
    {
      return id - o.id;
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


  public abstract Variant[] getVariants();
  
  public BlockMetal(String name)
  {
    super( Material.IRON );
    setHardness(1.0F);
    setResistance(8.0F);
    setUnlocalizedName("substratum.block");
    setRegistryName(name);
    setSoundType(SoundType.METAL);
    setCreativeTab(TabMaterials.tab);
  }
  
  @Override
  protected BlockStateContainer createBlockState()
  {
    if(property_variant == null)
    {
      property_variant = new PropertyVariant(getVariants());
    }
    return new BlockStateContainer(this, property_variant );
  }

  public IBlockState getVariantState(Variant var)
  {
    return getDefaultState().withProperty(property_variant, var);
  }
  
  @Override
  public IBlockState getStateFromMeta(int meta)
  {
    return getDefaultState()
        .withProperty(property_variant, getVariants()[meta]);
  }

  @Override
  public int getMetaFromState(IBlockState state)
  {
    return state.getValue(property_variant).id;
  }

  @Override
  public int damageDropped(IBlockState state)
  {
    return getMetaFromState(state);
  }
    
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
  {
    for(Variant v:getVariants())
    {
      list.add(new ItemStack(item, 1, v.id));
    }
  }
  
  @Override
  public String getUnlocalizedName(int meta)
  {
    return String.format("%s_%s", getUnlocalizedName(), getStateFromMeta(meta).getValue(property_variant).material.suffix_lc);
  }
}
