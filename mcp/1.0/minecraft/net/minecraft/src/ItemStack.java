package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.Block;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.StatList;
import net.minecraft.src.World;

public final class ItemStack {
   public int stackSize;
   public int animationsToGo;
   public int itemID;
   public NBTTagCompound field_40715_d;
   private int itemDamage;

   public ItemStack(Block var1) {
      this((Block)var1, 1);
   }

   public ItemStack(Block var1, int var2) {
      this(var1.blockID, var2, 0);
   }

   public ItemStack(Block var1, int var2, int var3) {
      this(var1.blockID, var2, var3);
   }

   public ItemStack(Item var1) {
      this(var1.shiftedIndex, 1, 0);
   }

   public ItemStack(Item var1, int var2) {
      this(var1.shiftedIndex, var2, 0);
   }

   public ItemStack(Item var1, int var2, int var3) {
      this(var1.shiftedIndex, var2, var3);
   }

   public ItemStack(int var1, int var2, int var3) {
      this.stackSize = 0;
      this.itemID = var1;
      this.stackSize = var2;
      this.itemDamage = var3;
   }

   public static ItemStack loadItemStackFromNBT(NBTTagCompound var0) {
      ItemStack var1 = new ItemStack();
      var1.readFromNBT(var0);
      return var1.getItem() != null?var1:null;
   }

   private ItemStack() {
      this.stackSize = 0;
   }

   public ItemStack splitStack(int var1) {
      ItemStack var2 = new ItemStack(this.itemID, var1, this.itemDamage);
      if(this.field_40715_d != null) {
         var2.field_40715_d = (NBTTagCompound)this.field_40715_d.func_40195_b();
      }

      this.stackSize -= var1;
      return var2;
   }

   public Item getItem() {
      return Item.itemsList[this.itemID];
   }

   public int getIconIndex() {
      return this.getItem().getIconIndex(this);
   }

   public boolean useItem(EntityPlayer var1, World var2, int var3, int var4, int var5, int var6) {
      boolean var7 = this.getItem().onItemUse(this, var1, var2, var3, var4, var5, var6);
      if(var7) {
         var1.addStat(StatList.objectUseStats[this.itemID], 1);
      }

      return var7;
   }

   public float getStrVsBlock(Block var1) {
      return this.getItem().getStrVsBlock(this, var1);
   }

   public ItemStack useItemRightClick(World var1, EntityPlayer var2) {
      return this.getItem().onItemRightClick(this, var1, var2);
   }

   public ItemStack onFoodEaten(World var1, EntityPlayer var2) {
      return this.getItem().onFoodEaten(this, var1, var2);
   }

   public NBTTagCompound writeToNBT(NBTTagCompound var1) {
      var1.setShort("id", (short)this.itemID);
      var1.setByte("Count", (byte)this.stackSize);
      var1.setShort("Damage", (short)this.itemDamage);
      if(this.field_40715_d != null) {
         var1.setTag("tag", this.field_40715_d);
      }

      return var1;
   }

   public void readFromNBT(NBTTagCompound var1) {
      this.itemID = var1.getShort("id");
      this.stackSize = var1.getByte("Count");
      this.itemDamage = var1.getShort("Damage");
      if(var1.hasKey("tag")) {
         this.field_40715_d = var1.getCompoundTag("tag");
      }

   }

   public int getMaxStackSize() {
      return this.getItem().getItemStackLimit();
   }

   public boolean isStackable() {
      return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
   }

   public boolean isItemStackDamageable() {
      return Item.itemsList[this.itemID].getMaxDamage() > 0;
   }

   public boolean getHasSubtypes() {
      return Item.itemsList[this.itemID].getHasSubtypes();
   }

   public boolean isItemDamaged() {
      return this.isItemStackDamageable() && this.itemDamage > 0;
   }

   public int getItemDamageForDisplay() {
      return this.itemDamage;
   }

   public int getItemDamage() {
      return this.itemDamage;
   }

   public void setItemDamage(int var1) {
      this.itemDamage = var1;
   }

   public int getMaxDamage() {
      return Item.itemsList[this.itemID].getMaxDamage();
   }

   public void damageItem(int var1, EntityLiving var2) {
      if(this.isItemStackDamageable()) {
         if(var1 > 0 && var2 instanceof EntityPlayer) {
            int var3 = EnchantmentHelper.getUnbreakingModifier(((EntityPlayer)var2).inventory);
            if(var3 > 0 && var2.worldObj.rand.nextInt(var3 + 1) > 0) {
               return;
            }
         }

         this.itemDamage += var1;
         if(this.itemDamage > this.getMaxDamage()) {
            var2.func_41005_b(this);
            if(var2 instanceof EntityPlayer) {
               ((EntityPlayer)var2).addStat(StatList.objectBreakStats[this.itemID], 1);
            }

            --this.stackSize;
            if(this.stackSize < 0) {
               this.stackSize = 0;
            }

            this.itemDamage = 0;
         }

      }
   }

   public void hitEntity(EntityLiving var1, EntityPlayer var2) {
      boolean var3 = Item.itemsList[this.itemID].hitEntity(this, var1, var2);
      if(var3) {
         var2.addStat(StatList.objectUseStats[this.itemID], 1);
      }

   }

   public void onDestroyBlock(int var1, int var2, int var3, int var4, EntityPlayer var5) {
      boolean var6 = Item.itemsList[this.itemID].onBlockDestroyed(this, var1, var2, var3, var4, var5);
      if(var6) {
         var5.addStat(StatList.objectUseStats[this.itemID], 1);
      }

   }

   public int getDamageVsEntity(Entity var1) {
      return Item.itemsList[this.itemID].getDamageVsEntity(var1);
   }

   public boolean canHarvestBlock(Block var1) {
      return Item.itemsList[this.itemID].canHarvestBlock(var1);
   }

   public void onItemDestroyedByUse(EntityPlayer var1) {
   }

   public void useItemOnEntity(EntityLiving var1) {
      Item.itemsList[this.itemID].useItemOnEntity(this, var1);
   }

   public ItemStack copy() {
      ItemStack var1 = new ItemStack(this.itemID, this.stackSize, this.itemDamage);
      if(this.field_40715_d != null) {
         var1.field_40715_d = (NBTTagCompound)this.field_40715_d.func_40195_b();
         if(!var1.field_40715_d.equals(this.field_40715_d)) {
            return var1;
         }
      }

      return var1;
   }

   public static boolean areItemStacksEqual(ItemStack var0, ItemStack var1) {
      return var0 == null && var1 == null?true:(var0 != null && var1 != null?var0.isItemStackEqual(var1):false);
   }

   private boolean isItemStackEqual(ItemStack var1) {
      return this.stackSize != var1.stackSize?false:(this.itemID != var1.itemID?false:(this.itemDamage != var1.itemDamage?false:(this.field_40715_d == null && var1.field_40715_d != null?false:this.field_40715_d == null || this.field_40715_d.equals(var1.field_40715_d))));
   }

   public boolean isItemEqual(ItemStack var1) {
      return this.itemID == var1.itemID && this.itemDamage == var1.itemDamage;
   }

   public static ItemStack copyItemStack(ItemStack var0) {
      return var0 == null?null:var0.copy();
   }

   public String toString() {
      return this.stackSize + "x" + Item.itemsList[this.itemID].getItemName() + "@" + this.itemDamage;
   }

   public void updateAnimation(World var1, Entity var2, int var3, boolean var4) {
      if(this.animationsToGo > 0) {
         --this.animationsToGo;
      }

      Item.itemsList[this.itemID].onUpdate(this, var1, var2, var3, var4);
   }

   public void onCrafting(World var1, EntityPlayer var2) {
      var2.addStat(StatList.objectCraftStats[this.itemID], this.stackSize);
      Item.itemsList[this.itemID].onCreated(this, var1, var2);
   }

   public boolean isStackEqual(ItemStack var1) {
      return this.itemID == var1.itemID && this.stackSize == var1.stackSize && this.itemDamage == var1.itemDamage;
   }

   public int getMaxItemUseDuration() {
      return this.getItem().getMaxItemUseDuration(this);
   }

   public EnumAction getItemUseAction() {
      return this.getItem().getItemUseAction(this);
   }

   public void onPlayerStoppedUsing(World var1, EntityPlayer var2, int var3) {
      this.getItem().onPlayerStoppedUsing(this, var1, var2, var3);
   }

   public boolean func_40710_n() {
      return this.field_40715_d != null;
   }

   public NBTTagCompound func_40709_o() {
      return this.field_40715_d;
   }

   public NBTTagList func_40714_p() {
      return this.field_40715_d == null?null:(NBTTagList)this.field_40715_d.func_40196_b("ench");
   }

   public void func_40706_d(NBTTagCompound var1) {
      if(Item.itemsList[this.itemID].getItemStackLimit() != 1) {
         throw new IllegalArgumentException("Cannot add tag data to a stackable item");
      } else {
         this.field_40715_d = var1;
      }
   }

   public List func_40712_q() {
      ArrayList var1 = new ArrayList();
      Item var2 = Item.itemsList[this.itemID];
      var1.add(var2.func_40397_d(this));
      var2.func_40404_a(this, var1);
      if(this.func_40710_n()) {
         NBTTagList var3 = this.func_40714_p();
         if(var3 != null) {
            for(int var4 = 0; var4 < var3.tagCount(); ++var4) {
               short var5 = ((NBTTagCompound)var3.tagAt(var4)).getShort("id");
               short var6 = ((NBTTagCompound)var3.tagAt(var4)).getShort("lvl");
               if(Enchantment.enchantmentsList[var5] != null) {
                  var1.add(Enchantment.enchantmentsList[var5].getTranslatedName(var6));
               }
            }
         }
      }

      return var1;
   }

   public boolean func_40713_r() {
      return this.getItem().func_40403_e(this);
   }

   public EnumRarity func_40707_s() {
      return this.getItem().func_40398_f(this);
   }

   public boolean func_40708_t() {
      return !this.getItem().func_40401_i(this)?false:!this.func_40711_u();
   }

   public void addEnchantment(Enchantment var1, int var2) {
      if(this.field_40715_d == null) {
         this.func_40706_d(new NBTTagCompound());
      }

      if(!this.field_40715_d.hasKey("ench")) {
         this.field_40715_d.setTag("ench", new NBTTagList("ench"));
      }

      NBTTagList var3 = (NBTTagList)this.field_40715_d.func_40196_b("ench");
      NBTTagCompound var4 = new NBTTagCompound();
      var4.setShort("id", (short)var1.effectId);
      var4.setShort("lvl", (short)((byte)var2));
      var3.setTag(var4);
   }

   public boolean func_40711_u() {
      return this.field_40715_d != null && this.field_40715_d.hasKey("ench");
   }
}
