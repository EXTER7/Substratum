package exter.basematerials.block;

import exter.basematerials.creativetab.TabMaterials;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;


public class BlockMetalStairs extends BlockStairs
{
  
  public final String oredict;
  
  // Make BlockStairs's constructor accessible.
  public BlockMetalStairs(IBlockState modelState,String name,String oredict)
  {
    super(modelState);
    this.oredict = oredict;
    setCreativeTab(TabMaterials.tab);
    setUnlocalizedName("basematerials.stairs." + name);
    useNeighborBrightness = true;
  }

}
