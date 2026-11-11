package net.minecraft.src;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelDragon extends ModelBase {
   private ModelRenderer field_40314_a;
   private ModelRenderer field_40312_b;
   private ModelRenderer field_40313_c;
   private ModelRenderer field_40310_d;
   private ModelRenderer field_40311_e;
   private ModelRenderer field_40308_f;
   private ModelRenderer field_40309_g;
   private ModelRenderer field_40315_n;
   private ModelRenderer field_40316_o;
   private ModelRenderer field_40320_p;
   private ModelRenderer field_40319_q;
   private ModelRenderer field_40318_r;
   private float field_40317_s;

   public ModelDragon(float var1) {
      this.textureWidth = 256;
      this.textureHeight = 256;
      this.setTextureOffset("body.body", 0, 0);
      this.setTextureOffset("wing.skin", -56, 88);
      this.setTextureOffset("wingtip.skin", -56, 144);
      this.setTextureOffset("rearleg.main", 0, 0);
      this.setTextureOffset("rearfoot.main", 112, 0);
      this.setTextureOffset("rearlegtip.main", 196, 0);
      this.setTextureOffset("head.upperhead", 112, 30);
      this.setTextureOffset("wing.bone", 112, 88);
      this.setTextureOffset("head.upperlip", 176, 44);
      this.setTextureOffset("jaw.jaw", 176, 65);
      this.setTextureOffset("frontleg.main", 112, 104);
      this.setTextureOffset("wingtip.bone", 112, 136);
      this.setTextureOffset("frontfoot.main", 144, 104);
      this.setTextureOffset("neck.box", 192, 104);
      this.setTextureOffset("frontlegtip.main", 226, 138);
      this.setTextureOffset("body.scale", 220, 53);
      this.setTextureOffset("head.scale", 0, 0);
      this.setTextureOffset("neck.scale", 48, 0);
      this.setTextureOffset("head.nostril", 112, 0);
      float var2 = -16.0F;
      this.field_40314_a = new ModelRenderer(this, "head");
      this.field_40314_a.addBox("upperlip", -6.0F, -1.0F, -8.0F + var2, 12, 5, 16);
      this.field_40314_a.addBox("upperhead", -8.0F, -8.0F, 6.0F + var2, 16, 16, 16);
      this.field_40314_a.mirror = true;
      this.field_40314_a.addBox("scale", -5.0F, -12.0F, 12.0F + var2, 2, 4, 6);
      this.field_40314_a.addBox("nostril", -5.0F, -3.0F, -6.0F + var2, 2, 2, 4);
      this.field_40314_a.mirror = false;
      this.field_40314_a.addBox("scale", 3.0F, -12.0F, 12.0F + var2, 2, 4, 6);
      this.field_40314_a.addBox("nostril", 3.0F, -3.0F, -6.0F + var2, 2, 2, 4);
      this.field_40313_c = new ModelRenderer(this, "jaw");
      this.field_40313_c.setRotationPoint(0.0F, 4.0F, 8.0F + var2);
      this.field_40313_c.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
      this.field_40314_a.addChild(this.field_40313_c);
      this.field_40312_b = new ModelRenderer(this, "neck");
      this.field_40312_b.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
      this.field_40312_b.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
      this.field_40310_d = new ModelRenderer(this, "body");
      this.field_40310_d.setRotationPoint(0.0F, 4.0F, 8.0F);
      this.field_40310_d.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
      this.field_40310_d.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
      this.field_40310_d.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
      this.field_40310_d.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
      this.field_40319_q = new ModelRenderer(this, "wing");
      this.field_40319_q.setRotationPoint(-12.0F, 5.0F, 2.0F);
      this.field_40319_q.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      this.field_40319_q.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.field_40318_r = new ModelRenderer(this, "wingtip");
      this.field_40318_r.setRotationPoint(-56.0F, 0.0F, 0.0F);
      this.field_40318_r.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      this.field_40318_r.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.field_40319_q.addChild(this.field_40318_r);
      this.field_40308_f = new ModelRenderer(this, "frontleg");
      this.field_40308_f.setRotationPoint(-12.0F, 20.0F, 2.0F);
      this.field_40308_f.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
      this.field_40315_n = new ModelRenderer(this, "frontlegtip");
      this.field_40315_n.setRotationPoint(0.0F, 20.0F, -1.0F);
      this.field_40315_n.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
      this.field_40308_f.addChild(this.field_40315_n);
      this.field_40320_p = new ModelRenderer(this, "frontfoot");
      this.field_40320_p.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.field_40320_p.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
      this.field_40315_n.addChild(this.field_40320_p);
      this.field_40311_e = new ModelRenderer(this, "rearleg");
      this.field_40311_e.setRotationPoint(-16.0F, 16.0F, 42.0F);
      this.field_40311_e.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
      this.field_40309_g = new ModelRenderer(this, "rearlegtip");
      this.field_40309_g.setRotationPoint(0.0F, 32.0F, -4.0F);
      this.field_40309_g.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
      this.field_40311_e.addChild(this.field_40309_g);
      this.field_40316_o = new ModelRenderer(this, "rearfoot");
      this.field_40316_o.setRotationPoint(0.0F, 31.0F, 4.0F);
      this.field_40316_o.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
      this.field_40309_g.addChild(this.field_40316_o);
   }

   public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4) {
      this.field_40317_s = var4;
   }

   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      GL11.glPushMatrix();
      EntityDragon var8 = (EntityDragon)var1;
      float var9 = var8.field_40173_aw + (var8.field_40172_ax - var8.field_40173_aw) * this.field_40317_s;
      this.field_40313_c.rotateAngleX = (float)(Math.sin((double)(var9 * 3.1415927F * 2.0F)) + 1.0D) * 0.2F;
      float var10 = (float)(Math.sin((double)(var9 * 3.1415927F * 2.0F - 1.0F)) + 1.0D);
      var10 = (var10 * var10 * 1.0F + var10 * 2.0F) * 0.05F;
      GL11.glTranslatef(0.0F, var10 - 2.0F, -3.0F);
      GL11.glRotatef(var10 * 2.0F, 1.0F, 0.0F, 0.0F);
      float var11 = -30.0F;
      float var12 = 22.0F;
      float var13 = 0.0F;
      float var14 = 1.5F;
      double[] var15 = var8.func_40160_a(6, this.field_40317_s);
      float var16 = this.func_40307_a(var8.func_40160_a(5, this.field_40317_s)[0] - var8.func_40160_a(10, this.field_40317_s)[0]);
      float var17 = this.func_40307_a(var8.func_40160_a(5, this.field_40317_s)[0] + (double)(var16 / 2.0F));
      var11 = var11 + 2.0F;
      float var18 = 0.0F;
      float var19 = var9 * 3.1415927F * 2.0F;
      var11 = 20.0F;
      var12 = -12.0F;

      for(int var20 = 0; var20 < 5; ++var20) {
         double[] var21 = var8.func_40160_a(5 - var20, this.field_40317_s);
         var18 = (float)Math.cos((double)((float)var20 * 0.45F + var19)) * 0.15F;
         this.field_40312_b.rotateAngleY = this.func_40307_a(var21[0] - var15[0]) * 3.1415927F / 180.0F * var14;
         this.field_40312_b.rotateAngleX = var18 + (float)(var21[1] - var15[1]) * 3.1415927F / 180.0F * var14 * 5.0F;
         this.field_40312_b.rotateAngleZ = -this.func_40307_a(var21[0] - (double)var17) * 3.1415927F / 180.0F * var14;
         this.field_40312_b.rotationPointY = var11;
         this.field_40312_b.rotationPointZ = var12;
         this.field_40312_b.rotationPointX = var13;
         var11 = (float)((double)var11 + Math.sin((double)this.field_40312_b.rotateAngleX) * 10.0D);
         var12 = (float)((double)var12 - Math.cos((double)this.field_40312_b.rotateAngleY) * Math.cos((double)this.field_40312_b.rotateAngleX) * 10.0D);
         var13 = (float)((double)var13 - Math.sin((double)this.field_40312_b.rotateAngleY) * Math.cos((double)this.field_40312_b.rotateAngleX) * 10.0D);
         this.field_40312_b.render(var7);
      }

      this.field_40314_a.rotationPointY = var11;
      this.field_40314_a.rotationPointZ = var12;
      this.field_40314_a.rotationPointX = var13;
      double[] var34 = var8.func_40160_a(0, this.field_40317_s);
      this.field_40314_a.rotateAngleY = this.func_40307_a(var34[0] - var15[0]) * 3.1415927F / 180.0F * 1.0F;
      this.field_40314_a.rotateAngleZ = -this.func_40307_a(var34[0] - (double)var17) * 3.1415927F / 180.0F * 1.0F;
      this.field_40314_a.render(var7);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-var16 * var14 * 1.0F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(0.0F, -1.0F, 0.0F);
      this.field_40310_d.rotateAngleZ = 0.0F;
      this.field_40310_d.render(var7);

      for(int var36 = 0; var36 < 2; ++var36) {
         GL11.glEnable(2884 /*GL_CULL_FACE*/);
         float var22 = var9 * 3.1415927F * 2.0F;
         this.field_40319_q.rotateAngleX = 0.125F - (float)Math.cos((double)var22) * 0.2F;
         this.field_40319_q.rotateAngleY = 0.25F;
         this.field_40319_q.rotateAngleZ = (float)(Math.sin((double)var22) + 0.125D) * 0.8F;
         this.field_40318_r.rotateAngleZ = -((float)(Math.sin((double)(var22 + 2.0F)) + 0.5D)) * 0.75F;
         this.field_40311_e.rotateAngleX = 1.0F + var10 * 0.1F;
         this.field_40309_g.rotateAngleX = 0.5F + var10 * 0.1F;
         this.field_40316_o.rotateAngleX = 0.75F + var10 * 0.1F;
         this.field_40308_f.rotateAngleX = 1.3F + var10 * 0.1F;
         this.field_40315_n.rotateAngleX = -0.5F - var10 * 0.1F;
         this.field_40320_p.rotateAngleX = 0.75F + var10 * 0.1F;
         this.field_40319_q.render(var7);
         this.field_40308_f.render(var7);
         this.field_40311_e.render(var7);
         GL11.glScalef(-1.0F, 1.0F, 1.0F);
         if(var36 == 0) {
            GL11.glCullFace(1028 /*GL_FRONT*/);
         }
      }

      GL11.glPopMatrix();
      GL11.glCullFace(1029 /*GL_BACK*/);
      GL11.glDisable(2884 /*GL_CULL_FACE*/);
      var18 = -((float)Math.sin((double)(var9 * 3.1415927F * 2.0F))) * 0.0F;
      var19 = var9 * 3.1415927F * 2.0F;
      var11 = 10.0F;
      var12 = 60.0F;
      var13 = 0.0F;
      var15 = var8.func_40160_a(11, this.field_40317_s);

      for(int var37 = 0; var37 < 12; ++var37) {
         var34 = var8.func_40160_a(12 + var37, this.field_40317_s);
         var18 = (float)((double)var18 + Math.sin((double)((float)var37 * 0.45F + var19)) * 0.05000000074505806D);
         this.field_40312_b.rotateAngleY = (this.func_40307_a(var34[0] - var15[0]) * var14 + 180.0F) * 3.1415927F / 180.0F;
         this.field_40312_b.rotateAngleX = var18 + (float)(var34[1] - var15[1]) * 3.1415927F / 180.0F * var14 * 5.0F;
         this.field_40312_b.rotateAngleZ = this.func_40307_a(var34[0] - (double)var17) * 3.1415927F / 180.0F * var14;
         this.field_40312_b.rotationPointY = var11;
         this.field_40312_b.rotationPointZ = var12;
         this.field_40312_b.rotationPointX = var13;
         var11 = (float)((double)var11 + Math.sin((double)this.field_40312_b.rotateAngleX) * 10.0D);
         var12 = (float)((double)var12 - Math.cos((double)this.field_40312_b.rotateAngleY) * Math.cos((double)this.field_40312_b.rotateAngleX) * 10.0D);
         var13 = (float)((double)var13 - Math.sin((double)this.field_40312_b.rotateAngleY) * Math.cos((double)this.field_40312_b.rotateAngleX) * 10.0D);
         this.field_40312_b.render(var7);
      }

      GL11.glPopMatrix();
   }

   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6);
   }

   private float func_40307_a(double var1) {
      while(var1 >= 180.0D) {
         var1 -= 360.0D;
      }

      while(var1 < -180.0D) {
         var1 += 360.0D;
      }

      return (float)var1;
   }
}
