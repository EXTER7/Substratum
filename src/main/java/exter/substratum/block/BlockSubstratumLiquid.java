package exter.substratum.block;

import java.util.Random;

import exter.substratum.creativetab.TabMaterials;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSubstratumLiquid extends BlockFluidClassic
{

  public BlockSubstratumLiquid(Fluid fluid, String name)
  {
    super(fluid, Material.lava);
    setLightOpacity(0);
    setLightLevel(1.0f);
    setUnlocalizedName("substratum." + name);
    setCreativeTab(TabMaterials.tab);
  }
 
  @Override
  public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
  {
    return 300;
  }

  @Override
  public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
  {
    return 0;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void randomTick(World world, BlockPos pos, IBlockState state, Random rand)
  {
    if(temperature < 1200)
    {
      return;
    }
    double dx;
    double dy;
    double dz;

    if(world.getBlockState(pos.add(0,1,0)).getMaterial() == Material.air && !world.getBlockState(pos.add(0,1,0)).isOpaqueCube())
    {
      if(rand.nextInt(100) == 0)
      {
        dx = (double) ((float) pos.getX() + rand.nextFloat());
        dy = (double) pos.getY() + state.getBoundingBox(world, pos).maxY;
        dz = (double) ((float) pos.getZ() + rand.nextFloat());
        world.spawnParticle(EnumParticleTypes.LAVA, dx, dy, dz, 0.0D, 0.0D, 0.0D);
        world.playSound(dx, dy, dz, SoundEvents.block_lava_pop, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
      }

      if(rand.nextInt(200) == 0)
      {
        world.playSound((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), SoundEvents.block_lava_ambient, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
      }
    }

    BlockPos down = pos.down();
    if (rand.nextInt(10) == 0 && world.getBlockState(down).isSideSolid(world, down, EnumFacing.UP))
    {
      dx = (double) ((float) pos.getX() + rand.nextFloat());
      dy = (double) pos.getY() - 1.05D;
      dz = (double) ((float) pos.getZ() + rand.nextFloat());

      world.spawnParticle(EnumParticleTypes.DRIP_LAVA, dx, dy, dz, 0.0D, 0.0D, 0.0D);
    }
  }

  @Override
  public boolean canDisplace(IBlockAccess world, BlockPos pos)
  {
    if(world.getBlockState(pos).getMaterial().isLiquid())
    {
      return false;
    }
    return super.canDisplace(world, pos);
  }

  @Override
  public boolean displaceIfPossible(World world, BlockPos pos)
  {
    if(world.getBlockState(pos).getMaterial().isLiquid())
    {
      return false;
    }
    return super.displaceIfPossible(world, pos);
  }

  @Override
  public void onEntityCollidedWithBlock(World wWorld, BlockPos pos, IBlockState state, Entity entity)
  {
    if(entity instanceof EntityLivingBase)
    {
      entity.motionX *= 0.5;
      entity.motionZ *= 0.5;
    }
    if(!entity.isImmuneToFire())
    {
      if(!(entity instanceof EntityItem))
      {
        entity.attackEntityFrom(DamageSource.lava, 3);
      }
      entity.setFire(15);
    }
  }
}
