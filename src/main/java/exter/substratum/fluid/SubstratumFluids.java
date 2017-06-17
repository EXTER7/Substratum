package exter.substratum.fluid;

import java.util.EnumMap;
import java.util.Map;

import exter.substratum.block.BlockSubstratumLiquid;
import exter.substratum.block.SubstratumBlocks;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialFluid;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidRegistry;

public class SubstratumFluids
{
  static public Map<EnumMaterial,FluidSubstratum> fluids = new EnumMap<>(EnumMaterial.class);
  
  static public void registerFluids()
  {
    for(EnumMaterialFluid material_fluid:EnumMaterialFluid.values())
    {
      FluidSubstratum fluid = new FluidSubstratum(material_fluid);
      FluidRegistry.registerFluid(fluid);

      Block liquid_block = new BlockSubstratumLiquid(fluid);
      SubstratumBlocks.register(liquid_block);

      fluid.setBlock(liquid_block);

      fluids.put(material_fluid.material, fluid);
    }
  }
}
