package exter.basematerials.item;

import java.util.EnumMap;
import java.util.Map;

import exter.basematerials.material.EnumMaterial;
import exter.basematerials.material.EnumMaterialItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BMItems
{

  static private Map<EnumMaterialItem,ItemMaterial> item_materials = new EnumMap<EnumMaterialItem,ItemMaterial>(EnumMaterialItem.class);

  static public void registerItems(Configuration config)
  {
    for(EnumMaterialItem matitem:EnumMaterialItem.values())
    {
      ItemMaterial item = new ItemMaterial(matitem.prefix,matitem.materials);
      item_materials.put(matitem, item);
      GameRegistry.registerItem(item, matitem.prefix);
      for(EnumMaterial mat:matitem.materials)
      {
        OreDictionary.registerOre(matitem.prefix + mat.suffix, item.getStack(mat));
      }
    }    
  }


  static public ItemStack getStack(EnumMaterialItem item, EnumMaterial material)
  {
    return getStack(item, material,1);
  }

  static public ItemStack getStack(EnumMaterialItem item, EnumMaterial material,int amount)
  {
    return item_materials.get(item).getStack(material, amount);
  }
}
