package exter.substratum.material;

import java.util.HashSet;
import java.util.Set;


public enum EnumMaterialEquipment
{
  COPPER(EnumMaterial.COPPER,
      1, 160, 5.0F, 1.0F, 5,  8.0F, -3.2F,
      10, new int[]{1, 3, 4, 1}, 9, "item.armor.equip_iron", 0.0F ),
  BRONZE(EnumMaterial.BRONZE,
      2, 200, 6.0F, 1.8F, 14, 8.0F, -3.2F,
      12, new int[]{2, 4, 5, 2}, 9, "item.armor.equip_iron", 0.0F),
  INVAR(EnumMaterial.INVAR,
      2, 250, 6.5F, 2.1F, 10, 8.0F, -3.1F,
      15, new int[]{2, 5, 6, 2}, 9, "item.armor.equip_iron", 0.0F),
  SILVER(EnumMaterial.SILVER,
      0, 32, 12.0F, 0.5F, 22, 6.0F, -3.0F,
      6, new int[]{1, 3, 5, 2}, 25, "item.armor.equip_gold", 0.0F),
  ELECTRUM(EnumMaterial.ELECTRUM,
      0, 32, 15.0F, 0.6F, 35, 6.0F, -3.0F,
      6, new int[]{1, 3, 5, 2}, 35, "item.armor.equip_gold", 0.0F),
  ALUMINIUM(EnumMaterial.ALUMINIUM,
      2, 220, 12.0F, 1.8F, 14, 8.0F, -2.8F,
      13, new int[]{2, 4, 5, 2}, 9, "item.armor.equip_iron", 0.0F),
  STEEL(EnumMaterial.STEEL,
      2, 350, 6.5F, 2.5F, 14, 8.0F, -3.15F,
      20, new int[]{2, 6, 7, 2}, 9, "item.armor.equip_iron", 0.0F),
  SIGNALUM(EnumMaterial.SIGNALUM,
      2, 500, 10.0F, 2.0F, 14, 7.0F, -3.0F,
      15, new int[]{2, 4, 5, 2}, 12, "item.armor.equip_iron", 0.0F),
  LUMIUM(EnumMaterial.LUMIUM,
      2, 600, 12.0F, 2.5F, 14, 7.0F, -3.0F,
      15, new int[]{2, 4, 5, 2}, 12, "item.armor.equip_iron", 0.0F),
  ENDERIUM(EnumMaterial.ENDERIUM,
      3, 1000, 10.0F, 4.0F, 20, 8.0F, -3.0F,
      40, new int[]{4, 7, 9, 4}, 20, "item.armor.equip_iron", 2.0F);
  
  public final EnumMaterial material;
  public final int harvestLevel;
  public final int maxUses;
  public final float efficiency;
  public final float damage;
  public final int tool_enchantability;
  public final float axe_damage;
  public final float axe_speed;
  public final int durability;
  public final int[] reductionAmounts;
  public final int armor_enchantability;
  public final String soundOnEquip;
  public final float toughness;
  
  EnumMaterialEquipment(
      EnumMaterial material,
      int harvestLevel,
      int maxUses,
      float efficiency,
      float damage,
      int tool_enchantability,
      float axe_damage,
      float axe_speed,
      int durability,
      int[] reductionAmounts,
      int armor_enchantability,
      String soundOnEquip,
      float toughness)
  {
    this.material = material;
    this.harvestLevel = harvestLevel;
    this.maxUses = maxUses;
    this.efficiency = efficiency;
    this.damage = damage;
    this.tool_enchantability = tool_enchantability;
    this.axe_damage = axe_damage;
    this.axe_speed = axe_speed;
    this.durability = durability;
    this.reductionAmounts = reductionAmounts;
    this.armor_enchantability = armor_enchantability;
    this.soundOnEquip = soundOnEquip;
    this.toughness = toughness;
  }
  
  
  static private Set<EnumMaterial> materials = null;

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
