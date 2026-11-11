package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemColored extends ItemBlock {
   private final Block field_35436_a = Block.blocksList[this.getBlockID()];
   private String[] field_41034_b;

   public ItemColored(int var1, boolean var2) {
      super(var1);
      if(var2) {
         this.setMaxDamage(0);
         this.setHasSubtypes(true);
      }

   }

   public int getColorFromDamage(int var1) {
      return this.field_35436_a.getRenderColor(var1);
   }

   public int getIconFromDamage(int var1) {
      return this.field_35436_a.getBlockTextureFromSideAndMetadata(0, var1);
   }

   public int getPlacedBlockMetadata(int var1) {
      return var1;
   }

   public ItemColored func_41033_a(String[] var1) {
      this.field_41034_b = var1;
      return this;
   }

   public String getItemNameIS(ItemStack var1) {
      if(this.field_41034_b == null) {
         return super.getItemNameIS(var1);
      } else {
         int var2 = var1.getItemDamage();
         return var2 >= 0 && var2 < this.field_41034_b.length?super.getItemNameIS(var1) + "." + this.field_41034_b[var2]:super.getItemNameIS(var1);
      }
   }
}
