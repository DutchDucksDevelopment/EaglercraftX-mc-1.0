package net.minecraft.src;

import net.minecraft.src.Container;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryCraftResult;
import net.minecraft.src.InventoryCrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotArmor;
import net.minecraft.src.SlotCrafting;

public class ContainerPlayer extends Container {
   public InventoryCrafting craftMatrix;
   public IInventory craftResult;
   public boolean isSinglePlayer;

   public ContainerPlayer(InventoryPlayer var1) {
      this(var1, true);
   }

   public ContainerPlayer(InventoryPlayer var1, boolean var2) {
      this.craftMatrix = new InventoryCrafting(this, 2, 2);
      this.craftResult = new InventoryCraftResult();
      this.isSinglePlayer = false;
      this.isSinglePlayer = var2;
      this.addSlot(new SlotCrafting(var1.player, this.craftMatrix, this.craftResult, 0, 144, 36));

      for(int var3 = 0; var3 < 2; ++var3) {
         for(int var4 = 0; var4 < 2; ++var4) {
            this.addSlot(new Slot(this.craftMatrix, var4 + var3 * 2, 88 + var4 * 18, 26 + var3 * 18));
         }
      }

      for(int var5 = 0; var5 < 4; ++var5) {
         this.addSlot(new SlotArmor(this, var1, var1.getSizeInventory() - 1 - var5, 8, 8 + var5 * 18, var5));
      }

      for(int var6 = 0; var6 < 3; ++var6) {
         for(int var8 = 0; var8 < 9; ++var8) {
            this.addSlot(new Slot(var1, var8 + (var6 + 1) * 9, 8 + var8 * 18, 84 + var6 * 18));
         }
      }

      for(int var7 = 0; var7 < 9; ++var7) {
         this.addSlot(new Slot(var1, var7, 8 + var7 * 18, 142));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   public void onCraftMatrixChanged(IInventory var1) {
      this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix));
   }

   public void onCraftGuiClosed(EntityPlayer var1) {
      super.onCraftGuiClosed(var1);

      for(int var2 = 0; var2 < 4; ++var2) {
         ItemStack var3 = this.craftMatrix.getStackInSlot(var2);
         if(var3 != null) {
            var1.dropPlayerItem(var3);
            this.craftMatrix.setInventorySlotContents(var2, (ItemStack)null);
         }
      }

   }

   public boolean canInteractWith(EntityPlayer var1) {
      return true;
   }

   public ItemStack transferStackInSlot(int var1) {
      ItemStack var2 = null;
      Slot var3 = (Slot)this.inventorySlots.get(var1);
      if(var3 != null && var3.getHasStack()) {
         ItemStack var4 = var3.getStack();
         var2 = var4.copy();
         if(var1 == 0) {
            if(!this.mergeItemStack(var4, 9, 45, true)) {
               return null;
            }
         } else if(var1 >= 9 && var1 < 36) {
            if(!this.mergeItemStack(var4, 36, 45, false)) {
               return null;
            }
         } else if(var1 >= 36 && var1 < 45) {
            if(!this.mergeItemStack(var4, 9, 36, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(var4, 9, 45, false)) {
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
