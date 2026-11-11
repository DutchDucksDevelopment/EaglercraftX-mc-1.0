package net.minecraft.src;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityBlaze;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBlaze;
import net.minecraft.src.RenderLiving;

public class RenderBlaze extends RenderLiving {
   private int field_40278_c;

   public RenderBlaze() {
      super(new ModelBlaze(), 0.5F);
      this.field_40278_c = ((ModelBlaze)this.mainModel).func_40321_a();
   }

   public void func_40277_a(EntityBlaze var1, double var2, double var4, double var6, float var8, float var9) {
      int var10 = ((ModelBlaze)this.mainModel).func_40321_a();
      if(var10 != this.field_40278_c) {
         this.field_40278_c = var10;
         this.mainModel = new ModelBlaze();
      }

      super.doRenderLiving(var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40277_a((EntityBlaze)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40277_a((EntityBlaze)var1, var2, var4, var6, var8, var9);
   }
}
