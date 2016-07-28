package exter.substratum.material;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public enum EnumMaterialEquipment
{
  COPPER(EnumMaterial.COPPER,
      EnumHelper.addToolMaterial("SUBSTRATUM_COPPER", 1, 160, 5.0F, 1.0F, 5),
      EnumHelper.addArmorMaterial("SUBSTRATUM_COPPER", "copper", 10, new int[]{1, 3, 4, 1}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
      8.0F, -3.2F),
  BRONZE(EnumMaterial.BRONZE,
      EnumHelper.addToolMaterial("SUBSTRATUM_BRONZE", 2, 200, 6.0F, 1.8F, 14),
      EnumHelper.addArmorMaterial("SUBSTRATUM_BRONZE", "bronze", 12, new int[]{2, 4, 5, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
      8.0F, -3.2F),
  INVAR(EnumMaterial.INVAR,
      EnumHelper.addToolMaterial("SUBSTRATUM_INVAR", 2, 250, 6.5F, 2.1F, 10),
      EnumHelper.addArmorMaterial("SUBSTRATUM_INVAR", "invar", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
      8.0F, -3.1F),
  SILVER(EnumMaterial.SILVER,
      EnumHelper.addToolMaterial("SUBSTRATUM_SILVER", 0, 32, 12.0F, 0.5F, 22),
      EnumHelper.addArmorMaterial("SUBSTRATUM_SILVER", "silver", 6, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F),
      6.0F, -3.0F),
  ELECTRUM(EnumMaterial.ELECTRUM,
      EnumHelper.addToolMaterial("SUBSTRATUM_ELECTRUM", 0, 32, 15.0F, 0.6F, 35),
      EnumHelper.addArmorMaterial("SUBSTRATUM_ELECTRUM", "electrum", 6, new int[]{1, 3, 5, 2}, 35, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F),
      6.0F, -3.0F),
  ALUMINIUM(EnumMaterial.ALUMINIUM,
      EnumHelper.addToolMaterial("SUBSTRATUM_ALUMINIUM", 2, 220, 10.0F, 1.8F, 14),
      EnumHelper.addArmorMaterial("SUBSTRATUM_ALUMINIUM", "aluminium", 13, new int[]{2, 4, 5, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
      8.0F, -2.8F),
  STEEL(EnumMaterial.STEEL,
      EnumHelper.addToolMaterial("SUBSTRATUM_STEEL", 2, 350, 6.5F, 2.5F, 14),
      EnumHelper.addArmorMaterial("SUBSTRATUM_STEEL", "steel", 20, new int[]{2, 6, 7, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
      8.0F, -3.15F),
  SIGNALUM(EnumMaterial.SIGNALUM,
      EnumHelper.addToolMaterial("SUBSTRATUM_SIGNALUM", 2, 500, 10.0F, 2.0F, 14),
      EnumHelper.addArmorMaterial("SUBSTRATUM_SIGNALUM", "signalum", 15, new int[]{2, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
      7.0F, -3.0F),
  LUMIUM(EnumMaterial.LUMIUM,
      EnumHelper.addToolMaterial("SUBSTRATUM_LUMIUM", 2, 600, 12.0F, 2.5F, 14),
      EnumHelper.addArmorMaterial("SUBSTRATUM_LUMIUM", "lumium", 15, new int[]{2, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
      7.0F, -3.0F),
  ENDERIUM(EnumMaterial.ENDERIUM,
      EnumHelper.addToolMaterial("SUBSTRATUM_ENDERIUM", 3, 1000, 10.0F, 4.0F, 20),
      EnumHelper.addArmorMaterial("SUBSTRATUM_ENDERIUM", "enderium", 35, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F),
      8.0F, -3.0F);
  
  
  public final EnumMaterial material;
  public final ToolMaterial tool;
  public final ArmorMaterial armor;
  public final float axe_damage;
  public final float axe_speed;
  
  static private Set<EnumMaterial> materials = null;
  
  ;
  EnumMaterialEquipment(EnumMaterial material,ToolMaterial tool,ArmorMaterial armor,float axe_damage,float axe_speed)
  {
    this.material = material;
    this.tool = tool;
    this.armor = armor;
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
