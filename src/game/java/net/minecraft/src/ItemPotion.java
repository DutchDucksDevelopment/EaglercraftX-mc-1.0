package net.minecraft.src;

import java.util.HashMap;
import java.util.List;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPotion;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.PotionHelper;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;

public class ItemPotion extends Item {
   private HashMap field_40435_a = new HashMap();

   public ItemPotion(int var1) {
      super(var1);
      this.setMaxStackSize(1);
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
   }

   public List func_40434_a_(ItemStack var1) {
      return this.func_40431_c_(var1.getItemDamage());
   }

   public List func_40431_c_(int var1) {
      List var2 = (List)this.field_40435_a.get(Integer.valueOf(var1));
      if(var2 == null) {
         var2 = PotionHelper.func_40360_b(var1, false);
         this.field_40435_a.put(Integer.valueOf(var1), var2);
      }

      return var2;
   }

   public ItemStack onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      --var1.stackSize;
      if(!var2.multiplayerWorld) {
         List var4 = this.func_40434_a_(var1);
         if(var4 != null) {
            for(java.util.Iterator var6_iter = (var4).iterator(); var6_iter.hasNext();) {
                PotionEffect var6 = (PotionEffect) var6_iter.next();
               var3.addPotionEffect(new PotionEffect(var6));
            }
         }
      }

      if(var1.stackSize <= 0) {
         return new ItemStack(Item.glassBottle);
      } else {
         var3.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
         return var1;
      }
   }

   public int getMaxItemUseDuration(ItemStack var1) {
      return 32;
   }

   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.drink;
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if(func_40433_c(var1.getItemDamage())) {
         --var1.stackSize;
         var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
         if(!var2.multiplayerWorld) {
            var2.entityJoinedWorld(new EntityPotion(var2, var3, var1.getItemDamage()));
         }

         return var1;
      } else {
         var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
         return var1;
      }
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
      return false;
   }

   public int getIconFromDamage(int var1) {
      return func_40433_c(var1)?154:140;
   }

   public static boolean func_40433_c(int var0) {
      return (var0 & 16384) != 0;
   }

   public int getColorFromDamage(int var1) {
      return PotionHelper.func_40358_a(var1, false);
   }

   public boolean func_40432_e(int var1) {
      List var2 = this.func_40431_c_(var1);
      if(var2 != null && !var2.isEmpty()) {
         for(java.util.Iterator var4_iter = (var2).iterator(); var4_iter.hasNext();) {
             PotionEffect var4 = (PotionEffect) var4_iter.next();
            if(Potion.potionTypes[var4.getPotionID()].func_40622_b()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public String func_40397_d(ItemStack var1) {
      if(var1.getItemDamage() == 0) {
         return StatCollector.translateToLocal("item.emptyPotion.name").trim();
      } else {
         String var2 = super.func_40397_d(var1);
         if(func_40433_c(var1.getItemDamage())) {
            var2 = StatCollector.translateToLocal("potion.prefix.grenade").trim() + " " + var2;
         }

         List var3 = Item.potion.func_40434_a_(var1);
         if(var3 != null && !var3.isEmpty()) {
            String var5 = ((PotionEffect)var3.get(0)).func_40468_d();
            var5 = var5 + ".postfix";
            return var2 + " " + StatCollector.translateToLocal(var5).trim();
         } else {
            String var4 = PotionHelper.func_40359_b(var1.getItemDamage());
            return StatCollector.translateToLocal(var4).trim() + " " + var2;
         }
      }
   }

   public void func_40404_a(ItemStack var1, List var2) {
      if(var1.getItemDamage() != 0) {
         List var3 = Item.potion.func_40434_a_(var1);
         if(var3 != null && !var3.isEmpty()) {
            for(java.util.Iterator var5_iter = (var3).iterator(); var5_iter.hasNext();) {
                PotionEffect var5 = (PotionEffect) var5_iter.next();
               String var6 = StatCollector.translateToLocal(var5.func_40468_d()).trim();
               if(var5.getAmplifier() > 0) {
                  var6 = var6 + " " + StatCollector.translateToLocal("potion.potency." + var5.getAmplifier()).trim();
               }

               if(var5.getDuration() > 20) {
                  var6 = var6 + " (" + Potion.func_40620_a(var5) + ")";
               }

               if(Potion.potionTypes[var5.getPotionID()].func_40615_f()) {
                  var2.add("\u00a7c" + var6);
               } else {
                  var2.add("\u00a77" + var6);
               }
            }
         } else {
            String var4 = StatCollector.translateToLocal("potion.empty").trim();
            var2.add("\u00a77" + var4);
         }

      }
   }

   public boolean func_40403_e(ItemStack var1) {
      List var2 = this.func_40434_a_(var1);
      return var2 != null && !var2.isEmpty();
   }
}
