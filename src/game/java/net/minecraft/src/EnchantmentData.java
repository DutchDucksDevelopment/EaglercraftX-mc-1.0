package net.minecraft.src;

import net.minecraft.src.Enchantment;
import net.minecraft.src.WeightedRandomChoice;

public class EnchantmentData extends WeightedRandomChoice {
   public final Enchantment field_40264_a;
   public final int field_40263_b;

   public EnchantmentData(Enchantment var1, int var2) {
      super(var1.getWeight());
      this.field_40264_a = var1;
      this.field_40263_b = var2;
   }
}
