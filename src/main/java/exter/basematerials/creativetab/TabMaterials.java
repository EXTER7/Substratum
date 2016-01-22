package exter.basematerials.creativetab;

import exter.basematerials.item.BMItems;
import exter.basematerials.material.EnumMaterial;
import exter.basematerials.material.EnumMaterialItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabMaterials extends CreativeTabs
{
  public static TabMaterials tab = new TabMaterials();

  private TabMaterials()
  {
    super("baseMaterials");
  }
  
  @Override
  public ItemStack getIconItemStack()
  {
    return BMItems.getStack(EnumMaterialItem.INGOT, EnumMaterial.COPPER);
  }

  @Override
  public Item getTabIconItem()
  {
    return null;
  }
}
