package exter.basematerials.recipes;

import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import exter.basematerials.block.BMBlocks;
import exter.basematerials.config.BMConfig;
import exter.basematerials.item.BMItems;
import exter.basematerials.item.ItemDust;


public class BMRecipes
{
  static public void preInit()
  {

  }

  static public void init()
  {
    if(BMConfig.recipe_bronze_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.BRONZE,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustTin"));
    }

    if(BMConfig.recipe_brass_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.BRASS,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustZinc"));
    }

    if(BMConfig.recipe_cupronickel_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.CUPRONICKEL,2),
          "dustCopper",
          "dustNickel"));
    }

    if(BMConfig.recipe_invar_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.INVAR,3),
          "dustIron", 
          "dustIron", 
          "dustNickel"));
    }

    if(BMConfig.recipe_electrum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.ELECTRUM,2),
          "dustGold", 
          "dustSilver"));
    }
    
    if(BMConfig.recipe_coaldust_enable)
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.COAL,1),
          "FCF",
          'F', new ItemStack(Items.flint), 
          'C', new ItemStack(Items.coal,1,0)));
    }

    if(BMConfig.recipe_enderpearldust_enable)
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.ENDERPEARL,1),
          "FEF",
          'F', new ItemStack(Items.flint), 
          'E', new ItemStack(Items.ender_pearl)));
    }

    if(BMConfig.recipe_signalum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.SIGNALUM,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustSilver",
          "blockRedstone", 
          "dustRedstone"));
    }

    if(BMConfig.recipe_lumium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.LUMIUM,4),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustSilver",
          "dustGlowstone",
          "dustGlowstone",
          "dustGlowstone",
          "dustGlowstone" ));
    }

    if(BMConfig.recipe_enderium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.dust(ItemDust.EnumMaterial.ENDERIUM,2),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustPlatinum",
          "dustEnderpearl",
          "dustEnderpearl",
          "dustEnderpearl",
          "dustEnderpearl" ));
    }


    //Dust -> Ingot smelting recipes.
    for(Map.Entry<String, ItemStack> metal:BMItems.dust_stacks.entrySet())
    {
      GameRegistry.addSmelting(
          metal.getValue(),
          BMItems.ingot_stacks.get(metal.getKey()),
          0);
    }

    //Nugget <-> Ingot crafting recipes.
    for(Map.Entry<String, ItemStack> metal:BMItems.nugget_stacks.entrySet())
    {
      ItemStack nuggets = metal.getValue().copy();
      nuggets.stackSize = 9;
      GameRegistry.addShapelessRecipe(
          nuggets,
          BMItems.ingot_stacks.get(metal.getKey()));
      GameRegistry.addRecipe(new ShapedOreRecipe(
          BMItems.ingot_stacks.get(metal.getKey()),
          "NNN",
          "NNN",
          "NNN",
          'N', metal.getValue())); 
    }


    //TODO Ore -> ingot furnace recipes
    for(Map.Entry<String, ItemStack> metal:BMBlocks.ore_stacks.entrySet())
    {
      GameRegistry.addSmelting(
          metal.getValue(),
          BMItems.ingot_stacks.get(metal.getKey()),
          0);
    }
    
//    BRUtils.registerOreSmelting(BlockFoundryOre.EnumOre.COPPER,ItemIngot.INGOT_COPPER);
//    BRUtils.registerOreSmelting(BlockFoundryOre.EnumOre.TIN,ItemIngot.INGOT_TIN);
//    BRUtils.registerOreSmelting(BlockFoundryOre.EnumOre.ZINC,ItemIngot.INGOT_ZINC);
//    BRUtils.registerOreSmelting(BlockFoundryOre.EnumOre.NICKEL,ItemIngot.INGOT_NICKEL);
//    BRUtils.registerOreSmelting(BlockFoundryOre.EnumOre.SILVER,ItemIngot.INGOT_SILVER);
//    BRUtils.registerOreSmelting(BlockFoundryOre.EnumOre.LEAD,ItemIngot.INGOT_LEAD);
    
  }

  static public void postInit()
  {
  }
}
