package net.minecraft.src;

import net.minecraft.src.ContainerBrewingStand;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

class SlotBrewingStandIngredient extends Slot {
   // $FF: synthetic field
   final ContainerBrewingStand field_40442_a;

   public SlotBrewingStandIngredient(ContainerBrewingStand var1, IInventory var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this.field_40442_a = var1;
   }

   public boolean isItemValid(ItemStack var1) {
      return var1 != null?Item.itemsList[var1.itemID].func_40406_n():false;
   }

   public int getSlotStackLimit() {
      return 64;
   }
}
