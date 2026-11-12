package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GameSettings;
import net.minecraft.src.IStatStringFormat;

public class StatStringFormatKeyInv implements IStatStringFormat {
   // $FF: synthetic field
   final Minecraft mc;

   public StatStringFormatKeyInv(Minecraft var1) {
      this.mc = var1;
   }

   public String formatString(String var1) {
      return String.format(var1, new Object[]{GameSettings.func_41085_c(this.mc.gameSettings.keyBindInventory.keyCode)});
   }
}
