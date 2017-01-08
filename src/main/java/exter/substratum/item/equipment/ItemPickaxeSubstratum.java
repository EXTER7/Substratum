package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeSubstratum extends ItemPickaxe
{
  public ItemPickaxeSubstratum(EnumMaterialEquipment equipment)
  {
    super(equipment.tool);
    setUnlocalizedName("substratum.pickaxe_" + equipment.material.suffix_lc);
    setRegistryName("pickaxe_" + equipment.material.suffix_lc);
  }
}
