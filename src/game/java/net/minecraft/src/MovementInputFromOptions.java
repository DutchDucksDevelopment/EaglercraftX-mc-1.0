package net.minecraft.src;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GameSettings;
import net.minecraft.src.MovementInput;

public class MovementInputFromOptions extends MovementInput {
   private GameSettings gameSettings;

   public MovementInputFromOptions(GameSettings var1) {
      this.gameSettings = var1;
   }

   public void updatePlayerMoveState(EntityPlayer var1) {
      this.moveStrafe = 0.0F;
      this.moveForward = 0.0F;
      if(this.gameSettings.keyBindForward.pressed) {
         ++this.moveForward;
      }

      if(this.gameSettings.keyBindBack.pressed) {
         --this.moveForward;
      }

      if(this.gameSettings.keyBindLeft.pressed) {
         ++this.moveStrafe;
      }

      if(this.gameSettings.keyBindRight.pressed) {
         --this.moveStrafe;
      }

      this.jump = this.gameSettings.keyBindJump.pressed;
      this.sneak = this.gameSettings.keyBindSneak.pressed;
      if(this.sneak) {
         this.moveStrafe = (float)((double)this.moveStrafe * 0.3D);
         this.moveForward = (float)((double)this.moveForward * 0.3D);
      }

   }
}
