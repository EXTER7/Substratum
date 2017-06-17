package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterial;
import net.minecraft.item.ItemAxe;

public class ItemAxeSubstratum extends ItemAxe
{
  public ItemAxeSubstratum(ToolMaterial tool, EnumMaterial material, float axe_damage, float axe_speed)
  {
    super(tool,axe_damage,axe_speed);
    setUnlocalizedName("substratum.axe_" + material.name);
    setRegistryName("axe_" + material.name);
  }
}
