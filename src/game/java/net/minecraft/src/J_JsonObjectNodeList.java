package net.minecraft.src;

import java.util.HashMap;
import net.minecraft.src.J_JsonFieldBuilder;
import net.minecraft.src.J_JsonObjectNodeBuilder;

class J_JsonObjectNodeList extends HashMap {
   // $FF: synthetic field
   final J_JsonObjectNodeBuilder nodeBuilder;

   J_JsonObjectNodeList(J_JsonObjectNodeBuilder var1) {
      this.nodeBuilder = var1;

      for(java.util.Iterator var3_iter = (J_JsonObjectNodeBuilder.func_27236_a(this.nodeBuilder)).iterator(); var3_iter.hasNext();) {
          J_JsonFieldBuilder var3 = (J_JsonFieldBuilder) var3_iter.next();
         this.put(var3.func_27303_b(), var3.func_27302_c());
      }

   }
}
