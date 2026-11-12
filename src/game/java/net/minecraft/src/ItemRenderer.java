package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MapData;
import net.minecraft.src.MapItemRenderer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderManager;
import net.minecraft.src.RenderPlayer;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class ItemRenderer {
   private Minecraft mc;
   private ItemStack itemToRender = null;
   private float equippedProgress = 0.0F;
   private float prevEquippedProgress = 0.0F;
   private RenderBlocks renderBlocksInstance = new RenderBlocks();
   private MapItemRenderer mapItemRenderer;
   private int field_20099_f = -1;

   public ItemRenderer(Minecraft var1) {
      this.mc = var1;
      this.mapItemRenderer = new MapItemRenderer(var1.fontRenderer, var1.gameSettings, var1.renderEngine);
   }

   public void renderItem(EntityLiving var1, ItemStack var2, int var3) {
      GL11.glPushMatrix();
      if(var2.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var2.itemID].getRenderType())) {
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("/terrain.png"));
         this.renderBlocksInstance.renderBlockOnInventory(Block.blocksList[var2.itemID], var2.getItemDamage(), 1.0F);
      } else {
         if(var2.itemID < 256) {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("/terrain.png"));
         } else {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("/gui/items.png"));
         }

         Tessellator var4 = Tessellator.instance;
         int var5 = var1.getItemIcon(var2, var3);
         float var6 = ((float)(var5 % 16 * 16) + 0.0F) / 256.0F;
         float var7 = ((float)(var5 % 16 * 16) + 15.99F) / 256.0F;
         float var8 = ((float)(var5 / 16 * 16) + 0.0F) / 256.0F;
         float var9 = ((float)(var5 / 16 * 16) + 15.99F) / 256.0F;
         float var10 = 0.0F;
         float var11 = 0.3F;
         GL11.glEnable('\u803a');
         GL11.glTranslatef(-var10, -var11, 0.0F);
         float var12 = 1.5F;
         GL11.glScalef(var12, var12, var12);
         GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
         this.func_40686_a(var4, var7, var8, var6, var9);
         if(var2 != null && var2.func_40713_r() && var3 == 0) {
            GL11.glDepthFunc(514);
            GL11.glDisable(2896 /*GL_LIGHTING*/);
            this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("%blur%/misc/glint.png"));
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glBlendFunc(768, 1);
            float var13 = 0.76F;
            GL11.glColor4f(0.5F * var13, 0.25F * var13, 0.8F * var13, 1.0F);
            GL11.glMatrixMode(5890 /*GL_TEXTURE*/);
            GL11.glPushMatrix();
            float var14 = 0.125F;
            GL11.glScalef(var14, var14, var14);
            float var15 = (float)(System.currentTimeMillis() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(var15, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            this.func_40686_a(var4, 0.0F, 0.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(var14, var14, var14);
            var15 = (float)(System.currentTimeMillis() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-var15, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            this.func_40686_a(var4, 0.0F, 0.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(5888 /*GL_MODELVIEW0_ARB*/);
            GL11.glDisable(3042 /*GL_BLEND*/);
            GL11.glEnable(2896 /*GL_LIGHTING*/);
            GL11.glDepthFunc(515);
         }

         GL11.glDisable('\u803a');
      }

      GL11.glPopMatrix();
   }

   private void func_40686_a(Tessellator var1, float var2, float var3, float var4, float var5) {
      float var6 = 1.0F;
      float var7 = 0.0625F;
      var1.startDrawingQuads();
      var1.setNormal(0.0F, 0.0F, 1.0F);
      var1.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)var2, (double)var5);
      var1.addVertexWithUV((double)var6, 0.0D, 0.0D, (double)var4, (double)var5);
      var1.addVertexWithUV((double)var6, 1.0D, 0.0D, (double)var4, (double)var3);
      var1.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)var2, (double)var3);
      var1.draw();
      var1.startDrawingQuads();
      var1.setNormal(0.0F, 0.0F, -1.0F);
      var1.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - var7), (double)var2, (double)var3);
      var1.addVertexWithUV((double)var6, 1.0D, (double)(0.0F - var7), (double)var4, (double)var3);
      var1.addVertexWithUV((double)var6, 0.0D, (double)(0.0F - var7), (double)var4, (double)var5);
      var1.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - var7), (double)var2, (double)var5);
      var1.draw();
      var1.startDrawingQuads();
      var1.setNormal(-1.0F, 0.0F, 0.0F);

      for(int var8 = 0; var8 < 16; ++var8) {
         float var9 = (float)var8 / 16.0F;
         float var10 = var2 + (var4 - var2) * var9 - 0.001953125F;
         float var11 = var6 * var9;
         var1.addVertexWithUV((double)var11, 0.0D, (double)(0.0F - var7), (double)var10, (double)var5);
         var1.addVertexWithUV((double)var11, 0.0D, 0.0D, (double)var10, (double)var5);
         var1.addVertexWithUV((double)var11, 1.0D, 0.0D, (double)var10, (double)var3);
         var1.addVertexWithUV((double)var11, 1.0D, (double)(0.0F - var7), (double)var10, (double)var3);
      }

      var1.draw();
      var1.startDrawingQuads();
      var1.setNormal(1.0F, 0.0F, 0.0F);

      for(int var12 = 0; var12 < 16; ++var12) {
         float var15 = (float)var12 / 16.0F;
         float var18 = var2 + (var4 - var2) * var15 - 0.001953125F;
         float var21 = var6 * var15 + 0.0625F;
         var1.addVertexWithUV((double)var21, 1.0D, (double)(0.0F - var7), (double)var18, (double)var3);
         var1.addVertexWithUV((double)var21, 1.0D, 0.0D, (double)var18, (double)var3);
         var1.addVertexWithUV((double)var21, 0.0D, 0.0D, (double)var18, (double)var5);
         var1.addVertexWithUV((double)var21, 0.0D, (double)(0.0F - var7), (double)var18, (double)var5);
      }

      var1.draw();
      var1.startDrawingQuads();
      var1.setNormal(0.0F, 1.0F, 0.0F);

      for(int var13 = 0; var13 < 16; ++var13) {
         float var16 = (float)var13 / 16.0F;
         float var19 = var5 + (var3 - var5) * var16 - 0.001953125F;
         float var22 = var6 * var16 + 0.0625F;
         var1.addVertexWithUV(0.0D, (double)var22, 0.0D, (double)var2, (double)var19);
         var1.addVertexWithUV((double)var6, (double)var22, 0.0D, (double)var4, (double)var19);
         var1.addVertexWithUV((double)var6, (double)var22, (double)(0.0F - var7), (double)var4, (double)var19);
         var1.addVertexWithUV(0.0D, (double)var22, (double)(0.0F - var7), (double)var2, (double)var19);
      }

      var1.draw();
      var1.startDrawingQuads();
      var1.setNormal(0.0F, -1.0F, 0.0F);

      for(int var14 = 0; var14 < 16; ++var14) {
         float var17 = (float)var14 / 16.0F;
         float var20 = var5 + (var3 - var5) * var17 - 0.001953125F;
         float var23 = var6 * var17;
         var1.addVertexWithUV((double)var6, (double)var23, 0.0D, (double)var4, (double)var20);
         var1.addVertexWithUV(0.0D, (double)var23, 0.0D, (double)var2, (double)var20);
         var1.addVertexWithUV(0.0D, (double)var23, (double)(0.0F - var7), (double)var2, (double)var20);
         var1.addVertexWithUV((double)var6, (double)var23, (double)(0.0F - var7), (double)var4, (double)var20);
      }

      var1.draw();
   }

   public void renderItemInFirstPerson(float var1) {
      float var2 = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * var1;
      EntityPlayerSP var3 = this.mc.thePlayer;
      float var4 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var1;
      GL11.glPushMatrix();
      GL11.glRotatef(var4, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var1, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glPopMatrix();
      if(var3 instanceof EntityPlayerSP) {
         EntityPlayerSP var5 = (EntityPlayerSP)var3;
         float var6 = var5.prevRenderArmPitch + (var5.renderArmPitch - var5.prevRenderArmPitch) * var1;
         float var7 = var5.prevRenderArmYaw + (var5.renderArmYaw - var5.prevRenderArmYaw) * var1;
         GL11.glRotatef((var3.rotationPitch - var6) * 0.1F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef((var3.rotationYaw - var7) * 0.1F, 0.0F, 1.0F, 0.0F);
      }

      ItemStack var14 = this.itemToRender;
      float var15 = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ));
      var15 = 1.0F;
      int var17 = this.mc.theWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ), 0);
      int var8 = var17 % 65536;
      int var9 = var17 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapEnabled, (float)var8 / 1.0F, (float)var9 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if(var14 != null) {
         var17 = Item.itemsList[var14.itemID].getColorFromDamage(var14.getItemDamage());
         float var22 = (float)(var17 >> 16 & 255) / 255.0F;
         float var33 = (float)(var17 >> 8 & 255) / 255.0F;
         float var10 = (float)(var17 & 255) / 255.0F;
         GL11.glColor4f(var15 * var22, var15 * var33, var15 * var10, 1.0F);
      } else {
         GL11.glColor4f(var15, var15, var15, 1.0F);
      }

      if(var14 != null && var14.itemID == Item.map.shiftedIndex) {
         GL11.glPushMatrix();
         float var21 = 0.8F;
         float var30 = var3.getSwingProgress(var1);
         float var41 = MathHelper.sin(var30 * 3.1415927F);
         float var52 = MathHelper.sin(MathHelper.sqrt_float(var30) * 3.1415927F);
         GL11.glTranslatef(-var52 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var30) * 3.1415927F * 2.0F) * 0.2F, -var41 * 0.2F);
         var30 = 1.0F - var4 / 45.0F + 0.1F;
         if(var30 < 0.0F) {
            var30 = 0.0F;
         }

         if(var30 > 1.0F) {
            var30 = 1.0F;
         }

         var30 = -MathHelper.cos(var30 * 3.1415927F) * 0.5F + 0.5F;
         GL11.glTranslatef(0.0F, 0.0F * var21 - (1.0F - var2) * 1.2F - var30 * 0.5F + 0.04F, -0.9F * var21);
         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(var30 * -85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glEnable('\u803a');
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTextureForDownloadableImage(this.mc.thePlayer.skinUrl, this.mc.thePlayer.getEntityTexture()));

         for(int var42 = 0; var42 < 2; ++var42) {
            int var53 = var42 * 2 - 1;
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.0F, -0.6F, 1.1F * (float)var53);
            GL11.glRotatef((float)(-45 * var53), 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef((float)(-65 * var53), 0.0F, 1.0F, 0.0F);
            Render var57 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
            RenderPlayer var64 = (RenderPlayer)var57;
            float var66 = 1.0F;
            GL11.glScalef(var66, var66, var66);
            var64.drawFirstPersonHand();
            GL11.glPopMatrix();
         }

         var41 = var3.getSwingProgress(var1);
         var52 = MathHelper.sin(var41 * var41 * 3.1415927F);
         float var58 = MathHelper.sin(MathHelper.sqrt_float(var41) * 3.1415927F);
         GL11.glRotatef(-var52 * 20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-var58 * 20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-var58 * 80.0F, 1.0F, 0.0F, 0.0F);
         var41 = 0.38F;
         GL11.glScalef(var41, var41, var41);
         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
         var52 = 0.015625F;
         GL11.glScalef(var52, var52, var52);
         this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/misc/mapbg.png"));
         Tessellator var59 = Tessellator.instance;
         GL11.glNormal3f(0.0F, 0.0F, -1.0F);
         var59.startDrawingQuads();
         byte var65 = 7;
         var59.addVertexWithUV((double)(0 - var65), (double)(128 + var65), 0.0D, 0.0D, 1.0D);
         var59.addVertexWithUV((double)(128 + var65), (double)(128 + var65), 0.0D, 1.0D, 1.0D);
         var59.addVertexWithUV((double)(128 + var65), (double)(0 - var65), 0.0D, 1.0D, 0.0D);
         var59.addVertexWithUV((double)(0 - var65), (double)(0 - var65), 0.0D, 0.0D, 0.0D);
         var59.draw();
         MapData var67 = Item.map.getMapData(var14, this.mc.theWorld);
         this.mapItemRenderer.renderMap(this.mc.thePlayer, this.mc.renderEngine, var67);
         GL11.glPopMatrix();
      } else if(var14 != null) {
         GL11.glPushMatrix();
         float var19 = 0.8F;
         if(var3.func_35205_Y() > 0) {
            EnumAction var24 = var14.getItemUseAction();
            if(var24 == EnumAction.eat || var24 == EnumAction.drink) {
               float var35 = (float)var3.func_35205_Y() - var1 + 1.0F;
               float var46 = 1.0F - var35 / (float)var14.getMaxItemUseDuration();
               float var12 = 1.0F - var46;
               var12 = var12 * var12 * var12;
               var12 = var12 * var12 * var12;
               var12 = var12 * var12 * var12;
               float var13 = 1.0F - var12;
               GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(var35 / 4.0F * 3.1415927F) * 0.1F) * (float)((double)var46 > 0.2D?1:0), 0.0F);
               GL11.glTranslatef(var13 * 0.6F, -var13 * 0.5F, 0.0F);
               GL11.glRotatef(var13 * 90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(var13 * 10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(var13 * 30.0F, 0.0F, 0.0F, 1.0F);
            }
         } else {
            float var23 = var3.getSwingProgress(var1);
            float var34 = MathHelper.sin(var23 * 3.1415927F);
            float var45 = MathHelper.sin(MathHelper.sqrt_float(var23) * 3.1415927F);
            GL11.glTranslatef(-var45 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var23) * 3.1415927F * 2.0F) * 0.2F, -var34 * 0.2F);
         }

         GL11.glTranslatef(0.7F * var19, -0.65F * var19 - (1.0F - var2) * 0.6F, -0.9F * var19);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glEnable('\u803a');
         float var25 = var3.getSwingProgress(var1);
         float var36 = MathHelper.sin(var25 * var25 * 3.1415927F);
         float var47 = MathHelper.sin(MathHelper.sqrt_float(var25) * 3.1415927F);
         GL11.glRotatef(-var36 * 20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-var47 * 20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-var47 * 80.0F, 1.0F, 0.0F, 0.0F);
         var25 = 0.4F;
         GL11.glScalef(var25, var25, var25);
         if(var3.func_35205_Y() > 0) {
            EnumAction var37 = var14.getItemUseAction();
            if(var37 == EnumAction.block) {
               GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            } else if(var37 == EnumAction.bow) {
               GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
               GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
               GL11.glTranslatef(-0.9F, 0.2F, 0.0F);
               var47 = (float)var14.getMaxItemUseDuration() - ((float)var3.func_35205_Y() - var1 + 1.0F);
               float var11 = var47 / 20.0F;
               var11 = (var11 * var11 + var11 * 2.0F) / 3.0F;
               if(var11 > 1.0F) {
                  var11 = 1.0F;
               }

               if(var11 > 0.1F) {
                  GL11.glTranslatef(0.0F, MathHelper.sin((var47 - 0.1F) * 1.3F) * 0.01F * (var11 - 0.1F), 0.0F);
               }

               GL11.glTranslatef(0.0F, 0.0F, var11 * 0.1F);
               GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslatef(0.0F, 0.5F, 0.0F);
               float var63 = 1.0F + var11 * 0.2F;
               GL11.glScalef(1.0F, 1.0F, var63);
               GL11.glTranslatef(0.0F, -0.5F, 0.0F);
               GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            }
         }

         if(var14.getItem().shouldRotateAroundWhenRendering()) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         }

         if(var14.itemID == Item.potion.shiftedIndex) {
            this.renderItem(var3, var14, 0);
            GL11.glColor4f(var15, var15, var15, 1.0F);
            this.renderItem(var3, var14, 1);
         } else {
            this.renderItem(var3, var14, 0);
         }

         GL11.glPopMatrix();
      } else {
         GL11.glPushMatrix();
         float var20 = 0.8F;
         float var27 = var3.getSwingProgress(var1);
         float var38 = MathHelper.sin(var27 * 3.1415927F);
         float var49 = MathHelper.sin(MathHelper.sqrt_float(var27) * 3.1415927F);
         GL11.glTranslatef(-var49 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(var27) * 3.1415927F * 2.0F) * 0.4F, -var38 * 0.4F);
         GL11.glTranslatef(0.8F * var20, -0.75F * var20 - (1.0F - var2) * 0.6F, -0.9F * var20);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glEnable('\u803a');
         var27 = var3.getSwingProgress(var1);
         var38 = MathHelper.sin(var27 * var27 * 3.1415927F);
         var49 = MathHelper.sin(MathHelper.sqrt_float(var27) * 3.1415927F);
         GL11.glRotatef(var49 * 70.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-var38 * 20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTextureForDownloadableImage(this.mc.thePlayer.skinUrl, this.mc.thePlayer.getEntityTexture()));
         GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
         GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glTranslatef(5.6F, 0.0F, 0.0F);
         Render var29 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
         RenderPlayer var40 = (RenderPlayer)var29;
         var49 = 1.0F;
         GL11.glScalef(var49, var49, var49);
         var40.drawFirstPersonHand();
         GL11.glPopMatrix();
      }

      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
   }

   public void renderOverlays(float var1) {
      GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
      if(this.mc.thePlayer.isBurning()) {
         int var2 = this.mc.renderEngine.getTexture("/terrain.png");
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, var2);
         this.renderFireInFirstPerson(var1);
      }

      if(this.mc.thePlayer.isEntityInsideOpaqueBlock()) {
         int var14 = MathHelper.floor_double(this.mc.thePlayer.posX);
         int var3 = MathHelper.floor_double(this.mc.thePlayer.posY);
         int var4 = MathHelper.floor_double(this.mc.thePlayer.posZ);
         int var5 = this.mc.renderEngine.getTexture("/terrain.png");
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, var5);
         int var6 = this.mc.theWorld.getBlockId(var14, var3, var4);
         if(this.mc.theWorld.isBlockNormalCube(var14, var3, var4)) {
            this.renderInsideOfBlock(var1, Block.blocksList[var6].getBlockTextureFromSide(2));
         } else {
            for(int var7 = 0; var7 < 8; ++var7) {
               float var8 = ((float)((var7 >> 0) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
               float var9 = ((float)((var7 >> 1) % 2) - 0.5F) * this.mc.thePlayer.height * 0.2F;
               float var10 = ((float)((var7 >> 2) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
               int var11 = MathHelper.floor_float((float)var14 + var8);
               int var12 = MathHelper.floor_float((float)var3 + var9);
               int var13 = MathHelper.floor_float((float)var4 + var10);
               if(this.mc.theWorld.isBlockNormalCube(var11, var12, var13)) {
                  var6 = this.mc.theWorld.getBlockId(var11, var12, var13);
               }
            }
         }

         if(Block.blocksList[var6] != null) {
            this.renderInsideOfBlock(var1, Block.blocksList[var6].getBlockTextureFromSide(2));
         }
      }

      if(this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
         int var15 = this.mc.renderEngine.getTexture("/misc/water.png");
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, var15);
         this.renderWarpedTextureOverlay(var1);
      }

      GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
   }

   private void renderInsideOfBlock(float var1, int var2) {
      Tessellator var3 = Tessellator.instance;
      this.mc.thePlayer.getEntityBrightness(var1);
      float var4 = 0.1F;
      GL11.glColor4f(var4, var4, var4, 0.5F);
      GL11.glPushMatrix();
      float var5 = -1.0F;
      float var6 = 1.0F;
      float var7 = -1.0F;
      float var8 = 1.0F;
      float var9 = -0.5F;
      float var10 = 0.0078125F;
      float var11 = (float)(var2 % 16) / 256.0F - var10;
      float var12 = ((float)(var2 % 16) + 15.99F) / 256.0F + var10;
      float var13 = (float)(var2 / 16) / 256.0F - var10;
      float var14 = ((float)(var2 / 16) + 15.99F) / 256.0F + var10;
      var3.startDrawingQuads();
      var3.addVertexWithUV((double)var5, (double)var7, (double)var9, (double)var12, (double)var14);
      var3.addVertexWithUV((double)var6, (double)var7, (double)var9, (double)var11, (double)var14);
      var3.addVertexWithUV((double)var6, (double)var8, (double)var9, (double)var11, (double)var13);
      var3.addVertexWithUV((double)var5, (double)var8, (double)var9, (double)var12, (double)var13);
      var3.draw();
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderWarpedTextureOverlay(float var1) {
      Tessellator var2 = Tessellator.instance;
      float var3 = this.mc.thePlayer.getEntityBrightness(var1);
      GL11.glColor4f(var3, var3, var3, 0.5F);
      GL11.glEnable(3042 /*GL_BLEND*/);
      GL11.glBlendFunc(770, 771);
      GL11.glPushMatrix();
      float var4 = 4.0F;
      float var5 = -1.0F;
      float var6 = 1.0F;
      float var7 = -1.0F;
      float var8 = 1.0F;
      float var9 = -0.5F;
      float var10 = -this.mc.thePlayer.rotationYaw / 64.0F;
      float var11 = this.mc.thePlayer.rotationPitch / 64.0F;
      var2.startDrawingQuads();
      var2.addVertexWithUV((double)var5, (double)var7, (double)var9, (double)(var4 + var10), (double)(var4 + var11));
      var2.addVertexWithUV((double)var6, (double)var7, (double)var9, (double)(0.0F + var10), (double)(var4 + var11));
      var2.addVertexWithUV((double)var6, (double)var8, (double)var9, (double)(0.0F + var10), (double)(0.0F + var11));
      var2.addVertexWithUV((double)var5, (double)var8, (double)var9, (double)(var4 + var10), (double)(0.0F + var11));
      var2.draw();
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042 /*GL_BLEND*/);
   }

   private void renderFireInFirstPerson(float var1) {
      Tessellator var2 = Tessellator.instance;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
      GL11.glEnable(3042 /*GL_BLEND*/);
      GL11.glBlendFunc(770, 771);
      float var3 = 1.0F;

      for(int var4 = 0; var4 < 2; ++var4) {
         GL11.glPushMatrix();
         int var5 = Block.fire.blockIndexInTexture + var4 * 16;
         int var6 = (var5 & 15) << 4;
         int var7 = var5 & 240;
         float var8 = (float)var6 / 256.0F;
         float var9 = ((float)var6 + 15.99F) / 256.0F;
         float var10 = (float)var7 / 256.0F;
         float var11 = ((float)var7 + 15.99F) / 256.0F;
         float var12 = (0.0F - var3) / 2.0F;
         float var13 = var12 + var3;
         float var14 = 0.0F - var3 / 2.0F;
         float var15 = var14 + var3;
         float var16 = -0.5F;
         GL11.glTranslatef((float)(-(var4 * 2 - 1)) * 0.24F, -0.3F, 0.0F);
         GL11.glRotatef((float)(var4 * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
         var2.startDrawingQuads();
         var2.addVertexWithUV((double)var12, (double)var14, (double)var16, (double)var9, (double)var11);
         var2.addVertexWithUV((double)var13, (double)var14, (double)var16, (double)var8, (double)var11);
         var2.addVertexWithUV((double)var13, (double)var15, (double)var16, (double)var8, (double)var10);
         var2.addVertexWithUV((double)var12, (double)var15, (double)var16, (double)var9, (double)var10);
         var2.draw();
         GL11.glPopMatrix();
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042 /*GL_BLEND*/);
   }

   public void updateEquippedItem() {
      this.prevEquippedProgress = this.equippedProgress;
      EntityPlayerSP var1 = this.mc.thePlayer;
      ItemStack var2 = var1.inventory.getCurrentItem();
      boolean var4 = this.field_20099_f == var1.inventory.currentItem && var2 == this.itemToRender;
      if(this.itemToRender == null && var2 == null) {
         var4 = true;
      }

      if(var2 != null && this.itemToRender != null && var2 != this.itemToRender && var2.itemID == this.itemToRender.itemID && var2.getItemDamage() == this.itemToRender.getItemDamage()) {
         this.itemToRender = var2;
         var4 = true;
      }

      float var5 = 0.4F;
      float var6 = var4?1.0F:0.0F;
      float var7 = var6 - this.equippedProgress;
      if(var7 < -var5) {
         var7 = -var5;
      }

      if(var7 > var5) {
         var7 = var5;
      }

      this.equippedProgress += var7;
      if(this.equippedProgress < 0.1F) {
         this.itemToRender = var2;
         this.field_20099_f = var1.inventory.currentItem;
      }

   }

   public void func_9449_b() {
      this.equippedProgress = 0.0F;
   }

   public void func_9450_c() {
      this.equippedProgress = 0.0F;
   }
}
