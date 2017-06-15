package exter.substratum.item.equipment;

import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorSubstratum extends ItemArmor
{
  EnumMaterial material;
  
  public ItemArmorSubstratum(EnumMaterialEquipment equipment, EntityEquipmentSlot slot)
  {
    super(equipment.armor, 0, slot);
    material = equipment.material;
    switch(slot)
    {
      case HEAD:
        setUnlocalizedName("substratum.helmet_" + material.name);
        setRegistryName("helmet_" + material.name);
        break;
      case CHEST:
        setUnlocalizedName("substratum.chestplate_" + material.name);
        setRegistryName("chestplate_" + material.name);
        break;
      case LEGS:
        setUnlocalizedName("substratum.leggings_" + material.name);
        setRegistryName("leggings_" + material.name);
        break;
      case FEET:
        setUnlocalizedName("substratum.boots_" + material.name);
        setRegistryName("boots_" + material.name);
        break;
      default:
        break;      
    }
  }
  
  
  
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
  {
    return String.format("substratum:textures/models/armor_%s_%s.png",
        material.name,
        armorType == EntityEquipmentSlot.LEGS?"2":"1");
  }
}
