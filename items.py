from enum import Enum

_texure_dir="src/main/resources/assets/substratum/textures"


def _od_prefix(name):
  upper = False
  result = ""
  for c in name:
    if c == '_':
      upper = True
    elif upper:
      result = result + c.upper()
    upper = False
  return result

class Registry(Enum):
  BLOCK = "tile"
  ITEM = "item"
  FLUID = "fluid"

class ItemType:

  def __init__(self, name, regtype, tool, texture, local_name):
    self.name = name
    self.regtype = regtype
    self.tool = bool(tool)
    self.local_name = local_name
    self.od_prefix = _od_prefix(name)
    self.texture = _texure_dir + "/" + texture

ITEM_TYPES = [
  ItemType("ore",           Registry.BLOCK,  False, "blocks/ore_%s.png",          "%s Ore", ),
  ItemType("block",         Registry.BLOCK,  False, "blocks/block_%s.png",        "Block of %s"),
  ItemType("slab",          Registry.BLOCK,  False, "blocks/slab_%s.png",         "%s Slab"),
  ItemType("slab_double",   Registry.BLOCK,  False, "blocks/slab_%s.png",         "Double %s Slab"),
  ItemType("stairs",        Registry.BLOCK,  False, "blocks/slab_%s.png",         "%s Stars"),
  ItemType("ingot",         Registry.ITEM,   False, "items/ingot_%s.png",         "%s Ingot"),
  ItemType("dust",          Registry.ITEM,   False, "items/dust_%s.png",          "%s Dust"),
  ItemType("dust_small",    Registry.ITEM,   False, "items/dust_small_%s.png",    "Small Pile of %s Dust"),
  ItemType("nugget",        Registry.ITEM,   False, "items/nugget_%s.png",        "%s Nugget"),
  ItemType("plate",         Registry.ITEM,   False, "items/plate_%s.png",         "%s Plate"),
  ItemType("rod",           Registry.ITEM,   False, "items/rod_%s.png",           "%s Rod"),
  ItemType("gear",          Registry.ITEM,   False, "items/gear_%s.png",          "%s Gear"),
  ItemType("bottle_dust",   Registry.ITEM,   False, "items/bottle_dust_%s.png",   "%s Dust Bottle"),
  ItemType("bottle_liquid", Registry.ITEM,   False, "items/bottle_liquid_%s.png", "Liquid %s Bottle"),
  ItemType("bucket_liquid", Registry.ITEM,   False, "items/bucket_liquid_%s.png", "Liquid %s Bucket"),
  ItemType("dye",           Registry.ITEM,   False, "items/dye_%s.png",           "%s Dye Powder"),
  ItemType("dye_small",     Registry.ITEM,   False, "items/dye_small_%s.png",     "Small Pile of %s Dye Powder"),
  ItemType("helmet",        Registry.ITEM,   False, "items/helmet_%s.png",        "%s Helmet"),
  ItemType("chestplate",    Registry.ITEM,   False, "items/chestplate_%s.png",    "%s Chestplate"),
  ItemType("leggings",      Registry.ITEM,   False, "items/leggings_%s.png",      "%s Leggings"),
  ItemType("boots",         Registry.ITEM,   False, "items/boots_%s.png",         "%s Boots"),
  ItemType("pickaxe",       Registry.ITEM,   True,  "items/pickaxe_%s.png",       "%s Pickaxe"),
  ItemType("axe",           Registry.ITEM,   True,  "items/axe_%s.png",           "%s Axe"),
  ItemType("sword",         Registry.ITEM,   True,  "items/sword_%s.png",         "%s Sword"),
  ItemType("shovel",        Registry.ITEM,   True,  "items/shovel_%s.png",        "%s Shovel"),
  ItemType("hoe",           Registry.ITEM,   True,  "items/hoe_%s.png",           "%s Hoe"),
  ItemType("liquid",        Registry.FLUID,  False, "blocks/liquid_%s.png",       "Liquid %s")
]

ITEM_TYPES_DICT = {}
for itype in ITEM_TYPES:
  ITEM_TYPES_DICT[itype.name] = itype

