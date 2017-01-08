package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.item.ItemSpade;

public class ItemShovelSubstratum extends ItemSpade
{
  public ItemShovelSubstratum(EnumMaterialEquipment equipment)
  {
    super(equipment.tool);
    setUnlocalizedName("substratum.shovel_" + equipment.material.suffix_lc);
    setRegistryName("shovel_" + equipment.material.suffix_lc);
  }
}
