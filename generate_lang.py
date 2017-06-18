#!/usr/bin/python3

import json

from materials import *
from items import *

assets_dir = os.path.join("src","main","resources","assets","substratum")
enus_lang  = os.path.join(assets_dir, "lang", "en_US.lang")

def output_json(filename,obj):
  f = open(filename, "w")
  f.write(json.dumps(obj, sort_keys=True, indent=2, separators=(',', ': ')))
  f.close()


lang = open(enus_lang, "w")
lang.write("item_group.substratum=Substratum\n")
lang.write("\n")

for mat in MATERIALS:
  for i in mat.items:
    itype = ITEM_TYPES_DICT[i]
    name = "%s_%s" % (itype.name, mat.name)
    if itype.name in mat.special_loc:
      localized = mat.special_loc[itype.name]
    else:
      localized = itype.local_name % mat.local_name
    lang.write("%s.substratum.%s.name=%s\n" % (itype.regtype.value, name, localized))
  lang.write("\n")

lang.write("\n")
lang.write("item.substratum.mortar.name=Mortar\n")

lang.close()


