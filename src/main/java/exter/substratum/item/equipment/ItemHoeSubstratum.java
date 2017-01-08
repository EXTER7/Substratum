package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.item.ItemHoe;

public class ItemHoeSubstratum extends ItemHoe
{
  public ItemHoeSubstratum(EnumMaterialEquipment equipment)
  {
    super(equipment.tool);
    setUnlocalizedName("substratum.hoe_" + equipment.material.suffix_lc);
    setRegistryName("hoe_" + equipment.material.suffix_lc);
  }
}
