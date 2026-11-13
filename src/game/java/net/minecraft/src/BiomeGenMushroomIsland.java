package net.minecraft.src;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EntityMooshroom;
import net.minecraft.src.SpawnListEntry;

public class BiomeGenMushroomIsland extends BiomeGenBase {
   public BiomeGenMushroomIsland(int var1) {
      super(var1);
      this.biomeDecorator.treesPerChunk = -100;
      this.biomeDecorator.flowersPerChunk = -100;
      this.biomeDecorator.grassPerChunk = -100;
      this.biomeDecorator.mushroomsPerChunk = 1;
      this.biomeDecorator.field_40718_J = 1;
      this.topBlock = (byte)Block.mycelium.blockID;
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom::new, 8, 4, 8));
   }
}
