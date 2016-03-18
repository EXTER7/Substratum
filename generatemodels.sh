#!/bin/bash

modeldir="src/main/resources/assets/substratum/models"
blockstatedir="src/main/resources/assets/substratum/blockstates"


for block in $(cat "blocks.list")
do
  ( # Block model
  cat <<- EOF
	{
	  "parent": "minecraft:block/cube_all",
	  "textures":
	  {
	    "all": "substratum:blocks/@@TEXTURE@@"
	  }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/"$block"/g" > $modeldir"/block/"$block".json"

  ( # Block item model
  cat <<- EOF
	{
	    "parent": "substratum:block/@@MODEL@@",
	    "display": {
	        "thirdperson": {
	            "rotation": [ 10, -45, 170 ],
	            "translation": [ 0, 1.5, -2.75 ],
	            "scale": [ 0.375, 0.375, 0.375 ]
	        }
	    }
	}
	EOF
  ) | sed -e "s/@@MODEL@@/"$block"/g" > $modeldir"/item/"$block".json"
done

for slab in $(cat "slabs.list")
do
  ( # Bottom slab
  cat <<- EOF
	{
	   "parent": "minecraft:block/half_slab",
	   "textures":
	   {
	     "bottom": "substratum:blocks/@@TEXTURE@@",
	     "top": "substratum:blocks/@@TEXTURE@@",
	     "side": "substratum:blocks/@@TEXTURESIDE@@"
	  }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/block"$slab"/g" \
    | sed -e "s/@@TEXTURESIDE@@/slab"$slab"/g" \
    | sed -e 's/substratum:blocks\/blockIron/minecraft:blocks\/iron_block/g' \
    | sed -e 's/substratum:blocks\/blockGold/minecraft:blocks\/gold_block/g' > $modeldir"/block/slabBottom"$slab".json"

  ( # Top slab
  cat <<- EOF
	{
	   "parent": "minecraft:block/upper_slab",
	   "textures":
	   {
	     "bottom": "substratum:blocks/@@TEXTURE@@",
	     "top": "substratum:blocks/@@TEXTURE@@",
	     "side": "substratum:blocks/@@TEXTURESIDE@@"
	  }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/block"$slab"/g" \
    | sed -e "s/@@TEXTURESIDE@@/slab"$slab"/g" \
    | sed -e 's/substratum:blocks\/blockIron/minecraft:blocks\/iron_block/g' \
    | sed -e 's/substratum:blocks\/blockGold/minecraft:blocks\/gold_block/g' > $modeldir"/block/slabTop"$slab".json"
  
  ( # Double slab
  cat <<- EOF
	{
	   "parent": "block/cube_column",
	   "textures":
	   {
	     "end": "substratum:blocks/@@TEXTURE@@",
	     "side": "substratum:blocks/@@TEXTURESIDE@@"
	  }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/block"$slab"/g" \
    | sed -e "s/@@TEXTURESIDE@@/slab"$slab"/g" \
    | sed -e 's/substratum:blocks\/blockIron/minecraft:blocks\/iron_block/g' \
    | sed -e 's/substratum:blocks\/blockGold/minecraft:blocks\/gold_block/g' > $modeldir"/block/slabDouble"$slab".json"

  ( # Slab item model
  cat <<- EOF
	{
	    "parent": "substratum:block/@@MODEL@@",
	    "display": {
	        "thirdperson": {
	            "rotation": [ 10, -45, 170 ],
	            "translation": [ 0, 1.5, -2.75 ],
	            "scale": [ 0.375, 0.375, 0.375 ]
	        }
	    }
	}
	EOF
  ) | sed -e "s/@@MODEL@@/slabBottom"$slab"/g" > $modeldir"/item/slab"$slab".json"

  ( # Stairs blockstate
  cat <<- EOF
	{
	  "variants": {
	    "facing=east,half=bottom,shape=straight":  { "model": "substratum:@@MODELSTRAIGHT@@" },
	    "facing=west,half=bottom,shape=straight":  { "model": "substratum:@@MODELSTRAIGHT@@", "y": 180, "uvlock": true },
	    "facing=south,half=bottom,shape=straight": { "model": "substratum:@@MODELSTRAIGHT@@", "y": 90, "uvlock": true },
	    "facing=north,half=bottom,shape=straight": { "model": "substratum:@@MODELSTRAIGHT@@", "y": 270, "uvlock": true },
	    "facing=east,half=bottom,shape=outer_right":  { "model": "substratum:@@MODELOUTER@@" },
	    "facing=west,half=bottom,shape=outer_right":  { "model": "substratum:@@MODELOUTER@@", "y": 180, "uvlock": true },
	    "facing=south,half=bottom,shape=outer_right": { "model": "substratum:@@MODELOUTER@@", "y": 90, "uvlock": true },
	    "facing=north,half=bottom,shape=outer_right": { "model": "substratum:@@MODELOUTER@@", "y": 270, "uvlock": true },
	    "facing=east,half=bottom,shape=outer_left":  { "model": "substratum:@@MODELOUTER@@", "y": 270, "uvlock": true },
	    "facing=west,half=bottom,shape=outer_left":  { "model": "substratum:@@MODELOUTER@@", "y": 90, "uvlock": true },
	    "facing=south,half=bottom,shape=outer_left": { "model": "substratum:@@MODELOUTER@@" },
	    "facing=north,half=bottom,shape=outer_left": { "model": "substratum:@@MODELOUTER@@", "y": 180, "uvlock": true },
	    "facing=east,half=bottom,shape=inner_right":  { "model": "substratum:@@MODELINNER@@" },
	    "facing=west,half=bottom,shape=inner_right":  { "model": "substratum:@@MODELINNER@@", "y": 180, "uvlock": true },
	    "facing=south,half=bottom,shape=inner_right": { "model": "substratum:@@MODELINNER@@", "y": 90, "uvlock": true },
	    "facing=north,half=bottom,shape=inner_right": { "model": "substratum:@@MODELINNER@@", "y": 270, "uvlock": true },
	    "facing=east,half=bottom,shape=inner_left":  { "model": "substratum:@@MODELINNER@@", "y": 270, "uvlock": true },
	    "facing=west,half=bottom,shape=inner_left":  { "model": "substratum:@@MODELINNER@@", "y": 90, "uvlock": true },
	    "facing=south,half=bottom,shape=inner_left": { "model": "substratum:@@MODELINNER@@" },
	    "facing=north,half=bottom,shape=inner_left": { "model": "substratum:@@MODELINNER@@", "y": 180, "uvlock": true },
	    "facing=east,half=top,shape=straight":  { "model": "substratum:@@MODELSTRAIGHT@@", "x": 180, "uvlock": true },
	    "facing=west,half=top,shape=straight":  { "model": "substratum:@@MODELSTRAIGHT@@", "x": 180, "y": 180, "uvlock": true },
	    "facing=south,half=top,shape=straight": { "model": "substratum:@@MODELSTRAIGHT@@", "x": 180, "y": 90, "uvlock": true },
	    "facing=north,half=top,shape=straight": { "model": "substratum:@@MODELSTRAIGHT@@", "x": 180, "y": 270, "uvlock": true },
	    "facing=east,half=top,shape=outer_right":  { "model": "substratum:@@MODELOUTER@@", "x": 180, "uvlock": true },
	    "facing=west,half=top,shape=outer_right":  { "model": "substratum:@@MODELOUTER@@", "x": 180, "y": 180, "uvlock": true },
	    "facing=south,half=top,shape=outer_right": { "model": "substratum:@@MODELOUTER@@", "x": 180, "y": 90, "uvlock": true },
	    "facing=north,half=top,shape=outer_right": { "model": "substratum:@@MODELOUTER@@", "x": 180, "y": 270, "uvlock": true },
	    "facing=east,half=top,shape=outer_left":  { "model": "substratum:@@MODELOUTER@@", "x": 180, "y": 90, "uvlock": true },
	    "facing=west,half=top,shape=outer_left":  { "model": "substratum:@@MODELOUTER@@", "x": 180, "y": 270, "uvlock": true },
	    "facing=south,half=top,shape=outer_left": { "model": "substratum:@@MODELOUTER@@", "x": 180, "y": 180, "uvlock": true },
	    "facing=north,half=top,shape=outer_left": { "model": "substratum:@@MODELOUTER@@", "x": 180, "uvlock": true },
	    "facing=east,half=top,shape=inner_right":  { "model": "substratum:@@MODELINNER@@", "x": 180, "uvlock": true },
	    "facing=west,half=top,shape=inner_right":  { "model": "substratum:@@MODELINNER@@", "x": 180, "y": 180, "uvlock": true },
	    "facing=south,half=top,shape=inner_right": { "model": "substratum:@@MODELINNER@@", "x": 180, "y": 90, "uvlock": true },
	    "facing=north,half=top,shape=inner_right": { "model": "substratum:@@MODELINNER@@", "x": 180, "y": 270, "uvlock": true },
	    "facing=east,half=top,shape=inner_left":  { "model": "substratum:@@MODELINNER@@", "x": 180, "y": 90, "uvlock": true },
	    "facing=west,half=top,shape=inner_left":  { "model": "substratum:@@MODELINNER@@", "x": 180, "y": 270, "uvlock": true },
	    "facing=south,half=top,shape=inner_left": { "model": "substratum:@@MODELINNER@@", "x": 180, "y": 180, "uvlock": true },
	    "facing=north,half=top,shape=inner_left": { "model": "substratum:@@MODELINNER@@", "x": 180, "uvlock": true }
	  }
	}
	EOF
  ) | sed -e "s/@@MODELSTRAIGHT@@/stairsStraight"$slab"/g" \
    | sed -e "s/@@MODELINNER@@/stairsInner"$slab"/g" \
    | sed -e "s/@@MODELOUTER@@/stairsOuter"$slab"/g" > $blockstatedir"/stairs"$slab".json"

  ( # Straight stairs
  cat <<- EOF
	{
	  "parent": "minecraft:block/stairs",
	  "textures":
	  {
	    "bottom": "substratum:blocks/@@TEXTURE@@",
	    "top": "substratum:blocks/@@TEXTURE@@",
	    "side": "substratum:blocks/@@TEXTURE@@"
	  }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/block"$slab"/g" \
    | sed -e 's/substratum:blocks\/blockIron/minecraft:blocks\/iron_block/g' \
    | sed -e 's/substratum:blocks\/blockGold/minecraft:blocks\/gold_block/g' > $modeldir"/block/stairsStraight"$slab".json"

  ( # Inner stairs
  cat <<- EOF
	{
	  "parent": "minecraft:block/inner_stairs",
	  "textures":
	  {
	    "bottom": "substratum:blocks/@@TEXTURE@@",
	    "top": "substratum:blocks/@@TEXTURE@@",
	    "side": "substratum:blocks/@@TEXTURE@@"
	  }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/block"$slab"/g" \
    | sed -e 's/substratum:blocks\/blockIron/minecraft:blocks\/iron_block/g' \
    | sed -e 's/substratum:blocks\/blockGold/minecraft:blocks\/gold_block/g' > $modeldir"/block/stairsInner"$slab".json"

  ( # Outer stairs
  cat <<- EOF
	{
	  "parent": "minecraft:block/outer_stairs",
	  "textures":
	  {
	    "bottom": "substratum:blocks/@@TEXTURE@@",
	    "top": "substratum:blocks/@@TEXTURE@@",
	    "side": "substratum:blocks/@@TEXTURE@@"
	  }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/block"$slab"/g" \
    | sed -e 's/substratum:blocks\/blockIron/minecraft:blocks\/iron_block/g' \
    | sed -e 's/substratum:blocks\/blockGold/minecraft:blocks\/gold_block/g' > $modeldir"/block/stairsOuter"$slab".json"

  ( # Stairs item model
  cat <<- EOF
	{
	    "parent": "substratum:block/@@MODEL@@",
	    "display": {
	        "thirdperson": {
	            "rotation": [ 10, -45, 170 ],
	            "translation": [ 0, 1.5, -2.75 ],
	            "scale": [ 0.375, 0.375, 0.375 ]
	        }
	    }
	}
	EOF
  ) | sed -e "s/@@MODEL@@/stairsStraight"$slab"/g" > $modeldir"/item/stairs"$slab".json"
done

for item in $(cat "items.list")
do
  ( # Item model
  cat <<- EOF
	{
	    "parent": "builtin/generated",
	    "textures": {
	        "layer0": "substratum:items/@@TEXTURE@@"
	    },
	    "display": {
	        "thirdperson": {
	            "rotation": [ -90, 0, 0 ],
	            "translation": [ 0, 1, -3 ],
	            "scale": [ 0.55, 0.55, 0.55 ]
	        },
	        "firstperson": {
	            "rotation": [ 0, -135, 25 ],
	            "translation": [ 0, 4, 2 ],
	            "scale": [ 1.7, 1.7, 1.7 ]
	        }
	    }
	}
	EOF
  ) | sed -e "s/@@TEXTURE@@/"$item"/g" > $modeldir"/item/"$item".json"
done

for fluid in $(cat "fluids.list")
do
  ( # Fluid model
    cat <<- EOF
	{
	  "forge_marker": 1,
	  "variants":
	  {
	    "normal":
	    {
	      "model": "forge:fluid",
	      "custom": { "fluid": "@@FLUID@@" }
	    }
	  }
	}
	EOF
  ) | sed -e "s/@@FLUID@@/"$(echo $fluid | tr '[A-Z]' '[a-z]')"/g" > $blockstatedir"/"$fluid".json"
done

