package exter.basematerials.recipes;

import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import exter.basematerials.block.BMBlocks;
import exter.basematerials.config.BMConfig;
import exter.basematerials.item.BMItems;
import exter.basematerials.material.EnumMaterial;
import exter.basematerials.material.EnumMaterialItem;


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
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRONZE,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustTin"));
    }

    if(BMConfig.recipe_brass_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRASS,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustZinc"));
    }

    if(BMConfig.recipe_cupronickel_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CUPRONICKEL,2),
          "dustCopper",
          "dustNickel"));
    }

    if(BMConfig.recipe_invar_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.INVAR,3),
          "dustIron", 
          "dustIron", 
          "dustNickel"));
    }

    if(BMConfig.recipe_electrum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ELECTRUM,2),
          "dustGold", 
          "dustSilver"));
    }
    
    if(BMItems.item_mortar != null)
    {
      
      GameRegistry.addRecipe(
          new ItemStack(BMItems.item_mortar),
          "  T",
          " F ",
          " S ",
          'F', new ItemStack(Items.flint),
          'T', new ItemStack(Items.stick),
          'S', new ItemStack(Blocks.stone)); 

      ItemStack mortar = new ItemStack(BMItems.item_mortar,1,OreDictionary.WILDCARD_VALUE);

      if(BMConfig.recipe_dusts_enable.get(EnumMaterial.COAL))
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.COAL),
            mortar, new ItemStack(Items.coal,1,0)));
      }

      if(BMConfig.recipe_dusts_enable.get(EnumMaterial.ENDERPEARL))
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERPEARL),
            mortar, new ItemStack(Items.ender_pearl)));
      }
      
      for(EnumMaterial mat:EnumMaterialItem.INGOT.materials)
      {
        if(BMConfig.recipe_dusts_enable.get(mat))
        {
          GameRegistry.addRecipe(new ShapelessOreRecipe(
              BMItems.getStack(EnumMaterialItem.DUST,mat),
              mortar, "ingot" + mat.suffix));
        }
      }
    }

    ItemStack bucket = new ItemStack(Items.bucket);
    if(BMConfig.recipe_buckets_enable.get(EnumMaterial.REDSTONE))
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.REDSTONE),
          bucket,
          "blockRedstone", 
          "dustRedstone"));
    }

    if(BMConfig.recipe_buckets_enable.get(EnumMaterial.GLOWSTONE))
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.GLOWSTONE),
          bucket,
          "dustGlowstone", 
          "dustGlowstone", 
          "dustGlowstone", 
          "dustGlowstone"));
    }

    if(BMConfig.recipe_buckets_enable.get(EnumMaterial.ENDERPEARL))
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.ENDERPEARL),
          bucket,
          "dustEnderpearl", 
          "dustEnderpearl", 
          "dustEnderpearl", 
          "dustEnderpearl"));
    }

    for(EnumMaterial mat:EnumMaterialItem.BUCKET_DUST.materials)
    {
      if(BMConfig.recipe_buckets_enable.get(mat))
      {
        GameRegistry.addSmelting(
            BMItems.getStack(EnumMaterialItem.BUCKET_DUST,mat),
            BMItems.getStack(EnumMaterialItem.BUCKET_LIQUID,mat),
            0);
      }
    }
    
    if(BMConfig.recipe_signalum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.SIGNALUM,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustSilver",
          "bucketLiquidRedstone"));
    }

    if(BMConfig.recipe_lumium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.LUMIUM,4),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustSilver",
          "bucketLiquidGlowstone" ));
    }

    if(BMConfig.recipe_enderium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          BMItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERIUM,2),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustPlatinum",
          "bucketLiquidEnderpearl" ));
    }


    //Dust -> Ingot smelting recipes.
    for(EnumMaterial mat:EnumMaterialItem.INGOT.materials)
    {
      GameRegistry.addSmelting(
          BMItems.getStack(EnumMaterialItem.DUST, mat),
          BMItems.getStack(EnumMaterialItem.INGOT, mat),
          0);
    }

    //Nugget <-> Ingot crafting recipes.
    for(EnumMaterial mat:EnumMaterialItem.NUGGET.materials)
    {
      ItemStack ingot = BMItems.getStack(EnumMaterialItem.INGOT, mat);
      GameRegistry.addShapelessRecipe(
          BMItems.getStack(EnumMaterialItem.NUGGET, mat, 9),
          ingot);
      GameRegistry.addRecipe(
          ingot,
          "NNN",
          "NNN",
          "NNN",
          'N', BMItems.getStack(EnumMaterialItem.NUGGET, mat)); 
    }

    //Block <-> Ingot crafting recipes.
    for(Map.Entry<EnumMaterial, ItemStack> entry:BMBlocks.block_stacks.entrySet())
    {
      ItemStack block = entry.getValue();
      EnumMaterial mat = entry.getKey(); 
      GameRegistry.addShapelessRecipe(
          BMItems.getStack(EnumMaterialItem.INGOT, mat, 9),
          block);
      GameRegistry.addRecipe(
          block,
          "III",
          "III",
          "III",
          'I', BMItems.getStack(EnumMaterialItem.INGOT, mat)); 
    }

    //Ore -> ingot furnace recipes
    for(Map.Entry<EnumMaterial, ItemStack> metal:BMBlocks.ore_stacks.entrySet())
    {
      GameRegistry.addSmelting(
          metal.getValue(),
          BMItems.getStack(EnumMaterialItem.INGOT, metal.getKey()),
          0);
    }
    
    //Gear crafting recipes.
    ItemStack stick = new ItemStack(Items.stick);
    ItemStack stone = new ItemStack(Blocks.cobblestone);
    for(EnumMaterial mat:EnumMaterialItem.GEAR.materials)
    {
      if(BMConfig.recipe_gears_enable.get(mat))
      {
        if(mat == EnumMaterial.STONE)
        {
          GameRegistry.addRecipe(new ShapedOreRecipe(
              BMItems.getStack(EnumMaterialItem.GEAR, mat),
              " I ",
              "ISI",
              " I ",
              'I', stone,
              'S', stick)); 
        } else
        {
          GameRegistry.addRecipe(new ShapedOreRecipe(
              BMItems.getStack(EnumMaterialItem.GEAR, mat),
              " I ",
              "ISI",
              " I ",
              'I', "ingot" + mat.suffix,
              'S', stick)); 
        }
      }
    }
    
    //Plate crafting recipes.
    for(EnumMaterial mat:EnumMaterialItem.PLATE.materials)
    {
      if(BMConfig.recipe_plates_enable.get(mat))
      {
        GameRegistry.addRecipe(new ShapedOreRecipe(
            BMItems.getStack(EnumMaterialItem.PLATE, mat, 3),
            "III",
            "   ",
            "   ",
            'I', "ingot" + mat.suffix)); 
      }
    }
  }
  
  static public void postInit()
  {
    
  }
}
