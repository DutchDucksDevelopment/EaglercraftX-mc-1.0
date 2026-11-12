package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.ComponentVillage;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.World;

public class ComponentVillageHouse3 extends ComponentVillage {
   private int field_35102_a = -1;

   public ComponentVillageHouse3(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      this.coordBaseMode = var4;
      this.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
   }

   public static ComponentVillageHouse3 func_35101_a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, 0, 0, 0, 9, 7, 12, var5);
      return canVillageGoDeeper(var7) && StructureComponent.getIntersectingStructureComponent(var0, var7) == null?new ComponentVillageHouse3(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.field_35102_a < 0) {
         this.field_35102_a = this.getAverageGroundLevel(var1, var3);
         if(this.field_35102_a < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.field_35102_a - this.boundingBox.maxY + 7 - 1, 0);
      }

      this.fillWithBlocks(var1, var3, 1, 1, 1, 7, 4, 4, 0, 0, false);
      this.fillWithBlocks(var1, var3, 2, 1, 6, 8, 4, 10, 0, 0, false);
      this.fillWithBlocks(var1, var3, 2, 0, 5, 8, 0, 10, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 1, 0, 1, 7, 0, 4, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 0, 0, 0, 0, 3, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
      this.fillWithBlocks(var1, var3, 8, 0, 0, 8, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
      this.fillWithBlocks(var1, var3, 1, 0, 0, 7, 2, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
      this.fillWithBlocks(var1, var3, 1, 0, 5, 2, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
      this.fillWithBlocks(var1, var3, 2, 0, 6, 2, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
      this.fillWithBlocks(var1, var3, 3, 0, 10, 7, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
      this.fillWithBlocks(var1, var3, 1, 2, 0, 7, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 1, 2, 5, 2, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 0, 4, 1, 8, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 0, 4, 4, 3, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 0, 5, 2, 8, 5, 3, Block.planks.blockID, Block.planks.blockID, false);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 0, 4, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 0, 4, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 4, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 4, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 4, 4, var3);
      int var4 = this.func_35009_c(Block.stairCompactPlanks.blockID, 3);
      int var5 = this.func_35009_c(Block.stairCompactPlanks.blockID, 2);

      for(int var6 = -1; var6 <= 2; ++var6) {
         for(int var7 = 0; var7 <= 8; ++var7) {
            this.placeBlockAtCurrentPosition(var1, Block.stairCompactPlanks.blockID, var4, var7, 4 + var6, var6, var3);
            if((var6 > -1 || var7 <= 1) && (var6 > 0 || var7 <= 3) && (var6 > 1 || var7 <= 4 || var7 >= 6)) {
               this.placeBlockAtCurrentPosition(var1, Block.stairCompactPlanks.blockID, var5, var7, 4 + var6, 5 - var6, var3);
            }
         }
      }

      this.fillWithBlocks(var1, var3, 3, 4, 5, 3, 4, 10, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 7, 4, 2, 7, 4, 10, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 4, 5, 4, 4, 5, 10, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 6, 5, 4, 6, 5, 10, Block.planks.blockID, Block.planks.blockID, false);
      this.fillWithBlocks(var1, var3, 5, 6, 3, 5, 6, 10, Block.planks.blockID, Block.planks.blockID, false);
      int var10 = this.func_35009_c(Block.stairCompactPlanks.blockID, 0);

      for(int var11 = 4; var11 >= 1; --var11) {
         this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, var11, 2 + var11, 7 - var11, var3);

         for(int var8 = 8 - var11; var8 <= 10; ++var8) {
            this.placeBlockAtCurrentPosition(var1, Block.stairCompactPlanks.blockID, var10, var11, 2 + var11, var8, var3);
         }
      }

      int var12 = this.func_35009_c(Block.stairCompactPlanks.blockID, 1);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 6, 6, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 7, 5, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Block.stairCompactPlanks.blockID, var12, 6, 6, 4, var3);

      for(int var13 = 6; var13 <= 8; ++var13) {
         for(int var9 = 5; var9 <= 10; ++var9) {
            this.placeBlockAtCurrentPosition(var1, Block.stairCompactPlanks.blockID, var12, var13, 12 - var13, var9, var3);
         }
      }

      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 0, 2, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 0, 2, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 0, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 0, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 4, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 5, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 6, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 2, 5, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 6, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 7, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 2, 2, 6, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 2, 2, 7, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 2, 2, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 2, 2, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 4, 4, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 5, 4, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 6, 4, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 5, 5, 10, var3);
      this.placeBlockAtCurrentPosition(var1, 0, 0, 2, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, 0, 0, 2, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Block.torchWood.blockID, 0, 2, 3, 1, var3);
      this.placeDoorAtCurrentPosition(var1, var3, var2, 2, 1, 0, this.func_35009_c(Block.doorWood.blockID, 1));
      this.fillWithBlocks(var1, var3, 1, 0, -1, 3, 2, -1, 0, 0, false);
      if(this.getBlockIdAtCurrentPosition(var1, 2, 0, -1, var3) == 0 && this.getBlockIdAtCurrentPosition(var1, 2, -1, -1, var3) != 0) {
         this.placeBlockAtCurrentPosition(var1, Block.stairCompactCobblestone.blockID, this.func_35009_c(Block.stairCompactCobblestone.blockID, 3), 2, 0, -1, var3);
      }

      for(int var14 = 0; var14 < 5; ++var14) {
         for(int var16 = 0; var16 < 9; ++var16) {
            this.clearCurrentPositionBlocksUpwards(var1, var16, 7, var14, var3);
            this.fillCurrentPositionBlocksDownwards(var1, Block.cobblestone.blockID, 0, var16, -1, var14, var3);
         }
      }

      for(int var15 = 5; var15 < 11; ++var15) {
         for(int var17 = 2; var17 < 9; ++var17) {
            this.clearCurrentPositionBlocksUpwards(var1, var17, 7, var15, var3);
            this.fillCurrentPositionBlocksDownwards(var1, Block.cobblestone.blockID, 0, var17, -1, var15, var3);
         }
      }

      this.func_40044_a(var1, var3, 4, 1, 2, 2);
      return true;
   }
}
