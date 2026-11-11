package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.ComponentNetherBridgePiece;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.World;

public class StructureNetherBridgeEnd extends ComponentNetherBridgePiece {
   private int field_40024_a;

   public StructureNetherBridgeEnd(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      this.coordBaseMode = var4;
      this.boundingBox = var3;
      this.field_40024_a = var2.nextInt();
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
   }

   public static StructureNetherBridgeEnd func_40023_a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -3, 0, 5, 10, 8, var5);
      return func_40021_a(var7) && StructureComponent.getIntersectingStructureComponent(var0, var7) == null?new StructureNetherBridgeEnd(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      Random var4 = new Random((long)this.field_40024_a);

      for(int var5 = 0; var5 <= 4; ++var5) {
         for(int var6 = 3; var6 <= 4; ++var6) {
            int var7 = var4.nextInt(8);
            this.fillWithBlocks(var1, var3, var5, var6, 0, var5, var6, var7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
         }
      }

      int var8 = var4.nextInt(8);
      this.fillWithBlocks(var1, var3, 0, 5, 0, 0, 5, var8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
      var8 = var4.nextInt(8);
      this.fillWithBlocks(var1, var3, 4, 5, 0, 4, 5, var8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

      for(var8 = 0; var8 <= 4; ++var8) {
         int var12 = var4.nextInt(5);
         this.fillWithBlocks(var1, var3, var8, 2, 0, var8, 2, var12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
      }

      for(var8 = 0; var8 <= 4; ++var8) {
         for(int var13 = 0; var13 <= 1; ++var13) {
            int var14 = var4.nextInt(3);
            this.fillWithBlocks(var1, var3, var8, var13, 0, var8, var13, var14, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
         }
      }

      return true;
   }
}
