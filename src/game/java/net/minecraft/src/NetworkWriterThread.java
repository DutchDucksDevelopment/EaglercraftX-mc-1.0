package net.minecraft.src;

import java.io.IOException;
import net.minecraft.src.NetworkManager;

class NetworkWriterThread extends Thread {
   // $FF: synthetic field
   final NetworkManager netManager;

   NetworkWriterThread(NetworkManager var1, String var2) {
      super(var2);
      this.netManager = var1;
   }

   public void run() {
      synchronized(NetworkManager.threadSyncObject) {
         ++NetworkManager.numWriteThreads;
      }

      while(true) {
         boolean var13 = false;

         try {
            var13 = true;
            if(!NetworkManager.isRunning(this.netManager)) {
               var13 = false;
               break;
            }

            while(NetworkManager.sendNetworkPacket(this.netManager)) {
               ;
            }

            try {
               if(NetworkManager.getOutputStream(this.netManager) != null) {
                  NetworkManager.getOutputStream(this.netManager).flush();
               }
            } catch (IOException var18) {
               if(!NetworkManager.getIsTerminating(this.netManager)) {
                  NetworkManager.func_30005_a(this.netManager, var18);
               }

               var18.printStackTrace();
            }

            try {
               sleep(2L);
            } catch (InterruptedException var16) {
               ;
            }
         } finally {
            if(var13) {
               synchronized(NetworkManager.threadSyncObject) {
                  --NetworkManager.numWriteThreads;
               }
            }
         }
      }

      synchronized(NetworkManager.threadSyncObject) {
         --NetworkManager.numWriteThreads;
      }
   }
}
