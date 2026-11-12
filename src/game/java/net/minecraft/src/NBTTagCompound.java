package net.minecraft.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagByte;
import net.minecraft.src.NBTTagByteArray;
import net.minecraft.src.NBTTagDouble;
import net.minecraft.src.NBTTagFloat;
import net.minecraft.src.NBTTagInt;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NBTTagLong;
import net.minecraft.src.NBTTagShort;
import net.minecraft.src.NBTTagString;

public class NBTTagCompound extends NBTBase {
   private Map tagMap = new HashMap();

   public boolean hasNoTags() {
      return this.tagMap.isEmpty();
   }

   public NBTTagCompound() {
      super("");
   }

   public NBTTagCompound(String var1) {
      super(var1);
   }

   void writeTagContents(DataOutput var1) throws IOException {
      for(java.util.Iterator var3_iter = (this.tagMap.values()).iterator(); var3_iter.hasNext();) {
          NBTBase var3 = (NBTBase) var3_iter.next();
         NBTBase.writeTag(var3, var1);
      }

      var1.writeByte(0);
   }

   void readTagContents(DataInput var1) throws IOException {
      this.tagMap.clear();

      NBTBase var2;
      while((var2 = NBTBase.readTag(var1)).getType() != 0) {
         this.tagMap.put(var2.getKey(), var2);
      }

   }

   public Collection func_28110_c() {
      return this.tagMap.values();
   }

   public byte getType() {
      return (byte)10;
   }

   public void setTag(String var1, NBTBase var2) {
      this.tagMap.put(var1, var2.setKey(var1));
   }

   public void setByte(String var1, byte var2) {
      this.tagMap.put(var1, new NBTTagByte(var1, var2));
   }

   public void setShort(String var1, short var2) {
      this.tagMap.put(var1, new NBTTagShort(var1, var2));
   }

   public void setInteger(String var1, int var2) {
      this.tagMap.put(var1, new NBTTagInt(var1, var2));
   }

   public void setLong(String var1, long var2) {
      this.tagMap.put(var1, new NBTTagLong(var1, var2));
   }

   public void setFloat(String var1, float var2) {
      this.tagMap.put(var1, new NBTTagFloat(var1, var2));
   }

   public void setDouble(String var1, double var2) {
      this.tagMap.put(var1, new NBTTagDouble(var1, var2));
   }

   public void setString(String var1, String var2) {
      this.tagMap.put(var1, new NBTTagString(var1, var2));
   }

   public void setByteArray(String var1, byte[] var2) {
      this.tagMap.put(var1, new NBTTagByteArray(var1, var2));
   }

   public void setCompoundTag(String var1, NBTTagCompound var2) {
      this.tagMap.put(var1, var2.setKey(var1));
   }

   public void setBoolean(String var1, boolean var2) {
      this.setByte(var1, (byte)(var2?1:0));
   }

   public NBTBase func_40196_b(String var1) {
      return (NBTBase)this.tagMap.get(var1);
   }

   public boolean hasKey(String var1) {
      return this.tagMap.containsKey(var1);
   }

   public byte getByte(String var1) {
      return !this.tagMap.containsKey(var1)?0:((NBTTagByte)this.tagMap.get(var1)).byteValue;
   }

   public short getShort(String var1) {
      return !this.tagMap.containsKey(var1)?0:((NBTTagShort)this.tagMap.get(var1)).shortValue;
   }

   public int getInteger(String var1) {
      return !this.tagMap.containsKey(var1)?0:((NBTTagInt)this.tagMap.get(var1)).intValue;
   }

   public long getLong(String var1) {
      return !this.tagMap.containsKey(var1)?0L:((NBTTagLong)this.tagMap.get(var1)).longValue;
   }

   public float getFloat(String var1) {
      return !this.tagMap.containsKey(var1)?0.0F:((NBTTagFloat)this.tagMap.get(var1)).floatValue;
   }

   public double getDouble(String var1) {
      return !this.tagMap.containsKey(var1)?0.0D:((NBTTagDouble)this.tagMap.get(var1)).doubleValue;
   }

   public String getString(String var1) {
      return !this.tagMap.containsKey(var1)?"":((NBTTagString)this.tagMap.get(var1)).stringValue;
   }

   public byte[] getByteArray(String var1) {
      return !this.tagMap.containsKey(var1)?new byte[0]:((NBTTagByteArray)this.tagMap.get(var1)).byteArray;
   }

   public NBTTagCompound getCompoundTag(String var1) {
      return !this.tagMap.containsKey(var1)?new NBTTagCompound(var1):(NBTTagCompound)this.tagMap.get(var1);
   }

   public NBTTagList getTagList(String var1) {
      return !this.tagMap.containsKey(var1)?new NBTTagList(var1):(NBTTagList)this.tagMap.get(var1);
   }

   public boolean getBoolean(String var1) {
      return this.getByte(var1) != 0;
   }

   public String toString() {
      return "" + this.tagMap.size() + " entries";
   }

   public NBTBase func_40195_b() {
      NBTTagCompound var1 = new NBTTagCompound(this.getKey());

      for(java.util.Iterator var3_iter = (this.tagMap.keySet()).iterator(); var3_iter.hasNext();) {
          String var3 = (String) var3_iter.next();
         var1.setTag(var3, ((NBTBase)this.tagMap.get(var3)).func_40195_b());
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         NBTTagCompound var2 = (NBTTagCompound)var1;
         return this.tagMap.entrySet().equals(var2.tagMap.entrySet());
      } else {
         return false;
      }
   }
}
