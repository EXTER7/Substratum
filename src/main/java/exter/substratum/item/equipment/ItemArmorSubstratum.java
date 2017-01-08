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
        setUnlocalizedName("substratum.helmet_" + material.suffix_lc);
        setRegistryName("helmet_" + material.suffix);
        break;
      case CHEST:
        setUnlocalizedName("substratum.chestplate_" + material.suffix_lc);
        setRegistryName("chestplate_" + material.suffix_lc);
        break;
      case LEGS:
        setUnlocalizedName("substratum.leggings_" + material.suffix_lc);
        setRegistryName("leggings_" + material.suffix_lc);
        break;
      case FEET:
        setUnlocalizedName("substratum.boots_" + material.suffix_lc);
        setRegistryName("boots_" + material.suffix_lc);
        break;
      default:
        break;      
    }
  }
  
  
  
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
  {
    return String.format("substratum:textures/models/armor_%s_%s.png",
        material.suffix_lc,
        armorType == EntityEquipmentSlot.LEGS?"2":"1");
  }
}
