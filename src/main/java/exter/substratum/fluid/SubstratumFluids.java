package exter.substratum.fluid;

import java.util.EnumMap;
import java.util.Map;

import exter.substratum.block.BlockSubstratumLiquid;
import exter.substratum.block.SubstratumBlocks;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

@SuppressWarnings("deprecation")
public class SubstratumFluids
{
  static public FluidSubstratum liquid_redstone;
  static public FluidSubstratum liquid_glowstone;
  static public FluidSubstratum liquid_enderpearl;

  static public Map<EnumMaterial,Fluid> material_fluids = new EnumMap<EnumMaterial,Fluid>(EnumMaterial.class);
  
  static private FluidSubstratum register(EnumMaterial material,String name,int temperature,int luminosity)
  {
    FluidSubstratum fluid = new FluidSubstratum(material, name,
        new ResourceLocation("substratum","blocks/" + name + "_still"),
        new ResourceLocation("substratum","blocks/" + name + "_flow"),
        temperature, luminosity);
    FluidRegistry.registerFluid(fluid);

    Block liquid_block = new BlockSubstratumLiquid(fluid, name);
    SubstratumBlocks.register(liquid_block);

    fluid.setBlock(liquid_block);

    material_fluids.put(material, fluid);
    
    return fluid;
  }
  
  static public void registerFluids()
  {
    liquid_redstone = register(EnumMaterial.REDSTONE, "liquidRedstone", 800, 4);
    liquid_glowstone = register(EnumMaterial.GLOWSTONE, "liquidGlowstone", 1100, 15);
    liquid_enderpearl = register(EnumMaterial.ENDERPEARL, "liquidEnderpearl", 1400, 2);
    
    ItemStack bucket = new ItemStack(Items.BUCKET);
    ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
    FluidContainerRegistry.registerFluidContainer(new FluidStack(liquid_redstone, Fluid.BUCKET_VOLUME), SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.REDSTONE), bucket);
    FluidContainerRegistry.registerFluidContainer(new FluidStack(liquid_glowstone, Fluid.BUCKET_VOLUME), SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.GLOWSTONE), bucket);
    FluidContainerRegistry.registerFluidContainer(new FluidStack(liquid_enderpearl, Fluid.BUCKET_VOLUME), SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.ENDERPEARL), bucket);
    FluidContainerRegistry.registerFluidContainer(new FluidStack(liquid_redstone, Fluid.BUCKET_VOLUME / 4), SubstratumItems.getStack(EnumMaterialItem.BOTTLE_LIQUID, EnumMaterial.REDSTONE), bottle);
    FluidContainerRegistry.registerFluidContainer(new FluidStack(liquid_glowstone, Fluid.BUCKET_VOLUME / 4), SubstratumItems.getStack(EnumMaterialItem.BOTTLE_LIQUID, EnumMaterial.GLOWSTONE), bottle);
    FluidContainerRegistry.registerFluidContainer(new FluidStack(liquid_enderpearl, Fluid.BUCKET_VOLUME / 4), SubstratumItems.getStack(EnumMaterialItem.BOTTLE_LIQUID, EnumMaterial.ENDERPEARL), bottle);
  }
}
