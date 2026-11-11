package net.minecraft.src;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.WorldGenSpikes;
import net.minecraft.src.WorldGenerator;

public class BiomeEndDecorator extends BiomeDecorator {
   protected WorldGenerator spikeGen;

   public BiomeEndDecorator(BiomeGenBase var1) {
      super(var1);
      this.spikeGen = new WorldGenSpikes(Block.whiteStone.blockID);
   }

   protected void decorate_do() {
      this.generateOres();
      if(this.decoRNG.nextInt(5) == 0) {
         int var1 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
         int var2 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
         int var3 = this.currentWorld.getTopSolidOrLiquidBlock(var1, var2);
         if(var3 > 0) {
            ;
         }

         this.spikeGen.generate(this.currentWorld, this.decoRNG, var1, var3, var2);
      }

      if(this.chunk_X == 0 && this.chunk_Z == 0) {
         EntityDragon var4 = new EntityDragon(this.currentWorld);
         var4.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.decoRNG.nextFloat() * 360.0F, 0.0F);
         this.currentWorld.entityJoinedWorld(var4);
      }

   }
}
