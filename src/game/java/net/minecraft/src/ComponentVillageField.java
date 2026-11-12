package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.ComponentVillage;
import net.minecraft.src.MathHelper;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.World;

public class ComponentVillageField extends ComponentVillage {
   private int field_35081_a = -1;

   public ComponentVillageField(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      this.coordBaseMode = var4;
      this.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
   }

   public static ComponentVillageField func_35080_a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, 0, 0, 0, 13, 4, 9, var5);
      return canVillageGoDeeper(var7) && StructureComponent.getIntersectingStructureComponent(var0, var7) == null?new ComponentVillageField(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.field_35081_a < 0) {
         this.field_35081_a = this.getAverageGroundLevel(var1, var3);
         if(this.field_35081_a < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.field_35081_a - this.boundingBox.maxY + 4 - 1, 0);
      }

      this.fillWithBlocks(var1, var3, 0, 1, 0, 12, 4, 8, 0, 0, false);
      this.fillWithBlocks(var1, var3, 1, 0, 1, 2, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
      this.fillWithBlocks(var1, var3, 4, 0, 1, 5, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
      this.fillWithBlocks(var1, var3, 7, 0, 1, 8, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
      this.fillWithBlocks(var1, var3, 10, 0, 1, 11, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
      this.fillWithBlocks(var1, var3, 0, 0, 0, 0, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
      this.fillWithBlocks(var1, var3, 6, 0, 0, 6, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
      this.fillWithBlocks(var1, var3, 12, 0, 0, 12, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
      this.fillWithBlocks(var1, var3, 1, 0, 0, 11, 0, 0, Block.wood.blockID, Block.wood.blockID, false);
      this.fillWithBlocks(var1, var3, 1, 0, 8, 11, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
      this.fillWithBlocks(var1, var3, 3, 0, 1, 3, 0, 7, Block.waterMoving.blockID, Block.waterMoving.blockID, false);
      this.fillWithBlocks(var1, var3, 9, 0, 1, 9, 0, 7, Block.waterMoving.blockID, Block.waterMoving.blockID, false);

      for(int var4 = 1; var4 <= 7; ++var4) {
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 1, 1, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 2, 1, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 4, 1, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 5, 1, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 7, 1, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 8, 1, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 10, 1, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Block.crops.blockID, MathHelper.getRandomIntegerInRange(var2, 2, 7), 11, 1, var4, var3);
      }

      for(int var6 = 0; var6 < 9; ++var6) {
         for(int var5 = 0; var5 < 13; ++var5) {
            this.clearCurrentPositionBlocksUpwards(var1, var5, 4, var6, var3);
            this.fillCurrentPositionBlocksDownwards(var1, Block.dirt.blockID, 0, var5, -1, var6, var3);
         }
      }

      return true;
   }
}
