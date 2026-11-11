package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.EntityEnderCrystal;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenSpikes extends WorldGenerator {
   private int field_40197_a;

   public WorldGenSpikes(int var1) {
      this.field_40197_a = var1;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      if(var1.isAirBlock(var3, var4, var5) && var1.getBlockId(var3, var4 - 1, var5) == this.field_40197_a) {
         int var6 = var2.nextInt(32) + 6;
         int var7 = var2.nextInt(4) + 1;

         for(int var8 = var3 - var7; var8 <= var3 + var7; ++var8) {
            for(int var9 = var5 - var7; var9 <= var5 + var7; ++var9) {
               int var10 = var8 - var3;
               int var11 = var9 - var5;
               if(var10 * var10 + var11 * var11 <= var7 * var7 + 1 && var1.getBlockId(var8, var4 - 1, var9) != this.field_40197_a) {
                  return false;
               }
            }
         }

         for(int var13 = var4; var13 < var4 + var6 && var13 < var1.field_35472_c; ++var13) {
            for(int var15 = var3 - var7; var15 <= var3 + var7; ++var15) {
               for(int var16 = var5 - var7; var16 <= var5 + var7; ++var16) {
                  int var17 = var15 - var3;
                  int var12 = var16 - var5;
                  if(var17 * var17 + var12 * var12 <= var7 * var7 + 1) {
                     var1.setBlockWithNotify(var15, var13, var16, Block.obsidian.blockID);
                  }
               }
            }
         }

         EntityEnderCrystal var14 = new EntityEnderCrystal(var1);
         var14.setLocationAndAngles((double)((float)var3 + 0.5F), (double)(var4 + var6), (double)((float)var5 + 0.5F), var2.nextFloat() * 360.0F, 0.0F);
         var1.entityJoinedWorld(var14);
         var1.setBlockWithNotify(var3, var4 + var6, var5, Block.bedrock.blockID);
         return true;
      } else {
         return false;
      }
   }
}
