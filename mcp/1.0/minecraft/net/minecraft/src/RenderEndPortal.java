package net.minecraft.src;

import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.src.ActiveRenderInfo;
import net.minecraft.src.GLAllocation;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityEndPortal;
import net.minecraft.src.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderEndPortal extends TileEntitySpecialRenderer {
   FloatBuffer field_40448_a = GLAllocation.createDirectFloatBuffer(16);

   public void func_40446_a(TileEntityEndPortal var1, double var2, double var4, double var6, float var8) {
      float var9 = (float)this.tileEntityRenderer.playerX;
      float var10 = (float)this.tileEntityRenderer.playerY;
      float var11 = (float)this.tileEntityRenderer.playerZ;
      GL11.glDisable(2896 /*GL_LIGHTING*/);
      Random var12 = new Random(31100L);
      float var13 = 0.75F;

      for(int var14 = 0; var14 < 16; ++var14) {
         GL11.glPushMatrix();
         float var15 = (float)(16 - var14);
         float var16 = 0.0625F;
         float var17 = 1.0F / (var15 + 1.0F);
         if(var14 == 0) {
            this.bindTextureByName("/misc/tunnel.png");
            var17 = 0.1F;
            var15 = 65.0F;
            var16 = 0.125F;
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glBlendFunc(770, 771);
         }

         if(var14 == 1) {
            this.bindTextureByName("/misc/particlefield.png");
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glBlendFunc(1, 1);
            var16 = 0.5F;
         }

         float var18 = (float)(-(var4 + (double)var13));
         float var19 = var18 + ActiveRenderInfo.field_41072_b;
         float var20 = var18 + var15 + ActiveRenderInfo.field_41072_b;
         float var21 = var19 / var20;
         var21 = (float)(var4 + (double)var13) + var21;
         GL11.glTranslatef(var9, var21, var11);
         GL11.glTexGeni(8192 /*GL_S*/, 9472 /*GL_TEXTURE_GEN_MODE*/, 9217 /*GL_OBJECT_LINEAR*/);
         GL11.glTexGeni(8193 /*GL_T*/, 9472 /*GL_TEXTURE_GEN_MODE*/, 9217 /*GL_OBJECT_LINEAR*/);
         GL11.glTexGeni(8194 /*GL_R*/, 9472 /*GL_TEXTURE_GEN_MODE*/, 9217 /*GL_OBJECT_LINEAR*/);
         GL11.glTexGeni(8195 /*GL_Q*/, 9472 /*GL_TEXTURE_GEN_MODE*/, 9216 /*GL_EYE_LINEAR*/);
         GL11.glTexGen(8192 /*GL_S*/, 9473 /*GL_OBJECT_PLANE*/, this.func_40447_a(1.0F, 0.0F, 0.0F, 0.0F));
         GL11.glTexGen(8193 /*GL_T*/, 9473 /*GL_OBJECT_PLANE*/, this.func_40447_a(0.0F, 0.0F, 1.0F, 0.0F));
         GL11.glTexGen(8194 /*GL_R*/, 9473 /*GL_OBJECT_PLANE*/, this.func_40447_a(0.0F, 0.0F, 0.0F, 1.0F));
         GL11.glTexGen(8195 /*GL_Q*/, 9474 /*GL_EYE_PLANE*/, this.func_40447_a(0.0F, 1.0F, 0.0F, 0.0F));
         GL11.glEnable(3168 /*GL_TEXTURE_GEN_S*/);
         GL11.glEnable(3169 /*GL_TEXTURE_GEN_T*/);
         GL11.glEnable(3170 /*GL_TEXTURE_GEN_R*/);
         GL11.glEnable(3171 /*GL_TEXTURE_GEN_Q*/);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5890 /*GL_TEXTURE*/);
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 700000.0F, 0.0F);
         GL11.glScalef(var16, var16, var16);
         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
         GL11.glRotatef((float)(var14 * var14 * 4321 + var14 * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
         GL11.glTranslatef(-var9, -var11, -var10);
         var19 = var18 + ActiveRenderInfo.field_41072_b;
         GL11.glTranslatef(ActiveRenderInfo.field_41074_a * var15 / var19, ActiveRenderInfo.field_41073_c * var15 / var19, -var10);
         Tessellator var25 = Tessellator.instance;
         var25.startDrawingQuads();
         var21 = var12.nextFloat() * 0.5F + 0.1F;
         float var22 = var12.nextFloat() * 0.5F + 0.4F;
         float var23 = var12.nextFloat() * 0.5F + 0.5F;
         if(var14 == 0) {
            var23 = 1.0F;
            var22 = 1.0F;
            var21 = 1.0F;
         }

         var25.setColorRGBA_F(var21 * var17, var22 * var17, var23 * var17, 1.0F);
         var25.addVertex(var2, var4 + (double)var13, var6);
         var25.addVertex(var2, var4 + (double)var13, var6 + 1.0D);
         var25.addVertex(var2 + 1.0D, var4 + (double)var13, var6 + 1.0D);
         var25.addVertex(var2 + 1.0D, var4 + (double)var13, var6);
         var25.draw();
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888 /*GL_MODELVIEW0_ARB*/);
      }

      GL11.glDisable(3042 /*GL_BLEND*/);
      GL11.glDisable(3168 /*GL_TEXTURE_GEN_S*/);
      GL11.glDisable(3169 /*GL_TEXTURE_GEN_T*/);
      GL11.glDisable(3170 /*GL_TEXTURE_GEN_R*/);
      GL11.glDisable(3171 /*GL_TEXTURE_GEN_Q*/);
      GL11.glEnable(2896 /*GL_LIGHTING*/);
   }

   private FloatBuffer func_40447_a(float var1, float var2, float var3, float var4) {
      this.field_40448_a.clear();
      this.field_40448_a.put(var1).put(var2).put(var3).put(var4);
      this.field_40448_a.flip();
      return this.field_40448_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
      this.func_40446_a((TileEntityEndPortal)var1, var2, var4, var6, var8);
   }
}
