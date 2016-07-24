package exter.substratum.init;

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


public class InitRecipes
{
  static private void addDyeMix(EnumDyePowderColor result,String... ingredients)
  {
    Object[] ing_oredict = new Object[ingredients.length];

    for(int i = 0; i < ingredients.length; i++)
    {
      ing_oredict[i] = "dustDye" + ingredients[i]; 
    }
    GameRegistry.addRecipe(new ShapelessOreRecipe(
        SubstratumItems.item_dye_powder.getStack(result,ingredients.length),
        ing_oredict));
    
    for(int i = 0; i < ingredients.length; i++)
    {
      ing_oredict[i] = "dustSmallDye" + ingredients[i]; 
    }
    GameRegistry.addRecipe(new ShapelessOreRecipe(
        SubstratumItems.item_dye_powder_small.getStack(result,ingredients.length),
        ing_oredict));
  }
  
  @SuppressWarnings("deprecation")
  static private void initLegacy()
  {
    GameRegistry.addShapelessRecipe(
        SubstratumItems.getStack(EnumMaterialItem.DUST, EnumMaterial.REDSTONE,10),
        SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST, EnumMaterial.REDSTONE));
    GameRegistry.addShapelessRecipe(
        SubstratumItems.getStack(EnumMaterialItem.DUST, EnumMaterial.GLOWSTONE,4),
        SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST, EnumMaterial.GLOWSTONE));
    GameRegistry.addShapelessRecipe(
        SubstratumItems.getStack(EnumMaterialItem.DUST, EnumMaterial.ENDERPEARL,4),
        SubstratumItems.getStack(EnumMaterialItem.BUCKET_DUST, EnumMaterial.ENDERPEARL));
  }
  
  static public void init()
  {
    InitBlendRecipes.init();
    InitEquipmentRecipes.init();
    
    if(SubstratumConfig.alumina_nugget_smelting)
    {
      GameRegistry.addSmelting(
          SubstratumItems.getStack(EnumMaterialItem.NUGGET,EnumMaterial.ALUMINA),
          SubstratumItems.getStack(EnumMaterialItem.NUGGET,EnumMaterial.ALUMINIUM),0);
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
      
      // Dye powder mixing
      addDyeMix(EnumDyePowderColor.LIGHT_GRAY, "White", "Gray");
      addDyeMix(EnumDyePowderColor.LIGHT_GRAY, "Black", "White", "White");
      addDyeMix(EnumDyePowderColor.GRAY, "Black", "White");
      addDyeMix(EnumDyePowderColor.ORANGE, "Red", "Yellow");
      addDyeMix(EnumDyePowderColor.LIME, "Green", "White");
      addDyeMix(EnumDyePowderColor.LIGHT_BLUE, "Blue", "White");
      addDyeMix(EnumDyePowderColor.PINK, "Red", "White");
      addDyeMix(EnumDyePowderColor.CYAN, "Green", "Blue");
      addDyeMix(EnumDyePowderColor.PURPLE, "Red", "Blue");
      addDyeMix(EnumDyePowderColor.MAGENTA, "Pink", "Purple");
      addDyeMix(EnumDyePowderColor.MAGENTA, "White", "Red", "Red", "Blue");
      addDyeMix(EnumDyePowderColor.MAGENTA, "Pink", "Red", "Blue");
      addDyeMix(EnumDyePowderColor.BROWN, "Black", "Red", "Yellow");
      addDyeMix(EnumDyePowderColor.BROWN, "Black", "Orange");
    }

    ItemStack bucket = new ItemStack(Items.BUCKET);
    ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);

    //Dust -> Dust bucket
    if(SubstratumConfig.material_recipes.get(EnumMaterial.REDSTONE).dust_bottle)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BOTTLE_DUST,EnumMaterial.REDSTONE),
          bottle,
          "dustRedstone", 
          "dustRedstone", 
          "dustSmallRedstone", 
          "dustSmallRedstone"));
    }

    if(SubstratumConfig.material_recipes.get(EnumMaterial.GLOWSTONE).dust_bottle)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BOTTLE_DUST,EnumMaterial.GLOWSTONE),
          bottle,
          "dustGlowstone"));
    }

    if(SubstratumConfig.material_recipes.get(EnumMaterial.ENDERPEARL).dust_bottle)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.BOTTLE_DUST,EnumMaterial.ENDERPEARL),
          bottle,
          "dustEnderpearl"));
    }
    


    for(EnumMaterial mat:EnumMaterialItem.BUCKET_LIQUID.materials)
    {
      ItemStack liquid_bucket = SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID,mat);
      ItemStack liquid_bottle = SubstratumItems.getStack(EnumMaterialItem.BOTTLE_LIQUID,mat);
      ItemStack dust_bottle = SubstratumItems.getStack(EnumMaterialItem.BOTTLE_DUST,mat);
      if(dust_bottle != null && SubstratumConfig.material_recipes.get(mat).dust_bottle)
      {
        GameRegistry.addSmelting(
            dust_bottle,
            liquid_bottle,
            0);
      }

      if(liquid_bottle != null)
      {
        GameRegistry.addShapelessRecipe(
            SubstratumItems.getStack(EnumMaterialItem.BOTTLE_LIQUID,mat,4),
            liquid_bucket,
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
    
    initLegacy();
  }
}
