package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.item.ItemSword;

public class ItemSwordSubstratum extends ItemSword
{
  public ItemSwordSubstratum(EnumMaterialEquipment equipment)
  {
    super(equipment.tool);
    setUnlocalizedName("substratum.sword" + equipment.material.suffix);
    setRegistryName("sword" + equipment.material.suffix);
  }
}
