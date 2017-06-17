
import os.path
from items import *


class Material:
  def __init__(self,name,local_name):
    self.name = name
    self.local_name = local_name
    self.special_loc = {}
    for itype in ITEM_TYPES:
      if os.path.isfile(itype.texture % name):
        self.items.append(itype.name)



MATERIALS = [
  Material("iron", "Iron"),
  Material("gold", "Gold"),
  Material("copper", "Copper"),
  Material("tin", "Tin"),
  Material("bronze", "Bronze"),
  Material("electrum", "Electrum"),
  Material("invar", "Invar"),
  Material("nickel", "Nickel"),
  Material("zinc", "Zinc"),
  Material("brass", "Brass"),
  Material("silver", "Silver"),
  Material("steel", "Steel"),
  Material("lead", "Lead"),
  Material("platinum", "Platinum"),
  Material("cupronickel", "Cupronickel"),
  Material("signalum", "Signalum"),
  Material("lumium", "Lumium"),
  Material("enderium", "Enderium"),
  Material("alumina", "Alumina"),
  Material("aluminium", "Aluminium"),
  Material("chrome", "Chromium"),
  Material("redstone", "Redstone"),
  Material("glowstone", "Glowstone"),
  Material("enderpearl", "Ender Pearl"),
  Material("obsidian", "Obsidian"),
  Material("coal", "Coal"),
  Material("charcoal", "Charcoal"),
  Material("gunpowder", "Gunpowder"),
  Material("blaze", "Blaze"),
  Material("black", "Black"),
  Material("red", "Red"),
  Material("green", "Green"),
  Material("brown", "Brown"),
  Material("blue", "Blue"),
  Material("purple", "Purple"),
  Material("cyan", "Cyan"),
  Material("light_gray", "Light Gray"),
  Material("gray", "Gray"),
  Material("pink", "Pink"),
  Material("lime", "Lime"),
  Material("yellow", "Yellow"),
  Material("light_blue", "Light Blue"),
  Material("magenta", "Magenta"),
  Material("orange", "Orange"),
  Material("white", "White"),
  Material("sulfur", "Sulfur"),
  Material("niter", "Niter")
]

MATERIALS_DICT = {}
for mat in MATERIALS:
  MATERIALS_DICT[mat.name] = mat

MATERIALS_DICT["iron"].vanilla_items = ["ingot", "nugget", "block"]
MATERIALS_DICT["gold"].vanilla_items = ["ingot", "nugget", "block"]
MATERIALS_DICT["redstone"].vanilla_items = ["dust"]
MATERIALS_DICT["glowstone"].vanilla_items = ["dust"]

MATERIALS_DICT["gunpowder"].special_loc = {"dust_small": "Small Pile of Gunpowder"}
MATERIALS_DICT["blaze"].special_loc = {"dust_small": "Small Pile of Blaze Powder"}

