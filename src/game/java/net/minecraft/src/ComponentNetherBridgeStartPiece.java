package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.src.ComponentNetherBridgeCrossing3;
import net.minecraft.src.StructureNetherBridgePieceWeight;
import net.minecraft.src.StructureNetherBridgePieces;

public class ComponentNetherBridgeStartPiece extends ComponentNetherBridgeCrossing3 {
   public StructureNetherBridgePieceWeight field_40037_a;
   public List field_40035_b = new ArrayList();
   public List field_40036_c;
   public ArrayList field_40034_d = new ArrayList();

   public ComponentNetherBridgeStartPiece(Random var1, int var2, int var3) {
      super(var1, var2, var3);

      for(StructureNetherBridgePieceWeight var7 : StructureNetherBridgePieces.func_40689_a()) {
         var7.field_40698_c = 0;
         this.field_40035_b.add(var7);
      }

      this.field_40036_c = new ArrayList();

      for(StructureNetherBridgePieceWeight var11 : StructureNetherBridgePieces.func_40687_b()) {
         var11.field_40698_c = 0;
         this.field_40036_c.add(var11);
      }

   }
}
