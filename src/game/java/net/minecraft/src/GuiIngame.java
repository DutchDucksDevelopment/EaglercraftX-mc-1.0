package net.minecraft.src;

import net.lax1dude.eaglercraft.EagRuntime;
import net.peyton.java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.ChatLine;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.FoodStats;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiChat;
import net.minecraft.src.GuiSavingLevelString;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Potion;
import net.minecraft.src.RenderDragon;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class GuiIngame extends Gui {
   private static RenderItem itemRenderer = new RenderItem();
   private List chatMessageList = new ArrayList();
   private Random rand = new Random();
   private Minecraft mc;
   public String field_933_a = null;
   private int updateCounter = 0;
   private String recordPlaying = "";
   private int recordPlayingUpFor = 0;
   private boolean recordIsPlaying = false;
   public float damageGuiPartialTime;
   float prevVignetteBrightness = 1.0F;

   public GuiIngame(Minecraft var1) {
      this.mc = var1;
   }

   public void renderGameOverlay(float var1, boolean var2, int var3, int var4) {
      ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      FontRenderer var8 = this.mc.fontRenderer;
      this.mc.entityRenderer.setupOverlayRendering();
      GL11.glEnable(3042 /*GL_BLEND*/);
      if(Minecraft.isFancyGraphicsEnabled()) {
         this.renderVignette(this.mc.thePlayer.getEntityBrightness(var1), var6, var7);
      } else {
         GL11.glBlendFunc(770, 771);
      }

      ItemStack var9 = this.mc.thePlayer.inventory.armorItemInSlot(3);
      if(this.mc.gameSettings.thirdPersonView == 0 && var9 != null && var9.itemID == Block.pumpkin.blockID) {
         this.renderPumpkinBlur(var6, var7);
      }

      if(!this.mc.thePlayer.isPotionActive(Potion.potionConfusion)) {
         float var10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * var1;
         if(var10 > 0.0F) {
            this.renderPortalOverlay(var10, var6, var7);
         }
      }

      if(!this.mc.playerController.func_35643_e()) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("/gui/gui.png"));
         InventoryPlayer var31 = this.mc.thePlayer.inventory;
         this.zLevel = -90.0F;
         this.drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
         this.drawTexturedModalRect(var6 / 2 - 91 - 1 + var31.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("/gui/icons.png"));
         GL11.glEnable(3042 /*GL_BLEND*/);
         GL11.glBlendFunc(775, 769);
         this.drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
         GL11.glDisable(3042 /*GL_BLEND*/);
         boolean var11 = this.mc.thePlayer.heartsLife / 3 % 2 == 1;
         if(this.mc.thePlayer.heartsLife < 10) {
            var11 = false;
         }

         int var12 = this.mc.thePlayer.getEntityHealth();
         int var13 = this.mc.thePlayer.prevHealth;
         this.rand.setSeed((long)(this.updateCounter * 312871));
         boolean var14 = false;
         FoodStats var15 = this.mc.thePlayer.getFoodStats();
         int var16 = var15.getFoodLevel();
         int var17 = var15.getPrevFoodLevel();
         this.func_41039_c();
         if(this.mc.playerController.shouldDrawHUD()) {
            int var18 = var6 / 2 - 91;
            int var19 = var6 / 2 + 91;
            int var20 = this.mc.thePlayer.xpBarCap();
            if(var20 > 0) {
               short var21 = 182;
               int var22 = (int)(this.mc.thePlayer.currentXP * (float)(var21 + 1));
               int var23 = var7 - 32 + 3;
               this.drawTexturedModalRect(var18, var23, 0, 64, var21, 5);
               if(var22 > 0) {
                  this.drawTexturedModalRect(var18, var23, 0, 69, var22, 5);
               }
            }

            int var72 = var7 - 39;
            int var74 = var72 - 10;
            int var76 = this.mc.thePlayer.getPlayerArmorValue();
            int var24 = -1;
            if(this.mc.thePlayer.isPotionActive(Potion.potionRegeneration)) {
               var24 = this.updateCounter % 25;
            }

            for(int var25 = 0; var25 < 10; ++var25) {
               if(var76 > 0) {
                  int var26 = var18 + var25 * 8;
                  if(var25 * 2 + 1 < var76) {
                     this.drawTexturedModalRect(var26, var74, 34, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 == var76) {
                     this.drawTexturedModalRect(var26, var74, 25, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 > var76) {
                     this.drawTexturedModalRect(var26, var74, 16, 9, 9, 9);
                  }
               }

               int var85 = 16;
               if(this.mc.thePlayer.isPotionActive(Potion.potionPoison)) {
                  var85 += 36;
               }

               byte var27 = 0;
               if(var11) {
                  var27 = 1;
               }

               int var28 = var18 + var25 * 8;
               int var29 = var72;
               if(var12 <= 4) {
                  var29 = var72 + this.rand.nextInt(2);
               }

               if(var25 == var24) {
                  var29 -= 2;
               }

               byte var30 = 0;
               if(this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
                  var30 = 5;
               }

               this.drawTexturedModalRect(var28, var29, 16 + var27 * 9, 9 * var30, 9, 9);
               if(var11) {
                  if(var25 * 2 + 1 < var13) {
                     this.drawTexturedModalRect(var28, var29, var85 + 54, 9 * var30, 9, 9);
                  }

                  if(var25 * 2 + 1 == var13) {
                     this.drawTexturedModalRect(var28, var29, var85 + 63, 9 * var30, 9, 9);
                  }
               }

               if(var25 * 2 + 1 < var12) {
                  this.drawTexturedModalRect(var28, var29, var85 + 36, 9 * var30, 9, 9);
               }

               if(var25 * 2 + 1 == var12) {
                  this.drawTexturedModalRect(var28, var29, var85 + 45, 9 * var30, 9, 9);
               }
            }

            for(int var80 = 0; var80 < 10; ++var80) {
               int var86 = var72;
               int var88 = 16;
               byte var90 = 0;
               if(this.mc.thePlayer.isPotionActive(Potion.potionHunger)) {
                  var88 += 36;
                  var90 = 13;
               }

               if(this.mc.thePlayer.getFoodStats().getFoodSaturationLevel() <= 0.0F && this.updateCounter % (var16 * 3 + 1) == 0) {
                  var86 = var72 + (this.rand.nextInt(3) - 1);
               }

               if(var14) {
                  var90 = 1;
               }

               int var91 = var19 - var80 * 8 - 9;
               this.drawTexturedModalRect(var91, var86, 16 + var90 * 9, 27, 9, 9);
               if(var14) {
                  if(var80 * 2 + 1 < var17) {
                     this.drawTexturedModalRect(var91, var86, var88 + 54, 27, 9, 9);
                  }

                  if(var80 * 2 + 1 == var17) {
                     this.drawTexturedModalRect(var91, var86, var88 + 63, 27, 9, 9);
                  }
               }

               if(var80 * 2 + 1 < var16) {
                  this.drawTexturedModalRect(var91, var86, var88 + 36, 27, 9, 9);
               }

               if(var80 * 2 + 1 == var16) {
                  this.drawTexturedModalRect(var91, var86, var88 + 45, 27, 9, 9);
               }
            }

            if(this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
               int var81 = (int)Math.ceil((double)(this.mc.thePlayer.func_41001_Z() - 2) * 10.0D / 300.0D);
               int var87 = (int)Math.ceil((double)this.mc.thePlayer.func_41001_Z() * 10.0D / 300.0D) - var81;

               for(int var89 = 0; var89 < var81 + var87; ++var89) {
                  if(var89 < var81) {
                     this.drawTexturedModalRect(var19 - var89 * 8 - 9, var74, 16, 18, 9, 9);
                  } else {
                     this.drawTexturedModalRect(var19 - var89 * 8 - 9, var74, 25, 18, 9, 9);
                  }
               }
            }
         }

         GL11.glDisable(3042 /*GL_BLEND*/);
         GL11.glEnable('\u803a');
         RenderHelper.func_41089_c();

         for(int var63 = 0; var63 < 9; ++var63) {
            int var68 = var6 / 2 - 90 + var63 * 20 + 2;
            int var70 = var7 - 16 - 3;
            this.renderInventorySlot(var63, var68, var70, var1);
         }

         RenderHelper.disableStandardItemLighting();
         GL11.glDisable('\u803a');
      }

      if(this.mc.thePlayer.getSleepTimer() > 0) {
         GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
         GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
         int var32 = this.mc.thePlayer.getSleepTimer();
         float var37 = (float)var32 / 100.0F;
         if(var37 > 1.0F) {
            var37 = 1.0F - (float)(var32 - 100) / 10.0F;
         }

         int var41 = (int)(220.0F * var37) << 24 | 1052704;
         this.drawRect(0, 0, var6, var7, var41);
         GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
         GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
      }

      if(this.mc.playerController.func_35642_f() && this.mc.thePlayer.playerLevel > 0) {
         boolean var33 = false;
         int var38 = var33?16777215:8453920;
         String var42 = "" + this.mc.thePlayer.playerLevel;
         int var47 = (var6 - var8.getStringWidth(var42)) / 2;
         int var53 = var7 - 31 - 4;
         var8.drawString(var42, var47 + 1, var53, 0);
         var8.drawString(var42, var47 - 1, var53, 0);
         var8.drawString(var42, var47, var53 + 1, 0);
         var8.drawString(var42, var47, var53 - 1, 0);
         var8.drawString(var42, var47, var53, var38);
      }

      if(this.mc.gameSettings.showDebugInfo) {
         GL11.glPushMatrix();
         if(Minecraft.hasPaidCheckTime > 0L) {
            GL11.glTranslatef(0.0F, 32.0F, 0.0F);
         }

         var8.drawStringWithShadow("Minecraft 1.0.0 (" + this.mc.debug + ")", 2, 2, 16777215);
         var8.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 16777215);
         var8.drawStringWithShadow(this.mc.func_6262_n(), 2, 22, 16777215);
         var8.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 16777215);
         var8.drawStringWithShadow(this.mc.func_21002_o(), 2, 42, 16777215);
         long var34 = EagRuntime.maxMemory();
         long var43 = EagRuntime.totalMemory();
         long var54 = EagRuntime.freeMemory();
         long var58 = var43 - var54;
         String var64 = "Used memory: " + var58 * 100L / var34 + "% (" + var58 / 1024L / 1024L + "MB) of " + var34 / 1024L / 1024L + "MB";
         this.drawString(var8, var64, var6 - var8.getStringWidth(var64) - 2, 2, 14737632);
         var64 = "Allocated memory: " + var43 * 100L / var34 + "% (" + var43 / 1024L / 1024L + "MB)";
         this.drawString(var8, var64, var6 - var8.getStringWidth(var64) - 2, 12, 14737632);
         this.drawString(var8, "x: " + this.mc.thePlayer.posX, 2, 64, 14737632);
         this.drawString(var8, "y: " + this.mc.thePlayer.posY, 2, 72, 14737632);
         this.drawString(var8, "z: " + this.mc.thePlayer.posZ, 2, 80, 14737632);
         this.drawString(var8, "f: " + (MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3), 2, 88, 14737632);
         this.drawString(var8, "Seed: " + this.mc.theWorld.getWorldSeed(), 2, 104, 14737632);
         GL11.glPopMatrix();
      }

      if(this.recordPlayingUpFor > 0) {
         float var35 = (float)this.recordPlayingUpFor - var1;
         int var39 = (int)(var35 * 256.0F / 20.0F);
         if(var39 > 255) {
            var39 = 255;
         }

         if(var39 > 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 48), 0.0F);
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glBlendFunc(770, 771);
            int var44 = 16777215;
            if(this.recordIsPlaying) {
               var44 = Color.HSBtoRGB(var35 / 50.0F, 0.7F, 0.6F) & 16777215;
            }

            var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var44 + (var39 << 24));
            GL11.glDisable(3042 /*GL_BLEND*/);
            GL11.glPopMatrix();
         }
      }

      byte var36 = 10;
      boolean var40 = false;
      if(this.mc.currentScreen instanceof GuiChat) {
         var36 = 20;
         var40 = true;
      }

      GL11.glEnable(3042 /*GL_BLEND*/);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, (float)(var7 - 48), 0.0F);

      for(int var45 = 0; var45 < this.chatMessageList.size() && var45 < var36; ++var45) {
         if(((ChatLine)this.chatMessageList.get(var45)).updateCounter < 200 || var40) {
            double var48 = (double)((ChatLine)this.chatMessageList.get(var45)).updateCounter / 200.0D;
            var48 = 1.0D - var48;
            var48 = var48 * 10.0D;
            if(var48 < 0.0D) {
               var48 = 0.0D;
            }

            if(var48 > 1.0D) {
               var48 = 1.0D;
            }

            var48 = var48 * var48;
            int var56 = (int)(255.0D * var48);
            if(var40) {
               var56 = 255;
            }

            if(var56 > 0) {
               byte var59 = 2;
               int var61 = -var45 * 9;
               String var66 = ((ChatLine)this.chatMessageList.get(var45)).message;
               this.drawRect(var59, var61 - 1, var59 + 320, var61 + 8, var56 / 2 << 24);
               GL11.glEnable(3042 /*GL_BLEND*/);
               var8.drawStringWithShadow(var66, var59, var61, 16777215 + (var56 << 24));
            }
         }
      }

      GL11.glPopMatrix();
      if(this.mc.thePlayer instanceof EntityClientPlayerMP && this.mc.gameSettings.keyBindPlayerList.pressed) {
         NetClientHandler var46 = ((EntityClientPlayerMP)this.mc.thePlayer).sendQueue;
         List var52 = var46.field_35786_c;
         int var55 = var46.field_35785_d;
         int var57 = var55;

         int var60;
         for(var60 = 1; var57 > 20; var57 = (var55 + var60 - 1) / var60) {
            ++var60;
         }

         int var62 = 300 / var60;
         if(var62 > 150) {
            var62 = 150;
         }

         int var67 = (var6 - var60 * var62) / 2;
         byte var69 = 10;
         this.drawRect(var67 - 1, var69 - 1, var67 + var62 * var60, var69 + 9 * var57, Integer.MIN_VALUE);

         for(int var71 = 0; var71 < var55; ++var71) {
            int var73 = var67 + var71 % var60 * var62;
            int var75 = var69 + var71 / var60 * 9;
            this.drawRect(var73, var75, var73 + var62 - 1, var75 + 8, 553648127);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
            if(var71 < var52.size()) {
               GuiSavingLevelString var77 = (GuiSavingLevelString)var52.get(var71);
               var8.drawStringWithShadow(var77.field_35624_a, var73, var75, 16777215);
               this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/gui/icons.png"));
               byte var78 = 0;
               byte var82 = 0;
               var78 = 0;
               var82 = 0;
               if(var77.field_35623_b < 0) {
                  var82 = 5;
               } else if(var77.field_35623_b < 150) {
                  var82 = 0;
               } else if(var77.field_35623_b < 300) {
                  var82 = 1;
               } else if(var77.field_35623_b < 600) {
                  var82 = 2;
               } else if(var77.field_35623_b < 1000) {
                  var82 = 3;
               } else {
                  var82 = 4;
               }

               this.zLevel += 100.0F;
               this.drawTexturedModalRect(var73 + var62 - 12, var75, 0 + var78 * 10, 176 + var82 * 8, 10, 8);
               this.zLevel -= 100.0F;
            }
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896 /*GL_LIGHTING*/);
      GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
   }

   private void func_41039_c() {
      if(RenderDragon.field_41038_a != null) {
         EntityDragon var1 = RenderDragon.field_41038_a;
         RenderDragon.field_41038_a = null;
         FontRenderer var2 = this.mc.fontRenderer;
         ScaledResolution var3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
         int var4 = var3.getScaledWidth();
         short var5 = 182;
         int var6 = var4 / 2 - var5 / 2;
         int var7 = (int)((float)var1.func_41010_ax() / (float)var1.getMaxHealth() * (float)(var5 + 1));
         byte var8 = 12;
         this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);
         this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);
         if(var7 > 0) {
            this.drawTexturedModalRect(var6, var8, 0, 79, var7, 5);
         }

         String var9 = "Boss health";
         var2.drawStringWithShadow(var9, var4 / 2 - var2.getStringWidth(var9) / 2, var8 - 10, 16711935);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("/gui/icons.png"));
      }
   }

   private void renderPumpkinBlur(int var1, int var2) {
      GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
      GL11.glDepthMask(false);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
      GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
      Tessellator var3 = Tessellator.instance;
      var3.startDrawingQuads();
      var3.addVertexWithUV(0.0D, (double)var2, -90.0D, 0.0D, 1.0D);
      var3.addVertexWithUV((double)var1, (double)var2, -90.0D, 1.0D, 1.0D);
      var3.addVertexWithUV((double)var1, 0.0D, -90.0D, 1.0D, 0.0D);
      var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
      var3.draw();
      GL11.glDepthMask(true);
      GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
      GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderVignette(float var1, int var2, int var3) {
      var1 = 1.0F - var1;
      if(var1 < 0.0F) {
         var1 = 0.0F;
      }

      if(var1 > 1.0F) {
         var1 = 1.0F;
      }

      this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(var1 - this.prevVignetteBrightness) * 0.01D);
      GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
      GL11.glDepthMask(false);
      GL11.glBlendFunc(0, 769);
      GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
      GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
      Tessellator var4 = Tessellator.instance;
      var4.startDrawingQuads();
      var4.addVertexWithUV(0.0D, (double)var3, -90.0D, 0.0D, 1.0D);
      var4.addVertexWithUV((double)var2, (double)var3, -90.0D, 1.0D, 1.0D);
      var4.addVertexWithUV((double)var2, 0.0D, -90.0D, 1.0D, 0.0D);
      var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
      var4.draw();
      GL11.glDepthMask(true);
      GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glBlendFunc(770, 771);
   }

   private void renderPortalOverlay(float var1, int var2, int var3) {
      if(var1 < 1.0F) {
         var1 = var1 * var1;
         var1 = var1 * var1;
         var1 = var1 * 0.8F + 0.2F;
      }

      GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
      GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
      GL11.glDepthMask(false);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, var1);
      GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, this.mc.renderEngine.getTexture("/terrain.png"));
      float var4 = (float)(Block.portal.blockIndexInTexture % 16) / 16.0F;
      float var5 = (float)(Block.portal.blockIndexInTexture / 16) / 16.0F;
      float var6 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16.0F;
      float var7 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16.0F;
      Tessellator var8 = Tessellator.instance;
      var8.startDrawingQuads();
      var8.addVertexWithUV(0.0D, (double)var3, -90.0D, (double)var4, (double)var7);
      var8.addVertexWithUV((double)var2, (double)var3, -90.0D, (double)var6, (double)var7);
      var8.addVertexWithUV((double)var2, 0.0D, -90.0D, (double)var6, (double)var5);
      var8.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)var4, (double)var5);
      var8.draw();
      GL11.glDepthMask(true);
      GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
      GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderInventorySlot(int var1, int var2, int var3, float var4) {
      ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[var1];
      if(var5 != null) {
         float var6 = (float)var5.animationsToGo - var4;
         if(var6 > 0.0F) {
            GL11.glPushMatrix();
            float var7 = 1.0F + var6 / 5.0F;
            GL11.glTranslatef((float)(var2 + 8), (float)(var3 + 12), 0.0F);
            GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef((float)(-(var2 + 8)), (float)(-(var3 + 12)), 0.0F);
         }

         itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, var2, var3);
         if(var6 > 0.0F) {
            GL11.glPopMatrix();
         }

         itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, var2, var3);
      }
   }

   public void updateTick() {
      if(this.recordPlayingUpFor > 0) {
         --this.recordPlayingUpFor;
      }

      ++this.updateCounter;

      for(int var1 = 0; var1 < this.chatMessageList.size(); ++var1) {
         ++((ChatLine)this.chatMessageList.get(var1)).updateCounter;
      }

   }

   public void clearChatMessages() {
      this.chatMessageList.clear();
   }

   public void addChatMessage(String var1) {
      while(this.mc.fontRenderer.getStringWidth(var1) > 320) {
         int var2;
         for(var2 = 1; var2 < var1.length() && this.mc.fontRenderer.getStringWidth(var1.substring(0, var2 + 1)) <= 320; ++var2) {
            ;
         }

         this.addChatMessage(var1.substring(0, var2));
         var1 = var1.substring(var2);
      }

      this.chatMessageList.add(0, new ChatLine(var1));

      while(this.chatMessageList.size() > 50) {
         this.chatMessageList.remove(this.chatMessageList.size() - 1);
      }

   }

   public void setRecordPlayingMessage(String var1) {
      this.recordPlaying = "Now playing: " + var1;
      this.recordPlayingUpFor = 60;
      this.recordIsPlaying = true;
   }

   public void addChatMessageTranslate(String var1) {
      StringTranslate var2 = StringTranslate.getInstance();
      String var3 = var2.translateKey(var1);
      this.addChatMessage(var3);
   }
}
