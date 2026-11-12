package net.minecraft.src;

public class Tessellator {
   public static final Tessellator instance = new Tessellator(2097152);
   private final net.peyton.eagler.minecraft.Tessellator backend;

   public Tessellator(int var1) {
      this.backend = new BackendTessellator(var1);
   }

   private static final class BackendTessellator extends net.peyton.eagler.minecraft.Tessellator {
      BackendTessellator(int var1) {
         super(var1);
      }
   }

   public int draw() {
      this.backend.draw();
      return 0;
   }

   public void startDrawingQuads() {
      this.backend.startDrawingQuads();
   }

   public void startDrawing(int var1) {
      this.backend.startDrawing(var1);
   }

   public void setTextureUV(double var1, double var3) {
      this.backend.setTextureUV(var1, var3);
   }

   public void setBrightness(int var1) {
   }

   public void setColorOpaque_F(float var1, float var2, float var3) {
      this.backend.setColorOpaque_F(var1, var2, var3);
   }

   public void setColorRGBA_F(float var1, float var2, float var3, float var4) {
      this.backend.setColorRGBA_F(var1, var2, var3, var4);
   }

   public void setColorOpaque(int var1, int var2, int var3) {
      this.backend.setColorOpaque(var1, var2, var3);
   }

   public void setColorRGBA(int var1, int var2, int var3, int var4) {
      this.backend.setColorRGBA(var1, var2, var3, var4);
   }

   public void addVertexWithUV(double var1, double var3, double var5, double var7, double var9) {
      this.backend.addVertexWithUV(var1, var3, var5, var7, var9);
   }

   public void addVertex(double var1, double var3, double var5) {
      this.backend.addVertex(var1, var3, var5);
   }

   public void setColorOpaque_I(int var1) {
      this.backend.setColorOpaque_I(var1);
   }

   public void setColorRGBA_I(int var1, int var2) {
      this.backend.setColorRGBA_I(var1, var2);
   }

   public void disableColor() {
      this.backend.disableColor();
   }

   public void setNormal(float var1, float var2, float var3) {
      this.backend.setNormal(var1, var2, var3);
   }

   public void setTranslationD(double var1, double var3, double var5) {
      this.backend.setTranslationD(var1, var3, var5);
   }

   public void setTranslationF(float var1, float var2, float var3) {
      this.backend.setTranslationF(var1, var2, var3);
   }
}
