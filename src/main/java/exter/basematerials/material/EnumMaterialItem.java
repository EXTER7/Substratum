package exter.basematerials.material;

import com.google.common.collect.ImmutableList;

public enum EnumMaterialItem
{
  INGOT("ingot",ImmutableList.of(
      EnumMaterial.COPPER,
      EnumMaterial.TIN,
      EnumMaterial.BRONZE,
      EnumMaterial.ELECTRUM,
      EnumMaterial.INVAR,
      EnumMaterial.NICKEL,
      EnumMaterial.ZINC,
      EnumMaterial.BRASS,
      EnumMaterial.SILVER,
      EnumMaterial.STEEL,
      EnumMaterial.LEAD,
      EnumMaterial.PLATINUM,
      EnumMaterial.CUPRONICKEL,
      EnumMaterial.SIGNALUM,
      EnumMaterial.LUMIUM,
      EnumMaterial.ENDERIUM)),
  
  DUST("dust",ImmutableList.of(
      EnumMaterial.COAL,
      EnumMaterial.IRON,
      EnumMaterial.GOLD,
      EnumMaterial.COPPER,
      EnumMaterial.TIN,
      EnumMaterial.BRONZE,
      EnumMaterial.ELECTRUM,
      EnumMaterial.INVAR,
      EnumMaterial.NICKEL,
      EnumMaterial.ZINC,
      EnumMaterial.BRASS,
      EnumMaterial.SILVER,
      EnumMaterial.STEEL,
      EnumMaterial.LEAD,
      EnumMaterial.PLATINUM,
      EnumMaterial.CUPRONICKEL,
      EnumMaterial.ENDERPEARL,
      EnumMaterial.SIGNALUM,
      EnumMaterial.LUMIUM,
      EnumMaterial.ENDERIUM)),
  
  NUGGET("nugget",ImmutableList.of(
      EnumMaterial.IRON,
      EnumMaterial.COPPER,
      EnumMaterial.TIN,
      EnumMaterial.BRONZE,
      EnumMaterial.ELECTRUM,
      EnumMaterial.INVAR,
      EnumMaterial.NICKEL,
      EnumMaterial.ZINC,
      EnumMaterial.BRASS,
      EnumMaterial.SILVER,
      EnumMaterial.STEEL,
      EnumMaterial.LEAD,
      EnumMaterial.PLATINUM,
      EnumMaterial.CUPRONICKEL,
      EnumMaterial.SIGNALUM,
      EnumMaterial.LUMIUM,
      EnumMaterial.ENDERIUM)),

  GEAR("gear",ImmutableList.of(
      EnumMaterial.STONE,
      EnumMaterial.IRON,
      EnumMaterial.GOLD,
      EnumMaterial.COPPER,
      EnumMaterial.TIN,
      EnumMaterial.BRONZE,
      EnumMaterial.ELECTRUM,
      EnumMaterial.INVAR,
      EnumMaterial.NICKEL,
      EnumMaterial.ZINC,
      EnumMaterial.BRASS,
      EnumMaterial.SILVER,
      EnumMaterial.STEEL,
      EnumMaterial.LEAD,
      EnumMaterial.PLATINUM,
      EnumMaterial.CUPRONICKEL,
      EnumMaterial.SIGNALUM,
      EnumMaterial.LUMIUM,
      EnumMaterial.ENDERIUM));

  
  
  public final ImmutableList<EnumMaterial> materials;
  public final String prefix;
  
  EnumMaterialItem(String prefix, ImmutableList<EnumMaterial> materials)
  {
    this.materials = materials;
    this.prefix = prefix;
  }
}
