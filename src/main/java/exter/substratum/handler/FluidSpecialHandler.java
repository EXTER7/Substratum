package exter.substratum.handler;

import exter.substratum.fluid.FluidContainerCapability;
import exter.substratum.item.ItemMaterial;
import exter.substratum.item.ItemMaterial.ISpecialHandler;
import exter.substratum.material.EnumMaterial;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class FluidSpecialHandler implements ISpecialHandler
{
  private final int capacity;
  
  public FluidSpecialHandler(int capacity)
  {
    this.capacity = capacity;
  }
  
  @Override
  public ActionResult<ItemStack> onRightClick(ItemStack stack, ItemMaterial item, EnumMaterial material, World world, EntityPlayer player, EnumHand hand)
  {
    return ActionResult.newResult(EnumActionResult.PASS, stack);
  }

  @Override
  public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
  {
    return new FluidContainerCapability(stack,capacity);
  }
}
