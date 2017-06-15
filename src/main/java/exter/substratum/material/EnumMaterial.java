package exter.substratum.material;

import exter.substratum.util.SubstratumUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public enum EnumMaterial
{
  NULL(null),
  STONE("stone"),
  COAL("coal"),
  CHARCOAL("charcoal"),
  IRON("iron"),
  GOLD("gold"),
  COPPER("copper"),
  TIN("tin"),
  BRONZE("bronze"),
  ELECTRUM("electrum"),
  INVAR("invar"),
  NICKEL("nickel"),
  ZINC("zinc"),
  BRASS("brass"),
  SILVER("silver"),
  STEEL("steel"),
  LEAD("lead"),
  PLATINUM("platinum"),
  CUPRONICKEL("cupronickel", "Constantan"),
  REDSTONE("redstone"),
  GLOWSTONE("glowstone"),
  ENDERPEARL("enderpearl"),
  SIGNALUM("signalum"),
  LUMIUM("lumium"),
  ENDERIUM("enderium"),
  SULFUR("sulfur"),
  NITER("niter"),
  GUNPOWDER("gunpowder"),
  OBSIDIAN("obsidian"),
  BLAZE("blaze"),
  ALUMINA("alumina"),
  ALUMINIUM("aluminium", "Aluminum"),
  CHROMIUM("chrome", "Chromium");
  
  public final String name;

  public final String ore_suffix_alias;

  public final String ore_suffix;
  
  public void registerItemInOreDictionary(ItemStack item,String prefix)
  {
    if(ore_suffix != null)
    {
      OreDictionary.registerOre(prefix + ore_suffix, item);
    }
    if(ore_suffix_alias != null)
    {
      OreDictionary.registerOre(prefix + ore_suffix_alias, item);
    }
  }

  EnumMaterial(String name)
  {
    this(name,null);
  }

  EnumMaterial(String name, String ore_suffix_alias)
  {
    this.name = name;
    this.ore_suffix_alias = ore_suffix_alias;
    this.ore_suffix = SubstratumUtils.convertToOreDictionaryName(name, true);
  }
}
