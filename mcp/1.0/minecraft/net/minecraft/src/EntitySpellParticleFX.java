package net.minecraft.src;

import net.minecraft.src.EntityFX;
import net.minecraft.src.Tessellator;
import net.minecraft.src.World;

public class EntitySpellParticleFX extends EntityFX {
   private int field_40111_a = 128;

   public EntitySpellParticleFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.motionY *= 0.20000000298023224D;
      if(var8 == 0.0D && var12 == 0.0D) {
         this.motionX *= 0.10000000149011612D;
         this.motionZ *= 0.10000000149011612D;
      }

      this.particleScale *= 0.75F;
      this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
      this.noClip = false;
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)this.particleAge + var2) / (float)this.particleMaxAge * 32.0F;
      if(var8 < 0.0F) {
         var8 = 0.0F;
      }

      if(var8 > 1.0F) {
         var8 = 1.0F;
      }

      super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if(this.particleAge++ >= this.particleMaxAge) {
         this.setEntityDead();
      }

      this.func_40099_c(this.field_40111_a + (7 - this.particleAge * 8 / this.particleMaxAge));
      this.motionY += 0.004D;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      if(this.posY == this.prevPosY) {
         this.motionX *= 1.1D;
         this.motionZ *= 1.1D;
      }

      this.motionX *= 0.9599999785423279D;
      this.motionY *= 0.9599999785423279D;
      this.motionZ *= 0.9599999785423279D;
      if(this.onGround) {
         this.motionX *= 0.699999988079071D;
         this.motionZ *= 0.699999988079071D;
      }

   }

   public void func_40110_b(int var1) {
      this.field_40111_a = var1;
   }
}
