package exter.substratum.block;

import exter.substratum.material.EnumMaterial;
import net.minecraft.util.IStringSerializable;

public interface IBlockVariants
{
  public interface IMaterialProperty extends IStringSerializable
  {
    EnumMaterial getMaterial();
    
    @Override
    default String getName()
    {
      return getMaterial().name;
    }
  }
  
  public String getUnlocalizedName(int meta);
}
