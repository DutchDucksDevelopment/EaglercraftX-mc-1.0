package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.WorldGenerator;

public class BiomeGenSwamp extends BiomeGenBase {
   protected BiomeGenSwamp(int var1) {
      super(var1);
      this.biomeDecorator.treesPerChunk = 2;
      this.biomeDecorator.flowersPerChunk = -999;
      this.biomeDecorator.deadBushPerChunk = 1;
      this.biomeDecorator.mushroomsPerChunk = 8;
      this.biomeDecorator.reedsPerChunk = 10;
      this.biomeDecorator.clayPerChunk = 1;
      this.biomeDecorator.waterlilyPerChunk = 4;
      this.field_40256_A = 14745456;
   }

   public WorldGenerator getRandomWorldGenForTrees(Random var1) {
      return this.worldGenSwamp;
   }

   public int func_40254_a(IBlockAccess var1, int var2, int var3, int var4) {
      double var5 = (double)var1.getWorldChunkManager().func_35554_b(var2, var3, var4);
      double var7 = (double)var1.getWorldChunkManager().func_35558_c(var2, var4);
      return ((ColorizerGrass.getGrassColor(var5, var7) & 16711422) + 5115470) / 2;
   }

   public int func_40255_b(IBlockAccess var1, int var2, int var3, int var4) {
      double var5 = (double)var1.getWorldChunkManager().func_35554_b(var2, var3, var4);
      double var7 = (double)var1.getWorldChunkManager().func_35558_c(var2, var4);
      return ((ColorizerFoliage.getFoliageColor(var5, var7) & 16711422) + 5115470) / 2;
   }
}
