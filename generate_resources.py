#!/usr/bin/python3

import json

from materials import *
from items import *

assets_dir     = os.path.join("src","main","resources","assets","substratum")
model_dir      = os.path.join(assets_dir, "models")
recipe_dir     = os.path.join(assets_dir, "recipes")
blockstate_dir = os.path.join(assets_dir, "blockstates")
enus_lang      = os.path.join(assets_dir, "lang", "en_US.lang")

def output_json(filename,obj):
  f = open(filename, "w")
  f.write(json.dumps(obj, sort_keys=True, indent=2, separators=(',', ': ')))
  f.close()

def block_model(model):
  model_json = {
	  "parent": "minecraft:block/cube_all",
	  "textures": {
	    "all": "substratum:blocks/" + model
	  }
	}
  output_json(os.path.join(model_dir, "block", model + ".json"), model_json)

  model_json = {
	  "parent": "substratum:block/" + model
	}
  output_json(os.path.join(model_dir, "item", model + ".json"), model_json)

def slab_model(material):
  texture = "substratum:blocks/block_" + material
  texture = texture.replace("substratum:blocks/block_iron","minecraft:blocks/iron_block")
  texture = texture.replace("substratum:blocks/gold_iron","minecraft:blocks/gold_block")
  texture_side = "substratum:blocks/slab_" + material

  # Bottom slab
  model_json = {
	   "parent": "minecraft:block/half_slab",
	   "textures": {
	     "bottom": "substratum:blocks/" + texture,
	     "top": "substratum:blocks/" + texture,
	     "side": "substratum:blocks/" + texture_side
	  }
	}
  output_json(os.path.join(model_dir, "block" , "slab_bottom_" + material + ".json"), model_json)


  # Top slab
  model_json = {
	   "parent": "minecraft:block/upper_slab",
	   "textures": {
	     "bottom": "substratum:blocks/" + texture,
	     "top": "substratum:blocks/" + texture,
	     "side": "substratum:blocks/" + texture_side
	  }
	}
  output_json(os.path.join(model_dir, "block" , "slab_top_" + material + ".json"), model_json)
  
  # Double slab
  model_json = {
	   "parent": "minecraft:block/upper_slab",
	   "textures": {
	     "end": "substratum:blocks/" + texture,
	     "side": "substratum:blocks/" + texture_side
	  }
	}

  output_json(os.path.join(model_dir, "block" , "slab_double_" + material +".json"), model_json)

  model_json = {
	  "parent": "substratum:block/slab_bottom_" + material
	}
  output_json(os.path.join(model_dir, "item" , "slab_" + material +".json"), model_json)


def stairs_model(material):
  # Stairs blockstate
  model_json = {
	  "variants": {
	    "facing=east,half=bottom,shape=straight":  { "model": "substratum:stairs_straight_" + material },
	    "facing=west,half=bottom,shape=straight":  { "model": "substratum:stairs_straight_" + material, "y": 180, "uvlock": True },
	    "facing=south,half=bottom,shape=straight": { "model": "substratum:stairs_straight_" + material, "y": 90, "uvlock": True },
	    "facing=north,half=bottom,shape=straight": { "model": "substratum:stairs_straight_" + material, "y": 270, "uvlock": True },
	    "facing=east,half=bottom,shape=outer_right":  { "model": "substratum:stairs_outer_" + material },
	    "facing=west,half=bottom,shape=outer_right":  { "model": "substratum:stairs_outer_" + material, "y": 180, "uvlock": True },
	    "facing=south,half=bottom,shape=outer_right": { "model": "substratum:stairs_outer_" + material, "y": 90, "uvlock": True },
	    "facing=north,half=bottom,shape=outer_right": { "model": "substratum:stairs_outer_" + material, "y": 270, "uvlock": True },
	    "facing=east,half=bottom,shape=outer_left":  { "model": "substratum:stairs_outer_" + material, "y": 270, "uvlock": True },
	    "facing=west,half=bottom,shape=outer_left":  { "model": "substratum:stairs_outer_" + material, "y": 90, "uvlock": True },
	    "facing=south,half=bottom,shape=outer_left": { "model": "substratum:stairs_outer_" + material },
	    "facing=north,half=bottom,shape=outer_left": { "model": "substratum:stairs_outer_" + material, "y": 180, "uvlock": True },
	    "facing=east,half=bottom,shape=inner_right":  { "model": "substratum:stairs_inner_" + material },
	    "facing=west,half=bottom,shape=inner_right":  { "model": "substratum:stairs_inner_" + material, "y": 180, "uvlock": True },
	    "facing=south,half=bottom,shape=inner_right": { "model": "substratum:stairs_inner_" + material, "y": 90, "uvlock": True },
	    "facing=north,half=bottom,shape=inner_right": { "model": "substratum:stairs_inner_" + material, "y": 270, "uvlock": True },
	    "facing=east,half=bottom,shape=inner_left":  { "model": "substratum:stairs_inner_" + material, "y": 270, "uvlock": True },
	    "facing=west,half=bottom,shape=inner_left":  { "model": "substratum:stairs_inner_" + material, "y": 90, "uvlock": True },
	    "facing=south,half=bottom,shape=inner_left": { "model": "substratum:stairs_inner_" + material },
	    "facing=north,half=bottom,shape=inner_left": { "model": "substratum:stairs_inner_" + material, "y": 180, "uvlock": True },
	    "facing=east,half=top,shape=straight":  { "model": "substratum:stairs_straight_" + material, "x": 180, "uvlock": True },
	    "facing=west,half=top,shape=straight":  { "model": "substratum:stairs_straight_" + material, "x": 180, "y": 180, "uvlock": True },
	    "facing=south,half=top,shape=straight": { "model": "substratum:stairs_straight_" + material, "x": 180, "y": 90, "uvlock": True },
	    "facing=north,half=top,shape=straight": { "model": "substratum:stairs_straight_" + material, "x": 180, "y": 270, "uvlock": True },
	    "facing=east,half=top,shape=outer_right":  { "model": "substratum:stairs_outer_" + material, "x": 180, "uvlock": True },
	    "facing=west,half=top,shape=outer_right":  { "model": "substratum:stairs_outer_" + material, "x": 180, "y": 180, "uvlock": True },
	    "facing=south,half=top,shape=outer_right": { "model": "substratum:stairs_outer_" + material, "x": 180, "y": 90, "uvlock": True },
	    "facing=north,half=top,shape=outer_right": { "model": "substratum:stairs_outer_" + material, "x": 180, "y": 270, "uvlock": True },
	    "facing=east,half=top,shape=outer_left":  { "model": "substratum:stairs_outer_" + material, "x": 180, "y": 90, "uvlock": True },
	    "facing=west,half=top,shape=outer_left":  { "model": "substratum:stairs_outer_" + material, "x": 180, "y": 270, "uvlock": True },
	    "facing=south,half=top,shape=outer_left": { "model": "substratum:stairs_outer_" + material, "x": 180, "y": 180, "uvlock": True },
	    "facing=north,half=top,shape=outer_left": { "model": "substratum:stairs_outer_" + material, "x": 180, "uvlock": True },
	    "facing=east,half=top,shape=inner_right":  { "model": "substratum:stairs_inner_" + material, "x": 180, "uvlock": True },
	    "facing=west,half=top,shape=inner_right":  { "model": "substratum:stairs_inner_" + material, "x": 180, "y": 180, "uvlock": True },
	    "facing=south,half=top,shape=inner_right": { "model": "substratum:stairs_inner_" + material, "x": 180, "y": 90, "uvlock": True },
	    "facing=north,half=top,shape=inner_right": { "model": "substratum:stairs_inner_" + material, "x": 180, "y": 270, "uvlock": True },
	    "facing=east,half=top,shape=inner_left":  { "model": "substratum:stairs_inner_" + material, "x": 180, "y": 90, "uvlock": True },
	    "facing=west,half=top,shape=inner_left":  { "model": "substratum:stairs_inner_" + material, "x": 180, "y": 270, "uvlock": True },
	    "facing=south,half=top,shape=inner_left": { "model": "substratum:stairs_inner_" + material, "x": 180, "y": 180, "uvlock": True },
	    "facing=north,half=top,shape=inner_left": { "model": "substratum:stairs_inner_" + material, "x": 180, "uvlock": True }
	  }
	}
  output_json(os.path.join(blockstate_dir, "stairs_" + material + ".json"), model_json)

  texture = "substratum:blocks/block_" + material
  texture = texture.replace("substratum:blocks/block_iron","minecraft:blocks/iron_block")
  texture = texture.replace("substratum:blocks/gold_iron","minecraft:blocks/gold_block")

  # Straight stairs
  model_json = {
	  "parent": "minecraft:block/stairs",
	  "textures":
	  {
	    "bottom": "substratum:blocks/" + texture,
	    "top": "substratum:blocks/" + texture,
	    "side": "substratum:blocks/" + texture
	  }
	}
  output_json(os.path.join(model_dir, "block" , "stairs_straight_" + material + ".json"), model_json)

  model_json = {
	  "parent": "minecraft:block/inner_stairs",
	  "textures": {
	    "bottom": "substratum:blocks/" + texture,
	    "top": "substratum:blocks/" + texture,
	    "side": "substratum:blocks/" + texture
	  }
	}
  output_json(os.path.join(model_dir, "block" , "stairs_inner_" + material + ".json"), model_json)

  model_json = {
	  "parent": "minecraft:block/outer_stairs",
	  "textures": {
	    "bottom": "substratum:blocks/" + texture,
	    "top": "substratum:blocks/" + texture,
	    "side": "substratum:blocks/" + texture
	  }
	}
  output_json(os.path.join(model_dir, "block" , "stairs_outer_" + material + ".json"), model_json)

  # Stairs item model
  model_json = {
	  "parent": "substratum:block/stairs_straight_" + material
	}
  output_json(os.path.join(model_dir, "item" , "stairs_" + material + ".json"), model_json)

def liquid_model(material):
  json_model = {
	  "forge_marker": 1,
	  "variants": {
	    "normal": {
	      "model": "forge:fluid",
	      "custom": { "fluid": "liquid_" + material }
	    }
	  }
	}
  output_json(os.path.join(blockstate_dir, "liquid_" + material + ".json"), model_json)


def item_model(parent,texture):
  model_json = {
	    "parent": parent,
	    "textures": {
	        "layer0": "substratum:items/" + texture
	    }
	}
  output_json(os.path.join(model_dir, "item", texture + ".json"), model_json)



def item_ingredient(item_name):
  return {"item": str(item_name)}

def ore_ingredient(ore_name):
  return {"ore": str(ore_name)}

def shapeless_recipe(name,ingredients,result,count=1):
  recipe_json = {
    "type": "crafting_shapeless",
    "ingredients": ingredients,
    "result": {
        "item": "substratum:" + result,
        "count": int(count)
    }
  }
  output_json(os.path.join(recipe_dir, name + ".json"), recipe_json)


def shaped_recipe(name,pattern,ingredients,result,count=1,data=-1):
  recipe_json = {
    "type": "crafting_shaped",
    "pattern": pattern,
    "key": ingredients,
    "result": {
      "item": result,
      "count": int(count)
    }
  }
  if int(data) >= 0:
    recipe_json["result"]["data"] = int(data)
  output_json(os.path.join(recipe_dir, name + ".json"), recipe_json)



lang = open(enus_lang, "w")
lang.write("item_group.substratum=Substratum\n")
lang.write("\n")

for mat in MATERIALS:
  if "ore" in mat.items:
    block_model("ore_" + mat.name)

  if "block" in mat.items:
    block_model("block_" + mat.name)
    stairs_model(mat.name)

  if "slab" in mat.items:
    slab_model(mat.name)

  if "liquid" in mat.items:
    liquid_model(mat.name)

  for i in mat.items:
    itype = ITEM_TYPES_DICT[i]
    name = itype.name + "_" + mat.name
    if itype.regtype == Registry.ITEM:
      if itype.tool:
        item_model("item/handheld",name)
      else:
        item_model("item/generated",name)
    if itype.name in mat.special_loc:
      localized = mat.special_loc[itype.name]
    else:
      localized = name + "=" + itype.local_name % mat.local_name
    lang.write(itype.regtype.value + ".substratum." + localized + "\n")

  if "dust" in mat.items and ("ingot" in mat.items or "ingot" in mat.vanilla_items):
    dust = "substratum:" + ITEM_TYPES_DICT["dust"].name + "_" + mat.name
    ingot = ITEM_TYPES_DICT["ingot"].od_prefix + mat.od_suffix
    shapeless_recipe("ingot_dust_" + mat.name,\
                     [ore_ingredient(ingot),item_ingredient("mortar")],\
                     dust)
  if "dust" in mat.items and ("dust_small" in mat.items or "dust_small" in mat.vanilla_items):
    dust = "substratum:" + ITEM_TYPES_DICT["dust"].name + "_" + mat.name
    dust_small = "substratum:" + ITEM_TYPES_DICT["dust_small"].name + "_" + mat.name
    shaped_recipe("dust_small_split_" + mat.name,\
                  ["D  ",\
                   "   ",\
                   "   " ],
                  {'D': item_ingredient(dust)},\
                  dust_small, 4)
    shapeless_recipe("dust_small_combine_" + mat.name,\
                     [item_ingredient(dust_small),\
                      item_ingredient(dust_small),\
                      item_ingredient(dust_small),\
                      item_ingredient(dust_small)],\
                     dust, 1)

  if "block" in mat.items and "ingot" in mat.items:
    block = "substratum:" + ITEM_TYPES_DICT["block"].name + "_" + mat.name
    ingot = "substratum:" + ITEM_TYPES_DICT["ingot"].name + "_" + mat.name
    shaped_recipe("block_combine_" + mat.name,\
                  ["III",\
                   "III",\
                   "III" ],
                  {'I': item_ingredient(ingot)},\
                  block)
    shapeless_recipe("block_split_" + mat.name,\
                     [item_ingredient(block)],\
                     ingot, 9)

  if "ingot" in mat.items and "nugget" in mat.items:
    ingot = "substratum:" + ITEM_TYPES_DICT["ingot"].name + "_" + mat.name
    nugget = "substratum:" + ITEM_TYPES_DICT["nugget"].name + "_" + mat.name
    shaped_recipe("ingot_combine_" + mat.name,\
                  ["NNN",\
                   "NNN",\
                   "NNN" ],
                  {'N': item_ingredient(nugget)},\
                  ingot)
    shapeless_recipe("ingot_split_" + mat.name,\
                     [item_ingredient(ingot)],\
                     nugget, 9)

  lang.write("\n")

lang.write("\n")
lang.write("item.substratum.mortar=Mortar\n")

lang.close()


