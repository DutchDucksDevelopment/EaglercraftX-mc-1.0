package net.minecraft.src;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBook;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityEnchantmentTable;
import net.minecraft.src.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderEnchantmentTable extends TileEntitySpecialRenderer {
   private ModelBook field_40450_a = new ModelBook();

   public void func_40449_a(TileEntityEnchantmentTable var1, double var2, double var4, double var6, float var8) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 0.75F, (float)var6 + 0.5F);
      float var9 = (float)var1.field_40068_a + var8;
      GL11.glTranslatef(0.0F, 0.1F + MathHelper.sin(var9 * 0.1F) * 0.01F, 0.0F);

      float var10;
      for(var10 = var1.field_40069_h - var1.field_40067_p; var10 >= 3.1415927F; var10 -= 6.2831855F) {
         ;
      }

      while(var10 < -3.1415927F) {
         var10 += 6.2831855F;
      }

      float var11 = var1.field_40067_p + var10 * var8;
      GL11.glRotatef(-var11 * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
      this.bindTextureByName("/item/book.png");
      float var12 = var1.field_40065_c + (var1.field_40063_b - var1.field_40065_c) * var8 + 0.25F;
      float var13 = var1.field_40065_c + (var1.field_40063_b - var1.field_40065_c) * var8 + 0.75F;
      var12 = (var12 - (float)MathHelper.func_40346_b((double)var12)) * 1.6F - 0.3F;
      var13 = (var13 - (float)MathHelper.func_40346_b((double)var13)) * 1.6F - 0.3F;
      if(var12 < 0.0F) {
         var12 = 0.0F;
      }

      if(var13 < 0.0F) {
         var13 = 0.0F;
      }

      if(var12 > 1.0F) {
         var12 = 1.0F;
      }

      if(var13 > 1.0F) {
         var13 = 1.0F;
      }

      float var14 = var1.field_40060_g + (var1.field_40059_f - var1.field_40060_g) * var8;
      this.field_40450_a.render((Entity)null, var9, var12, var13, var14, 0.0F, 0.0625F);
      GL11.glPopMatrix();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
      this.func_40449_a((TileEntityEnchantmentTable)var1, var2, var4, var6, var8);
   }
}
