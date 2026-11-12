package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityFireball;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;

public class EntitySmallFireball extends EntityFireball {
   public EntitySmallFireball(World var1) {
      super(var1);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntitySmallFireball(World var1, EntityLiving var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntitySmallFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.setSize(0.3125F, 0.3125F);
   }

   protected void func_40071_a(MovingObjectPosition var1) {
      if(!this.worldObj.multiplayerWorld) {
         if(var1.entityHit != null) {
            if(!var1.entityHit.func_40047_D() && var1.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5)) {
               var1.entityHit.func_40046_d(5);
            }
         } else {
            int var2 = var1.blockX;
            int var3 = var1.blockY;
            int var4 = var1.blockZ;
            switch(var1.sideHit) {
            case 0:
               --var3;
               break;
            case 1:
               ++var3;
               break;
            case 2:
               --var4;
               break;
            case 3:
               ++var4;
               break;
            case 4:
               --var2;
               break;
            case 5:
               ++var2;
            }

            if(this.worldObj.isAirBlock(var2, var3, var4)) {
               this.worldObj.setBlockWithNotify(var2, var3, var4, Block.fire.blockID);
            }
         }

         this.setEntityDead();
      }

   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean attackEntityFrom(DamageSource var1, int var2) {
      return false;
   }
}
