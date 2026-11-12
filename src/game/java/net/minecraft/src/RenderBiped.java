package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderLiving;
import org.lwjgl.opengl.GL11;

public class RenderBiped extends RenderLiving {
   protected ModelBiped modelBipedMain;
   protected float field_40296_d;

   public RenderBiped(ModelBiped var1, float var2) {
      this(var1, var2, 1.0F);
      this.modelBipedMain = var1;
   }

   public RenderBiped(ModelBiped var1, float var2, float var3) {
      super(var1, var2);
      this.modelBipedMain = var1;
      this.field_40296_d = var3;
   }

   protected void renderEquippedItems(EntityLiving var1, float var2) {
      super.renderEquippedItems(var1, var2);
      ItemStack var3 = var1.getHeldItem();
      if(var3 != null) {
         GL11.glPushMatrix();
         this.modelBipedMain.bipedRightArm.postRender(0.0625F);
         GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
         if(var3.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType())) {
            float var7 = 0.5F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            var7 = var7 * 0.75F;
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var7, -var7, var7);
         } else if(var3.itemID == Item.bow.shiftedIndex) {
            float var4 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var4, -var4, var4);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else if(Item.itemsList[var3.itemID].isFull3D()) {
            float var5 = 0.625F;
            GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
            GL11.glScalef(var5, -var5, var5);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else {
            float var6 = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(var6, var6, var6);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         }

         this.renderManager.itemRenderer.renderItem(var1, var3, 0);
         if(var3.itemID == Item.potion.shiftedIndex) {
            this.renderManager.itemRenderer.renderItem(var1, var3, 1);
         }

         GL11.glPopMatrix();
      }

   }
}
