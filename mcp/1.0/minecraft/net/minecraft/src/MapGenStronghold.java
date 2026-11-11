package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.ComponentStrongholdStairs2;
import net.minecraft.src.MapGenStructure;
import net.minecraft.src.StructureStart;
import net.minecraft.src.StructureStrongholdStart;

public class MapGenStronghold extends MapGenStructure {
   private BiomeGenBase[] allowedBiomeGenBases = new BiomeGenBase[]{BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.hills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains};
   private boolean ranBiomeCheck;
   private ChunkCoordIntPair[] structureCoords = new ChunkCoordIntPair[3];

   protected boolean canSpawnStructureAtCoords(int var1, int var2) {
      if(!this.ranBiomeCheck) {
         this.rand.setSeed(this.worldObj.getWorldSeed());
         double var3 = this.rand.nextDouble() * 3.141592653589793D * 2.0D;

         for(int var5 = 0; var5 < this.structureCoords.length; ++var5) {
            double var6 = (1.25D + this.rand.nextDouble()) * 32.0D;
            int var8 = (int)Math.round(Math.cos(var3) * var6);
            int var9 = (int)Math.round(Math.sin(var3) * var6);
            ArrayList var10 = new ArrayList();

            for(BiomeGenBase var14 : this.allowedBiomeGenBases) {
               var10.add(var14);
            }

            ChunkPosition var18 = this.worldObj.getWorldChunkManager().func_35556_a((var8 << 4) + 8, (var9 << 4) + 8, 112, var10, this.rand);
            if(var18 != null) {
               var8 = var18.x >> 4;
               var9 = var18.z >> 4;
            } else {
               System.out.println("Placed stronghold in INVALID biome at (" + var8 + ", " + var9 + ")");
            }

            this.structureCoords[var5] = new ChunkCoordIntPair(var8, var9);
            var3 += 6.283185307179586D / (double)this.structureCoords.length;
         }

         this.ranBiomeCheck = true;
      }

      for(ChunkCoordIntPair var17 : this.structureCoords) {
         if(var1 == var17.chunkXPos && var2 == var17.chunkZPos) {
            return true;
         }
      }

      return false;
   }

   protected List func_40482_a() {
      ArrayList var1 = new ArrayList();

      for(ChunkCoordIntPair var5 : this.structureCoords) {
         if(var5 != null) {
            var1.add(var5.func_40737_a(64));
         }
      }

      return var1;
   }

   protected StructureStart getStructureStart(int var1, int var2) {
      StructureStrongholdStart var3;
      for(var3 = new StructureStrongholdStart(this.worldObj, this.rand, var1, var2); var3.func_40560_b().isEmpty() || ((ComponentStrongholdStairs2)var3.func_40560_b().get(0)).field_40009_b == null; var3 = new StructureStrongholdStart(this.worldObj, this.rand, var1, var2)) {
         ;
      }

      return var3;
   }
}
