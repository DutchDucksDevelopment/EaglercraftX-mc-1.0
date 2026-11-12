package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.Container;
import net.minecraft.src.EnchantmentData;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotEnchantment;
import net.minecraft.src.SlotEnchantmentTable;
import net.minecraft.src.World;

public class ContainerEnchantment extends Container {
   public IInventory field_40236_a = new SlotEnchantmentTable(this, "Enchant", 1);
   private World field_40240_h;
   private int field_40241_i;
   private int field_40238_j;
   private int field_40239_k;
   private Random field_40237_l = new Random();
   public long field_40234_b;
   public int[] field_40235_c = new int[3];

   public ContainerEnchantment(InventoryPlayer var1, World var2, int var3, int var4, int var5) {
      this.field_40240_h = var2;
      this.field_40241_i = var3;
      this.field_40238_j = var4;
      this.field_40239_k = var5;
      this.addSlot(new SlotEnchantment(this, this.field_40236_a, 0, 25, 47));

      for(int var6 = 0; var6 < 3; ++var6) {
         for(int var7 = 0; var7 < 9; ++var7) {
            this.addSlot(new Slot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
         }
      }

      for(int var8 = 0; var8 < 9; ++var8) {
         this.addSlot(new Slot(var1, var8, 8 + var8 * 18, 142));
      }

   }

   public void updateCraftingResults() {
      super.updateCraftingResults();

      for(int var1 = 0; var1 < this.crafters.size(); ++var1) {
         ICrafting var2 = (ICrafting)this.crafters.get(var1);
         var2.updateCraftingInventoryInfo(this, 0, this.field_40235_c[0]);
         var2.updateCraftingInventoryInfo(this, 1, this.field_40235_c[1]);
         var2.updateCraftingInventoryInfo(this, 2, this.field_40235_c[2]);
      }

   }

   public void updateProgressBar(int var1, int var2) {
      if(var1 >= 0 && var1 <= 2) {
         this.field_40235_c[var1] = var2;
      } else {
         super.updateProgressBar(var1, var2);
      }

   }

   public void onCraftMatrixChanged(IInventory var1) {
      if(var1 == this.field_40236_a) {
         ItemStack var2 = var1.getStackInSlot(0);
         if(var2 != null && var2.func_40708_t()) {
            this.field_40234_b = this.field_40237_l.nextLong();
            if(!this.field_40240_h.multiplayerWorld) {
               int var6 = 0;

               for(int var4 = -1; var4 <= 1; ++var4) {
                  for(int var5 = -1; var5 <= 1; ++var5) {
                     if((var4 != 0 || var5 != 0) && this.field_40240_h.isAirBlock(this.field_40241_i + var5, this.field_40238_j, this.field_40239_k + var4) && this.field_40240_h.isAirBlock(this.field_40241_i + var5, this.field_40238_j + 1, this.field_40239_k + var4)) {
                        if(this.field_40240_h.getBlockId(this.field_40241_i + var5 * 2, this.field_40238_j, this.field_40239_k + var4 * 2) == Block.bookShelf.blockID) {
                           ++var6;
                        }

                        if(this.field_40240_h.getBlockId(this.field_40241_i + var5 * 2, this.field_40238_j + 1, this.field_40239_k + var4 * 2) == Block.bookShelf.blockID) {
                           ++var6;
                        }

                        if(var5 != 0 && var4 != 0) {
                           if(this.field_40240_h.getBlockId(this.field_40241_i + var5 * 2, this.field_40238_j, this.field_40239_k + var4) == Block.bookShelf.blockID) {
                              ++var6;
                           }

                           if(this.field_40240_h.getBlockId(this.field_40241_i + var5 * 2, this.field_40238_j + 1, this.field_40239_k + var4) == Block.bookShelf.blockID) {
                              ++var6;
                           }

                           if(this.field_40240_h.getBlockId(this.field_40241_i + var5, this.field_40238_j, this.field_40239_k + var4 * 2) == Block.bookShelf.blockID) {
                              ++var6;
                           }

                           if(this.field_40240_h.getBlockId(this.field_40241_i + var5, this.field_40238_j + 1, this.field_40239_k + var4 * 2) == Block.bookShelf.blockID) {
                              ++var6;
                           }
                        }
                     }
                  }
               }

               for(int var7 = 0; var7 < 3; ++var7) {
                  this.field_40235_c[var7] = EnchantmentHelper.calcItemStackEnchantability(this.field_40237_l, var7, var6, var2);
               }

               this.updateCraftingResults();
            }
         } else {
            for(int var3 = 0; var3 < 3; ++var3) {
               this.field_40235_c[var3] = 0;
            }
         }
      }

   }

   public boolean func_40233_a(EntityPlayer var1, int var2) {
      ItemStack var3 = this.field_40236_a.getStackInSlot(0);
      if(this.field_40235_c[var2] > 0 && var3 != null && var1.playerLevel >= this.field_40235_c[var2]) {
         if(!this.field_40240_h.multiplayerWorld) {
            List var4 = EnchantmentHelper.buildEnchantmentList(this.field_40237_l, var3, this.field_40235_c[var2]);
            if(var4 != null) {
               var1.func_40184_i(this.field_40235_c[var2]);

               for(java.util.Iterator var6_iter = (var4).iterator(); var6_iter.hasNext();) {
                   EnchantmentData var6 = (EnchantmentData) var6_iter.next();
                  var3.addEnchantment(var6.field_40264_a, var6.field_40263_b);
               }

               this.onCraftMatrixChanged(this.field_40236_a);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void onCraftGuiClosed(EntityPlayer var1) {
      super.onCraftGuiClosed(var1);
      if(!this.field_40240_h.multiplayerWorld) {
         ItemStack var2 = this.field_40236_a.getStackInSlot(0);
         if(var2 != null) {
            var1.dropPlayerItem(var2);
         }

      }
   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.field_40240_h.getBlockId(this.field_40241_i, this.field_40238_j, this.field_40239_k) != Block.enchantmentTable.blockID?false:var1.getDistanceSq((double)this.field_40241_i + 0.5D, (double)this.field_40238_j + 0.5D, (double)this.field_40239_k + 0.5D) <= 64.0D;
   }

   public ItemStack transferStackInSlot(int var1) {
      ItemStack var2 = null;
      Slot var3 = (Slot)this.inventorySlots.get(var1);
      if(var3 != null && var3.getHasStack()) {
         ItemStack var4 = var3.getStack();
         var2 = var4.copy();
         if(var1 != 0) {
            return null;
         }

         if(!this.mergeItemStack(var4, 1, 37, true)) {
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
