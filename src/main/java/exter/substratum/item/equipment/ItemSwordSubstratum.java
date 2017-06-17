package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterial;
import net.minecraft.item.ItemSword;

public class ItemSwordSubstratum extends ItemSword
{
  public ItemSwordSubstratum(ToolMaterial tool, EnumMaterial material)
  {
    super(tool);
    setUnlocalizedName("substratum.sword_" + material.name);
    setRegistryName("sword_" + material.name);
  }
}
