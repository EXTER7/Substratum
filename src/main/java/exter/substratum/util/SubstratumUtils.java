package exter.substratum.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


/**
 * Miscellaneous utility methods
 */
public class SubstratumUtils
{
  static public int divCeil(int a,int b)
  {
    return a / b + ((a % b == 0) ? 0 : 1);
  }

  static public String getItemOreDictionaryName(ItemStack stack)
  {
    for(String name:OreDictionary.getOreNames())
    {
      List<ItemStack> ores = OreDictionary.getOres(name);
      for(ItemStack i : ores)
      {
        if(i.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(i, stack))
        {
          return name;
        }
      }
    }
    return null;
  }
  
  static public String convertToRegistryName(String str)
  {
    if(str == null)
    {
      return null;
    }
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < str.length(); i++)
    {
      char c = str.charAt(i);
      if(Character.isUpperCase(c))
      {
        if(i > 0)
        {
          builder.append('_');
        }
        builder.append(Character.toLowerCase(c));
      } else
      {
        builder.append(c);
      }
    }
    return builder.toString();
  }
}
