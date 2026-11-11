package net.minecraft.src;

import java.util.List;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class EntityAnimal extends EntityCreature {
   private int field_39004_a;
   private int field_39005_b = 0;

   public EntityAnimal(World var1) {
      super(var1);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(12, new Integer(0));
   }

   public int func_40146_g() {
      return this.dataWatcher.getWatchableObjectInt(12);
   }

   public void func_40142_a_(int var1) {
      this.dataWatcher.updateObject(12, Integer.valueOf(var1));
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      int var1 = this.func_40146_g();
      if(var1 < 0) {
         ++var1;
         this.func_40142_a_(var1);
      } else if(var1 > 0) {
         --var1;
         this.func_40142_a_(var1);
      }

      if(this.field_39004_a > 0) {
         --this.field_39004_a;
         String var2 = "heart";
         if(this.field_39004_a % 10 == 0) {
            double var3 = this.rand.nextGaussian() * 0.02D;
            double var5 = this.rand.nextGaussian() * 0.02D;
            double var7 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(var2, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var3, var5, var7);
         }
      } else {
         this.field_39005_b = 0;
      }

   }

   protected void attackEntity(Entity var1, float var2) {
      if(var1 instanceof EntityPlayer) {
         if(var2 < 3.0F) {
            double var3 = var1.posX - this.posX;
            double var5 = var1.posZ - this.posZ;
            this.rotationYaw = (float)(Math.atan2(var5, var3) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.hasAttacked = true;
         }

         EntityPlayer var7 = (EntityPlayer)var1;
         if(var7.getCurrentEquippedItem() == null || !this.func_40143_a(var7.getCurrentEquippedItem())) {
            this.entityToAttack = null;
         }
      } else if(var1 instanceof EntityAnimal) {
         EntityAnimal var8 = (EntityAnimal)var1;
         if(this.func_40146_g() > 0 && var8.func_40146_g() < 0) {
            if((double)var2 < 2.5D) {
               this.hasAttacked = true;
            }
         } else if(this.field_39004_a > 0 && var8.field_39004_a > 0) {
            if(var8.entityToAttack == null) {
               var8.entityToAttack = this;
            }

            if(var8.entityToAttack == this && (double)var2 < 3.5D) {
               ++var8.field_39004_a;
               ++this.field_39004_a;
               ++this.field_39005_b;
               if(this.field_39005_b % 4 == 0) {
                  this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0D, 0.0D, 0.0D);
               }

               if(this.field_39005_b == 60) {
                  this.func_40144_b((EntityAnimal)var1);
               }
            } else {
               this.field_39005_b = 0;
            }
         } else {
            this.field_39005_b = 0;
         }
      }

   }

   private void func_40144_b(EntityAnimal var1) {
      EntityAnimal var2 = this.func_40145_a(var1);
      if(var2 != null) {
         this.func_40142_a_(6000);
         var1.func_40142_a_(6000);
         this.field_39004_a = 0;
         this.field_39005_b = 0;
         this.entityToAttack = null;
         var1.entityToAttack = null;
         var1.field_39005_b = 0;
         var1.field_39004_a = 0;
         var2.func_40142_a_(-24000);
         var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);

         for(int var3 = 0; var3 < 7; ++var3) {
            double var4 = this.rand.nextGaussian() * 0.02D;
            double var6 = this.rand.nextGaussian() * 0.02D;
            double var8 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
         }

         this.worldObj.entityJoinedWorld(var2);
      }

   }

   protected abstract EntityAnimal func_40145_a(EntityAnimal var1);

   protected void attackBlockedEntity(Entity var1, float var2) {
   }

   public boolean attackEntityFrom(DamageSource var1, int var2) {
      this.fleeingTick = 60;
      this.entityToAttack = null;
      this.field_39004_a = 0;
      return super.attackEntityFrom(var1, var2);
   }

   protected float getBlockPathWeight(int var1, int var2, int var3) {
      return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID?10.0F:this.worldObj.getLightBrightness(var1, var2, var3) - 0.5F;
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Age", this.func_40146_g());
      var1.setInteger("InLove", this.field_39004_a);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.func_40142_a_(var1.getInteger("Age"));
      this.field_39004_a = var1.getInteger("InLove");
   }

   protected Entity findPlayerToAttack() {
      if(this.fleeingTick > 0) {
         return null;
      } else {
         float var1 = 8.0F;
         if(this.field_39004_a > 0) {
            List var2 = this.worldObj.getEntitiesWithinAABB(this.getClass(), this.boundingBox.addCoord((double)var1, (double)var1, (double)var1));

            for(int var3 = 0; var3 < var2.size(); ++var3) {
               EntityAnimal var4 = (EntityAnimal)var2.get(var3);
               if(var4 != this && var4.field_39004_a > 0) {
                  return var4;
               }
            }
         } else if(this.func_40146_g() == 0) {
            List var5 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.addCoord((double)var1, (double)var1, (double)var1));

            for(int var7 = 0; var7 < var5.size(); ++var7) {
               EntityPlayer var9 = (EntityPlayer)var5.get(var7);
               if(var9.getCurrentEquippedItem() != null && this.func_40143_a(var9.getCurrentEquippedItem())) {
                  return var9;
               }
            }
         } else if(this.func_40146_g() > 0) {
            List var6 = this.worldObj.getEntitiesWithinAABB(this.getClass(), this.boundingBox.addCoord((double)var1, (double)var1, (double)var1));

            for(int var8 = 0; var8 < var6.size(); ++var8) {
               EntityAnimal var10 = (EntityAnimal)var6.get(var8);
               if(var10 != this && var10.func_40146_g() < 0) {
                  return var10;
               }
            }
         }

         return null;
      }
   }

   public boolean getCanSpawnHere() {
      int var1 = MathHelper.floor_double(this.posX);
      int var2 = MathHelper.floor_double(this.boundingBox.minY);
      int var3 = MathHelper.floor_double(this.posZ);
      return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && this.worldObj.getFullBlockLightValue(var1, var2, var3) > 8 && super.getCanSpawnHere();
   }

   public int getTalkInterval() {
      return 120;
   }

   protected boolean canDespawn() {
      return false;
   }

   protected int func_36001_a(EntityPlayer var1) {
      return 1 + this.worldObj.rand.nextInt(3);
   }

   protected boolean func_40143_a(ItemStack var1) {
      return var1.itemID == Item.wheat.shiftedIndex;
   }

   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(var2 != null && this.func_40143_a(var2) && this.func_40146_g() == 0) {
         --var2.stackSize;
         if(var2.stackSize <= 0) {
            var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
         }

         this.field_39004_a = 600;
         this.entityToAttack = null;

         for(int var3 = 0; var3 < 7; ++var3) {
            double var4 = this.rand.nextGaussian() * 0.02D;
            double var6 = this.rand.nextGaussian() * 0.02D;
            double var8 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
         }

         return true;
      } else {
         return super.interact(var1);
      }
   }

   public boolean func_40127_l() {
      return this.func_40146_g() < 0;
   }
}
