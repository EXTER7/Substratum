package exter.substratum.item;

import java.util.List;

import exter.substratum.creativetab.TabMaterials;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaterial extends Item
{
  public interface ISpecialHandler
  {
    ActionResult<ItemStack> onRightClick(ItemStack stack,ItemMaterial item,EnumMaterial material,World world, EntityPlayer player, EnumHand hand);
    ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt);
  }

  public final EnumMaterialItem item;
  
  private ISpecialHandler special = null;

  public ItemMaterial(EnumMaterialItem material_item)
  {
    super();
    this.item = material_item;
    setCreativeTab(TabMaterials.tab);
    setHasSubtypes(true);
    setUnlocalizedName("substratum." + material_item.prefix_lc);
    setRegistryName(material_item.prefix_lc);
  }
  
  public ItemMaterial setSpecialHandler(ISpecialHandler special)
  {
    this.special = special;
    return this;
  }
  
  @Override
  public String getUnlocalizedName(ItemStack itemstack)
  {
    return String.format("%s_%s", getUnlocalizedName(), item.materials.get(itemstack.getMetadata()).suffix_lc);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list)
  {
    int i;
    for(i = 0; i < this.item.materials.size(); i++)
    {
      if(!this.item.deprecated.contains(this.item.materials.get(i)))
      {
        ItemStack itemstack = new ItemStack(this, 1, i);
        list.add(itemstack);
      }
    }
  }
  
  public int getMaterialMeta(EnumMaterial material)
  {
    return item.materials.indexOf(material);
  }

  public ItemStack getStack(EnumMaterial material)
  {
    return getStack(material,1);
  }

  public ItemStack getStack(EnumMaterial material,int amount)
  {
    int meta = getMaterialMeta(material);
    if(meta == -1)
    {
      return null;
    }
    return new ItemStack(this,amount,meta);
  }
  
  @Override
  public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
  {
    if(special != null)
    {
      return special.onRightClick(stack, this, item.materials.get(stack.getMetadata()), world, player, hand);
    } else
    {
      return super.onItemRightClick(stack, world, player, hand);
    }
  }
  
  @Override
  public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
  {
    if(special != null)
    {
      return special.initCapabilities(stack, nbt);
    }
    return null;
  }
}
