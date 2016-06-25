package exter.substratum.material;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public enum EnumMaterialEquipment
{
  COPPER(EnumMaterial.COPPER,EnumHelper.addToolMaterial("SUBSTRATUM_COPPER", 1, 160, 5.0F, 1.0F, 5), 8.0F, -3.2F),
  BRONZE(EnumMaterial.BRONZE,EnumHelper.addToolMaterial("SUBSTRATUM_BRONZE", 2, 200, 6.0F, 1.8F, 14), 8.0F, -3.2F),
  STEEL(EnumMaterial.STEEL,EnumHelper.addToolMaterial("SUBSTRATUM_STEEL", 2, 350, 6.5F, 2.5F, 14), 8.0F, -3.15F),
  SILVER(EnumMaterial.SILVER,EnumHelper.addToolMaterial("SUBSTRATUM_SILVER", 0, 32, 12.0F, 0.5F, 22), 6.0F, -3.0F);
  
  
  public final EnumMaterial material;
  public final ToolMaterial tool;
  public final float axe_damage;
  public final float axe_speed;
  
  static private Set<EnumMaterial> materials = null;
  
  ;
  EnumMaterialEquipment(EnumMaterial material,ToolMaterial tool,float axe_damage,float axe_speed)
  {
    this.material = material;
    this.tool = tool;
    this.axe_damage = axe_damage;
    this.axe_speed = axe_speed;
  }
  
  static public Set<EnumMaterial> getMaterials()
  {
    if(materials == null)
    {
      materials = new HashSet<EnumMaterial>();
      for(EnumMaterialEquipment equipment:values())
      {
        materials.add(equipment.material);
      }
    }
    return materials;
  }
}
