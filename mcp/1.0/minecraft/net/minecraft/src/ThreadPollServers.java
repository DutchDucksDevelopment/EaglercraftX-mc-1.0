package net.minecraft.src;

import net.minecraft.src.GuiSlotServer;
import net.minecraft.src.ServerNBTStorage;

class ThreadPollServers extends Thread {
   // $FF: synthetic field
   final ServerNBTStorage server;
   // $FF: synthetic field
   final GuiSlotServer serverSlotContainer;

   ThreadPollServers(GuiSlotServer var1, ServerNBTStorage var2) {
      this.serverSlotContainer = var1;
      this.server = var2;
   }

   public void run() {
      // $FF: Couldn't be decompiled
   }
}
