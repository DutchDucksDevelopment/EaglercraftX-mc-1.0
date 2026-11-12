package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.src.IThreadedFileIO;

public class ThreadedFileIOBase implements Runnable {
   public static final ThreadedFileIOBase field_40573_a = new ThreadedFileIOBase();
   private List field_40571_b = Collections.synchronizedList(new ArrayList());
   private volatile long field_40572_c = 0L;
   private volatile long field_40569_d = 0L;
   private volatile boolean field_40570_e = false;

   private ThreadedFileIOBase() {
   }

   public void run() {
      this.func_40568_b();
   }

   private void func_40568_b() {
      for(int var1 = 0; var1 < this.field_40571_b.size(); ++var1) {
         IThreadedFileIO var2 = (IThreadedFileIO)this.field_40571_b.get(var1);
         boolean var3 = var2.func_40550_A_();
         if(!var3) {
            this.field_40571_b.remove(var1--);
            ++this.field_40569_d;
         }
      }

   }

   public void func_40567_a(IThreadedFileIO var1) {
      if(!this.field_40571_b.contains(var1)) {
         ++this.field_40572_c;
         this.field_40571_b.add(var1);
         this.func_40568_b();
      }
   }

   public void func_40566_a() throws InterruptedException {
   }
}
