package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class NetworkManager {
   public static final Object threadSyncObject = new Object();
   public static int numReadThreads;
   public static int numWriteThreads;
   private Object sendQueueLock = new Object();
   private boolean isRunning = true;
   private List readPackets = Collections.synchronizedList(new ArrayList());
   private NetHandler netHandler;
   private boolean isTerminating = false;
   private String terminationReason = "";
   private Object[] field_20101_t;
   private int timeSinceLastRead = 0;
   private int sendQueueByteLength = 0;
   public static int[] field_28145_d = new int[256];
   public static int[] field_28144_e = new int[256];
   public int chunkDataSendCounter = 0;
   private int field_20100_w = 50;

   public NetworkManager(String var1, NetHandler var3) {
      this.netHandler = var3;
      this.networkShutdown("disconnect.genericReason", new Object[]{"Multiplayer is not supported in this port"});
   }

   public void addToSendQueue(Packet var1) {
   }

   private boolean sendPacket() {
      return false;
   }

   public void wakeThreads() {
   }

   private boolean readPacket() {
      return false;
   }

   private void onNetworkError(Exception var1) {
      var1.printStackTrace();
      this.networkShutdown("disconnect.genericReason", new Object[]{"Internal exception: " + var1.toString()});
   }

   public void networkShutdown(String var1, Object... var2) {
      if(this.isRunning) {
         this.isTerminating = true;
         this.terminationReason = var1;
         this.field_20101_t = var2;
         this.isRunning = false;
      }

   }

   public void processReadPackets() {
      if(this.isTerminating) {
         this.netHandler.handleErrorMessage(this.terminationReason, this.field_20101_t);
      }

   }

   public void func_28142_c() {
      this.networkShutdown("disconnect.closed", new Object[0]);
   }

   // $FF: synthetic method
   static boolean isRunning(NetworkManager var0) {
      return var0.isRunning;
   }

   // $FF: synthetic method
   static boolean isServerTerminating(NetworkManager var0) {
      return false;
   }

   // $FF: synthetic method
   static boolean readNetworkPacket(NetworkManager var0) {
      return var0.readPacket();
   }

   // $FF: synthetic method
   static boolean sendNetworkPacket(NetworkManager var0) {
      return var0.sendPacket();
   }

   // $FF: synthetic method
   static java.io.DataOutputStream getOutputStream(NetworkManager var0) {
      return null;
   }

   // $FF: synthetic method
   static boolean getIsTerminating(NetworkManager var0) {
      return var0.isTerminating;
   }

   // $FF: synthetic method
   static void func_30005_a(NetworkManager var0, Exception var1) {
      var0.onNetworkError(var1);
   }

   // $FF: synthetic method
   static Thread getReadThread(NetworkManager var0) {
      return null;
   }

   // $FF: synthetic method
   static Thread getWriteThread(NetworkManager var0) {
      return null;
   }
}
