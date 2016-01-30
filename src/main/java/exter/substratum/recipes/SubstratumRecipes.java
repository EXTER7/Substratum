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
    if(SubstratumConfig.blend_bronze_enable)
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

    if(SubstratumConfig.blend_brass_enable)
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

    if(SubstratumConfig.blend_cupronickel_enable)
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

    if(SubstratumConfig.blend_invar_enable)
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

    if(SubstratumConfig.blend_electrum_enable)
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

      if(SubstratumConfig.material_recipes.get(EnumMaterial.COAL).dust_from_ingot)
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.COAL),
            mortar, new ItemStack(Items.coal,1,0)));
      }

      if(SubstratumConfig.material_recipes.get(EnumMaterial.CHARCOAL).dust_from_ingot)
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CHARCOAL),
            mortar, new ItemStack(Items.coal,1,1)));
      }

      if(SubstratumConfig.material_recipes.get(EnumMaterial.OBSIDIAN).dust_from_ingot)
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.OBSIDIAN,2),
            mortar, new ItemStack(Blocks.obsidian)));
      }

      if(SubstratumConfig.material_recipes.get(EnumMaterial.ENDERPEARL).dust_from_ingot)
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

        //Ingot -> Dust
        if(SubstratumItems.getStack(EnumMaterialItem.INGOT,mat) != null)
        {
          if(SubstratumConfig.material_recipes.get(mat).dust_from_ingot)
          {
            GameRegistry.addRecipe(new ShapelessOreRecipe(
                SubstratumItems.getStack(EnumMaterialItem.DUST,mat),
                mortar, "ingot" + mat.suffix));
          }
        }
      }
    }

    //Dust -> Dust bucket
    ItemStack bucket = new ItemStack(Items.bucket);
    if(SubstratumConfig.material_recipes.get(EnumMaterial.REDSTONE).dust_bucket)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.REDSTONE),
          bucket,
          "blockRedstone", 
          "dustRedstone"));
    }

    if(SubstratumConfig.material_recipes.get(EnumMaterial.GLOWSTONE).dust_bucket)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,EnumMaterial.GLOWSTONE),
          bucket,
          "dustGlowstone", 
          "dustGlowstone", 
          "dustGlowstone", 
          "dustGlowstone"));
    }

    if(SubstratumConfig.material_recipes.get(EnumMaterial.ENDERPEARL).dust_bucket)
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
      if(SubstratumConfig.material_recipes.get(mat).dust_bucket)
      {
        GameRegistry.addSmelting(
            SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,mat),
            SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID,mat),
            0);
      }
    }

    if(SubstratumConfig.blend_gunpowder_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          new ItemStack(Items.gunpowder,2),
          "dustNiter", 
          "dustNiter", 
          "dustSulfur", 
          "dustCharcoal"));
    }

    if(SubstratumConfig.blend_signalum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.SIGNALUM,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustSilver",
          "bucketLiquidRedstone"));
    }

    if(SubstratumConfig.blend_lumium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.LUMIUM,4),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustSilver",
          "bucketLiquidGlowstone" ));
    }

    if(SubstratumConfig.blend_enderium_enable)
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
      if(ingot != null && SubstratumConfig.material_recipes.get(mat).ingot_from_dust)
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
      if(SubstratumConfig.material_recipes.get(mat).gear_crafting)
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
          if(SubstratumConfig.cheaper_gear_recipes)
          {
            GameRegistry.addRecipe(new ShapedOreRecipe(
                SubstratumItems.getStack(EnumMaterialItem.GEAR, mat),
                " I ",
                "ISI",
                " I ",
                'I', "ingot" + mat.suffix,
                'S', stick)); 
          } else
          {
            GameRegistry.addRecipe(new ShapedOreRecipe(
                SubstratumItems.getStack(EnumMaterialItem.GEAR, mat),
                " P ",
                "PIP",
                " P ",
                'P', "plate" + mat.suffix,
                'I', "ingot" + mat.suffix)); 
          }
          if(SubstratumConfig.material_recipes.get(mat).ingots_from_gear)
          {
            ItemStack ingot = SubstratumItems.getStack(EnumMaterialItem.INGOT, mat, 4);
            if(ingot != null)
            {
              GameRegistry.addSmelting(SubstratumItems.getStack(EnumMaterialItem.GEAR, mat), ingot, 0);
            }
          }
        }
      }
    }

    //Slab crafting recipes.
    for(Map.Entry<EnumMaterial, ItemStack> entry:SubstratumBlocks.slab_stacks.entrySet())
    {
      ItemStack slabs = entry.getValue().copy();
      slabs.stackSize = 4;
      GameRegistry.addRecipe(new ShapedOreRecipe(
          slabs,
          "BB",
          'B', "block" + entry.getKey())); 
    }
    
    //Stairs crafting recipes.
    for(Map.Entry<EnumMaterial, ItemStack> entry:SubstratumBlocks.stairs_stacks.entrySet())
    {
      ItemStack stairs = entry.getValue().copy();
      stairs.stackSize = 4;
      GameRegistry.addRecipe(new ShapedOreRecipe(
          entry.getValue(),
          " B",
          "BB",
          'B', "block" + entry.getKey())); 
    }

    //Plate crafting recipes.
    for(EnumMaterial mat:EnumMaterialItem.PLATE.materials)
    {
      if(SubstratumConfig.material_recipes.get(mat).plate_crafting)
      {
        GameRegistry.addRecipe(new ShapedOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.PLATE, mat, SubstratumConfig.cheaper_plate_recipes ? 3 : 2),
            "III",
            "   ",
            "   ",
            'I', "ingot" + mat.suffix)); 
      }
      if(SubstratumConfig.material_recipes.get(mat).ingot_from_plate)
      {
        ItemStack ingot = SubstratumItems.getStack(EnumMaterialItem.INGOT,mat);
        if(ingot != null)
        {
          GameRegistry.addSmelting(SubstratumItems.getStack(EnumMaterialItem.PLATE, mat), ingot, 0);
        }
      }
    }
    
    //Rod crafting recipes.
    for(EnumMaterial mat:EnumMaterialItem.ROD.materials)
    {
      if(SubstratumConfig.material_recipes.get(mat).rod_crafting)
      {
        GameRegistry.addRecipe(new ShapedOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.ROD, mat, SubstratumConfig.cheaper_rod_recipes ? 6 : 4),
            "I  ",
            "I  ",
            "I  ",
            'I', "ingot" + mat.suffix)); 
      }
      if(SubstratumItems.item_mortar != null && SubstratumConfig.material_recipes.get(mat).dust_from_rod)
      {
        ItemStack mortar = new ItemStack(SubstratumItems.item_mortar,1,OreDictionary.WILDCARD_VALUE);
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,mat,2),
            mortar, SubstratumItems.getStack(EnumMaterialItem.ROD, mat)));
      }
    }
  }
  
  static public void postInit()
  {
    
  }
}