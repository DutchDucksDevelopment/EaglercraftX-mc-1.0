package net.minecraft.src;

import net.minecraft.src.ItemArmor;

public enum EnumArmorMaterial {
   CLOTH(5, new int[]{1, 3, 2, 1}, 15),
   CHAIN(15, new int[]{2, 5, 4, 1}, 12),
   IRON(15, new int[]{2, 6, 5, 2}, 9),
   GOLD(7, new int[]{2, 5, 3, 1}, 25),
   DIAMOND(33, new int[]{3, 8, 6, 3}, 10);

   private int field_40577_f;
   private int[] field_40578_g;
   private int enchantability;

   private EnumArmorMaterial(int var3, int[] var4, int var5) {
      this.field_40577_f = var3;
      this.field_40578_g = var4;
      this.enchantability = var5;
   }

   public int func_40576_a(int var1) {
      return ItemArmor.func_40436_c()[var1] * this.field_40577_f;
   }

   public int func_40574_b(int var1) {
      return this.field_40578_g[var1];
   }

   public int getEnchantability() {
      return this.enchantability;
   }
}
