package net.minecraft.src;

import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemBow extends Item {
   public ItemBow(int var1) {
      super(var1);
      this.maxStackSize = 1;
      this.setMaxDamage(384);
   }

   public void onPlayerStoppedUsing(ItemStack var1, World var2, EntityPlayer var3, int var4) {
      if(var3.capabilities.depleteBuckets || var3.inventory.hasItem(Item.arrow.shiftedIndex)) {
         int var5 = this.getMaxItemUseDuration(var1) - var4;
         float var6 = (float)var5 / 20.0F;
         var6 = (var6 * var6 + var6 * 2.0F) / 3.0F;
         if((double)var6 < 0.1D) {
            return;
         }

         if(var6 > 1.0F) {
            var6 = 1.0F;
         }

         EntityArrow var7 = new EntityArrow(var2, var3, var6 * 2.0F);
         if(var6 == 1.0F) {
            var7.arrowCritical = true;
         }

         var1.damageItem(1, var3);
         var2.playSoundAtEntity(var3, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var6 * 0.5F);
         var3.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
         if(!var2.multiplayerWorld) {
            var2.entityJoinedWorld(var7);
         }
      }

   }

   public ItemStack onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      return var1;
   }

   public int getMaxItemUseDuration(ItemStack var1) {
      return 72000;
   }

   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.bow;
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if(var3.capabilities.depleteBuckets || var3.inventory.hasItem(Item.arrow.shiftedIndex)) {
         var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
      }

      return var1;
   }
}
