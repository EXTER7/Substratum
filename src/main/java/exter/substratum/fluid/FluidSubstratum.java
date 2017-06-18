package exter.substratum.fluid;

import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialFluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidSubstratum extends Fluid
{
  public final EnumMaterial material;
  
  public FluidSubstratum(EnumMaterialFluid fluid_material)
  {
    super("liquid_" + fluid_material.material.name,
        new ResourceLocation("substratum","blocks/liquid_" + fluid_material.material.name + "_still"),
        new ResourceLocation("substratum","blocks/" + fluid_material.material.name + "_flow"));
    this.material = fluid_material.material;
    setUnlocalizedName("substratum.liquid_" + material.name);
    setTemperature(fluid_material.temperature);
    setLuminosity(fluid_material.luminosity);
  }
  
  @Override
  public String getLocalizedName(FluidStack stack)
  {
      String s = this.getUnlocalizedName();
      return s == null ? "" : I18n.translateToLocal(s + ".name");
  }
}
