package exter.substratum.item;

import java.util.List;

import com.google.common.collect.ImmutableList;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.material.EnumMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaterial extends Item
{
  public final ImmutableList<EnumMaterial> materials;
  public final String prefix;


  public ItemMaterial(String prefix,ImmutableList<EnumMaterial> materials)
  {
    super();
    this.materials = materials;
    this.prefix = prefix;
    setCreativeTab(TabMaterials.tab);
    setHasSubtypes(true);
    setUnlocalizedName("substratum." + prefix);
  }
  
  @Override
  public String getUnlocalizedName(ItemStack itemstack)
  {
    return getUnlocalizedName() + materials.get(itemstack.getMetadata()).suffix;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list)
  {
    int i;
    for(i = 0; i < materials.size(); i++)
    {
      ItemStack itemstack = new ItemStack(this, 1, i);
      list.add(itemstack);
    }
  }
  
  public int getMaterialMeta(EnumMaterial material)
  {
    return materials.indexOf(material);
  }

  public ItemStack getStack(EnumMaterial material)
  {
    return getStack(material,1);
  }

  public ItemStack getStack(EnumMaterial material,int amount)
  {
    int meta = getMaterialMeta(material);
    if(meta == -1)
    {
      return null;
    }
    return new ItemStack(this,amount,meta);
  }
}