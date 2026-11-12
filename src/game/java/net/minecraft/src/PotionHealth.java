package net.minecraft.src;

import net.minecraft.src.Potion;

public class PotionHealth extends Potion {
   public PotionHealth(int var1, boolean var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean func_40622_b() {
      return true;
   }

   public boolean isReady(int var1, int var2) {
      return var1 >= 1;
   }
}
