package net.minecraft.src;

import net.minecraft.src.LongHashMapEntry;

public class LongHashMap {
   private transient LongHashMapEntry[] playerListEntries = new LongHashMapEntry[16];
   private transient int numHashElements;
   private int capacity = 12;
   private final float percent = 0.75F;
   private transient volatile int field_35581_e;

   private static int getHashedKey(long var0) {
      return hash((int)(var0 ^ var0 >>> 32));
   }

   private static int hash(int var0) {
      var0 = var0 ^ var0 >>> 20 ^ var0 >>> 12;
      return var0 ^ var0 >>> 7 ^ var0 >>> 4;
   }

   private static int getHashIndex(int var0, int var1) {
      return var0 & var1 - 1;
   }

   public int getNumHashElements() {
      return this.numHashElements;
   }

   public Object getValueByKey(long var1) {
      int var3 = getHashedKey(var1);

      for(LongHashMapEntry var4 = this.playerListEntries[getHashIndex(var3, this.playerListEntries.length)]; var4 != null; var4 = var4.field_35833_c) {
         if(var4.field_35834_a == var1) {
            return var4.field_35832_b;
         }
      }

      return null;
   }

   public boolean func_35575_b(long var1) {
      return this.func_35569_c(var1) != null;
   }

   final LongHashMapEntry func_35569_c(long var1) {
      int var3 = getHashedKey(var1);

      for(LongHashMapEntry var4 = this.playerListEntries[getHashIndex(var3, this.playerListEntries.length)]; var4 != null; var4 = var4.field_35833_c) {
         if(var4.field_35834_a == var1) {
            return var4;
         }
      }

      return null;
   }

   public void add(long var1, Object var3) {
      int var4 = getHashedKey(var1);
      int var5 = getHashIndex(var4, this.playerListEntries.length);

      for(LongHashMapEntry var6 = this.playerListEntries[var5]; var6 != null; var6 = var6.field_35833_c) {
         if(var6.field_35834_a == var1) {
            var6.field_35832_b = var3;
         }
      }

      ++this.field_35581_e;
      this.createKey(var4, var1, var3, var5);
   }

   private void resizeTable(int var1) {
      LongHashMapEntry[] var2 = this.playerListEntries;
      int var3 = var2.length;
      if(var3 == 1073741824) {
         this.capacity = Integer.MAX_VALUE;
      } else {
         LongHashMapEntry[] var4 = new LongHashMapEntry[var1];
         this.copyHashTableTo(var4);
         this.playerListEntries = var4;
         this.capacity = (int)((float)var1 * this.percent);
      }
   }

   private void copyHashTableTo(LongHashMapEntry[] var1) {
      LongHashMapEntry[] var2 = this.playerListEntries;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var2.length; ++var4) {
         LongHashMapEntry var5 = var2[var4];
         if(var5 != null) {
            var2[var4] = null;

            while(true) {
               LongHashMapEntry var6 = var5.field_35833_c;
               int var7 = getHashIndex(var5.field_35831_d, var3);
               var5.field_35833_c = var1[var7];
               var1[var7] = var5;
               var5 = var6;
               if(var6 == null) {
                  break;
               }
            }
         }
      }

   }

   public Object remove(long var1) {
      LongHashMapEntry var3 = this.removeKey(var1);
      return var3 == null?null:var3.field_35832_b;
   }

   final LongHashMapEntry removeKey(long var1) {
      int var3 = getHashedKey(var1);
      int var4 = getHashIndex(var3, this.playerListEntries.length);
      LongHashMapEntry var5 = this.playerListEntries[var4];

      LongHashMapEntry var6;
      LongHashMapEntry var7;
      for(var6 = var5; var6 != null; var6 = var7) {
         var7 = var6.field_35833_c;
         if(var6.field_35834_a == var1) {
            ++this.field_35581_e;
            --this.numHashElements;
            if(var5 == var6) {
               this.playerListEntries[var4] = var7;
            } else {
               var5.field_35833_c = var7;
            }

            return var6;
         }

         var5 = var6;
      }

      return var6;
   }

   private void createKey(int var1, long var2, Object var4, int var5) {
      LongHashMapEntry var6 = this.playerListEntries[var5];
      this.playerListEntries[var5] = new LongHashMapEntry(var1, var2, var4, var6);
      if(this.numHashElements++ >= this.capacity) {
         this.resizeTable(2 * this.playerListEntries.length);
      }

   }

   // $FF: synthetic method
   static int getHashCode(long var0) {
      return getHashedKey(var0);
   }
}
