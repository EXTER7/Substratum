package exter.substratum.block;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.material.EnumMaterial;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;


public class BlockMetalStairs extends BlockStairs
{
  
  public final EnumMaterial material;
  
  // Make BlockStairs's constructor accessible.
  public BlockMetalStairs(IBlockState model_state,EnumMaterial material)
  {
    super(model_state);
    this.material = material;
    setCreativeTab(TabMaterials.tab);
    setUnlocalizedName("substratum.stairs_" + material.suffix_lc);
    setRegistryName("stairs_" + material.suffix_lc);
    useNeighborBrightness = true;
  }

}
