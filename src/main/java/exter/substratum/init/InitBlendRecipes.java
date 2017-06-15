package exter.substratum.init;


class InitBlendRecipes
{
  static void init()
  {
    // TODO: Convert to JSON recipes.
    /*
    if(SubstratumConfig.blend_bronze_enable)
    {
      GameRegistry.addRecipe(
          new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRONZE,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustTin"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRONZE),
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallTin"));
    }

    if(SubstratumConfig.blend_brass_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRASS,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustZinc"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.BRASS),
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallZinc"));
    }

    if(SubstratumConfig.blend_cupronickel_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CUPRONICKEL,2),
          "dustCopper",
          "dustNickel"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.CUPRONICKEL),
          "dustSmallCopper",
          "dustSmallCopper",
          "dustSmallNickel",
          "dustSmallNickel"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.CUPRONICKEL,2),
          "dustSmallCopper",
          "dustSmallNickel"));
    }

    if(SubstratumConfig.blend_invar_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.INVAR,3),
          "dustIron", 
          "dustIron", 
          "dustNickel"));
      
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.INVAR,3),
          "dustSmallIron", 
          "dustSmallIron", 
          "dustSmallNickel"));
    }

    if(SubstratumConfig.blend_electrum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ELECTRUM,2),
          "dustGold", 
          "dustSilver"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ELECTRUM),
          "dustSmallGold", 
          "dustSmallGold", 
          "dustSmallSilver",
          "dustSmallSilver"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.ELECTRUM,2),
          "dustSmallGold", 
          "dustSmallSilver"));
    }

    if(SubstratumConfig.blend_steel_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.STEEL),
          "dustCoal", 
          "dustCoal", 
          "dustCoal", 
          "dustCoal", 
          "dustIron"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.STEEL),
          "dustSmallCoal", 
          "dustSmallCoal", 
          "dustSmallCoal", 
          "dustSmallCoal", 
          "dustSmallIron"));
    }


    if(SubstratumConfig.blend_gunpowder_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          new ItemStack(Items.GUNPOWDER,2),
          "dustNiter", 
          "dustNiter", 
          "dustSulfur", 
          "dustCharcoal"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.GUNPOWDER,2),
          "dustSmallNiter", 
          "dustSmallNiter", 
          "dustSmallSulfur", 
          "dustSmallCharcoal"));
    }

    if(SubstratumConfig.blend_signalum_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.SIGNALUM,4),
          "dustCopper", 
          "dustCopper", 
          "dustCopper", 
          "dustSilver",
          "bucketLiquidRedstone"));
      
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.SIGNALUM),
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallCopper", 
          "dustSmallSilver",
          "bottleLiquidRedstone"));
    }

    if(SubstratumConfig.blend_lumium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.LUMIUM,4),
          "dustTin", 
          "dustTin", 
          "dustTin", 
          "dustSilver",
          "bucketLiquidGlowstone" ));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.LUMIUM),
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallSilver",
          "bottleLiquidGlowstone" ));
    }

    if(SubstratumConfig.blend_enderium_enable)
    {
      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERIUM,2),
          "dustTin", 
          "dustTin", 
          "dustSilver",
          "dustPlatinum",
          "bucketLiquidEnderpearl" ));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST_SMALL,EnumMaterial.ENDERIUM,2),
          "dustSmallTin", 
          "dustSmallTin", 
          "dustSmallSilver",
          "dustSmallPlatinum",
          "bottleLiquidEnderpearl" ));

      GameRegistry.addRecipe(new ShapelessOreRecipe(
          SubstratumItems.getStack(EnumMaterialItem.DUST,EnumMaterial.ENDERIUM),
          "dustTin", 
          "dustSmallSilver", 
          "dustSmallSilver", 
          "dustSmallPlatinum",
          "dustSmallPlatinum",
          "bottleLiquidEnderpearl",
          "bottleLiquidEnderpearl" ));
    }
    */
  }
}
