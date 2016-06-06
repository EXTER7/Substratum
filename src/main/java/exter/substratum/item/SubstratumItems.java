package exter.substratum.item;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import exter.substratum.config.SubstratumConfig;
import exter.substratum.material.EnumDyePowderColor;
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
  
  static public ItemDyePowder item_dye_powder = null;
  static public ItemDyePowderSmall item_dye_powder_small = null;
  
  static private Map<MaterialItem,ItemStack> vanilla_items;
  
  static public void registerItems(Configuration config)
  {
    for(EnumMaterialItem matitem:EnumMaterialItem.values())
    {
      ItemMaterial item = new ItemMaterial(matitem.prefix,matitem.materials);
      item_materials.put(matitem, item);
      GameRegistry.register(item);
      for(EnumMaterial mat:matitem.materials)
      {
        OreDictionary.registerOre(matitem.prefix + mat.suffix, item.getStack(mat));
      }
    }
    for(ItemMaterial matitem:item_materials.values())
    {
      ItemStack stack = matitem.getStack(EnumMaterial.ALUMINIUM);
      if(stack != null)
      {
        OreDictionary.registerOre(matitem.prefix + "Aluminum", stack);
      }
      stack = matitem.getStack(EnumMaterial.CHROMIUM);
      if(stack != null)
      {
        OreDictionary.registerOre(matitem.prefix + "Chromium", stack);
      }
    }
    item_materials.get(EnumMaterialItem.BUCKET_DUST).setContainerItem(Items.BUCKET).setMaxStackSize(1);
    item_materials.get(EnumMaterialItem.BUCKET_LIQUID).setContainerItem(Items.BUCKET).setMaxStackSize(1);
    item_materials.get(EnumMaterialItem.BOTTLE_LIQUID).setContainerItem(Items.GLASS_BOTTLE);
    
    if(SubstratumConfig.misc_mortar_uses > 0)
    {
      item_mortar = new ItemMortar(SubstratumConfig.misc_mortar_uses);
      GameRegistry.register(item_mortar);
    }
    
    if(SubstratumConfig.dye_enabled)
    {
      item_dye_powder = new ItemDyePowder();
      item_dye_powder_small = new ItemDyePowderSmall();
      
      GameRegistry.register(item_dye_powder);
      GameRegistry.register(item_dye_powder_small);
      
      for(EnumDyePowderColor color:EnumDyePowderColor.values())
      {
        OreDictionary.registerOre(color.oredict, item_dye_powder.getStack(color));
        OreDictionary.registerOre(color.oredict_small, item_dye_powder_small.getStack(color));
        OreDictionary.registerOre(color.oredict_dust, item_dye_powder.getStack(color));
        OreDictionary.registerOre(color.oredict_dust_small, item_dye_powder_small.getStack(color));
      }
    }
    OreDictionary.registerOre("dustGunpowder", new ItemStack(Items.GUNPOWDER));
    OreDictionary.registerOre("dustBlaze", new ItemStack(Items.BLAZE_POWDER));
    vanilla_items = new HashMap<MaterialItem,ItemStack>();
    vanilla_items.put(new MaterialItem(EnumMaterialItem.INGOT, EnumMaterial.IRON), new ItemStack(Items.IRON_INGOT));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.INGOT, EnumMaterial.GOLD), new ItemStack(Items.GOLD_INGOT));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.NUGGET, EnumMaterial.GOLD), new ItemStack(Items.GOLD_NUGGET));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.REDSTONE), new ItemStack(Items.REDSTONE));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.GLOWSTONE), new ItemStack(Items.GLOWSTONE_DUST));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.GUNPOWDER), new ItemStack(Items.GUNPOWDER));
    vanilla_items.put(new MaterialItem(EnumMaterialItem.DUST, EnumMaterial.BLAZE), new ItemStack(Items.BLAZE_POWDER));
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
