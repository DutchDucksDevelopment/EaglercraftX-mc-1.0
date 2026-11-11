package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class IntCache {
   private static int intCacheSize = 256;
   private static List field_35271_b = new ArrayList();
   private static List field_35272_c = new ArrayList();
   private static List field_35269_d = new ArrayList();
   private static List field_35270_e = new ArrayList();

   public static int[] getIntCache(int var0) {
      if(var0 <= 256) {
         if(field_35271_b.size() == 0) {
            int[] var5 = new int[256];
            field_35272_c.add(var5);
            return var5;
         } else {
            int[] var4 = (int[])field_35271_b.remove(field_35271_b.size() - 1);
            field_35272_c.add(var4);
            return var4;
         }
      } else if(var0 > intCacheSize) {
         intCacheSize = var0;
         field_35269_d.clear();
         field_35270_e.clear();
         int[] var3 = new int[intCacheSize];
         field_35270_e.add(var3);
         return var3;
      } else if(field_35269_d.size() == 0) {
         int[] var2 = new int[intCacheSize];
         field_35270_e.add(var2);
         return var2;
      } else {
         int[] var1 = (int[])field_35269_d.remove(field_35269_d.size() - 1);
         field_35270_e.add(var1);
         return var1;
      }
   }

   public static void func_35268_a() {
      if(field_35269_d.size() > 0) {
         field_35269_d.remove(field_35269_d.size() - 1);
      }

      if(field_35271_b.size() > 0) {
         field_35271_b.remove(field_35271_b.size() - 1);
      }

      field_35269_d.addAll(field_35270_e);
      field_35271_b.addAll(field_35272_c);
      field_35270_e.clear();
      field_35272_c.clear();
   }
}
