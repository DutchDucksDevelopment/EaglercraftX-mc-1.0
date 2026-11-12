package net.minecraft.src;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityEnderCrystal;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelEnderCrystal;
import net.minecraft.src.Render;
import org.lwjgl.opengl.GL11;

public class RenderEnderCrystal extends Render {
   private int field_41037_a = -1;
   private ModelBase field_41036_b;

   public RenderEnderCrystal() {
      this.shadowSize = 0.5F;
   }

   public void func_41035_a(EntityEnderCrystal var1, double var2, double var4, double var6, float var8, float var9) {
      if(this.field_41037_a != 1) {
         this.field_41036_b = new ModelEnderCrystal(0.0F);
         this.field_41037_a = 1;
      }

      float var10 = (float)var1.field_41032_a + var9;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      this.loadTexture("/mob/enderdragon/crystal.png");
      float var11 = MathHelper.sin(var10 * 0.2F) / 2.0F + 0.5F;
      var11 = var11 * var11 + var11;
      this.field_41036_b.render(var1, 0.0F, var10 * 3.0F, var11 * 0.2F, 0.0F, 0.0F, 0.0625F);
      GL11.glPopMatrix();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_41035_a((EntityEnderCrystal)var1, var2, var4, var6, var8, var9);
   }
}
