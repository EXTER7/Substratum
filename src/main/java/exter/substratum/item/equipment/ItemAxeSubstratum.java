package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.item.ItemAxe;

public class ItemAxeSubstratum extends ItemAxe
{
  public ItemAxeSubstratum(EnumMaterialEquipment equipment)
  {
    super(equipment.tool,equipment.axe_damage,equipment.axe_speed);
    setUnlocalizedName("substratum.axe_" + equipment.material.name);
    setRegistryName("axe_" + equipment.material.name);
  }
}
