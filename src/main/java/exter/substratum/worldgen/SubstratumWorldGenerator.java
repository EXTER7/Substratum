package exter.substratum.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SubstratumWorldGenerator implements IWorldGenerator
{
  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
  {
    WorldGenOre.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
  }
}