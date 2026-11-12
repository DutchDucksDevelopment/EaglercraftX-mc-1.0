package net.minecraft.src;

import net.minecraft.src.ContainerEnchantment;
import net.minecraft.src.InventoryBasic;

class SlotEnchantmentTable extends InventoryBasic {
   // $FF: synthetic field
   final ContainerEnchantment field_40070_a;

   SlotEnchantmentTable(ContainerEnchantment var1, String var2, int var3) {
      super(var2, var3);
      this.field_40070_a = var1;
   }

   public int getInventoryStackLimit() {
      return 1;
   }

   public void onInventoryChanged() {
      super.onInventoryChanged();
      this.field_40070_a.onCraftMatrixChanged(this);
   }
}
