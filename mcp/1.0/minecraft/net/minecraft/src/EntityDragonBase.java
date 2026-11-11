package net.minecraft.src;

import net.minecraft.src.DamageSource;
import net.minecraft.src.DragonPart;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;

public class EntityDragonBase extends EntityLiving {
   protected int field_40157_aB = 100;

   public EntityDragonBase(World var1) {
      super(var1);
   }

   public int getMaxHealth() {
      return this.field_40157_aB;
   }

   public boolean func_40156_a(DragonPart var1, DamageSource var2, int var3) {
      return this.attackEntityFrom(var2, var3);
   }

   public boolean attackEntityFrom(DamageSource var1, int var2) {
      return false;
   }

   protected boolean func_40155_e(DamageSource var1, int var2) {
      return super.attackEntityFrom(var1, var2);
   }
}
