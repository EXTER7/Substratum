package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.item.ItemAxe;

public class ItemAxeSubstratum extends ItemAxe
{
  public ItemAxeSubstratum(EnumMaterialEquipment equipment)
  {
    super(equipment.tool,equipment.axe_damage,equipment.axe_speed);
    setUnlocalizedName("substratum.axe" + equipment.material.suffix);
    setRegistryName("axe" + equipment.material.suffix);
  }
}
