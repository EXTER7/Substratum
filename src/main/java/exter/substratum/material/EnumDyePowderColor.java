package exter.substratum.material;

import exter.substratum.util.SubstratumUtils;
import net.minecraft.item.EnumDyeColor;

public enum EnumDyePowderColor
{
  BLACK("black",EnumDyeColor.BLACK),
  RED("red",EnumDyeColor.RED),
  GREEN("green",EnumDyeColor.GREEN),
  BROWN("brown",EnumDyeColor.BROWN),
  BLUE("blue",EnumDyeColor.BLUE),
  PURPLE("purple",EnumDyeColor.PURPLE),
  CYAN("cyan",EnumDyeColor.CYAN),
  LIGHT_GRAY("light_gray",EnumDyeColor.SILVER),
  GRAY("gray",EnumDyeColor.GRAY),
  PINK("pink",EnumDyeColor.PINK),
  LIME("lime",EnumDyeColor.LIME),
  YELLOW("yellow",EnumDyeColor.YELLOW),
  LIGHT_BLUE("light_blue",EnumDyeColor.LIGHT_BLUE),
  MAGENTA("magenta",EnumDyeColor.MAGENTA),
  ORANGE("orange",EnumDyeColor.ORANGE),
  WHITE("white",EnumDyeColor.WHITE);
  
  public final String name;
  public final String oredict;
  public final String oredict_small;
  public final String oredict_dust;
  public final String oredict_dust_small;
  public final EnumDyeColor dye;
  
  EnumDyePowderColor(String name,EnumDyeColor dye)
  {
    String ore_suffix = SubstratumUtils.convertToOreDictionaryName(name, true);
    this.name = name;
    this.oredict = "dye" + ore_suffix;
    this.oredict_small = "dyeSmall" + ore_suffix;
    this.oredict_dust = "dustDye" + ore_suffix;
    this.oredict_dust_small = "dustSmallDye" + ore_suffix;
    this.dye = dye;
  }
}
