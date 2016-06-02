package exter.substratum.material;

import net.minecraft.item.EnumDyeColor;

public enum EnumDyePowderColor
{
  BLACK("Black",EnumDyeColor.BLACK),
  RED("Red",EnumDyeColor.RED),
  GREEN("Green",EnumDyeColor.GREEN),
  BROWN("Brown",EnumDyeColor.BROWN),
  BLUE("Blue",EnumDyeColor.BLUE),
  PURPLE("Purple",EnumDyeColor.PURPLE),
  CYAN("Cyan",EnumDyeColor.CYAN),
  LIGHT_GRAY("LightGray",EnumDyeColor.SILVER),
  GRAY("Gray",EnumDyeColor.GRAY),
  PINK("Pink",EnumDyeColor.PINK),
  LIME("Lime",EnumDyeColor.LIME),
  YELLOW("Yellow",EnumDyeColor.YELLOW),
  LIGHT_BLUE("LightBlue",EnumDyeColor.LIGHT_BLUE),
  MAGENTA("Magenta",EnumDyeColor.MAGENTA),
  ORANGE("Orange",EnumDyeColor.ORANGE),
  WHITE("White",EnumDyeColor.WHITE);
  
  public final String name;
  public final String oredict;
  public final String oredict_small;
  public final String oredict_dust;
  public final String oredict_dust_small;
  public final EnumDyeColor dye;
  
  EnumDyePowderColor(String name,EnumDyeColor dye)
  {
    this.name = name;
    this.oredict = "dye" + name;
    this.oredict_small = "dyeSmall" + name;
    this.oredict_dust = "dustDye" + name;
    this.oredict_dust_small = "dustSmallDye" + name;
    this.dye = dye;
  }
}
