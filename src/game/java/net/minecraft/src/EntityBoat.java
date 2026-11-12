package net.minecraft.src;

import java.util.List;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityBoat extends Entity {
   private int boatPosRotationIncrements;
   private double boatX;
   private double boatY;
   private double boatZ;
   private double boatYaw;
   private double boatPitch;
   private double velocityX;
   private double velocityY;
   private double velocityZ;

   public EntityBoat(World var1) {
      super(var1);
      this.preventEntitySpawning = true;
      this.setSize(1.5F, 0.6F);
      this.yOffset = this.height / 2.0F;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      this.dataWatcher.addObject(17, new Integer(0));
      this.dataWatcher.addObject(18, new Integer(1));
      this.dataWatcher.addObject(19, new Integer(0));
   }

   public AxisAlignedBB getCollisionBox(Entity var1) {
      return var1.boundingBox;
   }

   public AxisAlignedBB getBoundingBox() {
      return this.boundingBox;
   }

   public boolean canBePushed() {
      return true;
   }

   public EntityBoat(World var1, double var2, double var4, double var6) {
      this(var1);
      this.setPosition(var2, var4 + (double)this.yOffset, var6);
      this.motionX = 0.0D;
      this.motionY = 0.0D;
      this.motionZ = 0.0D;
      this.prevPosX = var2;
      this.prevPosY = var4;
      this.prevPosZ = var6;
   }

   public double getMountedYOffset() {
      return (double)this.height * 0.0D - 0.30000001192092896D;
   }

   public boolean attackEntityFrom(DamageSource var1, int var2) {
      if(!this.worldObj.multiplayerWorld && !this.isDead) {
         this.func_41017_d(-this.func_41016_i());
         this.func_41019_c(10);
         this.func_41015_b(this.func_41020_g() + var2 * 10);
         this.setBeenAttacked();
         if(this.func_41020_g() > 40) {
            if(this.riddenByEntity != null) {
               this.riddenByEntity.mountEntity(this);
            }

            for(int var3 = 0; var3 < 3; ++var3) {
               this.dropItemWithOffset(Block.planks.blockID, 1, 0.0F);
            }

            for(int var4 = 0; var4 < 2; ++var4) {
               this.dropItemWithOffset(Item.stick.shiftedIndex, 1, 0.0F);
            }

            this.setEntityDead();
         }

         return true;
      } else {
         return true;
      }
   }

   public void performHurtAnimation() {
      this.func_41017_d(-this.func_41016_i());
      this.func_41019_c(10);
      this.func_41015_b(this.func_41020_g() * 11);
   }

   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {
      this.boatX = var1;
      this.boatY = var3;
      this.boatZ = var5;
      this.boatYaw = (double)var7;
      this.boatPitch = (double)var8;
      this.boatPosRotationIncrements = var9 + 4;
      this.motionX = this.velocityX;
      this.motionY = this.velocityY;
      this.motionZ = this.velocityZ;
   }

   public void setVelocity(double var1, double var3, double var5) {
      this.velocityX = this.motionX = var1;
      this.velocityY = this.motionY = var3;
      this.velocityZ = this.motionZ = var5;
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.func_41018_h() > 0) {
         this.func_41019_c(this.func_41018_h() - 1);
      }

      if(this.func_41020_g() > 0) {
         this.func_41015_b(this.func_41020_g() - 1);
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      byte var1 = 5;
      double var2 = 0.0D;

      for(int var4 = 0; var4 < var1; ++var4) {
         double var5 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 0) / (double)var1 - 0.125D;
         double var7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 1) / (double)var1 - 0.125D;
         AxisAlignedBB var9 = AxisAlignedBB.getBoundingBoxFromPool(this.boundingBox.minX, var5, this.boundingBox.minZ, this.boundingBox.maxX, var7, this.boundingBox.maxZ);
         if(this.worldObj.isAABBInMaterial(var9, Material.water)) {
            var2 += 1.0D / (double)var1;
         }
      }

      double var21 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      if(var21 > 0.15D) {
         double var6 = Math.cos((double)this.rotationYaw * 3.141592653589793D / 180.0D);
         double var8 = Math.sin((double)this.rotationYaw * 3.141592653589793D / 180.0D);

         for(int var10 = 0; (double)var10 < 1.0D + var21 * 60.0D; ++var10) {
            double var11 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
            double var13 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7D;
            if(this.rand.nextBoolean()) {
               double var15 = this.posX - var6 * var11 * 0.8D + var8 * var13;
               double var17 = this.posZ - var8 * var11 * 0.8D - var6 * var13;
               this.worldObj.spawnParticle("splash", var15, this.posY - 0.125D, var17, this.motionX, this.motionY, this.motionZ);
            } else {
               double var35 = this.posX + var6 + var8 * var11 * 0.7D;
               double var36 = this.posZ + var8 - var6 * var11 * 0.7D;
               this.worldObj.spawnParticle("splash", var35, this.posY - 0.125D, var36, this.motionX, this.motionY, this.motionZ);
            }
         }
      }

      if(this.worldObj.multiplayerWorld) {
         if(this.boatPosRotationIncrements > 0) {
            double var24 = this.posX + (this.boatX - this.posX) / (double)this.boatPosRotationIncrements;
            double var29 = this.posY + (this.boatY - this.posY) / (double)this.boatPosRotationIncrements;
            double var32 = this.posZ + (this.boatZ - this.posZ) / (double)this.boatPosRotationIncrements;

            double var34;
            for(var34 = this.boatYaw - (double)this.rotationYaw; var34 < -180.0D; var34 += 360.0D) {
               ;
            }

            while(var34 >= 180.0D) {
               var34 -= 360.0D;
            }

            this.rotationYaw = (float)((double)this.rotationYaw + var34 / (double)this.boatPosRotationIncrements);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
            --this.boatPosRotationIncrements;
            this.setPosition(var24, var29, var32);
            this.setRotation(this.rotationYaw, this.rotationPitch);
         } else {
            double var25 = this.posX + this.motionX;
            double var30 = this.posY + this.motionY;
            double var33 = this.posZ + this.motionZ;
            this.setPosition(var25, var30, var33);
            if(this.onGround) {
               this.motionX *= 0.5D;
               this.motionY *= 0.5D;
               this.motionZ *= 0.5D;
            }

            this.motionX *= 0.9900000095367432D;
            this.motionY *= 0.949999988079071D;
            this.motionZ *= 0.9900000095367432D;
         }

      } else {
         if(var2 < 1.0D) {
            double var22 = var2 * 2.0D - 1.0D;
            this.motionY += 0.03999999910593033D * var22;
         } else {
            if(this.motionY < 0.0D) {
               this.motionY /= 2.0D;
            }

            this.motionY += 0.007000000216066837D;
         }

         if(this.riddenByEntity != null) {
            this.motionX += this.riddenByEntity.motionX * 0.2D;
            this.motionZ += this.riddenByEntity.motionZ * 0.2D;
         }

         double var23 = 0.4D;
         if(this.motionX < -var23) {
            this.motionX = -var23;
         }

         if(this.motionX > var23) {
            this.motionX = var23;
         }

         if(this.motionZ < -var23) {
            this.motionZ = -var23;
         }

         if(this.motionZ > var23) {
            this.motionZ = var23;
         }

         if(this.onGround) {
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
         }

         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         if(this.isCollidedHorizontally && var21 > 0.2D) {
            if(!this.worldObj.multiplayerWorld) {
               this.setEntityDead();

               for(int var26 = 0; var26 < 3; ++var26) {
                  this.dropItemWithOffset(Block.planks.blockID, 1, 0.0F);
               }

               for(int var27 = 0; var27 < 2; ++var27) {
                  this.dropItemWithOffset(Item.stick.shiftedIndex, 1, 0.0F);
               }
            }
         } else {
            this.motionX *= 0.9900000095367432D;
            this.motionY *= 0.949999988079071D;
            this.motionZ *= 0.9900000095367432D;
         }

         this.rotationPitch = 0.0F;
         double var28 = (double)this.rotationYaw;
         double var31 = this.prevPosX - this.posX;
         double var12 = this.prevPosZ - this.posZ;
         if(var31 * var31 + var12 * var12 > 0.001D) {
            var28 = (double)((float)(Math.atan2(var12, var31) * 180.0D / 3.141592653589793D));
         }

         double var14;
         for(var14 = var28 - (double)this.rotationYaw; var14 >= 180.0D; var14 -= 360.0D) {
            ;
         }

         while(var14 < -180.0D) {
            var14 += 360.0D;
         }

         if(var14 > 20.0D) {
            var14 = 20.0D;
         }

         if(var14 < -20.0D) {
            var14 = -20.0D;
         }

         this.rotationYaw = (float)((double)this.rotationYaw + var14);
         this.setRotation(this.rotationYaw, this.rotationPitch);
         List var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
         if(var16 != null && var16.size() > 0) {
            for(int var37 = 0; var37 < var16.size(); ++var37) {
               Entity var18 = (Entity)var16.get(var37);
               if(var18 != this.riddenByEntity && var18.canBePushed() && var18 instanceof EntityBoat) {
                  var18.applyEntityCollision(this);
               }
            }
         }

         for(int var38 = 0; var38 < 4; ++var38) {
            int var39 = MathHelper.floor_double(this.posX + ((double)(var38 % 2) - 0.5D) * 0.8D);
            int var19 = MathHelper.floor_double(this.posY);
            int var20 = MathHelper.floor_double(this.posZ + ((double)(var38 / 2) - 0.5D) * 0.8D);
            if(this.worldObj.getBlockId(var39, var19, var20) == Block.snow.blockID) {
               this.worldObj.setBlockWithNotify(var39, var19, var20, 0);
            }
         }

         if(this.riddenByEntity != null && this.riddenByEntity.isDead) {
            this.riddenByEntity = null;
         }

      }
   }

   public void updateRiderPosition() {
      if(this.riddenByEntity != null) {
         double var1 = Math.cos((double)this.rotationYaw * 3.141592653589793D / 180.0D) * 0.4D;
         double var3 = Math.sin((double)this.rotationYaw * 3.141592653589793D / 180.0D) * 0.4D;
         this.riddenByEntity.setPosition(this.posX + var1, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
      }
   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
   }

   public float getShadowSize() {
      return 0.0F;
   }

   public boolean interact(EntityPlayer var1) {
      if(this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != var1) {
         return true;
      } else {
         if(!this.worldObj.multiplayerWorld) {
            var1.mountEntity(this);
         }

         return true;
      }
   }

   public void func_41015_b(int var1) {
      this.dataWatcher.updateObject(19, Integer.valueOf(var1));
   }

   public int func_41020_g() {
      return this.dataWatcher.getWatchableObjectInt(19);
   }

   public void func_41019_c(int var1) {
      this.dataWatcher.updateObject(17, Integer.valueOf(var1));
   }

   public int func_41018_h() {
      return this.dataWatcher.getWatchableObjectInt(17);
   }

   public void func_41017_d(int var1) {
      this.dataWatcher.updateObject(18, Integer.valueOf(var1));
   }

   public int func_41016_i() {
      return this.dataWatcher.getWatchableObjectInt(18);
   }
}
