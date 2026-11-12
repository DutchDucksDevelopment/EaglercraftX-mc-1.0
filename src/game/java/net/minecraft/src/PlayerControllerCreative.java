package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.PlayerController;
import net.minecraft.src.Session;
import net.minecraft.src.World;

public class PlayerControllerCreative extends PlayerController {
   private int field_35647_c;

   public PlayerControllerCreative(Minecraft var1) {
      super(var1);
      this.isInTestMode = true;
   }

   public static void func_35646_d(EntityPlayer var0) {
      var0.capabilities.allowFlying = true;
      var0.capabilities.depleteBuckets = true;
      var0.capabilities.disableDamage = true;
   }

   public static void func_35645_e(EntityPlayer var0) {
      var0.capabilities.allowFlying = false;
      var0.capabilities.isFlying = false;
      var0.capabilities.depleteBuckets = false;
      var0.capabilities.disableDamage = false;
   }

   public void func_6473_b(EntityPlayer var1) {
      func_35646_d(var1);

      for(int var2 = 0; var2 < 9; ++var2) {
         if(var1.inventory.mainInventory[var2] == null) {
            var1.inventory.mainInventory[var2] = new ItemStack((Block)Session.registeredBlocksList.get(var2));
         }
      }

   }

   public static void func_35644_a(Minecraft var0, PlayerController var1, int var2, int var3, int var4, int var5) {
      var0.theWorld.onBlockHit(var0.thePlayer, var2, var3, var4, var5);
      var1.sendBlockRemoved(var2, var3, var4, var5);
   }

   public boolean sendPlaceBlock(EntityPlayer var1, World var2, ItemStack var3, int var4, int var5, int var6, int var7) {
      int var8 = var2.getBlockId(var4, var5, var6);
      if(var8 > 0 && Block.blocksList[var8].blockActivated(var2, var4, var5, var6, var1)) {
         return true;
      } else if(var3 == null) {
         return false;
      } else {
         int var9 = var3.getItemDamage();
         int var10 = var3.stackSize;
         boolean var11 = var3.useItem(var1, var2, var4, var5, var6, var7);
         var3.setItemDamage(var9);
         var3.stackSize = var10;
         return var11;
      }
   }

   public void clickBlock(int var1, int var2, int var3, int var4) {
      func_35644_a(this.mc, this, var1, var2, var3, var4);
      this.field_35647_c = 5;
   }

   public void sendBlockRemoving(int var1, int var2, int var3, int var4) {
      --this.field_35647_c;
      if(this.field_35647_c <= 0) {
         this.field_35647_c = 5;
         func_35644_a(this.mc, this, var1, var2, var3, var4);
      }

   }

   public void resetBlockRemoving() {
   }

   public boolean shouldDrawHUD() {
      return false;
   }

   public void onWorldChange(World var1) {
      super.onWorldChange(var1);
   }

   public float getBlockReachDistance() {
      return 5.0F;
   }

   public boolean func_35641_g() {
      return false;
   }

   public boolean isInCreativeMode() {
      return true;
   }

   public boolean extendedReach() {
      return true;
   }
}
