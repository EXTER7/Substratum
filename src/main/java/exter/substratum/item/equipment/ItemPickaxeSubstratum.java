package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeSubstratum extends ItemPickaxe
{
  public ItemPickaxeSubstratum(ToolMaterial tool, EnumMaterial material)
  {
    super(tool);
    setUnlocalizedName("substratum.pickaxe_" + material.name);
    setRegistryName("pickaxe_" + material.name);
  }
}
