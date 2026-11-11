package net.minecraft.src;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.ChunkProviderEnd;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Vec3D;
import net.minecraft.src.WorldChunkManagerHell;
import net.minecraft.src.WorldProvider;

public class WorldProviderEnd extends WorldProvider {
   public void registerWorldChunkManager() {
      this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.5F, 0.0F);
      this.worldType = 1;
      this.hasNoSky = true;
      this.isNether = true;
   }

   public IChunkProvider getChunkProvider() {
      return new ChunkProviderEnd(this.worldObj, this.worldObj.getWorldSeed());
   }

   public float calculateCelestialAngle(long var1, float var3) {
      return 0.0F;
   }

   public float[] calcSunriseSunsetColors(float var1, float var2) {
      return null;
   }

   public Vec3D getFogColor(float var1, float var2) {
      int var3 = 8421536;
      float var4 = MathHelper.cos(var1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
      if(var4 < 0.0F) {
         var4 = 0.0F;
      }

      if(var4 > 1.0F) {
         var4 = 1.0F;
      }

      float var5 = (float)(var3 >> 16 & 255) / 255.0F;
      float var6 = (float)(var3 >> 8 & 255) / 255.0F;
      float var7 = (float)(var3 & 255) / 255.0F;
      var5 = var5 * (var4 * 0.0F + 0.15F);
      var6 = var6 * (var4 * 0.0F + 0.15F);
      var7 = var7 * (var4 * 0.0F + 0.15F);
      return Vec3D.createVector((double)var5, (double)var6, (double)var7);
   }

   public boolean func_28112_c() {
      return false;
   }

   public boolean canRespawnHere() {
      return false;
   }

   public float getCloudHeight() {
      return 8.0F;
   }

   public boolean canCoordinateBeSpawn(int var1, int var2) {
      int var3 = this.worldObj.getFirstUncoveredBlock(var1, var2);
      return var3 == 0?false:Block.blocksList[var3].blockMaterial.getIsSolid();
   }

   public ChunkCoordinates func_40469_f() {
      return new ChunkCoordinates(100, 50, 0);
   }
}
