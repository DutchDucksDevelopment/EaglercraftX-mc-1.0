package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.Container;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryCraftResult;
import net.minecraft.src.InventoryCrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotCrafting;
import net.minecraft.src.World;

public class ContainerWorkbench extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
   public IInventory craftResult = new InventoryCraftResult();
   private World worldObj;
   private int posX;
   private int posY;
   private int posZ;

   public ContainerWorkbench(InventoryPlayer var1, World var2, int var3, int var4, int var5) {
      this.worldObj = var2;
      this.posX = var3;
      this.posY = var4;
      this.posZ = var5;
      this.addSlot(new SlotCrafting(var1.player, this.craftMatrix, this.craftResult, 0, 124, 35));

      for(int var6 = 0; var6 < 3; ++var6) {
         for(int var7 = 0; var7 < 3; ++var7) {
            this.addSlot(new Slot(this.craftMatrix, var7 + var6 * 3, 30 + var7 * 18, 17 + var6 * 18));
         }
      }

      for(int var8 = 0; var8 < 3; ++var8) {
         for(int var10 = 0; var10 < 9; ++var10) {
            this.addSlot(new Slot(var1, var10 + var8 * 9 + 9, 8 + var10 * 18, 84 + var8 * 18));
         }
      }

      for(int var9 = 0; var9 < 9; ++var9) {
         this.addSlot(new Slot(var1, var9, 8 + var9 * 18, 142));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   public void onCraftMatrixChanged(IInventory var1) {
      this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix));
   }

   public void onCraftGuiClosed(EntityPlayer var1) {
      super.onCraftGuiClosed(var1);
      if(!this.worldObj.multiplayerWorld) {
         for(int var2 = 0; var2 < 9; ++var2) {
            ItemStack var3 = this.craftMatrix.getStackInSlot(var2);
            if(var3 != null) {
               var1.dropPlayerItem(var3);
            }
         }

      }
   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.worldObj.getBlockId(this.posX, this.posY, this.posZ) != Block.workbench.blockID?false:var1.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
   }

   public ItemStack transferStackInSlot(int var1) {
      ItemStack var2 = null;
      Slot var3 = (Slot)this.inventorySlots.get(var1);
      if(var3 != null && var3.getHasStack()) {
         ItemStack var4 = var3.getStack();
         var2 = var4.copy();
         if(var1 == 0) {
            if(!this.mergeItemStack(var4, 10, 46, true)) {
               return null;
            }
         } else if(var1 >= 10 && var1 < 37) {
            if(!this.mergeItemStack(var4, 37, 46, false)) {
               return null;
            }
         } else if(var1 >= 37 && var1 < 46) {
            if(!this.mergeItemStack(var4, 10, 37, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(var4, 10, 46, false)) {
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
