package exter.substratum.resgen;


public class SubstratumResourceGenerator
{
  public static void main(String[] args)
  {
    BlockResources.generate();
    ItemResources.generate();
    FluidResources.generate();
  }
}