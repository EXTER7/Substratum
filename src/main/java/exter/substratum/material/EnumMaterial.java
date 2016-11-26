package exter.substratum.material;

import exter.substratum.util.SubstratumUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public enum EnumMaterial
{
  NULL(null),
  STONE("Stone"),
  COAL("Coal"),
  CHARCOAL("Charcoal"),
  IRON("Iron"),
  GOLD("Gold"),
  COPPER("Copper"),
  TIN("Tin"),
  BRONZE("Bronze"),
  ELECTRUM("Electrum"),
  INVAR("Invar"),
  NICKEL("Nickel"),
  ZINC("Zinc"),
  BRASS("Brass"),
  SILVER("Silver"),
  STEEL("Steel"),
  LEAD("Lead"),
  PLATINUM("Platinum"),
  CUPRONICKEL("Cupronickel", "Constantan"),
  REDSTONE("Redstone"),
  GLOWSTONE("Glowstone"),
  ENDERPEARL("Enderpearl"),
  SIGNALUM("Signalum"),
  LUMIUM("Lumium"),
  ENDERIUM("Enderium"),
  SULFUR("Sulfur"),
  NITER("Niter"),
  GUNPOWDER("Gunpowder"),
  OBSIDIAN("Obsidian"),
  BLAZE("Blaze"),
  ALUMINA("Alumina"),
  ALUMINIUM("Aluminium", "Aluminum"),
  CHROMIUM("Chrome", "Chromium");
  
  public final String suffix;

  public final String suffix_alias;

  public final String suffix_lc;
  
  public void registerItemInOreDictionary(ItemStack item,String prefix)
  {
    if(suffix != null)
    {
      OreDictionary.registerOre(prefix + suffix, item);
    }
    if(suffix_alias != null)
    {
      OreDictionary.registerOre(prefix + suffix_alias, item);
    }
  }

  EnumMaterial(String suffix)
  {
    this.suffix = suffix;
    this.suffix_alias = null;
    this.suffix_lc = SubstratumUtils.convertToRegistryName(suffix);
  }

  EnumMaterial(String suffix, String suffix_alias)
  {
    this.suffix = suffix;
    this.suffix_alias = suffix_alias;
    this.suffix_lc = SubstratumUtils.convertToRegistryName(suffix);
  }
}
