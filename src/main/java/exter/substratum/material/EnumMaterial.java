package exter.substratum.material;

public enum EnumMaterial
{
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
  
  EnumMaterial(String suffix)
  {
    this.suffix = suffix;
    this.suffix_alias = null;
  }

  EnumMaterial(String suffix, String suffix_alias)
  {
    this.suffix = suffix;
    this.suffix_alias = suffix_alias;
  }
}
