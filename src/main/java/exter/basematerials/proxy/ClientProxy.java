package exter.basematerials.proxy;

import java.util.Map;

import exter.basematerials.block.BMBlocks;
import exter.basematerials.block.BlockDustOre;
import exter.basematerials.block.BlockMetal;
import exter.basematerials.block.BlockMetalSlab;
import exter.basematerials.block.BlockOre;
import exter.basematerials.item.BMItems;
import exter.basematerials.material.EnumMaterial;
import exter.basematerials.material.EnumMaterialItem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends CommonProxy
{
  static private class SimpleItemMeshDefinition implements ItemMeshDefinition
  {
    public final ModelResourceLocation location;
    
    public SimpleItemMeshDefinition(ModelResourceLocation location)
    {
      this.location = location;
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack)
    {
      return location;
    }    
  }
  
  
  private void registerItemModel(Block block,String name,int meta)
  {
    registerItemModel(Item.getItemFromBlock(block), name, meta);
  }

  private void registerItemModel(Item item,String name,int meta)
  {
    name = "basematerials:" + name;
    ModelBakery.registerItemVariants(item, new ResourceLocation(name));
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
    .register(item, meta, new ModelResourceLocation(name, "inventory"));
  }

  private void registerItemModel(Item item,String name)
  {
    name = "basematerials:" + name;
    ModelBakery.registerItemVariants(item, new ResourceLocation(name));
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
    .register(item, new SimpleItemMeshDefinition(new ModelResourceLocation(name, "inventory")));
  }

  @Override
  public void preInit()
  {
    
  }

  @Override
  public void init()
  {
   
    for(BlockOre.EnumVariant ore:BlockOre.EnumVariant.values())
    {
      registerItemModel(BMBlocks.block_ore,"ore" + ore.material.suffix, ore.ordinal());
    }

    for(BlockDustOre.EnumVariant ore:BlockDustOre.EnumVariant.values())
    {
      registerItemModel(BMBlocks.block_ore_dust,"ore" + ore.material.suffix, ore.ordinal());
    }

    for(BlockMetal block:BMBlocks.block_metal)
    {
      for(BlockMetal.Variant v:block.getVariants())
      {
        registerItemModel(block,"block" + v.material.suffix, v.id);
      }
    }

    for(BlockMetalSlab block:BMBlocks.block_slab)
    {
      for(BlockMetalSlab.Variant v:block.getVariants())
      {
        registerItemModel(block,"slab" + v.material.suffix, block.getBottomVariantMeta(v));
      }
    }

    for(Map.Entry<EnumMaterial, ItemStack> e:BMBlocks.stairs_stacks.entrySet())
    {
      ItemStack item = e.getValue();
      registerItemModel(item.getItem(),"stairs" + e.getKey().suffix,item.getMetadata());
    }


    for(EnumMaterialItem matitem:EnumMaterialItem.values())
    {
      for(EnumMaterial material:EnumMaterial.values())
      {
        ItemStack item = BMItems.getStack(matitem, material, false);
        if(item != null)
        {
          registerItemModel(item.getItem(),matitem.prefix + material.suffix, item.getMetadata());
        }
      }
    }
    
    registerItemModel(BMItems.item_mortar,"mortar");
  }
  

  @Override
  public void postInit()
  {

  }

}
