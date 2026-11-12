package net.minecraft.src;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotBrewingStandIngredient;
import net.minecraft.src.SlotBrewingStandPotion;
import net.minecraft.src.TileEntityBrewingStand;

public class ContainerBrewingStand extends Container {
   private TileEntityBrewingStand field_40243_a;
   private int field_40242_b = 0;

   public ContainerBrewingStand(InventoryPlayer var1, TileEntityBrewingStand var2) {
      this.field_40243_a = var2;
      this.addSlot(new SlotBrewingStandPotion(this, var1.player, var2, 0, 56, 46));
      this.addSlot(new SlotBrewingStandPotion(this, var1.player, var2, 1, 79, 53));
      this.addSlot(new SlotBrewingStandPotion(this, var1.player, var2, 2, 102, 46));
      this.addSlot(new SlotBrewingStandIngredient(this, var2, 3, 79, 17));

      for(int var3 = 0; var3 < 3; ++var3) {
         for(int var4 = 0; var4 < 9; ++var4) {
            this.addSlot(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
         }
      }

      for(int var5 = 0; var5 < 9; ++var5) {
         this.addSlot(new Slot(var1, var5, 8 + var5 * 18, 142));
      }

   }

   public void updateCraftingResults() {
      super.updateCraftingResults();

      for(int var1 = 0; var1 < this.crafters.size(); ++var1) {
         ICrafting var2 = (ICrafting)this.crafters.get(var1);
         if(this.field_40242_b != this.field_40243_a.func_40053_g()) {
            var2.updateCraftingInventoryInfo(this, 0, this.field_40243_a.func_40053_g());
         }
      }

      this.field_40242_b = this.field_40243_a.func_40053_g();
   }

   public void updateProgressBar(int var1, int var2) {
      if(var1 == 0) {
         this.field_40243_a.func_40049_b(var2);
      }

   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.field_40243_a.isUseableByPlayer(var1);
   }

   public ItemStack transferStackInSlot(int var1) {
      ItemStack var2 = null;
      Slot var3 = (Slot)this.inventorySlots.get(var1);
      if(var3 != null && var3.getHasStack()) {
         ItemStack var4 = var3.getStack();
         var2 = var4.copy();
         if((var1 < 0 || var1 > 2) && var1 != 3) {
            if(var1 >= 4 && var1 < 31) {
               if(!this.mergeItemStack(var4, 31, 40, false)) {
                  return null;
               }
            } else if(var1 >= 31 && var1 < 40) {
               if(!this.mergeItemStack(var4, 4, 31, false)) {
                  return null;
               }
            } else if(!this.mergeItemStack(var4, 4, 40, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(var4, 4, 40, true)) {
            return null;
         }

         if(var4.stackSize == 0) {
            var3.putStack((ItemStack)null);
         } else {
            var3.onSlotChanged();
         }

         if(var4.stackSize == var2.stackSize) {
            return null;
         }

         var3.onPickupFromSlot(var4);
      }

      return var2;
   }
}
