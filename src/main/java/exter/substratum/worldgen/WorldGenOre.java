package exter.substratum.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exter.substratum.config.SubstratumConfig.WorldgenConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenOre
{
  static private List<WorldGenOre> ores = new ArrayList<WorldGenOre>();
  
  public final int min;
  public final int max;

  public final int min_clusters;
  public final int max_clusters;

  public final IBlockState block;
  
  
  private WorldGenMinable wgm;
  
  
  private WorldGenOre(int min,int max,int min_frequency, int max_frequency, int cluster_size,IBlockState state, boolean nether)
  {
    if(min < max)
    {
      this.min = min;
      this.max = max;
    } else
    {
      this.min = max;
      this.max = min;
    }

    if(min_frequency < max_frequency)
    {
      this.min_clusters = (int)Math.round((double)(min_frequency * (this.max - this.min)) / 1000.0);
      this.max_clusters = (int)Math.round((double)(max_frequency * (this.max - this.min)) / 1000.0);
      
    } else
    {
      this.min_clusters = (int)Math.round((double)(max_frequency * (this.max - this.min)) / 1000.0);
      this.max_clusters = (int)Math.round((double)(min_frequency * (this.max - this.min)) / 1000.0);
    }

    block = state;
    wgm = new WorldGenMinable(state, cluster_size, BlockMatcher.forBlock(nether?Blocks.NETHERRACK:Blocks.STONE));
  }

  private void generateOre(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
  {
    int i;
    int clusters = random.nextInt(max_clusters - min_clusters + 1) + min_clusters;
    for(i = 0; i < clusters; i++)
    {
      int x = chunkX * 16 + random.nextInt(16);
      int y = min + random.nextInt(max - min + 1);
      int z = chunkZ * 16 + random.nextInt(16);
      BlockPos pos = new BlockPos(x,y,z);
      wgm.generate(world, random, pos);
    }
  }
  
  static public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
  {
    for(WorldGenOre wgo:ores)
    {
      wgo.generateOre(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
    }
  }
    
  static public void registerOre(WorldgenConfig config, IBlockState state, boolean nether)
  {
    if(config.enabled)
    {
      ores.add(new WorldGenOre(config.min_y, config.max_y, config.min_frequency, config.max_frequency, config.cluster_size, state, nether));
    }
  }
}
