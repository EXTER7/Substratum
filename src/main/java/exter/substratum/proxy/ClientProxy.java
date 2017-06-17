package exter.substratum.proxy;

import java.util.Map;

import exter.substratum.block.SubstratumBlocks;
import exter.substratum.config.SubstratumConfig;
import exter.substratum.fluid.SubstratumFluids;
import exter.substratum.block.BlockDustOre;
import exter.substratum.block.BlockMetal;
import exter.substratum.block.BlockMetalSlab;
import exter.substratum.block.BlockOre;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumDyePowderColor;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialFluid;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;

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
    name = "substratum:" + name;
    ModelBakery.registerItemVariants(item, new ResourceLocation(name));
    ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(name));
  }

  private void registerItemModel(Item item,String name)
  {
    registerItemModel(item,name,0);
  }
  
  @Override
  public void preInit()
  {
    for(EnumMaterialFluid fluid_material:EnumMaterialFluid.values())
    {
      Block block = SubstratumFluids.fluids.get(fluid_material.material).getBlock();
      Item item = Item.getItemFromBlock(block);
      ModelBakery.registerItemVariants(item);
      ModelLoader.setCustomMeshDefinition( item, new SimpleItemMeshDefinition(new ModelResourceLocation("substratum:liquid_" + fluid_material.material.name)));
      ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockFluidBase.LEVEL).build());
    }
    for(BlockOre.EnumVariant ore:BlockOre.EnumVariant.values())
    {
      registerItemModel(SubstratumBlocks.block_ore,"ore_" + ore.material.name, ore.ordinal());
    }

    for(BlockDustOre.EnumVariant ore:BlockDustOre.EnumVariant.values())
    {
      registerItemModel(SubstratumBlocks.block_ore_dust,"ore_" + ore.material.name, ore.ordinal());
    }

    for(BlockMetal block:SubstratumBlocks.block_metal)
    {
      for(BlockMetal.Variant v:block.getVariants())
      {
        registerItemModel(block,"block_" + v.material.name, v.id);
      }
    }

    for(BlockMetalSlab block:SubstratumBlocks.block_slab)
    {
      for(BlockMetalSlab.Variant v:block.getVariants())
      {
        registerItemModel(block,"slab_bottom_" + v.material.name, block.getBottomVariantMeta(v));
      }
    }

    for(Map.Entry<EnumMaterial, ItemStack> e:SubstratumBlocks.stairs_stacks.entrySet())
    {
      ItemStack item = e.getValue();
      registerItemModel(item.getItem(),"stairs_straight_" + e.getKey().name,item.getMetadata());
    }


    for(EnumMaterialItem matitem:EnumMaterialItem.values())
    {
      for(EnumMaterial material:EnumMaterial.values())
      {
        ItemStack item = SubstratumItems.getStack(matitem, material, false);
        if(item != null)
        {
          registerItemModel(item.getItem(),String.format("%s_%s", matitem.name,material.name), item.getMetadata());
        }
      }
    }
       
    if(SubstratumConfig.dye_enabled)
    {
      for(EnumDyePowderColor color:EnumDyePowderColor.values())
      {
        registerItemModel(SubstratumItems.item_dye_powder,"dye_" + color.name, color.ordinal());
        registerItemModel(SubstratumItems.item_dye_powder_small,"dye_small_" + color.name, color.ordinal());
      }
    }

    if(SubstratumItems.item_mortar != null)
    {
      registerItemModel(SubstratumItems.item_mortar,"mortar");
    }

    registerEquipment(SubstratumItems.pickaxes,"pickaxe");
    registerEquipment(SubstratumItems.axes,"axe");
    registerEquipment(SubstratumItems.shovels,"shovel");
    registerEquipment(SubstratumItems.hoes,"hoe");
    registerEquipment(SubstratumItems.swords,"sword");

    registerEquipment(SubstratumItems.helmets,"helmet");
    registerEquipment(SubstratumItems.chestplates,"chestplate");
    registerEquipment(SubstratumItems.leggings,"leggings");
    registerEquipment(SubstratumItems.boots,"boots");
  }
  
  private void registerEquipment(Map<EnumMaterial,? extends Item> map,String prefix)
  {
    for(Map.Entry<EnumMaterial,? extends Item> e:map.entrySet())
    {
      registerItemModel(e.getValue(),String.format("%s_%s", prefix, e.getKey().name));
    }
  }

  @Override
  public void init()
  {   

  }
  

  @Override
  public void postInit()
  {

  }

}
