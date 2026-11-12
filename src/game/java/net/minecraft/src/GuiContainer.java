package net.minecraft.src;

import java.util.List;
import net.minecraft.src.Container;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;
import net.minecraft.src.Slot;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public abstract class GuiContainer extends GuiScreen {
   protected static RenderItem itemRenderer = new RenderItem();
   protected int xSize = 176;
   protected int ySize = 166;
   public Container inventorySlots;
   protected int field_40216_e;
   protected int field_40215_f;

   public GuiContainer(Container var1) {
      this.inventorySlots = var1;
   }

   public void initGui() {
      super.initGui();
      this.mc.thePlayer.craftingInventory = this.inventorySlots;
      this.field_40216_e = (this.width - this.xSize) / 2;
      this.field_40215_f = (this.height - this.ySize) / 2;
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      int var4 = this.field_40216_e;
      int var5 = this.field_40215_f;
      this.drawGuiContainerBackgroundLayer(var3, var1, var2);
      RenderHelper.func_41089_c();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var4, (float)var5, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('\u803a');
      Slot var6 = null;
      int var7 = 240;
      short var8 = 240;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapEnabled, (float)var7 / 1.0F, (float)var8 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

      for(var7 = 0; var7 < this.inventorySlots.inventorySlots.size(); ++var7) {
         Slot var22 = (Slot)this.inventorySlots.inventorySlots.get(var7);
         this.drawSlotInventory(var22);
         if(this.getIsMouseOverSlot(var22, var1, var2)) {
            var6 = var22;
            GL11.glDisable(2896 /*GL_LIGHTING*/);
            GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
            int var9 = var22.xDisplayPosition;
            int var10 = var22.yDisplayPosition;
            this.drawGradientRect(var9, var10, var9 + 16, var10 + 16, -2130706433, -2130706433);
            GL11.glEnable(2896 /*GL_LIGHTING*/);
            GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
         }
      }

      InventoryPlayer var21 = this.mc.thePlayer.inventory;
      if(var21.getItemStack() != null) {
         GL11.glTranslatef(0.0F, 0.0F, 32.0F);
         this.zLevel = 200.0F;
         itemRenderer.field_40268_b = 200.0F;
         itemRenderer.renderItemIntoGUI(this.fontRenderer, this.mc.renderEngine, var21.getItemStack(), var1 - var4 - 8, var2 - var5 - 8);
         itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, var21.getItemStack(), var1 - var4 - 8, var2 - var5 - 8);
         this.zLevel = 0.0F;
         itemRenderer.field_40268_b = 0.0F;
      }

      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896 /*GL_LIGHTING*/);
      GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
      this.drawGuiContainerForegroundLayer();
      if(var21.getItemStack() == null && var6 != null && var6.getHasStack()) {
         ItemStack var23 = var6.getStack();
         List var24 = var23.func_40712_q();
         if(var24.size() > 0) {
            int var25 = 0;

            for(int var11 = 0; var11 < var24.size(); ++var11) {
               int var12 = this.fontRenderer.getStringWidth((String)var24.get(var11));
               if(var12 > var25) {
                  var25 = var12;
               }
            }

            int var26 = var1 - var4 + 12;
            int var27 = var2 - var5 - 12;
            int var14 = 8;
            if(var24.size() > 1) {
               var14 += 2 + (var24.size() - 1) * 10;
            }

            this.zLevel = 300.0F;
            itemRenderer.field_40268_b = 300.0F;
            int var15 = -267386864;
            this.drawGradientRect(var26 - 3, var27 - 4, var26 + var25 + 3, var27 - 3, var15, var15);
            this.drawGradientRect(var26 - 3, var27 + var14 + 3, var26 + var25 + 3, var27 + var14 + 4, var15, var15);
            this.drawGradientRect(var26 - 3, var27 - 3, var26 + var25 + 3, var27 + var14 + 3, var15, var15);
            this.drawGradientRect(var26 - 4, var27 - 3, var26 - 3, var27 + var14 + 3, var15, var15);
            this.drawGradientRect(var26 + var25 + 3, var27 - 3, var26 + var25 + 4, var27 + var14 + 3, var15, var15);
            int var16 = 1347420415;
            int var17 = (var16 & 16711422) >> 1 | var16 & -16777216;
            this.drawGradientRect(var26 - 3, var27 - 3 + 1, var26 - 3 + 1, var27 + var14 + 3 - 1, var16, var17);
            this.drawGradientRect(var26 + var25 + 2, var27 - 3 + 1, var26 + var25 + 3, var27 + var14 + 3 - 1, var16, var17);
            this.drawGradientRect(var26 - 3, var27 - 3, var26 + var25 + 3, var27 - 3 + 1, var16, var16);
            this.drawGradientRect(var26 - 3, var27 + var14 + 2, var26 + var25 + 3, var27 + var14 + 3, var17, var17);

            for(int var18 = 0; var18 < var24.size(); ++var18) {
               String var19 = (String)var24.get(var18);
               if(var18 == 0) {
                  var19 = "\u00a7" + Integer.toHexString(var23.func_40707_s().field_40535_e) + var19;
               } else {
                  var19 = "\u00a77" + var19;
               }

               this.fontRenderer.drawStringWithShadow(var19, var26, var27, -1);
               if(var18 == 0) {
                  var27 += 2;
               }

               var27 += 10;
            }

            this.zLevel = 0.0F;
            itemRenderer.field_40268_b = 0.0F;
         }
      }

      GL11.glPopMatrix();
      super.drawScreen(var1, var2, var3);
      GL11.glEnable(2896 /*GL_LIGHTING*/);
      GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
   }

   protected void drawGuiContainerForegroundLayer() {
   }

   protected abstract void drawGuiContainerBackgroundLayer(float var1, int var2, int var3);

   private void drawSlotInventory(Slot var1) {
      int var2 = var1.xDisplayPosition;
      int var3 = var1.yDisplayPosition;
      ItemStack var4 = var1.getStack();
      boolean var5 = false;
      this.zLevel = 100.0F;
      itemRenderer.field_40268_b = 100.0F;
      if(var4 == null) {
         int var8 = var1.getBackgroundIconIndex();
         if(var8 >= 0) {
            GL11.glDisable(2896 /*GL_LIGHTING*/);
            this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/gui/items.png"));
            this.drawTexturedModalRect(var2, var3, var8 % 16 * 16, var8 / 16 * 16, 16, 16);
            GL11.glEnable(2896 /*GL_LIGHTING*/);
            var5 = true;
         }
      }

      if(!var5) {
         itemRenderer.renderItemIntoGUI(this.fontRenderer, this.mc.renderEngine, var4, var2, var3);
         itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, var4, var2, var3);
      }

      itemRenderer.field_40268_b = 0.0F;
      this.zLevel = 0.0F;
      if(this == null) {
         this.zLevel = 100.0F;
         itemRenderer.field_40268_b = 100.0F;
         if(var4 == null) {
            int var6 = var1.getBackgroundIconIndex();
            if(var6 >= 0) {
               GL11.glDisable(2896 /*GL_LIGHTING*/);
               this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/gui/items.png"));
               this.drawTexturedModalRect(var2, var3, var6 % 16 * 16, var6 / 16 * 16, 16, 16);
               GL11.glEnable(2896 /*GL_LIGHTING*/);
               var5 = true;
            }
         }

         if(!var5) {
            itemRenderer.renderItemIntoGUI(this.fontRenderer, this.mc.renderEngine, var4, var2, var3);
            itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, var4, var2, var3);
         }

         itemRenderer.field_40268_b = 0.0F;
         this.zLevel = 0.0F;
      }

   }

   private Slot getSlotAtPosition(int var1, int var2) {
      for(int var3 = 0; var3 < this.inventorySlots.inventorySlots.size(); ++var3) {
         Slot var4 = (Slot)this.inventorySlots.inventorySlots.get(var3);
         if(this.getIsMouseOverSlot(var4, var1, var2)) {
            return var4;
         }
      }

      return null;
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      if(var3 == 0 || var3 == 1) {
         Slot var4 = this.getSlotAtPosition(var1, var2);
         int var5 = this.field_40216_e;
         int var6 = this.field_40215_f;
         boolean var7 = var1 < var5 || var2 < var6 || var1 >= var5 + this.xSize || var2 >= var6 + this.ySize;
         int var8 = -1;
         if(var4 != null) {
            var8 = var4.slotNumber;
         }

         if(var7) {
            var8 = -999;
         }

         if(var8 != -1) {
            boolean var9 = var8 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
            this.func_35309_a(var4, var8, var3, var9);
         }
      }

   }

   private boolean getIsMouseOverSlot(Slot var1, int var2, int var3) {
      int var4 = this.field_40216_e;
      int var5 = this.field_40215_f;
      var2 = var2 - var4;
      var3 = var3 - var5;
      return var2 >= var1.xDisplayPosition - 1 && var2 < var1.xDisplayPosition + 16 + 1 && var3 >= var1.yDisplayPosition - 1 && var3 < var1.yDisplayPosition + 16 + 1;
   }

   protected void func_35309_a(Slot var1, int var2, int var3, boolean var4) {
      if(var1 != null) {
         var2 = var1.slotNumber;
      }

      this.mc.playerController.windowClick(this.inventorySlots.windowId, var2, var3, var4, this.mc.thePlayer);
   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      if(var3 == 0) {
         ;
      }

   }

   protected void keyTyped(char var1, int var2) {
      if(var2 == 1 || var2 == this.mc.gameSettings.keyBindInventory.keyCode) {
         this.mc.thePlayer.closeScreen();
      }

   }

   public void onGuiClosed() {
      if(this.mc.thePlayer != null) {
         this.inventorySlots.onCraftGuiClosed(this.mc.thePlayer);
         this.mc.playerController.func_20086_a(this.inventorySlots.windowId, this.mc.thePlayer);
      }
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      if(!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead) {
         this.mc.thePlayer.closeScreen();
      }

   }
}
