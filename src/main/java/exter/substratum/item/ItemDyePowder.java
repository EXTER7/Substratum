package exter.substratum.item;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.material.EnumDyePowderColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDyePowder extends Item
{
  public ItemDyePowder()
  {
    setCreativeTab(TabMaterials.tab);
    setHasSubtypes(true);
    setUnlocalizedName("substratum.dye");
    setRegistryName(getName());
  }
  
  protected String getName()
  {
    return "dye";
  }
  
  @Override
  public String getUnlocalizedName(ItemStack itemstack)
  {
    return String.format("%s_%s", getUnlocalizedName(), EnumDyePowderColor.values()[itemstack.getMetadata()].name);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> list)
  {
    for(EnumDyePowderColor dye:EnumDyePowderColor.values())
    {
      ItemStack itemstack = new ItemStack(this, 1, dye.ordinal());
      list.add(itemstack);
    }
  }

  public ItemStack getStack(EnumDyePowderColor color)
  {
    return getStack(color,1);
  }

  public ItemStack getStack(EnumDyePowderColor color,int amount)
  {
    return new ItemStack(this,amount,color.ordinal());
  }
}
