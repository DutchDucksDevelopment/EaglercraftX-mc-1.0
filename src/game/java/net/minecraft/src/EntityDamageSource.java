package net.minecraft.src;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;

public class EntityDamageSource extends DamageSource {
   private Entity damageSourceEntity;

   public EntityDamageSource(String var1, Entity var2) {
      super(var1);
      this.damageSourceEntity = var2;
   }

   public Entity getEntity() {
      return this.damageSourceEntity;
   }
}
