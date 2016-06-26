package exter.substratum.fluid;

import javax.annotation.Nullable;

import exter.substratum.item.ItemMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class FluidContainerCapability implements ICapabilityProvider, IFluidHandler
{
  private final ItemStack item;
  private final int amount;

  public FluidContainerCapability(ItemStack item,int amount)
  {
    this.item = item;
    this.amount = amount;
  }
  
  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing)
  {
    return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing)
  {
    if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
    {
      return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
    }
    return null;
  }
  
  private Fluid getFluid()
  {
    return SubstratumFluids.material_fluids.get(
            ((ItemMaterial)item.getItem()).materials.get(item.getMetadata()));
  }

  @Override
  public IFluidTankProperties[] getTankProperties()
  {
    return new IFluidTankProperties[]{ new FluidTankProperties(new FluidStack(getFluid(),amount),amount)};
  }

  @Override
  public int fill(FluidStack resource, boolean doFill)
  {
    return 0;
  }

  @Nullable
  @Override
  public FluidStack drain(FluidStack resource, boolean doDrain)
  {
    Fluid fluid = getFluid();
    if(item.stackSize != 1 || resource == null || resource.amount < amount || !resource.getFluid().getName().equals(fluid.getName()))
    {
      return null;
    }

    if (doDrain)
    {
      item.deserializeNBT(new ItemStack(item.getItem().getContainerItem()).serializeNBT());
    }
    return new FluidStack(fluid,amount);
  }

  @Nullable
  @Override
  public FluidStack drain(int maxDrain, boolean doDrain)
  {
    Fluid fluid = getFluid();
    if(item.stackSize != 1 || maxDrain < amount)
    {
      return null;
    }

    if (doDrain)
    {
      item.deserializeNBT(new ItemStack(item.getItem().getContainerItem()).serializeNBT());
    }
    return new FluidStack(fluid,amount);
  }
}
