package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelDragon;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class RenderDragon extends RenderLiving {
   public static EntityDragon field_41038_a;
   private static int field_40284_d = 0;
   protected ModelDragon field_40285_c;

   public RenderDragon() {
      super(new ModelDragon(0.0F), 0.5F);
      this.field_40285_c = (ModelDragon)this.mainModel;
      this.setRenderPassModel(this.mainModel);
   }

   protected void func_40282_a(EntityDragon var1, float var2, float var3, float var4) {
      float var5 = (float)var1.func_40160_a(7, var4)[0];
      float var6 = (float)(var1.func_40160_a(5, var4)[1] - var1.func_40160_a(10, var4)[1]);
      GL11.glRotatef(-var5, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(var6 * 10.0F, 1.0F, 0.0F, 0.0F);
      GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      if(var1.deathTime > 0) {
         float var7 = ((float)var1.deathTime + var4 - 1.0F) / 20.0F * 1.6F;
         var7 = MathHelper.sqrt_float(var7);
         if(var7 > 1.0F) {
            var7 = 1.0F;
         }

         GL11.glRotatef(var7 * this.getDeathMaxRotation(var1), 0.0F, 0.0F, 1.0F);
      }

   }

   protected void func_40280_a(EntityDragon var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      if(var1.field_40178_aA > 0) {
         float var8 = (float)var1.field_40178_aA / 200.0F;
         GL11.glDepthFunc(515);
         GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
         GL11.glAlphaFunc(516, var8);
         this.loadDownloadableImageTexture(var1.skinUrl, "/mob/enderdragon/shuffle.png");
         this.mainModel.render(var1, var2, var3, var4, var5, var6, var7);
         GL11.glAlphaFunc(516, 0.1F);
         GL11.glDepthFunc(514);
      }

      this.loadDownloadableImageTexture(var1.skinUrl, var1.getEntityTexture());
      this.mainModel.render(var1, var2, var3, var4, var5, var6, var7);
      if(var1.hurtTime > 0) {
         GL11.glDepthFunc(514);
         GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
         GL11.glEnable(3042 /*GL_BLEND*/);
         GL11.glBlendFunc(770, 771);
         GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
         this.mainModel.render(var1, var2, var3, var4, var5, var6, var7);
         GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
         GL11.glDisable(3042 /*GL_BLEND*/);
         GL11.glDepthFunc(515);
      }

   }

   public void func_40281_a(EntityDragon var1, double var2, double var4, double var6, float var8, float var9) {
      field_41038_a = var1;
      if(field_40284_d != 4) {
         this.mainModel = new ModelDragon(0.0F);
         field_40284_d = 4;
      }

      super.doRenderLiving(var1, var2, var4, var6, var8, var9);
      if(var1.field_41013_bH != null) {
         float var10 = (float)var1.field_41013_bH.field_41032_a + var9;
         float var11 = MathHelper.sin(var10 * 0.2F) / 2.0F + 0.5F;
         var11 = (var11 * var11 + var11) * 0.2F;
         float var12 = (float)(var1.field_41013_bH.posX - var1.posX - (var1.prevPosX - var1.posX) * (double)(1.0F - var9));
         float var13 = (float)((double)var11 + var1.field_41013_bH.posY - 1.0D - var1.posY - (var1.prevPosY - var1.posY) * (double)(1.0F - var9));
         float var14 = (float)(var1.field_41013_bH.posZ - var1.posZ - (var1.prevPosZ - var1.posZ) * (double)(1.0F - var9));
         float var15 = MathHelper.sqrt_float(var12 * var12 + var14 * var14);
         float var16 = MathHelper.sqrt_float(var12 * var12 + var13 * var13 + var14 * var14);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)var2, (float)var4 + 2.0F, (float)var6);
         GL11.glRotatef((float)(-Math.atan2((double)var14, (double)var12)) * 180.0F / 3.1415927F - 90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef((float)(-Math.atan2((double)var15, (double)var13)) * 180.0F / 3.1415927F - 90.0F, 1.0F, 0.0F, 0.0F);
         Tessellator var17 = Tessellator.instance;
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2884 /*GL_CULL_FACE*/);
         this.loadTexture("/mob/enderdragon/beam.png");
         GL11.glShadeModel(7425 /*GL_SMOOTH*/);
         float var18 = 0.0F - ((float)var1.ticksExisted + var9) * 0.01F;
         float var19 = MathHelper.sqrt_float(var12 * var12 + var13 * var13 + var14 * var14) / 32.0F - ((float)var1.ticksExisted + var9) * 0.01F;
         var17.startDrawing(5);
         byte var20 = 8;

         for(int var21 = 0; var21 <= var20; ++var21) {
            float var22 = MathHelper.sin((float)(var21 % var20) * 3.1415927F * 2.0F / (float)var20) * 0.75F;
            float var23 = MathHelper.cos((float)(var21 % var20) * 3.1415927F * 2.0F / (float)var20) * 0.75F;
            float var24 = (float)(var21 % var20) * 1.0F / (float)var20;
            var17.setColorOpaque_I(0);
            var17.addVertexWithUV((double)(var22 * 0.2F), (double)(var23 * 0.2F), 0.0D, (double)var24, (double)var19);
            var17.setColorOpaque_I(16777215);
            var17.addVertexWithUV((double)var22, (double)var23, (double)var16, (double)var24, (double)var18);
         }

         var17.draw();
         GL11.glEnable(2884 /*GL_CULL_FACE*/);
         GL11.glShadeModel(7424 /*GL_FLAT*/);
         RenderHelper.enableStandardItemLighting();
         GL11.glPopMatrix();
      }

   }

   protected void func_40279_a(EntityDragon var1, float var2) {
      super.renderEquippedItems(var1, var2);
      Tessellator var3 = Tessellator.instance;
      if(var1.field_40178_aA > 0) {
         RenderHelper.disableStandardItemLighting();
         float var4 = ((float)var1.field_40178_aA + var2) / 200.0F;
         float var5 = 0.0F;
         if(var4 > 0.8F) {
            var5 = (var4 - 0.8F) / 0.2F;
         }

         Random var6 = new Random(432L);
         GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
         GL11.glShadeModel(7425 /*GL_SMOOTH*/);
         GL11.glEnable(3042 /*GL_BLEND*/);
         GL11.glBlendFunc(770, 1);
         GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
         GL11.glEnable(2884 /*GL_CULL_FACE*/);
         GL11.glDepthMask(false);
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, -1.0F, -2.0F);

         for(int var7 = 0; (float)var7 < (var4 + var4 * var4) / 2.0F * 60.0F; ++var7) {
            GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var6.nextFloat() * 360.0F + var4 * 90.0F, 0.0F, 0.0F, 1.0F);
            var3.startDrawing(6);
            float var8 = var6.nextFloat() * 20.0F + 5.0F + var5 * 10.0F;
            float var9 = var6.nextFloat() * 2.0F + 1.0F + var5 * 2.0F;
            var3.setColorRGBA_I(16777215, (int)(255.0F * (1.0F - var5)));
            var3.addVertex(0.0D, 0.0D, 0.0D);
            var3.setColorRGBA_I(16711935, 0);
            var3.addVertex(-0.866D * (double)var9, (double)var8, (double)(-0.5F * var9));
            var3.addVertex(0.866D * (double)var9, (double)var8, (double)(-0.5F * var9));
            var3.addVertex(0.0D, (double)var8, (double)(1.0F * var9));
            var3.addVertex(-0.866D * (double)var9, (double)var8, (double)(-0.5F * var9));
            var3.draw();
         }

         GL11.glPopMatrix();
         GL11.glDepthMask(true);
         GL11.glDisable(2884 /*GL_CULL_FACE*/);
         GL11.glDisable(3042 /*GL_BLEND*/);
         GL11.glShadeModel(7424 /*GL_FLAT*/);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
         GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
         RenderHelper.enableStandardItemLighting();
      }

   }

   protected int func_40283_a(EntityDragon var1, int var2, float var3) {
      if(var2 == 1) {
         GL11.glDepthFunc(515);
      }

      if(var2 != 0) {
         return -1;
      } else {
         this.loadTexture("/mob/enderdragon/ender_eyes.png");
         float var4 = 1.0F;
         GL11.glEnable(3042 /*GL_BLEND*/);
         GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
         GL11.glBlendFunc(1, 1);
         GL11.glDisable(2896 /*GL_LIGHTING*/);
         GL11.glDepthFunc(514);
         char var5 = '\uf0f0';
         int var6 = var5 % 65536;
         int var7 = var5 / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapEnabled, (float)var6 / 1.0F, (float)var7 / 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(2896 /*GL_LIGHTING*/);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
         return 1;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected int shouldRenderPass(EntityLiving var1, int var2, float var3) {
      return this.func_40283_a((EntityDragon)var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderEquippedItems(EntityLiving var1, float var2) {
      this.func_40279_a((EntityDragon)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void rotateCorpse(EntityLiving var1, float var2, float var3, float var4) {
      this.func_40282_a((EntityDragon)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void func_40270_a(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.func_40280_a((EntityDragon)var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40281_a((EntityDragon)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40281_a((EntityDragon)var1, var2, var4, var6, var8, var9);
   }
}
