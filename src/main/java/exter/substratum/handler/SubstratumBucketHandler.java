package exter.substratum.handler;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import exter.substratum.block.BlockSubstratumLiquid;
import exter.substratum.fluid.SubstratumFluids;
import exter.substratum.item.ItemMaterial;
import exter.substratum.item.ItemMaterial.IRightClickHandler;
import exter.substratum.item.SubstratumItems;
import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SubstratumBucketHandler implements IRightClickHandler
{
  private final Map<String, ItemStack> buckets;
  private final Map<EnumMaterial, Fluid> liquids;

  public SubstratumBucketHandler()
  {
    buckets = new HashMap<String, ItemStack>();
    buckets.put(SubstratumFluids.liquid_redstone.getName(), SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.REDSTONE));
    buckets.put(SubstratumFluids.liquid_glowstone.getName(), SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.GLOWSTONE));
    buckets.put(SubstratumFluids.liquid_enderpearl.getName(), SubstratumItems.getStack(EnumMaterialItem.BUCKET_LIQUID, EnumMaterial.ENDERPEARL));

    liquids = new EnumMap<EnumMaterial, Fluid>(EnumMaterial.class);
    liquids.put(EnumMaterial.REDSTONE, SubstratumFluids.liquid_redstone);
    liquids.put(EnumMaterial.GLOWSTONE, SubstratumFluids.liquid_glowstone);
    liquids.put(EnumMaterial.ENDERPEARL, SubstratumFluids.liquid_enderpearl);    
  }

  @SubscribeEvent
  public void onBucketFill(FillBucketEvent event)
  {
    ItemStack result = fillBucket(event.getWorld(), event.getTarget());
    if(result == null)
    {
      return;
    }
    event.setFilledBucket(result);
    event.setResult(Result.ALLOW);
  }

  private ItemStack fillBucket(World world, RayTraceResult rt)
  {
    BlockPos pos = rt.getBlockPos();
    IBlockState state = world.getBlockState(pos);

    if(state.getBlock() instanceof BlockSubstratumLiquid)
    {
      BlockSubstratumLiquid block = (BlockSubstratumLiquid) state.getBlock();
      ItemStack bucket = buckets.get(block.getFluid().getName());
      if(bucket != null && block.isSourceBlock(world, pos))
      {
        world.setBlockToAir(pos);
        return bucket.copy();
      }
    }
    return null;
  }

  protected RayTraceResult getMovingObjectPositionFromPlayer(World world, EntityPlayer player)
  {
    float pitch = player.rotationPitch;
    float yaw = player.rotationYaw;
    double x = player.posX;
    double y = player.posY + (double) player.getEyeHeight();
    double z = player.posZ;
    Vec3d vec3d = new Vec3d(x, y, z);
    float spitch = -MathHelper.cos(-pitch * 0.017453292F);
    
    float lx = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI) * spitch;
    float ly = MathHelper.sin(-pitch * 0.017453292F);
    float lz = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI) * spitch;
    double range = 5.0D;
    if(player instanceof net.minecraft.entity.player.EntityPlayerMP)
    {
      range = ((net.minecraft.entity.player.EntityPlayerMP) player).interactionManager.getBlockReachDistance();
    }
    Vec3d vec3d1 = vec3d.addVector((double) lx * range, (double) ly * range, (double) lz * range);
    return world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
  }

  public boolean tryPlaceContainedLiquid(EnumMaterial material, EntityPlayer player, World world, BlockPos pos)
  {

    IBlockState state = world.getBlockState(pos);
    boolean solid = !state.getMaterial().isSolid();
    boolean replaceable = state.getBlock().isReplaceable(world, pos);

    if(!world.isAirBlock(pos) && !solid && !replaceable)
    {
      return false;
    } else
    {
      if(!world.isRemote && (solid || replaceable) && !state.getMaterial().isLiquid())
      {
        world.destroyBlock(pos, true);
      }

      SoundEvent soundevent = SoundEvents.item_bucket_empty_lava;
      world.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
      world.setBlockState(pos, liquids.get(material).getBlock().getDefaultState(), 11);

      return true;
    }
  }

  @Override
  public ActionResult<ItemStack> onRightClick(ItemStack stack, ItemMaterial item, EnumMaterial material, World world, EntityPlayer player, EnumHand hand)
  {
    RayTraceResult raytraceresult = getMovingObjectPositionFromPlayer(world, player);

    if(raytraceresult == null)
    {
      return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    } else if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
    {
      return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    } else
    {
      BlockPos pos = raytraceresult.getBlockPos();

      if(!world.isBlockModifiable(player, pos))
      {
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
      } else
      {
        boolean replaceable = world.getBlockState(pos).getBlock().isReplaceable(world, pos);
        BlockPos replace_pos = replaceable && raytraceresult.sideHit == EnumFacing.UP ? pos : pos.offset(raytraceresult.sideHit);

        if(!player.canPlayerEdit(replace_pos, raytraceresult.sideHit, stack))
        {
          return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        } else if(tryPlaceContainedLiquid(material, player, world, replace_pos))
        {
          player.addStat(StatList.getObjectUseStats(item));
          return !player.capabilities.isCreativeMode ? new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(Items.bucket)) : new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        } else
        {
          return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
      }
    }
  }
}
