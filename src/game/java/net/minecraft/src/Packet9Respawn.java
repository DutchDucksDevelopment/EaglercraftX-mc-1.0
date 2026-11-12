package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class Packet9Respawn extends Packet {
   public long mapSeed;
   public int respawnDimension;
   public int difficulty;
   public int worldHeight;
   public int creativeMode;

   public Packet9Respawn() {
   }

   public Packet9Respawn(byte var1, byte var2, long var3, int var5, int var6) {
      this.respawnDimension = var1;
      this.difficulty = var2;
      this.mapSeed = var3;
      this.worldHeight = var5;
      this.creativeMode = var6;
   }

   public void processPacket(NetHandler var1) {
      var1.handleRespawn(this);
   }

   public void readPacketData(DataInputStream var1) throws IOException {
      this.respawnDimension = var1.readByte();
      this.difficulty = var1.readByte();
      this.creativeMode = var1.readByte();
      this.worldHeight = var1.readShort();
      this.mapSeed = var1.readLong();
   }

   public void writePacketData(DataOutputStream var1) throws IOException {
      var1.writeByte(this.respawnDimension);
      var1.writeByte(this.difficulty);
      var1.writeByte(this.creativeMode);
      var1.writeShort(this.worldHeight);
      var1.writeLong(this.mapSeed);
   }

   public int getPacketSize() {
      return 13;
   }
}
