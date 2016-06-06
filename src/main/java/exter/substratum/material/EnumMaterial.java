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
  CUPRONICKEL("Cupronickel"),
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
  ALUMINIUM("Aluminium");
  
  public final String suffix;
  
  EnumMaterial(String suffix)
  {
    this.suffix = suffix;
  }
}
