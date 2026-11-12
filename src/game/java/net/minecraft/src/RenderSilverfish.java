package net.minecraft.src;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntitySilverfish;
import net.minecraft.src.ModelSilverfish;
import net.minecraft.src.RenderLiving;

public class RenderSilverfish extends RenderLiving {
   private int field_35450_c;

   public RenderSilverfish() {
      super(new ModelSilverfish(), 0.3F);
   }

   protected float getSilverfishDeathRotation(EntitySilverfish var1) {
      return 180.0F;
   }

   public void renderSilverfish(EntitySilverfish var1, double var2, double var4, double var6, float var8, float var9) {
      int var10 = ((ModelSilverfish)this.mainModel).func_35395_a();
      if(var10 != this.field_35450_c) {
         this.field_35450_c = var10;
         this.mainModel = new ModelSilverfish();
         System.out.println("new silverfish model");
      }

      super.doRenderLiving(var1, var2, var4, var6, var8, var9);
   }

   protected int shouldSilverfishRenderPass(EntitySilverfish var1, int var2, float var3) {
      return -1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float getDeathMaxRotation(EntityLiving var1) {
      return this.getSilverfishDeathRotation((EntitySilverfish)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected int shouldRenderPass(EntityLiving var1, int var2, float var3) {
      return this.shouldSilverfishRenderPass((EntitySilverfish)var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.renderSilverfish((EntitySilverfish)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.renderSilverfish((EntitySilverfish)var1, var2, var4, var6, var8, var9);
   }
}
