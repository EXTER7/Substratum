package exter.basematerials.material;

public enum EnumMaterial
{
  STONE("Stone"),
  COAL("Coal"),
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
  ENDERIUM("Enderium");
  
  public final String suffix;
  
  EnumMaterial(String suffix)
  {
    this.suffix = suffix;
  }
}
