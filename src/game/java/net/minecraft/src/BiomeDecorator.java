package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenWaterlily;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenBigMushroom;
import net.minecraft.src.WorldGenCactus;
import net.minecraft.src.WorldGenClay;
import net.minecraft.src.WorldGenDeadBush;
import net.minecraft.src.WorldGenFlowers;
import net.minecraft.src.WorldGenLiquids;
import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.WorldGenPumpkin;
import net.minecraft.src.WorldGenReed;
import net.minecraft.src.WorldGenSand;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class BiomeDecorator {
   protected World currentWorld;
   protected Random decoRNG;
   protected int chunk_X;
   protected int chunk_Z;
   protected BiomeGenBase biome;
   protected WorldGenerator clayGen = new WorldGenClay(4);
   protected WorldGenerator sandGen;
   protected WorldGenerator gravelAsSandGen;
   protected WorldGenerator dirtGen;
   protected WorldGenerator gravelGen;
   protected WorldGenerator coalGen;
   protected WorldGenerator ironGen;
   protected WorldGenerator goldGen;
   protected WorldGenerator redstoneGen;
   protected WorldGenerator diamondGen;
   protected WorldGenerator lapisGen;
   protected WorldGenerator plantYellowGen;
   protected WorldGenerator plantRedGen;
   protected WorldGenerator mushroomBrownGen;
   protected WorldGenerator mushroomRedGen;
   protected WorldGenerator field_40720_u;
   protected WorldGenerator reedGen;
   protected WorldGenerator cactusGen;
   protected WorldGenerator waterlilyGen;
   protected int waterlilyPerChunk;
   protected int treesPerChunk;
   protected int flowersPerChunk;
   protected int grassPerChunk;
   protected int deadBushPerChunk;
   protected int mushroomsPerChunk;
   protected int reedsPerChunk;
   protected int cactiPerChunk;
   protected int sandPerChunk;
   protected int sandPerChunk2;
   protected int clayPerChunk;
   protected int field_40718_J;
   public boolean generateFluid;

   public BiomeDecorator(BiomeGenBase var1) {
      this.sandGen = new WorldGenSand(7, Block.sand.blockID);
      this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
      this.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
      this.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
      this.coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
      this.ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
      this.goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
      this.redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
      this.diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
      this.lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
      this.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
      this.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
      this.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
      this.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
      this.field_40720_u = new WorldGenBigMushroom();
      this.reedGen = new WorldGenReed();
      this.cactusGen = new WorldGenCactus();
      this.waterlilyGen = new MapGenWaterlily();
      this.waterlilyPerChunk = 0;
      this.treesPerChunk = 0;
      this.flowersPerChunk = 2;
      this.grassPerChunk = 1;
      this.deadBushPerChunk = 0;
      this.mushroomsPerChunk = 0;
      this.reedsPerChunk = 0;
      this.cactiPerChunk = 0;
      this.sandPerChunk = 1;
      this.sandPerChunk2 = 3;
      this.clayPerChunk = 1;
      this.field_40718_J = 0;
      this.generateFluid = true;
      this.biome = var1;
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      if(this.currentWorld != null) {
         throw new RuntimeException("Already decorating!!");
      } else {
         this.currentWorld = var1;
         this.decoRNG = var2;
         this.chunk_X = var3;
         this.chunk_Z = var4;
         this.decorate_do();
         this.currentWorld = null;
         this.decoRNG = null;
      }
   }

   protected void decorate_do() {
      this.generateOres();

      for(int var1 = 0; var1 < this.sandPerChunk2; ++var1) {
         int var2 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var3 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.sandGen.generate(this.currentWorld, this.decoRNG, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
      }

      for(int var7 = 0; var7 < this.clayPerChunk; ++var7) {
         int var10 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var27 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.clayGen.generate(this.currentWorld, this.decoRNG, var10, this.currentWorld.getTopSolidOrLiquidBlock(var10, var27), var27);
      }

      for(int var8 = 0; var8 < this.sandPerChunk; ++var8) {
         int var11 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var28 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.sandGen.generate(this.currentWorld, this.decoRNG, var11, this.currentWorld.getTopSolidOrLiquidBlock(var11, var28), var28);
      }

      int var9 = this.treesPerChunk;
      if(this.decoRNG.nextInt(10) == 0) {
         ++var9;
      }

      for(int var12 = 0; var12 < var9; ++var12) {
         int var29 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var4 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         WorldGenerator var5 = this.biome.getRandomWorldGenForTrees(this.decoRNG);
         var5.func_517_a(1.0D, 1.0D, 1.0D);
         var5.generate(this.currentWorld, this.decoRNG, var29, this.currentWorld.getHeightValue(var29, var4), var4);
      }

      for(int var13 = 0; var13 < this.field_40718_J; ++var13) {
         int var30 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var46 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.field_40720_u.generate(this.currentWorld, this.decoRNG, var30, this.currentWorld.getHeightValue(var30, var46), var46);
      }

      for(int var14 = 0; var14 < this.flowersPerChunk; ++var14) {
         int var31 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var47 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var62 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.plantYellowGen.generate(this.currentWorld, this.decoRNG, var31, var47, var62);
         if(this.decoRNG.nextInt(4) == 0) {
            var31 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
            var47 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
            var62 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
            this.plantRedGen.generate(this.currentWorld, this.decoRNG, var31, var47, var62);
         }
      }

      for(int var15 = 0; var15 < this.grassPerChunk; ++var15) {
         byte var33 = 1;
         int var49 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var64 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var6 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         (new WorldGenTallGrass(Block.tallGrass.blockID, var33)).generate(this.currentWorld, this.decoRNG, var49, var64, var6);
      }

      for(int var16 = 0; var16 < this.deadBushPerChunk; ++var16) {
         int var34 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var50 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var65 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         (new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.currentWorld, this.decoRNG, var34, var50, var65);
      }

      for(int var17 = 0; var17 < this.waterlilyPerChunk; ++var17) {
         int var35 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var51 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;

         int var66;
         for(var66 = this.decoRNG.nextInt(this.currentWorld.field_35472_c); var66 > 0 && this.currentWorld.getBlockId(var35, var66 - 1, var51) == 0; --var66) {
            ;
         }

         this.waterlilyGen.generate(this.currentWorld, this.decoRNG, var35, var66, var51);
      }

      for(int var18 = 0; var18 < this.mushroomsPerChunk; ++var18) {
         if(this.decoRNG.nextInt(4) == 0) {
            int var36 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
            int var52 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
            int var67 = this.currentWorld.getHeightValue(var36, var52);
            this.mushroomBrownGen.generate(this.currentWorld, this.decoRNG, var36, var67, var52);
         }

         if(this.decoRNG.nextInt(8) == 0) {
            int var37 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
            int var53 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
            int var68 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
            this.mushroomRedGen.generate(this.currentWorld, this.decoRNG, var37, var68, var53);
         }
      }

      if(this.decoRNG.nextInt(4) == 0) {
         int var19 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var38 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var54 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.mushroomBrownGen.generate(this.currentWorld, this.decoRNG, var19, var38, var54);
      }

      if(this.decoRNG.nextInt(8) == 0) {
         int var20 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var39 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var55 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.mushroomRedGen.generate(this.currentWorld, this.decoRNG, var20, var39, var55);
      }

      for(int var21 = 0; var21 < this.reedsPerChunk; ++var21) {
         int var40 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var56 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         int var69 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         this.reedGen.generate(this.currentWorld, this.decoRNG, var40, var69, var56);
      }

      for(int var22 = 0; var22 < 10; ++var22) {
         int var41 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var57 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var70 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.reedGen.generate(this.currentWorld, this.decoRNG, var41, var57, var70);
      }

      if(this.decoRNG.nextInt(32) == 0) {
         int var23 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var42 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var58 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         (new WorldGenPumpkin()).generate(this.currentWorld, this.decoRNG, var23, var42, var58);
      }

      for(int var24 = 0; var24 < this.cactiPerChunk; ++var24) {
         int var43 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var59 = this.decoRNG.nextInt(this.currentWorld.field_35472_c);
         int var71 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         this.cactusGen.generate(this.currentWorld, this.decoRNG, var43, var59, var71);
      }

      if(this.generateFluid) {
         for(int var25 = 0; var25 < 50; ++var25) {
            int var44 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
            int var60 = this.decoRNG.nextInt(this.decoRNG.nextInt(this.currentWorld.field_35472_c - 8) + 8);
            int var72 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
            (new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.currentWorld, this.decoRNG, var44, var60, var72);
         }

         for(int var26 = 0; var26 < 20; ++var26) {
            int var45 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
            int var61 = this.decoRNG.nextInt(this.decoRNG.nextInt(this.decoRNG.nextInt(this.currentWorld.field_35472_c - 16) + 8) + 8);
            int var73 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
            (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.currentWorld, this.decoRNG, var45, var61, var73);
         }
      }

   }

   protected void genStandardOre1(int var1, WorldGenerator var2, int var3, int var4) {
      for(int var5 = 0; var5 < var1; ++var5) {
         int var6 = this.chunk_X + this.decoRNG.nextInt(16);
         int var7 = this.decoRNG.nextInt(var4 - var3) + var3;
         int var8 = this.chunk_Z + this.decoRNG.nextInt(16);
         var2.generate(this.currentWorld, this.decoRNG, var6, var7, var8);
      }

   }

   protected void genStandardOre2(int var1, WorldGenerator var2, int var3, int var4) {
      for(int var5 = 0; var5 < var1; ++var5) {
         int var6 = this.chunk_X + this.decoRNG.nextInt(16);
         int var7 = this.decoRNG.nextInt(var4) + this.decoRNG.nextInt(var4) + (var3 - var4);
         int var8 = this.chunk_Z + this.decoRNG.nextInt(16);
         var2.generate(this.currentWorld, this.decoRNG, var6, var7, var8);
      }

   }

   protected void generateOres() {
      this.genStandardOre1(20, this.dirtGen, 0, this.currentWorld.field_35472_c);
      this.genStandardOre1(10, this.gravelGen, 0, this.currentWorld.field_35472_c);
      this.genStandardOre1(20, this.coalGen, 0, this.currentWorld.field_35472_c);
      this.genStandardOre1(20, this.ironGen, 0, this.currentWorld.field_35472_c / 2);
      this.genStandardOre1(2, this.goldGen, 0, this.currentWorld.field_35472_c / 4);
      this.genStandardOre1(8, this.redstoneGen, 0, this.currentWorld.field_35472_c / 8);
      this.genStandardOre1(1, this.diamondGen, 0, this.currentWorld.field_35472_c / 8);
      this.genStandardOre2(1, this.lapisGen, this.currentWorld.field_35472_c / 8, this.currentWorld.field_35472_c / 8);
   }
}
