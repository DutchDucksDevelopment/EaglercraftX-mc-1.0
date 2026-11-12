package net.minecraft.src;

import net.minecraft.src.GenLayerDownfall;
import net.minecraft.src.GenLayerDownfallMix;
import net.minecraft.src.GenLayerIsland;
import net.minecraft.src.GenLayerMushroomIsland;
import net.minecraft.src.GenLayerRiver;
import net.minecraft.src.GenLayerRiverInit;
import net.minecraft.src.GenLayerRiverMix;
import net.minecraft.src.GenLayerShore;
import net.minecraft.src.GenLayerSmooth;
import net.minecraft.src.GenLayerSmoothZoom;
import net.minecraft.src.GenLayerSnow;
import net.minecraft.src.GenLayerTemperature;
import net.minecraft.src.GenLayerTemperatureMix;
import net.minecraft.src.GenLayerVillageLandscape;
import net.minecraft.src.GenLayerZoom;
import net.minecraft.src.GenLayerZoomFuzzy;
import net.minecraft.src.GenLayerZoomVoronoi;
import net.minecraft.src.LayerIsland;

public abstract class GenLayer {
   private long worldGendSeed;
   protected GenLayer parent;
   private long chunkSeed;
   private long baseSeed;

   public static GenLayer[] func_35497_a(long var0) {
      LayerIsland var2 = new LayerIsland(1L);
      GenLayerZoomFuzzy var10 = new GenLayerZoomFuzzy(2000L, var2);
      GenLayerIsland var11 = new GenLayerIsland(1L, var10);
      GenLayerZoom var12 = new GenLayerZoom(2001L, var11);
      GenLayerIsland var13 = new GenLayerIsland(2L, var12);
      GenLayerSnow var14 = new GenLayerSnow(2L, var13);
      GenLayerZoom var15 = new GenLayerZoom(2002L, var14);
      GenLayerIsland var16 = new GenLayerIsland(3L, var15);
      GenLayerZoom var17 = new GenLayerZoom(2003L, var16);
      GenLayerIsland var18 = new GenLayerIsland(4L, var17);
      GenLayerMushroomIsland var19 = new GenLayerMushroomIsland(5L, var18);
      byte var3 = 4;
      GenLayer var4 = GenLayerZoom.func_35515_a(1000L, var19, 0);
      GenLayerRiverInit var20 = new GenLayerRiverInit(100L, var4);
      GenLayer var21 = GenLayerZoom.func_35515_a(1000L, var20, var3 + 2);
      GenLayerRiver var22 = new GenLayerRiver(1L, var21);
      GenLayerSmooth var23 = new GenLayerSmooth(1000L, var22);
      GenLayer var5 = GenLayerZoom.func_35515_a(1000L, var19, 0);
      GenLayer var24 = new GenLayerVillageLandscape(200L, var5);
      var24 = GenLayerZoom.func_35515_a(1000L, var24, 2);
      GenLayer var6 = new GenLayerTemperature(var24);
      GenLayer var7 = new GenLayerDownfall(var24);

      for(int var8 = 0; var8 < var3; ++var8) {
         var24 = new GenLayerZoom((long)(1000 + var8), var24);
         if(var8 == 0) {
            var24 = new GenLayerIsland(3L, var24);
         }

         if(var8 == 0) {
            var24 = new GenLayerShore(1000L, var24);
         }

         var6 = new GenLayerSmoothZoom((long)(1000 + var8), var6);
         var6 = new GenLayerTemperatureMix(var6, var24, var8);
         var7 = new GenLayerSmoothZoom((long)(1000 + var8), var7);
         var7 = new GenLayerDownfallMix(var7, var24, var8);
      }

      GenLayerSmooth var26 = new GenLayerSmooth(1000L, var24);
      GenLayerRiverMix var27 = new GenLayerRiverMix(100L, var26, var23);
      GenLayer var29 = GenLayerSmoothZoom.func_35517_a(1000L, var6, 2);
      GenLayer var31 = GenLayerSmoothZoom.func_35517_a(1000L, var7, 2);
      GenLayerZoomVoronoi var9 = new GenLayerZoomVoronoi(10L, var27);
      var27.func_35496_b(var0);
      var29.func_35496_b(var0);
      var31.func_35496_b(var0);
      var9.func_35496_b(var0);
      return new GenLayer[]{var27, var9, var29, var31, var27};
   }

   public GenLayer(long var1) {
      this.baseSeed = var1;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += var1;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += var1;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += var1;
   }

   public void func_35496_b(long var1) {
      this.worldGendSeed = var1;
      if(this.parent != null) {
         this.parent.func_35496_b(var1);
      }

      this.worldGendSeed *= this.worldGendSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGendSeed += this.baseSeed;
      this.worldGendSeed *= this.worldGendSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGendSeed += this.baseSeed;
      this.worldGendSeed *= this.worldGendSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGendSeed += this.baseSeed;
   }

   public void func_35499_a(long var1, long var3) {
      this.chunkSeed = this.worldGendSeed;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var1;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var3;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var1;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var3;
   }

   protected int nextInt(int var1) {
      int var2 = (int)((this.chunkSeed >> 24) % (long)var1);
      if(var2 < 0) {
         var2 += var1;
      }

      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += this.worldGendSeed;
      return var2;
   }

   public abstract int[] func_35500_a(int var1, int var2, int var3, int var4);
}
