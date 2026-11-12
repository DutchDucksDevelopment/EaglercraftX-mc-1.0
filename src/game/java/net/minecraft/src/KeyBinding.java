package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.IntHashMap;

public class KeyBinding {
   public static List keybindArray = new ArrayList();
   public static IntHashMap hash = new IntHashMap();
   public String keyDescription;
   public int keyCode;
   public boolean pressed;
   public int pressTime = 0;

   public static void onTick(int var0) {
      KeyBinding var1 = (KeyBinding)hash.lookup(var0);
      if(var1 != null) {
         ++var1.pressTime;
      }

   }

   public static void setKeyBindState(int var0, boolean var1) {
      KeyBinding var2 = (KeyBinding)hash.lookup(var0);
      if(var2 != null) {
         var2.pressed = var1;
      }

   }

   public static void unPressAllKeys() {
      for(java.util.Iterator var1_iter = (keybindArray).iterator(); var1_iter.hasNext();) {
          KeyBinding var1 = (KeyBinding) var1_iter.next();
         var1.unpressKey();
      }

   }

   public static void resetKeyBindingArrayAndHash() {
      hash.clearMap();

      for(java.util.Iterator var1_iter = (keybindArray).iterator(); var1_iter.hasNext();) {
          KeyBinding var1 = (KeyBinding) var1_iter.next();
         hash.addKey(var1.keyCode, var1);
      }

   }

   public KeyBinding(String var1, int var2) {
      this.keyDescription = var1;
      this.keyCode = var2;
      keybindArray.add(this);
      hash.addKey(var2, this);
   }

   public boolean isPressed() {
      if(this.pressTime == 0) {
         return false;
      } else {
         --this.pressTime;
         return true;
      }
   }

   private void unpressKey() {
      this.pressTime = 0;
      this.pressed = false;
   }
}
