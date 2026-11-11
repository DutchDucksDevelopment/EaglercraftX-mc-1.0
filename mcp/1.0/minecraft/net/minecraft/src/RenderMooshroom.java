package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMooshroom;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelQuadruped;
import net.minecraft.src.RenderLiving;
import org.lwjgl.opengl.GL11;

public class RenderMooshroom extends RenderLiving {
   public RenderMooshroom(ModelBase var1, float var2) {
      super(var1, var2);
   }

   public void func_40273_a(EntityMooshroom var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRenderLiving(var1, var2, var4, var6, var8, var9);
   }

   protected void func_40272_a(EntityMooshroom var1, float var2) {
      super.renderEquippedItems(var1, var2);
      if(!var1.func_40127_l()) {
         this.loadTexture("/terrain.png");
         GL11.glEnable(2884 /*GL_CULL_FACE*/);
         GL11.glPushMatrix();
         GL11.glScalef(1.0F, -1.0F, 1.0F);
         GL11.glTranslatef(0.2F, 0.4F, 0.5F);
         GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
         this.renderBlocks.renderBlockOnInventory(Block.mushroomRed, 0, 1.0F);
         GL11.glTranslatef(0.1F, 0.0F, -0.6F);
         GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
         this.renderBlocks.renderBlockOnInventory(Block.mushroomRed, 0, 1.0F);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         ((ModelQuadruped)this.mainModel).head.postRender(0.0625F);
         GL11.glScalef(1.0F, -1.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.75F, -0.2F);
         GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
         this.renderBlocks.renderBlockOnInventory(Block.mushroomRed, 0, 1.0F);
         GL11.glPopMatrix();
         GL11.glDisable(2884 /*GL_CULL_FACE*/);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderEquippedItems(EntityLiving var1, float var2) {
      this.func_40272_a((EntityMooshroom)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40273_a((EntityMooshroom)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_40273_a((EntityMooshroom)var1, var2, var4, var6, var8, var9);
   }
}
