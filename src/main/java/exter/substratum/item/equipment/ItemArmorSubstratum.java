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
        setUnlocalizedName("substratum.helmet" + material.suffix);
        setRegistryName("helmet" + material.suffix);
        break;
      case CHEST:
        setUnlocalizedName("substratum.chestplate" + material.suffix);
        setRegistryName("chestplate" + material.suffix);
        break;
      case LEGS:
        setUnlocalizedName("substratum.leggings" + material.suffix);
        setRegistryName("leggings" + material.suffix);
        break;
      case FEET:
        setUnlocalizedName("substratum.boots" + material.suffix);
        setRegistryName("boots" + material.suffix);
        break;
      default:
        break;      
    }
  }
  
  
  
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
  {
    if(armorType == EntityEquipmentSlot.LEGS)
    {
      return "substratum:textures/models/armor" + material.suffix + "2.png";
    }
    return "substratum:textures/models/armor" + material.suffix + "1.png";
  }
}
