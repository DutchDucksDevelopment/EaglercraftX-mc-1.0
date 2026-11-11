package net.minecraft.src;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.StructureMineshaftPieces;
import net.minecraft.src.World;

public class ComponentMineshaftRoom extends StructureComponent {
   private LinkedList field_35065_a = new LinkedList();

   public ComponentMineshaftRoom(int var1, Random var2, int var3, int var4) {
      super(var1);
      this.boundingBox = new StructureBoundingBox(var3, 50, var4, var3 + 7 + var2.nextInt(6), 54 + var2.nextInt(6), var4 + 7 + var2.nextInt(6));
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      int var4 = this.func_35012_c();
      int var6 = this.boundingBox.getYSize() - 3 - 1;
      if(var6 <= 0) {
         var6 = 1;
      }

      int var9;
      for(int var5 = 0; var5 < this.boundingBox.getXSize(); var5 = var9 + 4) {
         var9 = var5 + var3.nextInt(this.boundingBox.getXSize());
         if(var9 + 3 > this.boundingBox.getXSize()) {
            break;
         }

         StructureComponent var7 = StructureMineshaftPieces.getNextComponent(var1, var2, var3, this.boundingBox.minX + var9, this.boundingBox.minY + var3.nextInt(var6) + 1, this.boundingBox.minZ - 1, 2, var4);
         if(var7 != null) {
            StructureBoundingBox var8 = var7.getBoundingBox();
            this.field_35065_a.add(new StructureBoundingBox(var8.minX, var8.minY, this.boundingBox.minZ, var8.maxX, var8.maxY, this.boundingBox.minZ + 1));
         }
      }

      for(int var10 = 0; var10 < this.boundingBox.getXSize(); var10 = var9 + 4) {
         var9 = var10 + var3.nextInt(this.boundingBox.getXSize());
         if(var9 + 3 > this.boundingBox.getXSize()) {
            break;
         }

         StructureComponent var16 = StructureMineshaftPieces.getNextComponent(var1, var2, var3, this.boundingBox.minX + var9, this.boundingBox.minY + var3.nextInt(var6) + 1, this.boundingBox.maxZ + 1, 0, var4);
         if(var16 != null) {
            StructureBoundingBox var19 = var16.getBoundingBox();
            this.field_35065_a.add(new StructureBoundingBox(var19.minX, var19.minY, this.boundingBox.maxZ - 1, var19.maxX, var19.maxY, this.boundingBox.maxZ));
         }
      }

      for(int var12 = 0; var12 < this.boundingBox.getZSize(); var12 = var9 + 4) {
         var9 = var12 + var3.nextInt(this.boundingBox.getZSize());
         if(var9 + 3 > this.boundingBox.getZSize()) {
            break;
         }

         StructureComponent var17 = StructureMineshaftPieces.getNextComponent(var1, var2, var3, this.boundingBox.minX - 1, this.boundingBox.minY + var3.nextInt(var6) + 1, this.boundingBox.minZ + var9, 1, var4);
         if(var17 != null) {
            StructureBoundingBox var20 = var17.getBoundingBox();
            this.field_35065_a.add(new StructureBoundingBox(this.boundingBox.minX, var20.minY, var20.minZ, this.boundingBox.minX + 1, var20.maxY, var20.maxZ));
         }
      }

      for(int var14 = 0; var14 < this.boundingBox.getZSize(); var14 = var9 + 4) {
         var9 = var14 + var3.nextInt(this.boundingBox.getZSize());
         if(var9 + 3 > this.boundingBox.getZSize()) {
            break;
         }

         StructureComponent var18 = StructureMineshaftPieces.getNextComponent(var1, var2, var3, this.boundingBox.maxX + 1, this.boundingBox.minY + var3.nextInt(var6) + 1, this.boundingBox.minZ + var9, 3, var4);
         if(var18 != null) {
            StructureBoundingBox var21 = var18.getBoundingBox();
            this.field_35065_a.add(new StructureBoundingBox(this.boundingBox.maxX - 1, var21.minY, var21.minZ, this.boundingBox.maxX, var21.maxY, var21.maxZ));
         }
      }

   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithBlocks(var1, var3, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, Block.dirt.blockID, 0, true);
         this.fillWithBlocks(var1, var3, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, 0, 0, false);

         for(StructureBoundingBox var5 : this.field_35065_a) {
            this.fillWithBlocks(var1, var3, var5.minX, var5.maxY - 2, var5.minZ, var5.maxX, var5.maxY, var5.maxZ, 0, 0, false);
         }

         this.randomlyRareFillWithBlocks(var1, var3, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, 0, false);
         return true;
      }
   }
}
