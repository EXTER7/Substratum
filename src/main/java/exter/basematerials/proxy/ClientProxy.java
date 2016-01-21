package exter.basematerials.proxy;

import java.util.Map;

import exter.basematerials.block.BMBlocks;
import exter.basematerials.block.BlockMetal;
import exter.basematerials.block.BlockMetalSlab;
import exter.basematerials.block.BlockMetalStairs;
import exter.basematerials.block.BlockOre;
import exter.basematerials.item.BMItems;
import exter.basematerials.item.ItemDust;
import exter.basematerials.item.ItemIngot;
import exter.basematerials.item.ItemNugget;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends CommonProxy
{
  
  private void registerItemModel(Block block,String name)
  {
    registerItemModel(Item.getItemFromBlock(block), name);
  }

  private void registerItemModel(Block block,String name,int meta)
  {
    registerItemModel(Item.getItemFromBlock(block), name, meta);
  }

  private void registerItemModel(Item item,String name)
  {
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
    .register(item, 0,
      new ModelResourceLocation("basematerials:" + name, "inventory"));
  }

  private void registerItemModel(Item item,String name,int meta)
  {
    name = "basematerials:" + name;
    ModelBakery.registerItemVariants(item, new ResourceLocation(name));
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
    .register(item, meta, new ModelResourceLocation(name, "inventory"));
  }

  @Override
  public void preInit()
  {
    
  }

  @Override
  public void init()
  {
   
    for(BlockOre.EnumMaterial ore:BlockOre.EnumMaterial.values())
    {
      registerItemModel(BMBlocks.block_ore,ore.oredict, ore.ordinal());
    }

    for(BlockMetal block:BMBlocks.block_metal)
    {
      for(BlockMetal.Variant v:block.getVariants())
      {
        registerItemModel(block,v.oredict, v.id);
      }
    }

    for(BlockMetalSlab block:BMBlocks.block_slab)
    {
      for(BlockMetalSlab.Variant v:block.getVariants())
      {
        registerItemModel(block,v.oredict, block.getBottomVariantMeta(v));
      }
    }

    for(Map.Entry<String, BlockMetalStairs> e:BMBlocks.stairs_blocks.entrySet())
    {
      registerItemModel(e.getValue(),e.getValue().oredict);
    }


    for(ItemIngot.EnumMaterial material:ItemIngot.EnumMaterial.values())
    {
      registerItemModel(BMItems.item_ingot,material.oredict, material.ordinal());
    }

    for(ItemDust.EnumMaterial material:ItemDust.EnumMaterial.values())
    {
      registerItemModel(BMItems.item_dust,material.oredict, material.ordinal());
    }

    for(ItemNugget.EnumMaterial material:ItemNugget.EnumMaterial.values())
    {
      registerItemModel(BMItems.item_nugget,material.oredict, material.ordinal());
    }
  }
  

  @Override
  public void postInit()
  {

  }

}
