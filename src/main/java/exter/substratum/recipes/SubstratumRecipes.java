package exter.substratum.recipes;

import java.util.Map;

import exter.substratum.block.SubstratumBlocks;
import exter.substratum.config.SubstratumConfig;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumDyePowderColor;
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
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CUPRONICKEL),
          "dustCopper",
          "dustCopper",
          "dustNickel",
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
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ELECTRUM),
          "dustSmallGold", 
          "dustSmallGold", 
          "dustSmallSilver",
          "dustSmallSilver"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.ELECTRUM,2),
          "dustSmallGold", 
          "dustSmallSilver"));
    }

    if(SubstratumConfig.blend_steel_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.STEEL),
          "dustCoal", 
          "dustCoal", 
          "dustCoal", 
          "dustCoal", 
          "dustIron"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.STEEL),
          "dustSmallCoal", 
          "dustSmallCoal", 
          "dustSmallCoal", 
          "dustSmallCoal", 
          "dustSmallIron"));
    }

    if(SubstratumItems.item_mortar != null)
    {
      
      GameRegistry.addRecipe(
          new ItemStack(SubstratumItems.item_mortar),
          "  T",
          " F ",
          " S ",
          'F', new ItemStack(Items.FLINT),
          'T', new ItemStack(Items.STICK),
          'S', new ItemStack(Blocks.STONE)); 

      ItemStack mortar = new ItemStack(SubstratumItems.item_mortar,1,OreDictionary.WILDCARD_VALUE);

      if(SubstratumConfig.material_recipes.get(EnumMaterial.COAL).dust_from_ingot)
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.COAL),
            mortar, new ItemStack(Items.COAL,1,0)));
      }

      if(SubstratumConfig.material_recipes.get(EnumMaterial.CHARCOAL).dust_from_ingot)
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CHARCOAL),
            mortar, new ItemStack(Items.COAL,1,1)));
      }

      if(SubstratumConfig.material_recipes.get(EnumMaterial.OBSIDIAN).dust_from_ingot)
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.OBSIDIAN,2),
            mortar, new ItemStack(Blocks.OBSIDIAN)));
      }

      if(SubstratumConfig.material_recipes.get(EnumMaterial.ENDERPEARL).dust_from_ingot)
      {
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERPEARL),
            mortar, new ItemStack(Items.ENDER_PEARL)));
      }
      
      
      for(EnumMaterial mat:EnumMaterialItem.DUST.materials)
      {
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

      //Dye -> Dye powder
      if(SubstratumConfig.dye_enabled)
      {
        for(EnumDyePowderColor color:EnumDyePowderColor.values())
        {
          GameRegistry.addRecipe(new ShapelessOreRecipe(
                SubstratumItems.item_dye_powder.getStack(color),
                mortar, color.oredict));
        }
      }
    }

    for(EnumMaterial mat:EnumMaterialItem.DUST_SMALL.materials)
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
    }

    if(SubstratumConfig.dye_enabled)
    {
      for(EnumDyePowderColor color:EnumDyePowderColor.values())
      {
        //Dye powder -> Small dye powder
        GameRegistry.addRecipe(
            SubstratumItems.item_dye_powder_small.getStack(color,4),
            "  ",
            " D",
            'D', SubstratumItems.item_dye_powder.getStack(color));
      
        //Small dye powder -> Dye powder
        GameRegistry.addRecipe(
            SubstratumItems.item_dye_powder.getStack(color),
            "SS",
            "SS",
            'S', SubstratumItems.item_dye_powder_small.getStack(color));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(Blocks.STAINED_GLASS,2,color.dye.getMetadata()),
            color.oredict_small,
            Blocks.GLASS,
            Blocks.GLASS));
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(Blocks.STAINED_HARDENED_CLAY,2,color.dye.getMetadata()),
            color.oredict_small,
            Blocks.HARDENED_CLAY,
            Blocks.HARDENED_CLAY));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(
            new ItemStack(Blocks.STAINED_GLASS_PANE,5,color.dye.getMetadata()),
            color.oredict_small,
            Blocks.GLASS_PANE,
            Blocks.GLASS_PANE,
            Blocks.GLASS_PANE,
            Blocks.GLASS_PANE,
            Blocks.GLASS_PANE));
      }
      
      //Small dye powder mixing
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.LIGHT_GRAY,2),
          "dustSmallDyeWhite",
          "dustSmallDyeGray"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.LIGHT_GRAY,3),
          "dustSmallDyeBlack",
          "dustSmallDyeWhite",
          "dustSmallDyeWhite"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.GRAY,2),
          "dustSmallDyeBlack",
          "dustSmallDyeWhite"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.ORANGE,2),
          "dustSmallDyeRed",
          "dustSmallDyeYellow"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.LIME,2),
          "dustSmallDyeGreen",
          "dustSmallDyeWhite"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.LIGHT_BLUE,2),
          "dustSmallDyeBlue",
          "dustSmallDyeWhite"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.PINK,2),
          "dustSmallDyeRed",
          "dustSmallDyeWhite"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.CYAN,2),
          "dustSmallDyeGreen",
          "dustSmallDyeBlue"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.PURPLE,2),
          "dustSmallDyeRed",
          "dustSmallDyeBlue"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.MAGENTA,2),
          "dustSmallDyePink",
          "dustSmallDyePurple"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.MAGENTA,4),
          "dustSmallDyeWhite",
          "dustSmallDyeRed",
          "dustSmallDyeRed",
          "dustSmallDyeBlue"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.MAGENTA,3),
          "dustSmallDyePink",
          "dustSmallDyeRed",
          "dustSmallDyeBlue"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.BROWN,3),
          "dustSmallDyeBlack",
          "dustSmallDyeRed",
          "dustSmallDyeYellow"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder_small.getStack(EnumDyePowderColor.BROWN,2),
          "dustSmallDyeBlack",
          "dustSmallDyeOrange"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder.getStack(EnumDyePowderColor.BROWN,3),
          "dustDyeBlack",
          "dustDyeRed",
          "dustDyeYellow"));
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.item_dye_powder.getStack(EnumDyePowderColor.BROWN,2),
          "dustDyeBlack",
          "dustDyeOrange"));
    }

    //Dust -> Dust bucket
    ItemStack bucket = new ItemStack(Items.BUCKET);
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

    ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
    for(EnumMaterial mat:EnumMaterialItem.BUCKET_DUST.materials)
    {
      if(SubstratumConfig.material_recipes.get(mat).dust_bucket)
      {
        ItemStack liquid = SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID,mat);
        ItemStack liquid_bottle = SubstratumItems.getStack(EnumMaterialItem.BOTTLE_LIQUID,mat);
        GameRegistry.addSmelting(
            SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST,mat),
            liquid,
            0);
        
        GameRegistry.addShapelessRecipe(
            SubstratumItems.getStack(EnumMaterialItem.BOTTLE_LIQUID,mat,4),
            liquid,
            bottle, 
            bottle, 
            bottle, 
            bottle);

        GameRegistry.addShapelessRecipe(
            SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID,mat),
            bucket,
            liquid_bottle, 
            liquid_bottle, 
            liquid_bottle, 
            liquid_bottle);
      }
    }

    if(SubstratumConfig.blend_gunpowder_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          new ItemStack(Items.GUNPOWDER,2),
          "dustNiter", 
          "dustNiter", 
          "dustSulfur", 
          "dustCharcoal"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.GUNPOWDER,2),
          "dustSmallNiter", 
          "dustSmallNiter", 
          "dustSmallSulfur", 
          "dustSmallCharcoal"));
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
      
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.SIGNALUM),
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallSilver",
          "bottleLiquidRedstone"));
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

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.LUMIUM),
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallSilver",
          "bottleLiquidGlowstone" ));
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

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.ENDERIUM,2),
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallPlatinum",
          "bottleLiquidEnderpearl" ));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERIUM),
          "dustTin", 
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallPlatinum",
          "dustSmallPlatinum",
          "bottleLiquidEnderpearl",
          "bottleLiquidEnderpearl" ));
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
    ItemStack stick = new ItemStack(Items.STICK);
    ItemStack stone = new ItemStack(Blocks.COBBLESTONE);
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
      if(SubstratumConfig.material_recipes.get(entry.getKey()).slab_from_blocks)
      {
        ItemStack slabs = entry.getValue().copy();
        slabs.stackSize = 4;
        GameRegistry.addRecipe(new ShapedOreRecipe(
            slabs,
            "BB",
            'B', "block" + entry.getKey().suffix));
      }
    }
    
    //Stairs crafting recipes.
    for(Map.Entry<EnumMaterial, ItemStack> entry:SubstratumBlocks.stairs_stacks.entrySet())
    {
      if(SubstratumConfig.material_recipes.get(entry.getKey()).stairs_from_blocks)
      {
        ItemStack stairs = entry.getValue().copy();
        stairs.stackSize = 4;
        GameRegistry.addRecipe(new ShapedOreRecipe(
            stairs,
            " B",
            "BB",
            'B', "block" + entry.getKey().suffix)); 
      }
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
}
