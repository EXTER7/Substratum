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
  

  static public String convertToOreDictionaryName(String str,boolean suffix)
  {
    if(str == null)
    {
      return null;
    }
    StringBuilder builder = new StringBuilder();
    char c = str.charAt(0);
    builder.append(suffix?Character.toUpperCase(c):c);
    for(int i = 1; i < str.length(); i++)
    {
      c = str.charAt(i);
      if(c == '_')
      {
        c = str.charAt(++i);
        builder.append(Character.toUpperCase(c));
      } else
      {
        builder.append(c);
      }
    }
    return builder.toString();
  }
}
