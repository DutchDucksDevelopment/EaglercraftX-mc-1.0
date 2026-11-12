package net.minecraft.src;

import net.minecraft.src.IStatType;
import net.minecraft.src.StatBase;

class StatTypeSimple implements IStatType {
   public String format(int var1) {
      return StatBase.getNumberFormat().format((long)var1);
   }
}
