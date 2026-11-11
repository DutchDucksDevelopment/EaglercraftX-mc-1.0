package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class PacketCount {
   public static boolean field_40565_a = true;
   private static final Map field_40563_b = new HashMap();
   private static final Map field_40564_c = new HashMap();
   private static final Object field_40562_d = new Object();

   public static void func_40561_a(int var0, long var1) {
      if(field_40565_a) {
         synchronized(field_40562_d) {
            if(field_40563_b.containsKey(Integer.valueOf(var0))) {
               field_40563_b.put(Integer.valueOf(var0), Long.valueOf(((Long)field_40563_b.get(Integer.valueOf(var0))).longValue() + 1L));
               field_40564_c.put(Integer.valueOf(var0), Long.valueOf(((Long)field_40564_c.get(Integer.valueOf(var0))).longValue() + var1));
            } else {
               field_40563_b.put(Integer.valueOf(var0), Long.valueOf(1L));
               field_40564_c.put(Integer.valueOf(var0), Long.valueOf(var1));
            }

         }
      }
   }
}
