package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.ComponentVillageRoadPiece;
import net.minecraft.src.ComponentVillageStartPiece;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.StructureStart;
import net.minecraft.src.StructureVillagePieces;
import net.minecraft.src.World;

class StructureVillageStart extends StructureStart {
   private boolean hasMoreThanTwoComponents = false;

   public StructureVillageStart(World var1, Random var2, int var3, int var4) {
      byte var5 = 0;
      ArrayList var6 = StructureVillagePieces.getStructureVillageWeightedPieceList(var2, var5);
      ComponentVillageStartPiece var7 = new ComponentVillageStartPiece(var1.getWorldChunkManager(), 0, var2, (var3 << 4) + 2, (var4 << 4) + 2, var6, var5);
      this.components.add(var7);
      var7.buildComponent(var7, this.components, var2);
      ArrayList var8 = var7.field_35106_f;
      ArrayList var9 = var7.field_35108_e;

      while(!var8.isEmpty() || !var9.isEmpty()) {
         if(!var8.isEmpty()) {
            int var10 = var2.nextInt(var8.size());
            StructureComponent var11 = (StructureComponent)var8.remove(var10);
            var11.buildComponent(var7, this.components, var2);
         } else {
            int var13 = var2.nextInt(var9.size());
            StructureComponent var15 = (StructureComponent)var9.remove(var13);
            var15.buildComponent(var7, this.components, var2);
         }
      }

      this.updateBoundingBox();
      int var14 = 0;

      for(StructureComponent var12 : this.components) {
         if(!(var12 instanceof ComponentVillageRoadPiece)) {
            ++var14;
         }
      }

      this.hasMoreThanTwoComponents = var14 > 2;
   }

   public boolean isSizeableStructure() {
      return this.hasMoreThanTwoComponents;
   }
}
