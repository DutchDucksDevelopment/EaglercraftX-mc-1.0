package net.minecraft.src;

import java.util.Random;

public class FontRenderer extends net.peyton.eagler.minecraft.FontRenderer {
   public int field_41063_b = 8;
   public Random field_41064_c;

   public FontRenderer(GameSettings var1, String var2, RenderEngine var3) {
      super(var1, var2, var3, false);
      this.FONT_HEIGHT = this.field_41063_b;
      this.field_41064_c = this.fontRandom;
   }

   public void drawStringWithShadow(String var1, int var2, int var3, int var4) {
      this.drawString(var1, (float)var2, (float)var3, var4, true);
   }

   public void renderString(String var1, int var2, int var3, int var4, boolean var5) {
      int var6 = var4;
      if(var5) {
         int var7 = var4 & -16777216;
         var6 = (var4 & 16579836) >> 2;
         var6 = var6 + var7;
      }

      this.drawString(var1, (float)var2, (float)var3, var6, false);
   }

   public void drawSplitString(String var1, int var2, int var3, int var4, int var5) {
      this.func_40609_a(var1, var2, var3, var4, var5, false);
   }

   public void func_40609_a(String var1, int var2, int var3, int var4, int var5, boolean var6) {
      String[] var7 = var1.split("\n");
      if(var7.length > 1) {
         for(int var8 = 0; var8 < var7.length; ++var8) {
            this.func_40609_a(var7[var8], var2, var3, var4, var5, var6);
            var3 += this.splitStringWidth(var7[var8], var4);
         }
      } else {
         for(String var10 : this.listFormattedStringToWidth(var1, var4)) {
            this.renderString(var10, var2, var3, var5, var6);
            var3 += this.field_41063_b;
         }
      }
   }

   public int splitStringWidth(String var1, int var2) {
      return this.field_41063_b * this.listFormattedStringToWidth(var1, var2).size();
   }
}
