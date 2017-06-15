package exter.substratum.init;

import exter.substratum.material.EnumMaterial;
import net.minecraft.item.Item;

class InitEquipmentRecipes
{
  static void nuggetSmelting(Item item,EnumMaterial material)
  {
    /*
    GameRegistry.addSmelting(
        new ItemStack(item,1,OreDictionary.WILDCARD_VALUE),
        SubstratumItems.getStack(EnumMaterialItem.NUGGET, material), 0);
        */
  }
  
  static void init()
  {
    /*
    for(Entry<EnumMaterial, ItemPickaxeSubstratum> tool:SubstratumItems.pickaxes.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "III",
          " S ",
          " S ",
          'S',"stickWood",
          'I',"ingot" + tool.getKey().suffix));
      nuggetSmelting(tool.getValue(),tool.getKey());
    }
    
    for(Entry<EnumMaterial, ItemAxeSubstratum> tool:SubstratumItems.axes.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          "IS",
          " S",
          'S',"stickWood",
          'I',"ingot" + tool.getKey().suffix));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          "SI",
          "S ",
          'S',"stickWood",
          'I',"ingot" + tool.getKey().suffix));
      nuggetSmelting(tool.getValue(),tool.getKey());
    }
    
    for(Entry<EnumMaterial, ItemShovelSubstratum> tool:SubstratumItems.shovels.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "I",
          "S",
          "S",
          'S',"stickWood",
          'I',"ingot" + tool.getKey().suffix));
      nuggetSmelting(tool.getValue(),tool.getKey());
    }
    
    for(Entry<EnumMaterial, ItemHoeSubstratum> tool:SubstratumItems.hoes.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          " S",
          " S",
          'S',"stickWood",
          'I',"ingot" + tool.getKey().suffix));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "II",
          "S ",
          "S ",
          'S',"stickWood",
          'I',"ingot" + tool.getKey().suffix));
      nuggetSmelting(tool.getValue(),tool.getKey());
    }
    
    for(Entry<EnumMaterial, ItemSwordSubstratum> tool:SubstratumItems.swords.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tool.getValue()),
          "I",
          "I",
          "S",
          'S',"stickWood",
          'I',"ingot" + tool.getKey().suffix));
      nuggetSmelting(tool.getValue(),tool.getKey());
    }

    
    for(Entry<EnumMaterial, ItemArmorSubstratum> armor:SubstratumItems.helmets.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(armor.getValue()),
          "III",
          "I I",
          'I',"ingot" + armor.getKey().suffix));
      nuggetSmelting(armor.getValue(),armor.getKey());
    }

    for(Entry<EnumMaterial, ItemArmorSubstratum> armor:SubstratumItems.chestplates.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(armor.getValue()),
          "I I",
          "III",
          "III",
          'I',"ingot" + armor.getKey().suffix));
      nuggetSmelting(armor.getValue(),armor.getKey());
    }

    for(Entry<EnumMaterial, ItemArmorSubstratum> armor:SubstratumItems.leggings.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(armor.getValue()),
          "III",
          "I I",
          "I I",
          'I',"ingot" + armor.getKey().suffix));
      nuggetSmelting(armor.getValue(),armor.getKey());
    }

    for(Entry<EnumMaterial, ItemArmorSubstratum> armor:SubstratumItems.boots.entrySet())
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(armor.getValue()),
          "I I",
          "I I",
          'I',"ingot" + armor.getKey().suffix));
      nuggetSmelting(armor.getValue(),armor.getKey());
    }
    */
  }
}
