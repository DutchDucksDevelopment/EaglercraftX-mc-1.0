package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.EntityBlaze;
import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.MapGenStructure;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.StructureNetherBridgeStart;
import net.minecraft.src.StructureStart;

public class MapGenNetherBridge extends MapGenStructure {
   private List field_40486_a = new ArrayList();

   public MapGenNetherBridge() {
      this.field_40486_a.add(new SpawnListEntry(EntityBlaze::new, 10, 2, 3));
      this.field_40486_a.add(new SpawnListEntry(EntityPigZombie::new, 10, 4, 4));
      this.field_40486_a.add(new SpawnListEntry(EntityMagmaCube::new, 3, 4, 4));
   }

   public List func_40485_b() {
      return this.field_40486_a;
   }

   protected boolean canSpawnStructureAtCoords(int var1, int var2) {
      int var3 = var1 >> 4;
      int var4 = var2 >> 4;
      this.rand.setSeed((long)(var3 ^ var4 << 4) ^ this.worldObj.getWorldSeed());
      this.rand.nextInt();
      return this.rand.nextInt(3) != 0?false:(var1 != (var3 << 4) + 4 + this.rand.nextInt(8)?false:var2 == (var4 << 4) + 4 + this.rand.nextInt(8));
   }

   protected StructureStart getStructureStart(int var1, int var2) {
      return new StructureNetherBridgeStart(this.worldObj, this.rand, var1, var2);
   }
}
