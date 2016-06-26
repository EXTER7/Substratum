package exter.substratum.fluid;

import exter.substratum.material.EnumMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidSubstratum extends Fluid
{
  public final EnumMaterial material;
  
  public FluidSubstratum(EnumMaterial material,String fluidName, ResourceLocation still, ResourceLocation flowing, int temperature, int luminosity)
  {
    super(fluidName, still, flowing);
    this.material = material;
    setUnlocalizedName("substratum." + fluidName);
    setTemperature(temperature);
    setLuminosity(luminosity);
  }
}
