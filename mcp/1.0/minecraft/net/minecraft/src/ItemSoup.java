package net.minecraft.src;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemSoup extends ItemFood {
   public ItemSoup(int var1, int var2) {
      super(var1, var2, false);
      this.setMaxStackSize(1);
   }

   public ItemStack onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      super.onFoodEaten(var1, var2, var3);
      return new ItemStack(Item.bowlEmpty);
   }
}
