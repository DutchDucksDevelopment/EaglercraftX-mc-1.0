package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityEnchantmentTable;
import net.minecraft.src.World;

public class BlockEnchantmentTable extends BlockContainer {
   protected BlockEnchantmentTable(int var1) {
      super(var1, 166, Material.rock);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
      this.setLightOpacity(0);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      super.randomDisplayTick(var1, var2, var3, var4, var5);

      for(int var6 = var2 - 2; var6 <= var2 + 2; ++var6) {
         for(int var7 = var4 - 2; var7 <= var4 + 2; ++var7) {
            if(var6 > var2 - 2 && var6 < var2 + 2 && var7 == var4 - 1) {
               var7 = var4 + 2;
            }

            if(var5.nextInt(16) == 0) {
               for(int var8 = var3; var8 <= var3 + 1; ++var8) {
                  if(var1.getBlockId(var6, var8, var7) == Block.bookShelf.blockID) {
                     if(!var1.isAirBlock((var6 - var2) / 2 + var2, var8, (var7 - var4) / 2 + var4)) {
                        break;
                     }

                     var1.spawnParticle("enchantmenttable", (double)var2 + 0.5D, (double)var3 + 2.0D, (double)var4 + 0.5D, (double)((float)(var6 - var2) + var5.nextFloat()) - 0.5D, (double)((float)(var8 - var3) - var5.nextFloat() - 1.0F), (double)((float)(var7 - var4) + var5.nextFloat()) - 0.5D);
                  }
               }
            }
         }
      }

   }

   public boolean isOpaqueCube() {
      return false;
   }

   public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
      return this.getBlockTextureFromSide(var1);
   }

   public int getBlockTextureFromSide(int var1) {
      return var1 == 0?this.blockIndexInTexture + 17:(var1 == 1?this.blockIndexInTexture:this.blockIndexInTexture + 16);
   }

   public TileEntity getBlockEntity() {
      return new TileEntityEnchantmentTable();
   }

   public boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      if(var1.multiplayerWorld) {
         return true;
      } else {
         var5.func_40181_c(var2, var3, var4);
         return true;
      }
   }
}
