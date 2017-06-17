package exter.substratum.resgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import exter.substratum.material.EnumMaterial;
import exter.substratum.material.EnumMaterialFluid;

public class FluidResources
{
  private static void writeFluidBlockState(String output,EnumMaterial material) throws IOException
  {
    FileWriter writer = new FileWriter(new File(String.join(File.separator, new String[] {ResourcePaths.BLOCKSTATES, output})));
    JsonWriter json = new JsonWriter(writer);
    json.beginObject();
    json.setIndent("  ");
    json.name("forge_marker").value(1);
    json.name("variants").beginObject();
    json.name("normal").beginObject();
    json.name("model").value("forge:fluid");
    json.name("custom").beginObject();
    json.name("fluid").value("liquid" + material.name);
    json.endObject();
    json.endObject();
    json.endObject();
    json.endObject();
    json.close();
    writer.close();
  }

  static public void generate()
  {
    try
    {
      for(EnumMaterialFluid material_fluid:EnumMaterialFluid.values())
      {
        writeFluidBlockState(String.format("liquid_%s.json", material_fluid.material.name),material_fluid.material);
      }
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
