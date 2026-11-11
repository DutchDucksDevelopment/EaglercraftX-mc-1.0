package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelMinecart;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Vec3D;
import org.lwjgl.opengl.GL11;

public class RenderMinecart extends Render {
   protected ModelBase modelMinecart;

   public RenderMinecart() {
      this.shadowSize = 0.5F;
      this.modelMinecart = new ModelMinecart();
   }

   public void func_152_a(EntityMinecart var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      long var10 = (long)var1.entityId * 493286711L;
      var10 = var10 * var10 * 4392167121L + var10 * 98761L;
      float var12 = (((float)(var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float var13 = (((float)(var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float var14 = (((float)(var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      GL11.glTranslatef(var12, var13, var14);
      double var15 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var9;
      double var17 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var9;
      double var19 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var9;
      double var21 = 0.30000001192092896D;
      Vec3D var23 = var1.func_514_g(var15, var17, var19);
      float var24 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;
      if(var23 != null) {
         Vec3D var25 = var1.func_515_a(var15, var17, var19, var21);
         Vec3D var26 = var1.func_515_a(var15, var17, var19, -var21);
         if(var25 == null) {
            var25 = var23;
         }

         if(var26 == null) {
            var26 = var23;
         }

         var2 += var23.xCoord - var15;
         var4 += (var25.yCoord + var26.yCoord) / 2.0D - var17;
         var6 += var23.zCoord - var19;
         Vec3D var27 = var26.addVector(-var25.xCoord, -var25.yCoord, -var25.zCoord);
         if(var27.lengthVector() != 0.0D) {
            var27 = var27.normalize();
            var8 = (float)(Math.atan2(var27.zCoord, var27.xCoord) * 180.0D / 3.141592653589793D);
            var24 = (float)(Math.atan(var27.yCoord) * 73.0D);
         }
      }

      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      GL11.glRotatef(180.0F - var8, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
      float var29 = (float)var1.func_41023_l() - var9;
      float var30 = (float)var1.func_41025_i() - var9;
      if(var30 < 0.0F) {
         var30 = 0.0F;
      }

      if(var29 > 0.0F) {
         GL11.glRotatef(MathHelper.sin(var29) * var29 * var30 / 10.0F * (float)var1.func_41030_m(), 1.0F, 0.0F, 0.0F);
      }

      if(var1.minecartType != 0) {
         this.loadTexture("/terrain.png");
         float var32 = 0.75F;
         GL11.glScalef(var32, var32, var32);
         if(var1.minecartType == 1) {
            GL11.glTranslatef(-0.5F, 0.0F, 0.5F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            (new RenderBlocks()).renderBlockOnInventory(Block.chest, 0, var1.getEntityBrightness(var9));
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.5F, 0.0F, -0.5F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         } else if(var1.minecartType == 2) {
            GL11.glTranslatef(0.0F, 0.3125F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            (new RenderBlocks()).renderBlockOnInventory(Block.stoneOvenIdle, 0, var1.getEntityBrightness(var9));
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.3125F, 0.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GL11.glScalef(1.0F / var32, 1.0F / var32, 1.0F / var32);
      }

      this.loadTexture("/item/cart.png");
      GL11.glScalef(-1.0F, -1.0F, 1.0F);
      this.modelMinecart.render(var1, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GL11.glPopMatrix();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_152_a((EntityMinecart)var1, var2, var4, var6, var8, var9);
   }
}
