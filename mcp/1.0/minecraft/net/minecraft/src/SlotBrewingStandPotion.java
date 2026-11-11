package net.minecraft.src;

import net.minecraft.src.AchievementList;
import net.minecraft.src.ContainerBrewingStand;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

class SlotBrewingStandPotion extends Slot {
   private EntityPlayer field_40440_f;
   // $FF: synthetic field
   final ContainerBrewingStand field_40441_a;

   public SlotBrewingStandPotion(ContainerBrewingStand var1, EntityPlayer var2, IInventory var3, int var4, int var5, int var6) {
      super(var3, var4, var5, var6);
      this.field_40441_a = var1;
      this.field_40440_f = var2;
   }

   public boolean isItemValid(ItemStack var1) {
      return var1 != null && (var1.itemID == Item.potion.shiftedIndex || var1.itemID == Item.glassBottle.shiftedIndex);
   }

   public int getSlotStackLimit() {
      return 1;
   }

   public void onPickupFromSlot(ItemStack var1) {
      if(var1.itemID == Item.potion.shiftedIndex && var1.getItemDamage() > 0) {
         this.field_40440_f.addStat(AchievementList.potion, 1);
      }

      super.onPickupFromSlot(var1);
   }
}
