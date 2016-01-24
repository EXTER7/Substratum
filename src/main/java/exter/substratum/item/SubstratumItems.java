package exter.substratum.item;

import java.util.EnumMap;
import java.util.Map;

import exter.substratum.config.SubstratumConfig;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class SubstratumItems
{

  static public Map<EnumMaterialItem,ItemMaterial> item_materials = new EnumMap<EnumMaterialItem,ItemMaterial>(EnumMaterialItem.class);

  static public ItemMortar item_mortar = null;
  
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
    item_materials.get(EnumMaterialItem.BUCKET_DUST).setContainerItem(Items.bucket).setMaxStackSize(1);
    item_materials.get(EnumMaterialItem.BUCKET_LIQUID).setContainerItem(Items.bucket).setMaxStackSize(1);
    
    if(SubstratumConfig.misc_mortar_uses > 0)
    {
      item_mortar = new ItemMortar(SubstratumConfig.misc_mortar_uses);
      GameRegistry.registerItem(item_mortar, "mortar");
    }
  }


  static public ItemStack getStack(EnumMaterialItem item, EnumMaterial material)
  {
    return getStack(item, material, 1, true);
  }

  static public ItemStack getStack(EnumMaterialItem item, EnumMaterial material,int amount)
  {
    return getStack(item, material, amount, true);
  }

  static public ItemStack getStack(EnumMaterialItem item, EnumMaterial material, boolean vanilla)
  {
    return getStack(item, material, 1, vanilla);
  }

  static public ItemStack getStack(EnumMaterialItem item, EnumMaterial material,int amount, boolean vanilla)
  {
    if(vanilla)
    {
      if(item == EnumMaterialItem.INGOT)
      {
        if(material == EnumMaterial.IRON)
        {
          return new ItemStack(Items.iron_ingot, amount);
        }
        if(material == EnumMaterial.GOLD)
        {
          return new ItemStack(Items.gold_ingot, amount);
        }
      }
      if(item == EnumMaterialItem.NUGGET && material == EnumMaterial.GOLD)
      {
        return new ItemStack(Items.gold_nugget, amount);
      }
    }
    return item_materials.get(item).getStack(material, amount);
  }
}
