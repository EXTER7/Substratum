package exter.substratum.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exter.substratum.config.SubstratumConfig.WorldgenConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
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
  
  
  private WorldGenOre(int min,int max,int min_clusters, int max_clusters,IBlockState state, boolean nether)
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

    if(min_clusters < max_clusters)
    {
      this.min_clusters = min_clusters;
      this.max_clusters = max_clusters;
    } else
    {
      this.min_clusters = max_clusters;
      this.max_clusters = min_clusters;
    }

    block = state;
    wgm = new WorldGenMinable(state, 7, BlockHelper.forBlock(nether?Blocks.netherrack:Blocks.stone));
  }

  private void generateOre(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
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
  
  static public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
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
      ores.add(new WorldGenOre(config.min_y, config.max_y, config.min_clusters, config.max_clusters, state, nether));
    }
  }
}
