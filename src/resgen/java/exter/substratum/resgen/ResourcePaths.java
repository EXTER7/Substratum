package exter.substratum.resgen;

import java.io.File;

public class ResourcePaths
{
  static public final String RESOURCES = String.join(File.separator, new String[] {"src","main","resources","assets","substratum"});
  static public final String BLOCKSTATES = String.join(File.separator, new String[] {RESOURCES,"blockstates"});
  static public final String MODELS = String.join(File.separator, new String[] {RESOURCES,"models"});
}
