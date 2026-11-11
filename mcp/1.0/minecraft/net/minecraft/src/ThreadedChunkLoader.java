package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.ChunkLoader;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.IChunkLoader;
import net.minecraft.src.IThreadedFileIO;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.RegionFileCache;
import net.minecraft.src.ThreadedChunkLoaderPending;
import net.minecraft.src.ThreadedFileIOBase;
import net.minecraft.src.World;

public class ThreadedChunkLoader implements IChunkLoader, IThreadedFileIO {
   private List field_40556_a = new ArrayList();
   private Set field_40554_b = new HashSet();
   private Object field_40555_c = new Object();
   private final File field_40553_d;

   public ThreadedChunkLoader(File var1) {
      this.field_40553_d = var1;
   }

   public Chunk loadChunk(World var1, int var2, int var3) throws IOException {
      NBTTagCompound var4 = null;
      ChunkCoordIntPair var5 = new ChunkCoordIntPair(var2, var3);
      synchronized(this.field_40555_c) {
         if(this.field_40554_b.contains(var5)) {
            for(int var7 = 0; var7 < this.field_40556_a.size(); ++var7) {
               if(((ThreadedChunkLoaderPending)this.field_40556_a.get(var7)).field_40739_a.equals(var5)) {
                  var4 = ((ThreadedChunkLoaderPending)this.field_40556_a.get(var7)).field_40738_b;
                  break;
               }
            }
         }
      }

      if(var4 == null) {
         DataInputStream var10 = RegionFileCache.getChunkInputStream(this.field_40553_d, var2, var3);
         if(var10 == null) {
            return null;
         }

         var4 = CompressedStreamTools.func_1141_a(var10);
      }

      if(!var4.hasKey("Level")) {
         System.out.println("Chunk file at " + var2 + "," + var3 + " is missing level data, skipping");
         return null;
      } else if(!var4.getCompoundTag("Level").hasKey("Blocks")) {
         System.out.println("Chunk file at " + var2 + "," + var3 + " is missing block data, skipping");
         return null;
      } else {
         Chunk var11 = ChunkLoader.loadChunkIntoWorldFromCompound(var1, var4.getCompoundTag("Level"));
         if(!var11.isAtLocation(var2, var3)) {
            System.out.println("Chunk file at " + var2 + "," + var3 + " is in the wrong location; relocating. (Expected " + var2 + ", " + var3 + ", got " + var11.xPosition + ", " + var11.zPosition + ")");
            var4.setInteger("xPos", var2);
            var4.setInteger("zPos", var3);
            var11 = ChunkLoader.loadChunkIntoWorldFromCompound(var1, var4.getCompoundTag("Level"));
         }

         var11.func_25124_i();
         return var11;
      }
   }

   public void saveChunk(World var1, Chunk var2) {
      var1.checkSessionLock();

      try {
         NBTTagCompound var3 = new NBTTagCompound();
         NBTTagCompound var4 = new NBTTagCompound();
         var3.setTag("Level", var4);
         ChunkLoader.storeChunkInCompound(var2, var1, var4);
         this.func_40552_a(var2.func_40740_k(), var3);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private void func_40552_a(ChunkCoordIntPair var1, NBTTagCompound var2) {
      synchronized(this.field_40555_c) {
         if(this.field_40554_b.contains(var1)) {
            for(int var4 = 0; var4 < this.field_40556_a.size(); ++var4) {
               if(((ThreadedChunkLoaderPending)this.field_40556_a.get(var4)).field_40739_a.equals(var1)) {
                  this.field_40556_a.set(var4, new ThreadedChunkLoaderPending(var1, var2));
                  return;
               }
            }
         }

         this.field_40556_a.add(new ThreadedChunkLoaderPending(var1, var2));
         this.field_40554_b.add(var1);
         ThreadedFileIOBase.field_40573_a.func_40567_a(this);
      }
   }

   public boolean func_40550_A_() {
      ThreadedChunkLoaderPending var1 = null;
      synchronized(this.field_40555_c) {
         if(this.field_40556_a.size() <= 0) {
            return false;
         }

         var1 = (ThreadedChunkLoaderPending)this.field_40556_a.remove(0);
         this.field_40554_b.remove(var1.field_40739_a);
      }

      if(var1 != null) {
         try {
            this.func_40551_a(var1);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

      return true;
   }

   public void func_40551_a(ThreadedChunkLoaderPending var1) throws IOException {
      DataOutputStream var2 = RegionFileCache.getChunkOutputStream(this.field_40553_d, var1.field_40739_a.chunkXPos, var1.field_40739_a.chunkZPos);
      CompressedStreamTools.writeTo(var1.field_40738_b, var2);
      var2.close();
   }

   public void saveExtraChunkData(World var1, Chunk var2) {
   }

   public void func_814_a() {
   }

   public void saveExtraData() {
   }
}
