package net.minecraft.src;

public final class WorldSettings {
   private final long worldSeed;
   private final int worldType;
   private final boolean mapFeaturesEnabled;
   private final boolean hardcoreEnabled;

   public WorldSettings(long var1, int var3, boolean var4, boolean var5) {
      this.worldSeed = var1;
      this.worldType = var3;
      this.mapFeaturesEnabled = var4;
      this.hardcoreEnabled = var5;
   }

   public long getSeed() {
      return this.worldSeed;
   }

   public int getGameType() {
      return this.worldType;
   }

   public boolean getHardcoreEnabled() {
      return this.hardcoreEnabled;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }
}
