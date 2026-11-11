package net.minecraft.src;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityDragonBase;
import net.minecraft.src.NBTTagCompound;

public class DragonPart extends Entity {
   public final EntityDragonBase field_40073_a;
   public final String field_40072_b;

   public DragonPart(EntityDragonBase var1, String var2, float var3, float var4) {
      super(var1.worldObj);
      this.setSize(var3, var4);
      this.field_40073_a = var1;
      this.field_40072_b = var2;
   }

   protected void entityInit() {
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public boolean attackEntityFrom(DamageSource var1, int var2) {
      return this.field_40073_a.func_40156_a(this, var1, var2);
   }

   public boolean func_41004_h(Entity var1) {
      return this == var1 || this.field_40073_a == var1;
   }
}
