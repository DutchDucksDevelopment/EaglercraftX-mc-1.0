package net.minecraft.src;

import java.util.List;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemPotion;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.PotionHelper;
import net.minecraft.src.TileEntity;

public class TileEntityBrewingStand extends TileEntity implements IInventory {
   private ItemStack[] field_40058_a = new ItemStack[4];
   private int field_40056_b;
   private int field_40057_c;
   private int field_40055_d;

   public String getInvName() {
      return "Brewing Stand";
   }

   public int getSizeInventory() {
      return this.field_40058_a.length;
   }

   public void updateEntity() {
      if(this.field_40056_b > 0) {
         --this.field_40056_b;
         if(this.field_40056_b == 0) {
            this.func_40052_p();
            this.onInventoryChanged();
         } else if(!this.func_40050_o()) {
            this.field_40056_b = 0;
            this.onInventoryChanged();
         } else if(this.field_40055_d != this.field_40058_a[3].itemID) {
            this.field_40056_b = 0;
            this.onInventoryChanged();
         }
      } else if(this.func_40050_o()) {
         this.field_40056_b = 600;
         this.field_40055_d = this.field_40058_a[3].itemID;
      }

      int var1 = this.func_40054_n();
      if(var1 != this.field_40057_c) {
         this.field_40057_c = var1;
         this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, var1);
      }

      super.updateEntity();
   }

   public int func_40053_g() {
      return this.field_40056_b;
   }

   private boolean func_40050_o() {
      if(this.field_40058_a[3] != null && this.field_40058_a[3].stackSize > 0) {
         ItemStack var1 = this.field_40058_a[3];
         if(!Item.itemsList[var1.itemID].func_40406_n()) {
            return false;
         } else {
            boolean var2 = false;

            for(int var3 = 0; var3 < 3; ++var3) {
               if(this.field_40058_a[var3] != null && this.field_40058_a[var3].itemID == Item.potion.shiftedIndex) {
                  int var4 = this.field_40058_a[var3].getItemDamage();
                  int var5 = this.func_40051_b(var4, var1);
                  if(!ItemPotion.func_40433_c(var4) && ItemPotion.func_40433_c(var5)) {
                     var2 = true;
                     break;
                  }

                  List var6 = Item.potion.func_40431_c_(var4);
                  List var7 = Item.potion.func_40431_c_(var5);
                  if((var4 <= 0 || var6 != var7) && (var6 == null || !var6.equals(var7) && var7 != null) && var4 != var5) {
                     var2 = true;
                     break;
                  }
               }
            }

            return var2;
         }
      } else {
         return false;
      }
   }

   private void func_40052_p() {
      if(this.func_40050_o()) {
         ItemStack var1 = this.field_40058_a[3];

         for(int var2 = 0; var2 < 3; ++var2) {
            if(this.field_40058_a[var2] != null && this.field_40058_a[var2].itemID == Item.potion.shiftedIndex) {
               int var3 = this.field_40058_a[var2].getItemDamage();
               int var4 = this.func_40051_b(var3, var1);
               List var5 = Item.potion.func_40431_c_(var3);
               List var6 = Item.potion.func_40431_c_(var4);
               if(var3 > 0 && var5 == var6 || var5 != null && (var5.equals(var6) || var6 == null)) {
                  if(!ItemPotion.func_40433_c(var3) && ItemPotion.func_40433_c(var4)) {
                     this.field_40058_a[var2].setItemDamage(var4);
                  }
               } else if(var3 != var4) {
                  this.field_40058_a[var2].setItemDamage(var4);
               }
            }
         }

         if(Item.itemsList[var1.itemID].hasContainerItem()) {
            this.field_40058_a[3] = new ItemStack(Item.itemsList[var1.itemID].getContainerItem());
         } else {
            --this.field_40058_a[3].stackSize;
            if(this.field_40058_a[3].stackSize <= 0) {
               this.field_40058_a[3] = null;
            }
         }

      }
   }

   private int func_40051_b(int var1, ItemStack var2) {
      return var2 == null?var1:(Item.itemsList[var2.itemID].func_40406_n()?PotionHelper.func_40356_a(var1, Item.itemsList[var2.itemID].func_40405_m()):var1);
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items");
      this.field_40058_a = new ItemStack[this.getSizeInventory()];

      for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
         NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
         byte var5 = var4.getByte("Slot");
         if(var5 >= 0 && var5 < this.field_40058_a.length) {
            this.field_40058_a[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

      this.field_40056_b = var1.getShort("BrewTime");
   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setShort("BrewTime", (short)this.field_40056_b);
      NBTTagList var2 = new NBTTagList();

      for(int var3 = 0; var3 < this.field_40058_a.length; ++var3) {
         if(this.field_40058_a[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.field_40058_a[var3].writeToNBT(var4);
            var2.setTag(var4);
         }
      }

      var1.setTag("Items", var2);
   }

   public ItemStack getStackInSlot(int var1) {
      return var1 >= 0 && var1 < this.field_40058_a.length?this.field_40058_a[var1]:null;
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(var1 >= 0 && var1 < this.field_40058_a.length) {
         ItemStack var3 = this.field_40058_a[var1];
         this.field_40058_a[var1] = null;
         return var3;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      if(var1 >= 0 && var1 < this.field_40058_a.length) {
         this.field_40058_a[var1] = var2;
      }

   }

   public int getInventoryStackLimit() {
      return 1;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this?false:var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
   }

   public void openChest() {
   }

   public void closeChest() {
   }

   public void func_40049_b(int var1) {
      this.field_40056_b = var1;
   }

   public int func_40054_n() {
      int var1 = 0;

      for(int var2 = 0; var2 < 3; ++var2) {
         if(this.field_40058_a[var2] != null) {
            var1 |= 1 << var2;
         }
      }

      return var1;
   }
}
