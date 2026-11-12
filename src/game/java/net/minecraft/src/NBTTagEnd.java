package net.minecraft.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.src.NBTBase;

public class NBTTagEnd extends NBTBase {
   public NBTTagEnd() {
      super((String)null);
   }

   void readTagContents(DataInput var1) throws IOException {
   }

   void writeTagContents(DataOutput var1) throws IOException {
   }

   public byte getType() {
      return (byte)0;
   }

   public String toString() {
      return "END";
   }

   public NBTBase func_40195_b() {
      return new NBTTagEnd();
   }

   public boolean equals(Object var1) {
      return super.equals(var1);
   }
}
