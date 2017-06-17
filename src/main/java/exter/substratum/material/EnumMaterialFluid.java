package exter.substratum.material;

public enum EnumMaterialFluid
{
  REDSTONE(EnumMaterial.REDSTONE, 800, 4),
  GLOWSTONE(EnumMaterial.GLOWSTONE,1100, 15),
  ENDERPEARL(EnumMaterial.ENDERPEARL, 1400, 2);
  
  public final EnumMaterial material;
  public final int temperature;
  public final int luminosity;
  
  EnumMaterialFluid(EnumMaterial material,int temperature,int luminosity)
  {
    this.material = material;
    this.temperature = temperature;
    this.luminosity = luminosity;
  }
}
