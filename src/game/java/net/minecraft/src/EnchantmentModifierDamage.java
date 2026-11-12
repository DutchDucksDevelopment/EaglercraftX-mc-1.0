package net.minecraft.src;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Empty3;
import net.minecraft.src.Enchantment;
import net.minecraft.src.IEnchantmentModifier;

final class EnchantmentModifierDamage implements IEnchantmentModifier {
   public int damageModifier;
   public DamageSource damageSource;

   private EnchantmentModifierDamage() {
   }

   public void calculateModifier(Enchantment var1, int var2) {
      this.damageModifier += var1.calcModifierDamage(var2, this.damageSource);
   }

   // $FF: synthetic method
   EnchantmentModifierDamage(Empty3 var1) {
      this();
   }
}
