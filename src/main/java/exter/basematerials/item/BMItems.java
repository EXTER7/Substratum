package exter.basematerials.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BMItems
{

  static public ItemIngot item_ingot;
  static public ItemDust item_dust;
  static public ItemNugget item_nugget;

   
  static public final Map<String,ItemStack> ingot_stacks = new HashMap<String,ItemStack>();
  static public final Map<String,ItemStack> dust_stacks = new HashMap<String,ItemStack>();
  static public final Map<String,ItemStack> nugget_stacks = new HashMap<String,ItemStack>();

  static public void registerItems(Configuration config)
  {
    
    item_ingot = new ItemIngot();
    item_dust = new ItemDust();
    item_nugget = new ItemNugget();
    
    GameRegistry.registerItem(item_ingot, "ingot");
    GameRegistry.registerItem(item_dust, "dust");
    GameRegistry.registerItem(item_nugget, "nugget");
    
    for (ItemIngot.EnumMaterial material:ItemIngot.EnumMaterial.values())
    {
      ItemStack is = ingot(material);
      OreDictionary.registerOre(material.oredict, is);
      ingot_stacks.put(material.name, is);
    }
    ingot_stacks.put("iron", new ItemStack(Items.iron_ingot));
    ingot_stacks.put("gold", new ItemStack(Items.gold_ingot));

    for (ItemDust.EnumMaterial material:ItemDust.EnumMaterial.values())
    {
      ItemStack is = dust(material);
      OreDictionary.registerOre(material.oredict, is);
      dust_stacks.put(material.name, is);
    }

    for (ItemNugget.EnumMaterial material:ItemNugget.EnumMaterial.values())
    {
      ItemStack is = nugget(material);
      OreDictionary.registerOre(material.oredict, is);
      nugget_stacks.put(material.name, is);
    }

  }

  static public ItemStack ingot(ItemIngot.EnumMaterial material)
  {
    return ingot(material,1);
  }

  static public ItemStack ingot(ItemIngot.EnumMaterial material,int amount)
  {
    return new ItemStack(item_ingot,amount,material.ordinal());
  }

  static public ItemStack dust(ItemDust.EnumMaterial material)
  {
    return dust(material,1);
  }

  static public ItemStack dust(ItemDust.EnumMaterial material,int amount)
  {
    return new ItemStack(item_dust,amount,material.ordinal());
  }

  static public ItemStack nugget(ItemNugget.EnumMaterial material)
  {
    return nugget(material,1);
  }

  static public ItemStack nugget(ItemNugget.EnumMaterial material,int amount)
  {
    return new ItemStack(item_nugget,amount,material.ordinal());
  }
}
