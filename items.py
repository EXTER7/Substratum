class ItemType:
  def __init__(self, name, tool, local_name):
    self.name = name
    self.tool = bool(tool)
    self.local_name = local_name

ITEM_TYPES = [
  ItemType("ingot", False, "%s Ingot"),
  ItemType("dust", False, "%s Dust"),
  ItemType("dust_small", False, "Small Pile of %s Dust"),
  ItemType("nugget", False, "%s Nugget"),
  ItemType("rod", False, "%s Rod"),
  ItemType("plate", False, "%s Plate"),
  ItemType("gear", False, "%s Gear"),
  ItemType("bottle_dust", False, "%s Dust Bottle"),
  ItemType("bottle_liquid", False, "Liquid %s Bottle"),
  ItemType("bucket_liquid", False, "Liquid %s Bucket"),
  ItemType("dye", False, "%s Dye Powder"),
  ItemType("dye_small", False, "Small Pile of %s Dye Powder"),
  ItemType("helmet", False, "%s Helmet"),
  ItemType("chestplate", False, "%s Chestplate"),
  ItemType("leggings", False, "%s Leggings"),
  ItemType("boots", False, "%s Boots"),
  ItemType("pickaxe", True, "%s Pickaxe"),
  ItemType("axe", True, "%s Axe"),
  ItemType("sword", True, "%s Sword"),
  ItemType("shovel", True, "%s Shovel"),
  ItemType("hoe", True, "%s Hoe")
]

