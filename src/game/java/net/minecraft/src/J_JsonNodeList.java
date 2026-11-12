package net.minecraft.src;

import java.util.ArrayList;
import net.minecraft.src.J_JsonNode;

class J_JsonNodeList extends ArrayList {
   // $FF: synthetic field
   final Iterable field_27405_a;

   J_JsonNodeList(Iterable var1) {
      this.field_27405_a = var1;

      for(java.util.Iterator var3_iter = (this.field_27405_a).iterator(); var3_iter.hasNext();) {
          J_JsonNode var3 = (J_JsonNode) var3_iter.next();
         this.add(var3);
      }

   }
}
