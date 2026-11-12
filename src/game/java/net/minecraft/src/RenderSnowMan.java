package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntitySnowman;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModelSnowMan;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderLiving;
import org.lwjgl.opengl.GL11;

public class RenderSnowMan extends RenderLiving {
   private ModelSnowMan field_40289_c;

   public RenderSnowMan() {
      super(new ModelSnowMan(), 0.5F);
      this.field_40289_c = (ModelSnowMan)super.mainModel;
      this.setRenderPassModel(this.field_40289_c);
   }

   protected void func_40288_a(EntitySnowman var1, float var2) {
      super.renderEquippedItems(var1, var2);
      ItemStack var3 = new ItemStack(Block.pumpkin, 1);
      if(var3 != null && var3.getItem().shiftedIndex < 256) {
         GL11.glPushMatrix();
         this.field_40289_c.field_40305_c.postRender(0.0625F);
         if(RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType())) {
            float var4 = 0.625F;
            GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var4, -var4, var4);
         }

         this.renderManager.itemRenderer.renderItem(var1, var3, 0);
         GL11.glPopMatrix();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderEquippedItems(EntityLiving var1, float var2) {
      this.func_40288_a((EntitySnowman)var1, var2);
   }
}
