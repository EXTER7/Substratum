#!/usr/bin/python3

import os.path
import json

from materials import *
from items import *

texuredir="src/main/resources/assets/substratum/textures"
modeldir="src/main/resources/assets/substratum/models"
blockstatedir="src/main/resources/assets/substratum/blockstates"

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
  output_json(modeldir + "/block/" + model + ".json", model_json)

  model_json = {
	  "parent": "substratum:block/" + model
	}
  output_json(modeldir + "/item/" + model + ".json", model_json)

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
  output_json(modeldir + "/block/slab_bottom_" + material + ".json", model_json)


  # Top slab
  model_json = {
	   "parent": "minecraft:block/upper_slab",
	   "textures": {
	     "bottom": "substratum:blocks/" + texture,
	     "top": "substratum:blocks/" + texture,
	     "side": "substratum:blocks/" + texture_side
	  }
	}
  output_json(modeldir + "/block/slab_top_" + material + ".json", model_json)
  
  # Double slab
  model_json = {
	   "parent": "minecraft:block/upper_slab",
	   "textures": {
	     "end": "substratum:blocks/" + texture,
	     "side": "substratum:blocks/" + texture_side
	  }
	}

  output_json(modeldir + "/block/slab_double_" + material +".json", model_json)

  model_json = {
	  "parent": "substratum:block/slab_bottom_" + material
	}
  output_json(modeldir + "/item/slab_" + material +".json", model_json)


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
  output_json(blockstatedir + "/stairs_" + material + ".json", model_json)

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
  output_json(modeldir + "/block/stairs_straight_" + material + ".json", model_json)

  model_json = {
	  "parent": "minecraft:block/inner_stairs",
	  "textures": {
	    "bottom": "substratum:blocks/@@TEXTURE@@",
	    "top": "substratum:blocks/@@TEXTURE@@",
	    "side": "substratum:blocks/@@TEXTURE@@"
	  }
	}
  output_json(modeldir + "/block/stairs_inner_" + material + ".json", model_json)

  model_json = {
	  "parent": "minecraft:block/outer_stairs",
	  "textures": {
	    "bottom": "substratum:blocks/@@TEXTURE@@",
	    "top": "substratum:blocks/@@TEXTURE@@",
	    "side": "substratum:blocks/@@TEXTURE@@"
	  }
	}
  output_json(modeldir + "/block/stairs_outer_" + material + ".json", model_json)

  # Stairs item model
  model_json = {
	  "parent": "substratum:block/stairs_straight_" + material
	}
  output_json(modeldir + "/item/stairs_" + material + ".json", model_json)

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
  output_json(blockstatedir + "/liquid_" + material + ".json", model_json)


def item_model(parent,texture):
  model_json = {
	    "parent": parent,
	    "textures": {
	        "layer0": "substratum:items/" + texture
	    }
	}
  output_json(modeldir + "/item/" + texture + ".json", model_json)


enus_lang = "src/main/resources/assets/substratum/lang/en_US.lang"

lang = open(enus_lang, "w")
lang.write("item_group.substratum=Substratum\n")
lang.write("\n")

for mat in MATERIALS:
  if os.path.isfile(texuredir + "/blocks/ore_" + mat.name + ".png"):
    block_model("ore_" + mat.name)
    lang.write("tile.substratum.ore_" + mat.name + "="+ mat.local_name + " Ore\n")

  if os.path.isfile(texuredir + "/blocks/block_" + mat.name + ".png"):
    block_model("block_" + mat.name)
    stairs_model(mat.name)
    lang.write("tile.substratum.block_" + mat.name + "=Block of "+ mat.local_name + "\n")
    lang.write("tile.substratum.stairs_" + mat.name + "="+ mat.local_name + " Stairs\n")

  if os.path.isfile(texuredir + "/blocks/slab_" + mat.name + ".png"):
    slab_model(mat.name)
    lang.write("tile.substratum.slab_" + mat.name + "="+ mat.local_name + " Slab\n")
    lang.write("tile.substratum.slab_double_" + mat.name + "=Double"+ mat.local_name + " Slab\n")

  if os.path.isfile(texuredir + "/blocks/liquid_" + mat.name + ".png"):
    liquid_model(mat.name)
    lang.write("fluid.substratum.liquid_" + mat.name + "=Liquid "+ mat.local_name + "\n")

  for itype in ITEM_TYPES:
    if os.path.isfile(texuredir + "/items/" + itype.name + "_" + mat.name + ".png"):
      if itype.tool:
        item_model("item/handheld",itype.name + "_" + mat.name)
      else:
        item_model("item/generated",itype.name + "_" + mat.name)
      lang.write("item.substratum." + itype.name + "_" + mat.name + "=" + itype.local_name % mat.local_name + "\n")


lang.write("item.substratum.dust_small_gunpowder=Small Pile of Gunpowder\n")
lang.write("item.substratum.dust_small_blaze=Small Pile of Blaze Powder\n")
lang.write("\n")
lang.write("item.substratum.mortar=Mortar\n")

lang.close()


