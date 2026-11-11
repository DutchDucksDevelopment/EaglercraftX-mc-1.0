package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.World;

public abstract class StructureStart {
   protected LinkedList components = new LinkedList();
   protected StructureBoundingBox boundingBox;

   public StructureBoundingBox getBoundingBox() {
      return this.boundingBox;
   }

   public LinkedList func_40560_b() {
      return this.components;
   }

   public void generateStructure(World var1, Random var2, StructureBoundingBox var3) {
      Iterator var4 = this.components.iterator();

      while(var4.hasNext()) {
         StructureComponent var5 = (StructureComponent)var4.next();
         if(var5.getBoundingBox().intersectsWith(var3) && !var5.addComponentParts(var1, var2, var3)) {
            var4.remove();
         }
      }

   }

   protected void updateBoundingBox() {
      this.boundingBox = StructureBoundingBox.getNewBoundingBox();

      for(StructureComponent var2 : this.components) {
         this.boundingBox.resizeBoundingBoxTo(var2.getBoundingBox());
      }

   }

   protected void markAvailableHeight(World var1, Random var2, int var3) {
      int var4 = var1.field_35470_e - var3;
      int var5 = this.boundingBox.getYSize() + 1;
      if(var5 < var4) {
         var5 += var2.nextInt(var4 - var5);
      }

      int var6 = var5 - this.boundingBox.maxY;
      this.boundingBox.offset(0, var6, 0);

      for(StructureComponent var8 : this.components) {
         var8.getBoundingBox().offset(0, var6, 0);
      }

   }

   protected void func_40559_a(World var1, Random var2, int var3, int var4) {
      int var5 = var4 - var3 + 1 - this.boundingBox.getYSize();
      int var6 = 1;
      if(var5 > 1) {
         var6 = var3 + var2.nextInt(var5);
      } else {
         var6 = var3;
      }

      int var7 = var6 - this.boundingBox.minY;
      this.boundingBox.offset(0, var7, 0);

      for(StructureComponent var9 : this.components) {
         var9.getBoundingBox().offset(0, var7, 0);
      }

   }

   public boolean isSizeableStructure() {
      return true;
   }
}
