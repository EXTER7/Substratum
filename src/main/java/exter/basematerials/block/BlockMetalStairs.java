package exter.basematerials.block;

import exter.basematerials.creativetab.TabMaterials;
import exter.basematerials.material.EnumMaterial;
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
    setUnlocalizedName("basematerials.stairs" + material.suffix);
    useNeighborBrightness = true;
  }

}
