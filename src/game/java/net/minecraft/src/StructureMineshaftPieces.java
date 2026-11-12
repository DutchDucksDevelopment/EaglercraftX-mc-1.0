package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.ComponentMineshaftCorridor;
import net.minecraft.src.ComponentMineshaftCross;
import net.minecraft.src.ComponentMineshaftStairs;
import net.minecraft.src.Item;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.StructurePieceTreasure;

public class StructureMineshaftPieces {
   private static final StructurePieceTreasure[] lootArray;

   private static StructureComponent getRandomComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = var1.nextInt(100);
      if(var7 >= 80) {
         StructureBoundingBox var8 = ComponentMineshaftCross.func_35071_a(var0, var1, var2, var3, var4, var5);
         if(var8 != null) {
            return new ComponentMineshaftCross(var6, var1, var8, var5);
         }
      } else if(var7 >= 70) {
         StructureBoundingBox var9 = ComponentMineshaftStairs.func_35027_a(var0, var1, var2, var3, var4, var5);
         if(var9 != null) {
            return new ComponentMineshaftStairs(var6, var1, var9, var5);
         }
      } else {
         StructureBoundingBox var10 = ComponentMineshaftCorridor.func_35066_a(var0, var1, var2, var3, var4, var5);
         if(var10 != null) {
            return new ComponentMineshaftCorridor(var6, var1, var10, var5);
         }
      }

      return null;
   }

   private static StructureComponent getNextMineShaftComponent(StructureComponent var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 8) {
         return null;
      } else if(Math.abs(var3 - var0.getBoundingBox().minX) <= 80 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 80) {
         StructureComponent var8 = getRandomComponent(var1, var2, var3, var4, var5, var6, var7 + 1);
         if(var8 != null) {
            var1.add(var8);
            var8.buildComponent(var0, var1, var2);
         }

         return var8;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static StructureComponent getNextComponent(StructureComponent var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return getNextMineShaftComponent(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static StructurePieceTreasure[] getTreasurePieces() {
      return lootArray;
   }

   static {
      lootArray = new StructurePieceTreasure[]{new StructurePieceTreasure(Item.ingotIron.shiftedIndex, 0, 1, 5, 10), new StructurePieceTreasure(Item.ingotGold.shiftedIndex, 0, 1, 3, 5), new StructurePieceTreasure(Item.redstone.shiftedIndex, 0, 4, 9, 5), new StructurePieceTreasure(Item.dyePowder.shiftedIndex, 4, 4, 9, 5), new StructurePieceTreasure(Item.diamond.shiftedIndex, 0, 1, 2, 3), new StructurePieceTreasure(Item.coal.shiftedIndex, 0, 3, 8, 10), new StructurePieceTreasure(Item.bread.shiftedIndex, 0, 1, 3, 15), new StructurePieceTreasure(Item.pickaxeSteel.shiftedIndex, 0, 1, 1, 1), new StructurePieceTreasure(Block.rail.blockID, 0, 4, 8, 1), new StructurePieceTreasure(Item.melonSeeds.shiftedIndex, 0, 2, 4, 10), new StructurePieceTreasure(Item.pumpkinSeeds.shiftedIndex, 0, 2, 4, 10)};
   }
}
