package net.minecraft.src;

import net.minecraft.src.Empty3;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.IEnchantmentModifier;

final class EnchantmentModifierLiving implements IEnchantmentModifier {
   public int livingModifier;
   public EntityLiving entityLiving;

   private EnchantmentModifierLiving() {
   }

   public void calculateModifier(Enchantment var1, int var2) {
      this.livingModifier += var1.calcModifierLiving(var2, this.entityLiving);
   }

   // $FF: synthetic method
   EnchantmentModifierLiving(Empty3 var1) {
      this();
   }
}
