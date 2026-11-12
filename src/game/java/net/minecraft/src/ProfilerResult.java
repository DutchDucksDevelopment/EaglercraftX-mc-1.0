package net.minecraft.src;

public final class ProfilerResult implements Comparable {
   public double field_40704_a;
   public double field_40702_b;
   public String field_40703_c;

   public ProfilerResult(String var1, double var2, double var4) {
      this.field_40703_c = var1;
      this.field_40704_a = var2;
      this.field_40702_b = var4;
   }

   public int func_40701_a(ProfilerResult var1) {
      return var1.field_40704_a < this.field_40704_a?-1:(var1.field_40704_a > this.field_40704_a?1:var1.field_40703_c.compareTo(this.field_40703_c));
   }

   public int func_40700_a() {
      return (this.field_40703_c.hashCode() & 11184810) + 4473924;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.func_40701_a((ProfilerResult)var1);
   }
}
