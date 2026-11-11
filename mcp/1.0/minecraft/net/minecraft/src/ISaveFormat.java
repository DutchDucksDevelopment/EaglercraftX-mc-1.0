package net.minecraft.src;

import java.util.List;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.ISaveHandler;
import net.minecraft.src.WorldInfo;

public interface ISaveFormat {
   String func_22178_a();

   ISaveHandler getSaveLoader(String var1, boolean var2);

   List getSaveList();

   void flushCache();

   WorldInfo getWorldInfo(String var1);

   void deleteWorldDirectory(String var1);

   void renameWorld(String var1, String var2);

   boolean isOldMapFormat(String var1);

   boolean convertMapFormat(String var1, IProgressUpdate var2);
}
