package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class BiomeGenTaiga extends BiomeGenBase {
   public BiomeGenTaiga(int var1) {
      super(var1);
      this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
      this.biomeDecorator.treesPerChunk = 10;
      this.biomeDecorator.grassPerChunk = 1;
   }

   public WorldGenerator getRandomWorldGenForTrees(Random var1) {
      return (WorldGenerator)(var1.nextInt(3) == 0?new WorldGenTaiga1():new WorldGenTaiga2(false));
   }
}
