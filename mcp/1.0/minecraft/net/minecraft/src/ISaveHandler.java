package net.minecraft.src;

import java.io.File;
import java.util.List;
import net.minecraft.src.IChunkLoader;
import net.minecraft.src.WorldInfo;
import net.minecraft.src.WorldProvider;

public interface ISaveHandler {
   WorldInfo loadWorldInfo();

   void checkSessionLock();

   IChunkLoader getChunkLoader(WorldProvider var1);

   void saveWorldInfoAndPlayer(WorldInfo var1, List var2);

   void saveWorldInfo(WorldInfo var1);

   File getMapFile(String var1);

   String func_40530_d();
}
