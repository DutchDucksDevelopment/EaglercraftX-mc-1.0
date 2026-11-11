package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityFallingSand;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import net.minecraft.src.World;
import org.lwjgl.opengl.GL11;

public class RenderFallingSand extends Render {
   private RenderBlocks field_197_d = new RenderBlocks();

   public RenderFallingSand() {
      this.shadowSize = 0.5F;
   }

   public void doRenderFallingSand(EntityFallingSand var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      this.loadTexture("/terrain.png");
      Block var10 = Block.blocksList[var1.blockID];
      World var11 = var1.getWorld();
      GL11.glDisable(2896 /*GL_LIGHTING*/);
      if(var10 == Block.field_41050_bK) {
         this.field_197_d.blockAccess = var11;
         Tessellator var12 = Tessellator.instance;
         var12.startDrawingQuads();
         var12.setTranslationD((double)((float)(-MathHelper.floor_double(var1.posX)) - 0.5F), (double)((float)(-MathHelper.floor_double(var1.posY)) - 0.5F), (double)((float)(-MathHelper.floor_double(var1.posZ)) - 0.5F));
         this.field_197_d.renderBlockByRenderType(var10, MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ));
         var12.setTranslationD(0.0D, 0.0D, 0.0D);
         var12.draw();
      } else {
         this.field_197_d.renderBlockFallingSand(var10, var11, MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ));
      }

      GL11.glEnable(2896 /*GL_LIGHTING*/);
      GL11.glPopMatrix();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRenderFallingSand((EntityFallingSand)var1, var2, var4, var6, var8, var9);
   }
}
