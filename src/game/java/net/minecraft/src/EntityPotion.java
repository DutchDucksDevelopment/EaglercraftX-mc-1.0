package net.minecraft.src;

import java.util.List;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityThrowable;
import net.minecraft.src.Item;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.World;

public class EntityPotion extends EntityThrowable {
   private int field_40089_d;

   public EntityPotion(World var1) {
      super(var1);
   }

   public EntityPotion(World var1, EntityLiving var2, int var3) {
      super(var1, var2);
      this.field_40089_d = var3;
   }

   public EntityPotion(World var1, double var2, double var4, double var6, int var8) {
      super(var1, var2, var4, var6);
      this.field_40089_d = var8;
   }

   protected float func_40075_e() {
      return 0.05F;
   }

   protected float func_40077_c() {
      return 0.5F;
   }

   protected float func_40074_d() {
      return -20.0F;
   }

   public int func_40088_g() {
      return this.field_40089_d;
   }

   protected void func_40078_a(MovingObjectPosition var1) {
      if(!this.worldObj.multiplayerWorld) {
         List var2 = Item.potion.func_40431_c_(this.field_40089_d);
         if(var2 != null && !var2.isEmpty()) {
            AxisAlignedBB var3 = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
            List var4 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var3);
            if(var4 != null && !var4.isEmpty()) {
               for(java.util.Iterator var6_iter = (var4).iterator(); var6_iter.hasNext();) {
                   Entity var6 = (Entity) var6_iter.next();
                  double var7 = this.getDistanceSqToEntity(var6);
                  if(var7 < 16.0D) {
                     double var9 = 1.0D - Math.sqrt(var7) / 4.0D;
                     if(var6 == var1.entityHit) {
                        var9 = 1.0D;
                     }

                     for(java.util.Iterator var12_iter = (var2).iterator(); var12_iter.hasNext();) {
                         PotionEffect var12 = (PotionEffect) var12_iter.next();
                        int var13 = var12.getPotionID();
                        if(Potion.potionTypes[var13].func_40622_b()) {
                           Potion.potionTypes[var13].func_40613_a(this.field_40083_c, (EntityLiving)var6, var12.getAmplifier(), var9);
                        } else {
                           int var14 = (int)(var9 * (double)var12.getDuration() + 0.5D);
                           if(var14 > 20) {
                              ((EntityLiving)var6).addPotionEffect(new PotionEffect(var13, var14, var12.getAmplifier()));
                           }
                        }
                     }
                  }
               }
            }
         }

         this.worldObj.playAuxSFX(2002, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), this.field_40089_d);
         this.setEntityDead();
      }

   }
}
