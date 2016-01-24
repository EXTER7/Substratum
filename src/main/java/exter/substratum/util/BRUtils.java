package exter.substratum.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;


/**
 * Miscellaneous utility methods
 */
public class BRUtils
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

  @SuppressWarnings("deprecation")
  static public ItemStack getModItemFromOreDictionary(String modid,String orename)
  {
    modid = modid.toLowerCase();
    for(ItemStack is:OreDictionary.getOres(orename))
    {
      if(GameRegistry.findUniqueIdentifierFor(is.getItem()).modId.equals(modid))
      {
        is = is.copy();
        is.stackSize = 1;
        return is;
      }
    }
    return null;
  }
}
