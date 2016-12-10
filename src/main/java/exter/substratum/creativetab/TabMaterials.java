package exter.substratum.creativetab;

import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabMaterials extends CreativeTabs
{
  public static TabMaterials tab = new TabMaterials();

  private TabMaterials()
  {
    super("substratum");
  }
  
  @Override
  public ItemStack getIconItemStack()
  {
    return SubstratumItems.getStack(EnumMaterialItem.INGOT, EnumMaterial.COPPER);
  }

  @Override
  public ItemStack getTabIconItem()
  {
    return getIconItemStack();
  }
}
