#!/bin/bash

item_texturedir="src/main/resources/assets/substratum/textures/items"
block_texturedir="src/main/resources/assets/substratum/textures/blocks"

output="src/main/resources/assets/substratum/lang/en_US.lang"

function registry_name()
{
  echo $1 | sed 's/\([A-Z]\)/_\1/g' | tr 'A-Z' 'a-z'
}

function generate()
{
  IFS='
'
  materials=$(cat materials.list)
  prefixes=$(cat $1)

  for p in $prefixes
  do
    pref_id=$(echo $p | cut -d'=' -f 1)
    pref_name=$(echo $p | cut -d'=' -f 2)
    pref_tex=$(echo $pref_name | cut -s -d':' -f 2)
    if [ ! -z $pref_tex ]
    then
      pref_name=$(echo $pref_name | cut -s -d':' -f 1)
    else
      pref_tex=$pref_id
    fi
    for m in $materials
    do
      mat_id=$(echo $m | cut -d'=' -f 1)
      mat_name=$(echo $m | cut -d'=' -f 2)
      item_id=$pref_id"_"$mat_id
      tex_name=$3"/"$pref_tex"_"$mat_id".png"
      if [ -f $tex_name ]
      then
        echo $2"."$item_id".name="$(echo $pref_name | sed 's/@@MATERIAL@@/'$mat_name'/g') >> $output
      fi
    done
    echo "" >> $output
  done
}

# Creative tab
echo "itemGroup.substratum=Substratum" > $output
echo "" >> $output

# Generate block names
generate block_prefixes.list "tile.substratum" $block_texturedir

# Generate item names
generate item_prefixes.list "item.substratum" $item_texturedir


# Special item name cases
echo "" >> $output
echo "item.substratum.dust_small_gunpowder=Small Pile of Gunpowder" >> $output
echo "item.substratum.dust_small_blaze=Small Pile of Blaze Powder" >> $output
echo "" >> $output
echo "item.substratum.mortar=Mortar" >> $output
echo "" >> $output
echo "" >> $output

# Fluids
for fluid in $(cat "fluids.list")
do
  echo "fluid.substratum.liquid"$(registry_name $fluid)"=Liquid "$fluid >> $output
done
echo "" >> $output

for fluid in $(cat "fluids.list")
do
  echo "tile.substratum.liquid"$(registry_name $fluid)".name=Liquid "$fluid >> $output
done

