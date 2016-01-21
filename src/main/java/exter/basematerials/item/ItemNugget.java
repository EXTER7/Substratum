package exter.basematerials.item;

import java.util.List;

import exter.basematerials.creativetab.TabMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNugget extends Item
{

  static public enum EnumMaterial
  {
    IRON("iron","nuggetIron"),
    COPPER("copper","nuggetCopper"),
    TIN("tin","nuggetTin"),
    BRONZE("bronze","nuggetBronze"),
    ELECTRUM("electrum","nuggetElectrum"),
    INVAR("invar","nuggetInvar"),
    NICKEL("nickel","nuggetNickel"),
    ZINC("zinc","nuggetZinc"),
    BRASS("brass","nuggetBrass"),
    SILVER("silver","nuggetSilver"),
    STEEL("steel","nuggetSteel"),
    LEAD("lead","nuggetLead"),
    PLATINUM("platinum","nuggetPlatinum"),
    CUPRONICKEL("cupronickel","nuggetCupronickel"),
    SIGNALUM("signalum","nuggetSignalum"),
    LUMIUM("lumium","nuggetLumium"),
    ENDERIUM("enderium","nuggetEnderium");


    public final String name;
    public final String oredict;

    EnumMaterial(String name,String oredict)
    {
      this.name = name;
      this.oredict = oredict;
    }
  }
  
  
  public ItemNugget() {
    super();
    setCreativeTab(TabMaterials.tab);
    setHasSubtypes(true);
    setUnlocalizedName("basematerials.nugget");
  }
  @Override
  public String getUnlocalizedName(ItemStack itemstack) {
    return getUnlocalizedName() + "." + EnumMaterial.values()[itemstack.getMetadata()].name;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list)
  {
    for (EnumMaterial mat:EnumMaterial.values())
    {
      ItemStack itemstack = new ItemStack(this, 1, mat.ordinal());
      list.add(itemstack);
    }
  }
}
