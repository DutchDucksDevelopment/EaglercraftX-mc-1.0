package net.minecraft.src;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.ModelMagmaCube;
import net.minecraft.src.RenderLiving;
import org.lwjgl.opengl.GL11;

public class RenderMagmaCube extends RenderLiving {
   private int field_40276_c;

   public RenderMagmaCube() {
      super(new ModelMagmaCube(), 0.25F);
      this.field_40276_c = ((ModelMagmaCube)this.mainModel).func_40343_a();
   }

   public void func_40275_a(EntityMagmaCube var1, double var2, double var4, double var6, float var8, float var9) {
      int var10 = ((ModelMagmaCube)this.mainModel).func_40343_a();
      if(var10 != this.field_40276_c) {
         this.field_40276_c = var10;
         this.mainModel = new ModelMagmaCube();
         System.out.println("new lava slime model");
      }

      super.doRenderLiving(var1, var2, var4, var6, var8, var9);
   }

   protected void func_40274_a(EntityMagmaCube var1, float var2) {
      int var3 = var1.getSlimeSize();
      float var4 = (var1.field_767_b + (var1.field_768_a - var1.field_767_b) * var2) / ((float)var3 * 0.5F + 1.0F);
      float var5 = 1.0F / (var4 + 1.0F);
      float var6 = (float)var3;
      GL11.glScalef(var5 * var6, 1.0F / var5 * var6, var5 * var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLiving var1, float var2) {
      this.func_40274_a((EntityMagmaCube)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40275_a((EntityMagmaCube)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40275_a((EntityMagmaCube)var1, var2, var4, var6, var8, var9);
   }
}
