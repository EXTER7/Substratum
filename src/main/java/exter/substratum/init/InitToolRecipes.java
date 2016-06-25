package exter.substratum.init;

import java.util.Map.Entry;

import exter.substratum.item.SubstratumItems;
import exter.substratum.item.equipment.ItemAxeSubstratum;
import exter.substratum.item.equipment.ItemHoeSubstratum;
import exter.substratum.item.equipment.ItemPickaxeSubstratum;
import exter.substratum.item.equipment.ItemShovelSubstratum;
import exter.substratum.item.equipment.ItemSwordSubstratum;
import exter.substratum.material.EnumMaterial;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

class InitToolRecipes
{
  static void init()
  {
    ItemStack stick = new ItemStack(Items.STICK);
    for(Entry<EnumMaterial, ItemPickaxeSubstratum> tool:SubstratumItems.pickaxes.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "III",
          " S ",
          " S ",
          'S',stick,
          'I',"ingot" + tool.getKey().suffix
          ));
    }
    
    for(Entry<EnumMaterial, ItemAxeSubstratum> tool:SubstratumItems.axes.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          "IS",
          " S",
          'S',stick,
          'I',"ingot" + tool.getKey().suffix
          ));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          "SI",
          "S",
          'S',stick,
          'I',"ingot" + tool.getKey().suffix
          ));
    }
    
    for(Entry<EnumMaterial, ItemShovelSubstratum> tool:SubstratumItems.shovels.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "I",
          "S",
          "S",
          'S',stick,
          'I',"ingot" + tool.getKey().suffix
          ));
    }
    
    for(Entry<EnumMaterial, ItemHoeSubstratum> tool:SubstratumItems.hoes.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          " S",
          " S",
          'S',stick,
          'I',"ingot" + tool.getKey().suffix
          ));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          "S ",
          "S",
          'S',stick,
          'I',"ingot" + tool.getKey().suffix
          ));
    }
    
    for(Entry<EnumMaterial, ItemSwordSubstratum> tool:SubstratumItems.swords.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "I",
          "I",
          "S",
          'S',stick,
          'I',"ingot" + tool.getKey().suffix
          ));
    }
  }
}
