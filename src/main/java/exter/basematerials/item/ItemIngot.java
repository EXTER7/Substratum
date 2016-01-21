package exter.basematerials.item;

import java.util.List;

import exter.basematerials.creativetab.TabMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIngot extends Item
{
  
  static public enum EnumMaterial
  {
    COPPER("copper","ingotCopper"),
    TIN("tin","ingotTin"),
    BRONZE("bronze","ingotBronze"),
    ELECTRUM("electrum","ingotElectrum"),
    INVAR("invar","ingotInvar"),
    NICKEL("nickel","ingotNickel"),
    ZINC("zinc","ingotZinc"),
    BRASS("brass","ingotBrass"),
    SILVER("silver","ingotSilver"),
    STEEL("steel","ingotSteel"),
    LEAD("lead","ingotLead"),
    PLATINUM("platinum","ingotPlatinum"),
    CUPRONICKEL("cupronickel","ingotCupronickel"),
    SIGNALUM("signalum","ingotSignalum"),
    LUMIUM("lumium","ingotLumium"),
    ENDERIUM("enderium","ingotEnderium");


    public final String name;
    public final String oredict;

    EnumMaterial(String name,String oredict)
    {
      this.name = name;
      this.oredict = oredict;
    }
  }
  
  public ItemIngot() {
    super();
    setCreativeTab(TabMaterials.tab);
    setHasSubtypes(true);
    setUnlocalizedName("basematerials.ingot");
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
