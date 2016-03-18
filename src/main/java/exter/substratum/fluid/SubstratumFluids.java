package exter.substratum.fluid;

import exter.substratum.block.BlockSubstratumLiquid;
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
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("deprecation")
public class SubstratumFluids
{
  static public Fluid liquid_redstone;
  static public Fluid liquid_glowstone;
  static public Fluid liquid_enderpearl;
  static private Fluid register(String name,int temperature,int luminosity)
  {
    Fluid fluid = new Fluid(name,
        new ResourceLocation("substratum","blocks/" + name + "_still"),
        new ResourceLocation("substratum","blocks/" + name + "_flow"))
      .setTemperature(temperature)
      .setLuminosity(luminosity)
      .setUnlocalizedName("substratum." + name);
    FluidRegistry.registerFluid(fluid);

    Block liquid_block = new BlockSubstratumLiquid(fluid, name);
    GameRegistry.registerBlock(liquid_block, name);

    fluid.setBlock(liquid_block);
    
    return fluid;
  }
  
  static public void registerFluids()
  {
    liquid_redstone = register( "liquidRedstone", 800, 4);
    liquid_glowstone = register( "liquidGlowstone", 1100, 15);
    liquid_enderpearl = register( "liquidEnderpearl", 1400, 2);
    ItemStack bucket = new ItemStack(Items.bucket);
    FluidContainerRegistry.registerFluidContainer(liquid_redstone, SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.REDSTONE), bucket);
    FluidContainerRegistry.registerFluidContainer(liquid_glowstone, SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.GLOWSTONE), bucket);
    FluidContainerRegistry.registerFluidContainer(liquid_enderpearl, SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.ENDERPEARL), bucket);

  }
}
