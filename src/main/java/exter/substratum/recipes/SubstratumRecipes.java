package exter.substratum.recipes;

import java.util.Map;

import exter.substratum.block.SubstratumBlocks;
import exter.substratum.config.SubstratumConfig;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;


public class SubstratumRecipes
{
  static public void preInit()
  {

  }

  static public void init()
  {
    if(SubstratumConfig.recipe_bronze_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRONZE,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustTin"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRONZE),
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallTin"));
    }

    if(SubstratumConfig.recipe_brass_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRASS,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustZinc"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRASS),
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallZinc"));
    }

    if(SubstratumConfig.recipe_cupronickel_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CUPRONICKEL,2),
          "dustCopper",
          "dustNickel"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.CUPRONICKEL,2),
          "dustSmallCopper",
          "dustSmallNickel"));
    }

    if(SubstratumConfig.recipe_invar_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.INVAR,3),
          "dustIron", 
          "dustIron", 
          "dustNickel"));
      
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.INVAR,3),
          "dustSmallIron", 
          "dustSmallIron", 
          "dustSmallNickel"));
    }

    if(SubstratumConfig.recipe_electrum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ELECTRUM,2),
          "dustGold", 
          "dustSilver"));
      
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.ELECTRUM,2),
          "dustSmallGold", 
          "dustSmallSilver"));
    }
    
    if(SubstratumItems.item_mortar != null)
    {
      
      GameRegistry.addRecipe(
          new ItemStack(SubstratumItems.item_mortar),
          "  T",
          " F ",
          " S ",
          'F', new ItemStack(Items.flint),
          'T', new ItemStack(Items.stick),
          'S', new ItemStack(Blocks.stone)); 

      ItemStack mortar = new ItemStack(SubstratumItems.item_mortar,1,OreDictionary.WILDCARD_VALUE);

      if(SubstratumConfig.recipe_dusts_enable.get(EnumMaterial.COAL))
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.COAL),
            mortar, new ItemStack(Items.coal,1,0)));
      }

      if(SubstratumConfig.recipe_dusts_enable.get(EnumMaterial.CHARCOAL))
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CHARCOAL),
            mortar, new ItemStack(Items.coal,1,1)));
      }

      if(SubstratumConfig.recipe_dusts_enable.get(EnumMaterial.OBSIDIAN))
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.OBSIDIAN,2),
            mortar, new ItemStack(Blocks.obsidian)));
      }

      if(SubstratumConfig.recipe_dusts_enable.get(EnumMaterial.ENDERPEARL))
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERPEARL),
            mortar, new ItemStack(Items.ender_pearl)));
      }
      
      for(EnumMaterial mat:EnumMaterialItem.DUST.materials)
      {
        //Dust -> Small dust
        GameRegistry.addRecipe(new ShapedOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,mat,4),
            "  ",
            " D",
            'D', "dust" + mat.suffix));
        
        //Small dust -> Dust
        GameRegistry.addRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,mat),
            "SS",
            "SS",
            'S', SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,mat));

        if(SubstratumItems.getStack(EnumMaterialItem.INGOT,mat) != null)
        {
          if(SubstratumConfig.recipe_dusts_enable.get(mat))
          {
            GameRegistry.addRecipe(new ShapelessOreRecipe(
                SubstratumItems.getStack(EnumMaterialItem.DUST,mat),
                mortar, "ingot" + mat.suffix));
          }
        }
      }
    }

    ItemStack bucket = new ItemStack(Items.bucket);
    if(SubstratumConfig.recipe_buckets_enable.get(EnumMaterial.REDSTONE))
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.REDSTONE),
          bucket,
          "blockRedstone", 
          "dustRedstone"));
    }

    if(SubstratumConfig.recipe_buckets_enable.get(EnumMaterial.GLOWSTONE))
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.GLOWSTONE),
          bucket,
          "dustGlowstone", 
          "dustGlowstone", 
          "dustGlowstone", 
          "dustGlowstone"));
    }

    if(SubstratumConfig.recipe_buckets_enable.get(EnumMaterial.ENDERPEARL))
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.ENDERPEARL),
          bucket,
          "dustEnderpearl", 
          "dustEnderpearl", 
          "dustEnderpearl", 
          "dustEnderpearl"));
    }

    for(EnumMaterial mat:EnumMaterialItem.BUCKET_DUST.materials)
    {
      if(SubstratumConfig.recipe_buckets_enable.get(mat))
      {
        GameRegistry.addSmelting(
            SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,mat),
            SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID,mat),
            0);
      }
    }

    if(SubstratumConfig.recipe_gunpowder_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          new ItemStack(Items.gunpowder,2),
          "dustNiter", 
          "dustNiter", 
          "dustSulfur", 
          "dustCharcoal"));
    }

    if(SubstratumConfig.recipe_signalum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.SIGNALUM,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustSilver",
          "bucketLiquidRedstone"));
    }

    if(SubstratumConfig.recipe_lumium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.LUMIUM,4),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustSilver",
          "bucketLiquidGlowstone" ));
    }

    if(SubstratumConfig.recipe_enderium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERIUM,2),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustPlatinum",
          "bucketLiquidEnderpearl" ));
    }


    //Dust -> Ingot smelting recipes.
    for(EnumMaterial mat:EnumMaterialItem.DUST.materials)
    {
      ItemStack ingot = SubstratumItems.getStack(EnumMaterialItem.INGOT, mat);
      if(ingot != null)
      {
        GameRegistry.addSmelting(
            SubstratumItems.getStack(EnumMaterialItem.DUST, mat),
            ingot,
            0);
      }
    }

    //Nugget <-> Ingot crafting recipes.
    for(EnumMaterial mat:EnumMaterialItem.NUGGET.materials)
    {
      ItemStack ingot = SubstratumItems.getStack(EnumMaterialItem.INGOT, mat);
      GameRegistry.addShapelessRecipe(
          SubstratumItems.getStack(EnumMaterialItem.NUGGET, mat, 9),
          ingot);
      GameRegistry.addRecipe(
          ingot,
          "NNN",
          "NNN",
          "NNN",
          'N', SubstratumItems.getStack(EnumMaterialItem.NUGGET, mat)); 
    }

    //Block <-> Ingot crafting recipes.
    for(Map.Entry<EnumMaterial, ItemStack> entry:SubstratumBlocks.block_stacks.entrySet())
    {
      ItemStack block = entry.getValue();
      EnumMaterial mat = entry.getKey(); 
      GameRegistry.addShapelessRecipe(
          SubstratumItems.getStack(EnumMaterialItem.INGOT, mat, 9),
          block);
      GameRegistry.addRecipe(
          block,
          "III",
          "III",
          "III",
          'I', SubstratumItems.getStack(EnumMaterialItem.INGOT, mat)); 
    }

    //Ore -> ingot furnace recipes
    for(Map.Entry<EnumMaterial, ItemStack> metal:SubstratumBlocks.ore_stacks.entrySet())
    {
      ItemStack ingot = SubstratumItems.getStack(EnumMaterialItem.INGOT, metal.getKey());
      if(ingot != null)
      {
        GameRegistry.addSmelting(
            metal.getValue(),
            ingot,
            0);
      }
    }
    
    //Gear crafting recipes.
    ItemStack stick = new ItemStack(Items.stick);
    ItemStack stone = new ItemStack(Blocks.cobblestone);
    for(EnumMaterial mat:EnumMaterialItem.GEAR.materials)
    {
      if(SubstratumConfig.recipe_gears_enable.get(mat))
      {
        if(mat == EnumMaterial.STONE)
        {
          GameRegistry.addRecipe(new ShapedOreRecipe(
              SubstratumItems.getStack(EnumMaterialItem.GEAR, mat),
              " I ",
              "ISI",
              " I ",
              'I', stone,
              'S', stick)); 
        } else
        {
          GameRegistry.addRecipe(new ShapedOreRecipe(
              SubstratumItems.getStack(EnumMaterialItem.GEAR, mat),
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
      if(SubstratumConfig.recipe_plates_enable.get(mat))
      {
        GameRegistry.addRecipe(new ShapedOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.PLATE, mat, 3),
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
