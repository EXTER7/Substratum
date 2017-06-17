package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterial;
import net.minecraft.item.ItemSpade;

public class ItemShovelSubstratum extends ItemSpade
{
  public ItemShovelSubstratum(ToolMaterial tool, EnumMaterial material)
  {
    super(tool);
    setUnlocalizedName("substratum.shovel_" + material.name);
    setRegistryName("shovel_" + material.name);
  }
}
