package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ScreenShotHelper {
   private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   private static ByteBuffer buffer;
   private static byte[] pixelData;
   private static int[] imageData;

   public static String saveScreenshot(File var0, int var1, int var2) {
      return func_35879_a(var0, (String)null, var1, var2);
   }

   public static String func_35879_a(File var0, String var1, int var2, int var3) {
      try {
         File var4 = new File(var0, "screenshots");
         var4.mkdir();
         if(buffer == null || buffer.capacity() < var2 * var3) {
            buffer = BufferUtils.createByteBuffer(var2 * var3 * 3);
         }

         if(imageData == null || imageData.length < var2 * var3 * 3) {
            pixelData = new byte[var2 * var3 * 3];
            imageData = new int[var2 * var3];
         }

         GL11.glPixelStorei(3333 /*GL_PACK_ALIGNMENT*/, 1);
         GL11.glPixelStorei(3317 /*GL_UNPACK_ALIGNMENT*/, 1);
         buffer.clear();
         GL11.glReadPixels(0, 0, var2, var3, 6407 /*GL_RGB*/, 5121 /*GL_UNSIGNED_BYTE*/, buffer);
         buffer.clear();
         String var5 = "" + dateFormat.format(new Date());
         File var6;
         if(var1 == null) {
            for(int var7 = 1; (var6 = new File(var4, var5 + (var7 == 1?"":"_" + var7) + ".png")).exists(); ++var7) {
               ;
            }
         } else {
            var6 = new File(var4, var1);
         }

         buffer.get(pixelData);

         for(int var15 = 0; var15 < var2; ++var15) {
            for(int var8 = 0; var8 < var3; ++var8) {
               int var9 = var15 + (var3 - var8 - 1) * var2;
               int var10 = pixelData[var9 * 3 + 0] & 255;
               int var11 = pixelData[var9 * 3 + 1] & 255;
               int var12 = pixelData[var9 * 3 + 2] & 255;
               int var13 = -16777216 | var10 << 16 | var11 << 8 | var12;
               imageData[var15 + var8 * var2] = var13;
            }
         }

         BufferedImage var16 = new BufferedImage(var2, var3, 1);
         var16.setRGB(0, 0, var2, var3, imageData, 0, var2);
         ImageIO.write(var16, "png", var6);
         return "Saved screenshot as " + var6.getName();
      } catch (Exception var14) {
         var14.printStackTrace();
         return "Failed to save: " + var14;
      }
   }
}
