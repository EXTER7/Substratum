package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterial;
import net.minecraft.item.ItemHoe;

public class ItemHoeSubstratum extends ItemHoe
{
  public ItemHoeSubstratum(ToolMaterial tool, EnumMaterial material)
  {
    super(tool);
    setUnlocalizedName("substratum.hoe_" + material.name);
    setRegistryName("hoe_" + material.name);
  }
}
