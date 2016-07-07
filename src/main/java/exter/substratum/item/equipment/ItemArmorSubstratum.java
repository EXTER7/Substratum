package exter.substratum.item.equipment;

import java.util.List;

import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialEquipment;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorSubstratum extends ItemArmor
{
  EnumMaterial material;
  boolean old;

  public ItemArmorSubstratum(EnumMaterialEquipment equipment, EntityEquipmentSlot slot)
  {
    this(equipment,slot,false);
  }
  
  public ItemArmorSubstratum(EnumMaterialEquipment equipment, EntityEquipmentSlot slot,boolean old)
  {
    super(equipment.armor, 0, slot);
    this.old = old;
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
        setRegistryName((old?"chestplate.leggings":"leggings") + material.suffix);
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
  
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list)
  {
    if(!old) //Hide old leggings from creative list.
    {
      super.getSubItems(item, tabs, list);
    }
  }
}
