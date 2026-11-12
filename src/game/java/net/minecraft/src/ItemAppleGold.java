package net.minecraft.src;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;

public class ItemAppleGold extends ItemFood {
   public ItemAppleGold(int var1, int var2, float var3, boolean var4) {
      super(var1, var2, var3, var4);
   }

   public boolean func_40403_e(ItemStack var1) {
      return true;
   }

   public EnumRarity func_40398_f(ItemStack var1) {
      return EnumRarity.epic;
   }
}
