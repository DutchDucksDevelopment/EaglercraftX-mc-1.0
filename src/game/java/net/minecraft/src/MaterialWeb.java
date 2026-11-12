package net.minecraft.src;

import net.minecraft.src.MapColor;
import net.minecraft.src.Material;

class MaterialWeb extends Material {
   MaterialWeb(MapColor var1) {
      super(var1);
   }

   public boolean getIsSolid() {
      return false;
   }
}
