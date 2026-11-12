package net.minecraft.src;

import java.util.Collection;
import java.util.Random;
import net.minecraft.src.WeightedRandomChoice;

public class WeightedRandom {
   public static int func_35736_a(Collection var0) {
      int var1 = 0;

      for(java.util.Iterator var3_iter = (var0).iterator(); var3_iter.hasNext();) {
          WeightedRandomChoice var3 = (WeightedRandomChoice) var3_iter.next();
         var1 += var3.field_35590_d;
      }

      return var1;
   }

   public static WeightedRandomChoice func_35734_a(Random var0, Collection var1, int var2) {
      if(var2 <= 0) {
         throw new IllegalArgumentException();
      } else {
         int var3 = var0.nextInt(var2);

         for(java.util.Iterator var5_iter = (var1).iterator(); var5_iter.hasNext();) {
             WeightedRandomChoice var5 = (WeightedRandomChoice) var5_iter.next();
            var3 -= var5.field_35590_d;
            if(var3 < 0) {
               return var5;
            }
         }

         return null;
      }
   }

   public static WeightedRandomChoice func_35733_a(Random var0, Collection var1) {
      return func_35734_a(var0, var1, func_35736_a(var1));
   }

   public static int func_35737_a(WeightedRandomChoice[] var0) {
      int var1 = 0;

      for(WeightedRandomChoice var5 : var0) {
         var1 += var5.field_35590_d;
      }

      return var1;
   }

   public static WeightedRandomChoice func_35732_a(Random var0, WeightedRandomChoice[] var1, int var2) {
      if(var2 <= 0) {
         throw new IllegalArgumentException();
      } else {
         int var3 = var0.nextInt(var2);

         for(WeightedRandomChoice var7 : var1) {
            var3 -= var7.field_35590_d;
            if(var3 < 0) {
               return var7;
            }
         }

         return null;
      }
   }

   public static WeightedRandomChoice func_35735_a(Random var0, WeightedRandomChoice[] var1) {
      return func_35732_a(var0, var1, func_35737_a(var1));
   }
}
