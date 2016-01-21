package exter.basematerials.item;

import java.util.List;

import exter.basematerials.creativetab.TabMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDust extends Item
{

  static public enum EnumMaterial
  {
    COAL("coal","dustCoal"),
    IRON("iron","dustIron"),
    GOLD("gold","dustGold"),
    COPPER("copper","dustCopper"),
    TIN("tin","dustTin"),
    BRONZE("bronze","dustBronze"),
    ELECTRUM("electrum","dustElectrum"),
    INVAR("invar","dustInvar"),
    NICKEL("nickel","dustNickel"),
    ZINC("zinc","dustZinc"),
    BRASS("brass","dustBrass"),
    SILVER("silver","dustSilver"),
    STEEL("steel","dustSteel"),
    LEAD("lead","dustLead"),
    PLATINUM("platinum","dustPlatinum"),
    CUPRONICKEL("cupronickel","dustCupronickel"),
    ENDERPEARL("enderpearl","dustEnderpearl"),
    SIGNALUM("signalum","dustSignalum"),
    LUMIUM("lumium","dustLumium"),
    ENDERIUM("enderium","dustEnderium");
    
    public final String name;
    public final String oredict;

    EnumMaterial(String name,String oredict)
    {
      this.name = name;
      this.oredict = oredict;
    }
  }
  
  
  public ItemDust()
  {
    super();
    setCreativeTab(TabMaterials.tab);
    setHasSubtypes(true);
    setUnlocalizedName("basematerials.dust");
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
