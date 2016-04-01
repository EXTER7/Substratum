package exter.substratum.item;

import java.util.EnumMap;
import java.util.HashMap;
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
  static private class MaterialItem
  {
    public final EnumMaterialItem item;
    public final EnumMaterial material;

    public MaterialItem(EnumMaterialItem item, EnumMaterial material)
    {
      this.item = item;
      this.material = material;
    }
    
    @Override
    public int hashCode()
    {
      return item.ordinal() * 256 + material.ordinal();
    }
    
    @Override
    public boolean equals(Object obj)
    {
      if(this == obj)
      {
        return true;
      }
      if(obj == null)
      {
        return false;
      }
      if(getClass() != obj.getClass())
      {
        return false;
      }
      MaterialItem other = (MaterialItem) obj;
      if(item != other.item)
      {
        return false;
      }
      if(material != other.material)
      {
        return false;
      }
      return true;
    }
  }

  static public Map<EnumMaterialItem,ItemMaterial> item_materials = new EnumMap<EnumMaterialItem,ItemMaterial>(EnumMaterialItem.class);

  static public ItemMortar item_mortar = null;
  
  static private Map<MaterialItem,ItemStack> vanilla_items;
  
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
    item_materials.get(EnumMaterialItem.BOTTLE_LIQUID).setContainerItem(Items.glass_bottle);
    
    if(SubstratumConfig.misc_mortar_uses > 0)
    {
      item_mortar = new ItemMortar(SubstratumConfig.misc_mortar_uses);
      GameRegistry.registerItem(item_mortar, "mortar");
    }
    
    OreDictionary.registerOre("dustGunpowder", new ItemStack(Items.gunpowder));
    OreDictionary.registerOre("dustBlaze", new ItemStack(Items.blaze_powder));
    vanilla_items = new HashMap<MaterialItem,ItemStack>();
    vanilla_items.put(new MaterialItem(EnumMaterialItem.INGOT, EnumMaterial.IRON), new ItemStack(Items.iron_ingot));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.INGOT, EnumMaterial.GOLD), new ItemStack(Items.gold_ingot));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.NUGGET, EnumMaterial.GOLD), new ItemStack(Items.gold_nugget));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.REDSTONE), new ItemStack(Items.redstone));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.GLOWSTONE), new ItemStack(Items.glowstone_dust));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.GUNPOWDER), new ItemStack(Items.gunpowder));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.BLAZE), new ItemStack(Items.blaze_powder));
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
      ItemStack stack = vanilla_items.get(new MaterialItem(item, material));
      if(stack != null)
      {
        stack = stack.copy();
        stack.stackSize = amount;
        return stack;
      }
    }
    return item_materials.get(item).getStack(material, amount);
  }
}
