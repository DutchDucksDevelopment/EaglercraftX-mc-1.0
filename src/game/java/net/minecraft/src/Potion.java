package net.minecraft.src;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.PotionHealth;

public class Potion {
   public static final Potion[] potionTypes = new Potion[32];
   public static final Potion field_35676_b = null;
   public static final Potion potionSpeed = (new Potion(1, false, 8171462)).setPotionName("potion.moveSpeed").func_40618_a(0, 0);
   public static final Potion potionSlowdown = (new Potion(2, true, 5926017)).setPotionName("potion.moveSlowdown").func_40618_a(1, 0);
   public static final Potion potionDigSpeed = (new Potion(3, false, 14270531)).setPotionName("potion.digSpeed").func_40618_a(2, 0).func_40614_a(1.5D);
   public static final Potion potionDigSlow = (new Potion(4, true, 4866583)).setPotionName("potion.digSlowDown").func_40618_a(3, 0);
   public static final Potion potionDamageBoost = (new Potion(5, false, 9643043)).setPotionName("potion.damageBoost").func_40618_a(4, 0);
   public static final Potion potionHeal = (new PotionHealth(6, false, 16262179)).setPotionName("potion.heal");
   public static final Potion potionHealDamage = (new PotionHealth(7, true, 4393481)).setPotionName("potion.harm");
   public static final Potion potionJump = (new Potion(8, false, 7889559)).setPotionName("potion.jump").func_40618_a(2, 1);
   public static final Potion potionConfusion = (new Potion(9, true, 5578058)).setPotionName("potion.confusion").func_40618_a(3, 1).func_40614_a(0.25D);
   public static final Potion potionRegeneration = (new Potion(10, false, 13458603)).setPotionName("potion.regeneration").func_40618_a(7, 0).func_40614_a(0.25D);
   public static final Potion potionResistance = (new Potion(11, false, 10044730)).setPotionName("potion.resistance").func_40618_a(6, 1);
   public static final Potion potionFireReistance = (new Potion(12, false, 14981690)).setPotionName("potion.fireResistance").func_40618_a(7, 1);
   public static final Potion potionWaterBreathing = (new Potion(13, false, 3035801)).setPotionName("potion.waterBreathing").func_40618_a(0, 2);
   public static final Potion potionInvisibility = (new Potion(14, false, 8356754)).setPotionName("potion.invisibility").func_40618_a(0, 1).func_40616_h();
   public static final Potion potionBlindness = (new Potion(15, true, 2039587)).setPotionName("potion.blindness").func_40618_a(5, 1).func_40614_a(0.25D);
   public static final Potion potionNightVision = (new Potion(16, false, 2039713)).setPotionName("potion.nightVision").func_40618_a(4, 1).func_40616_h();
   public static final Potion potionHunger = (new Potion(17, true, 5797459)).setPotionName("potion.hunger").func_40618_a(1, 1);
   public static final Potion potionWeakness = (new Potion(18, true, 4738376)).setPotionName("potion.weakness").func_40618_a(5, 0);
   public static final Potion potionPoison = (new Potion(19, true, 5149489)).setPotionName("potion.poison").func_40618_a(6, 0).func_40614_a(0.25D);
   public static final Potion field_35688_v = null;
   public static final Potion field_35687_w = null;
   public static final Potion field_35697_x = null;
   public static final Potion field_35696_y = null;
   public static final Potion field_35695_z = null;
   public static final Potion field_35667_A = null;
   public static final Potion field_35668_B = null;
   public static final Potion field_35669_C = null;
   public static final Potion field_35663_D = null;
   public static final Potion field_35664_E = null;
   public static final Potion field_35665_F = null;
   public static final Potion field_35666_G = null;
   public final int id;
   private String name = "";
   private int field_40627_J = -1;
   private final boolean field_40628_K;
   private double field_40624_L;
   private boolean field_40625_M;
   private final int field_40626_N;

   protected Potion(int var1, boolean var2, int var3) {
      this.id = var1;
      potionTypes[var1] = this;
      this.field_40628_K = var2;
      if(var2) {
         this.field_40624_L = 0.5D;
      } else {
         this.field_40624_L = 1.0D;
      }

      this.field_40626_N = var3;
   }

   protected Potion func_40618_a(int var1, int var2) {
      this.field_40627_J = var1 + var2 * 8;
      return this;
   }

   public int func_40619_a() {
      return this.id;
   }

   public void performEffect(EntityLiving var1, int var2) {
      if(this.id == potionRegeneration.id) {
         if(var1.getEntityHealth() < var1.getMaxHealth()) {
            var1.heal(1);
         }
      } else if(this.id == potionPoison.id) {
         if(var1.getEntityHealth() > 1) {
            var1.attackEntityFrom(DamageSource.magic, 1);
         }
      } else if(this.id == potionHunger.id && var1 instanceof EntityPlayer) {
         ((EntityPlayer)var1).addExhaustion(0.025F * (float)(var2 + 1));
      } else if((this.id != potionHeal.id || var1.func_40122_aP()) && (this.id != potionHealDamage.id || !var1.func_40122_aP())) {
         if(this.id == potionHealDamage.id && !var1.func_40122_aP() || this.id == potionHeal.id && var1.func_40122_aP()) {
            var1.attackEntityFrom(DamageSource.magic, 6 << var2);
         }
      } else {
         var1.heal(6 << var2);
      }

   }

   public void func_40613_a(EntityLiving var1, EntityLiving var2, int var3, double var4) {
      if((this.id != potionHeal.id || var2.func_40122_aP()) && (this.id != potionHealDamage.id || !var2.func_40122_aP())) {
         if(this.id == potionHealDamage.id && !var2.func_40122_aP() || this.id == potionHeal.id && var2.func_40122_aP()) {
            int var7 = (int)(var4 * (double)(6 << var3) + 0.5D);
            if(var1 == null) {
               var2.attackEntityFrom(DamageSource.magic, var7);
            } else {
               var2.attackEntityFrom(DamageSource.func_40542_b(var2, var1), var7);
            }
         }
      } else {
         int var6 = (int)(var4 * (double)(6 << var3) + 0.5D);
         var2.heal(var6);
      }

   }

   public boolean func_40622_b() {
      return false;
   }

   public boolean isReady(int var1, int var2) {
      if(this.id != potionRegeneration.id && this.id != potionPoison.id) {
         return this.id == potionHunger.id;
      } else {
         int var3 = 25 >> var2;
         return var3 > 0?var1 % var3 == 0:true;
      }
   }

   public Potion setPotionName(String var1) {
      this.name = var1;
      return this;
   }

   public String func_40623_c() {
      return this.name;
   }

   public boolean func_40617_d() {
      return this.field_40627_J >= 0;
   }

   public int func_40611_e() {
      return this.field_40627_J;
   }

   public boolean func_40615_f() {
      return this.field_40628_K;
   }

   public static String func_40620_a(PotionEffect var0) {
      int var1 = var0.getDuration();
      int var2 = var1 / 20;
      int var3 = var2 / 60;
      var2 = var2 % 60;
      return var2 < 10?var3 + ":0" + var2:var3 + ":" + var2;
   }

   protected Potion func_40614_a(double var1) {
      this.field_40624_L = var1;
      return this;
   }

   public double func_40610_g() {
      return this.field_40624_L;
   }

   public Potion func_40616_h() {
      this.field_40625_M = true;
      return this;
   }

   public boolean func_40612_i() {
      return this.field_40625_M;
   }

   public int func_40621_j() {
      return this.field_40626_N;
   }
}
