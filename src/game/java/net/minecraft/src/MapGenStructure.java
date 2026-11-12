package net.minecraft.src;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MapGenBase;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.StructureStart;
import net.minecraft.src.World;

public abstract class MapGenStructure extends MapGenBase {
   protected HashMap coordMap = new HashMap();

   public void generate(IChunkProvider var1, World var2, int var3, int var4, byte[] var5) {
      super.generate(var1, var2, var3, var4, var5);
   }

   protected void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, byte[] var6) {
      if(!this.coordMap.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(var2, var3)))) {
         this.rand.nextInt();
         if(this.canSpawnStructureAtCoords(var2, var3)) {
            StructureStart var7 = this.getStructureStart(var2, var3);
            this.coordMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(var2, var3)), var7);
         }

      }
   }

   public boolean generateStructuresInChunk(World var1, Random var2, int var3, int var4) {
      int var5 = (var3 << 4) + 8;
      int var6 = (var4 << 4) + 8;
      boolean var7 = false;

      for(java.util.Iterator var9_iter = (this.coordMap.values()).iterator(); var9_iter.hasNext();) {
          StructureStart var9 = (StructureStart) var9_iter.next();
         if(var9.isSizeableStructure() && var9.getBoundingBox().isInsideStructureBB(var5, var6, var5 + 15, var6 + 15)) {
            var9.generateStructure(var1, var2, new StructureBoundingBox(var5, var6, var5 + 15, var6 + 15));
            var7 = true;
         }
      }

      return var7;
   }

   public boolean func_40483_a(int var1, int var2, int var3) {
      label24:
      for(java.util.Iterator var5_iter = (this.coordMap.values()).iterator(); var5_iter.hasNext();) {
          StructureStart var5 = (StructureStart) var5_iter.next();
         if(var5.isSizeableStructure() && var5.getBoundingBox().isInsideStructureBB(var1, var3, var1, var3)) {
            Iterator var6 = var5.func_40560_b().iterator();

            while(true) {
               if(!var6.hasNext()) {
                  continue label24;
               }

               StructureComponent var7 = (StructureComponent)var6.next();
               if(var7.getBoundingBox().isVecInside(var1, var2, var3)) {
                  break;
               }
            }

            return true;
         }
      }

      return false;
   }

   public ChunkPosition func_40484_a(World var1, int var2, int var3, int var4) {
      this.worldObj = var1;
      this.rand.setSeed(var1.getWorldSeed());
      long var5 = this.rand.nextLong();
      long var7 = this.rand.nextLong();
      long var9 = (long)(var2 >> 4) * var5;
      long var11 = (long)(var4 >> 4) * var7;
      this.rand.setSeed(var9 ^ var11 ^ var1.getWorldSeed());
      this.recursiveGenerate(var1, var2 >> 4, var4 >> 4, 0, 0, (byte[])null);
      double var13 = Double.MAX_VALUE;
      ChunkPosition var15 = null;

      for(java.util.Iterator var17_iter = (this.coordMap.values()).iterator(); var17_iter.hasNext();) {
          StructureStart var17 = (StructureStart) var17_iter.next();
         if(var17.isSizeableStructure()) {
            StructureComponent var18 = (StructureComponent)var17.func_40560_b().get(0);
            ChunkPosition var19 = var18.func_40008_a_();
            int var20 = var19.x - var2;
            int var21 = var19.y - var3;
            int var22 = var19.z - var4;
            double var23 = (double)(var20 + var20 * var21 * var21 + var22 * var22);
            if(var23 < var13) {
               var13 = var23;
               var15 = var19;
            }
         }
      }

      if(var15 != null) {
         return var15;
      } else {
         List var25 = this.func_40482_a();
         if(var25 != null) {
            ChunkPosition var26 = null;

            for(java.util.Iterator var28_iter = (var25).iterator(); var28_iter.hasNext();) {
                ChunkPosition var28 = (ChunkPosition) var28_iter.next();
               int var29 = var28.x - var2;
               int var30 = var28.y - var3;
               int var31 = var28.z - var4;
               double var32 = (double)(var29 + var29 * var30 * var30 + var31 * var31);
               if(var32 < var13) {
                  var13 = var32;
                  var26 = var28;
               }
            }

            return var26;
         } else {
            return null;
         }
      }
   }

   protected List func_40482_a() {
      return null;
   }

   protected abstract boolean canSpawnStructureAtCoords(int var1, int var2);

   protected abstract StructureStart getStructureStart(int var1, int var2);
}
