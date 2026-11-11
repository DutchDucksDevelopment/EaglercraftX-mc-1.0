package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.Direction;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.StatList;
import net.minecraft.src.World;

public class BlockVine extends Block {
   public BlockVine(int var1) {
      super(var1, 143, Material.vine);
      this.setTickOnLoad(true);
   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public int getRenderType() {
      return 20;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      float var7 = 1.0F;
      float var8 = 1.0F;
      float var9 = 1.0F;
      float var10 = 0.0F;
      float var11 = 0.0F;
      float var12 = 0.0F;
      boolean var13 = var6 > 0;
      if((var6 & 2) != 0) {
         var10 = Math.max(var10, 0.0625F);
         var7 = 0.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var9 = 0.0F;
         var12 = 1.0F;
         var13 = true;
      }

      if((var6 & 8) != 0) {
         var7 = Math.min(var7, 0.9375F);
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var9 = 0.0F;
         var12 = 1.0F;
         var13 = true;
      }

      if((var6 & 4) != 0) {
         var12 = Math.max(var12, 0.0625F);
         var9 = 0.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var13 = true;
      }

      if((var6 & 1) != 0) {
         var9 = Math.min(var9, 0.9375F);
         var12 = 1.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var13 = true;
      }

      if(!var13 && this.canBePlacedOn(var1.getBlockId(var2, var3 + 1, var4))) {
         var8 = Math.min(var8, 0.9375F);
         var11 = 1.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var9 = 0.0F;
         var12 = 1.0F;
      }

      this.setBlockBounds(var7, var8, var9, var10, var11, var12);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      switch(var5) {
      case 1:
         return this.canBePlacedOn(var1.getBlockId(var2, var3 + 1, var4));
      case 2:
         return this.canBePlacedOn(var1.getBlockId(var2, var3, var4 + 1));
      case 3:
         return this.canBePlacedOn(var1.getBlockId(var2, var3, var4 - 1));
      case 4:
         return this.canBePlacedOn(var1.getBlockId(var2 + 1, var3, var4));
      case 5:
         return this.canBePlacedOn(var1.getBlockId(var2 - 1, var3, var4));
      default:
         return false;
      }
   }

   private boolean canBePlacedOn(int var1) {
      if(var1 == 0) {
         return false;
      } else {
         Block var2 = Block.blocksList[var1];
         return var2.renderAsNormalBlock() && var2.blockMaterial.getIsSolid();
      }
   }

   private boolean canVineStay(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = var5;
      if(var5 > 0) {
         for(int var7 = 0; var7 <= 3; ++var7) {
            int var8 = 1 << var7;
            if((var5 & var8) != 0 && !this.canBePlacedOn(var1.getBlockId(var2 + Direction.field_35871_a[var7], var3, var4 + Direction.field_35870_b[var7])) && (var1.getBlockId(var2, var3 + 1, var4) != this.blockID || (var1.getBlockMetadata(var2, var3 + 1, var4) & var8) == 0)) {
               var6 &= ~var8;
            }
         }
      }

      if(var6 == 0 && !this.canBePlacedOn(var1.getBlockId(var2, var3 + 1, var4))) {
         return false;
      } else {
         if(var6 != var5) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
         }

         return true;
      }
   }

   public int getBlockColor() {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   public int getRenderColor(int var1) {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      return var1.getWorldChunkManager().getBiomeGenAt(var2, var4).func_40255_b(var1, var2, var3, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
      if(!var1.multiplayerWorld && !this.canVineStay(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockWithNotify(var2, var3, var4, 0);
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.multiplayerWorld && var1.rand.nextInt(4) == 0) {
         byte var6 = 4;
         int var7 = 5;
         boolean var8 = false;

         label72:
         for(int var9 = var2 - var6; var9 <= var2 + var6; ++var9) {
            for(int var10 = var4 - var6; var10 <= var4 + var6; ++var10) {
               for(int var11 = var3 - 1; var11 <= var3 + 1; ++var11) {
                  if(var1.getBlockId(var9, var11, var10) == this.blockID) {
                     --var7;
                     if(var7 <= 0) {
                        var8 = true;
                        break label72;
                     }
                  }
               }
            }
         }

         int var15 = var1.getBlockMetadata(var2, var3, var4);
         int var16 = var1.rand.nextInt(6);
         int var17 = Direction.field_35869_d[var16];
         if(var16 == 1 && var3 < var1.field_35472_c - 1 && var1.isAirBlock(var2, var3 + 1, var4)) {
            if(var8) {
               return;
            }

            int var19 = var1.rand.nextInt(16) & var15;
            if(var19 > 0) {
               for(int var22 = 0; var22 <= 3; ++var22) {
                  if(!this.canBePlacedOn(var1.getBlockId(var2 + Direction.field_35871_a[var22], var3 + 1, var4 + Direction.field_35870_b[var22]))) {
                     var19 &= ~(1 << var22);
                  }
               }

               if(var19 > 0) {
                  var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, this.blockID, var19);
               }
            }
         } else if(var16 >= 2 && var16 <= 5 && (var15 & 1 << var17) == 0) {
            if(var8) {
               return;
            }

            int var18 = var1.getBlockId(var2 + Direction.field_35871_a[var17], var3, var4 + Direction.field_35870_b[var17]);
            if(var18 != 0 && Block.blocksList[var18] != null) {
               if(Block.blocksList[var18].blockMaterial.getIsOpaque() && Block.blocksList[var18].renderAsNormalBlock()) {
                  var1.setBlockMetadataWithNotify(var2, var3, var4, var15 | 1 << var17);
               }
            } else {
               int var21 = var17 + 1 & 3;
               int var23 = var17 + 3 & 3;
               if((var15 & 1 << var21) != 0 && this.canBePlacedOn(var1.getBlockId(var2 + Direction.field_35871_a[var17] + Direction.field_35871_a[var21], var3, var4 + Direction.field_35870_b[var17] + Direction.field_35870_b[var21]))) {
                  var1.setBlockAndMetadataWithNotify(var2 + Direction.field_35871_a[var17], var3, var4 + Direction.field_35870_b[var17], this.blockID, 1 << var21);
               } else if((var15 & 1 << var23) != 0 && this.canBePlacedOn(var1.getBlockId(var2 + Direction.field_35871_a[var17] + Direction.field_35871_a[var23], var3, var4 + Direction.field_35870_b[var17] + Direction.field_35870_b[var23]))) {
                  var1.setBlockAndMetadataWithNotify(var2 + Direction.field_35871_a[var17], var3, var4 + Direction.field_35870_b[var17], this.blockID, 1 << var23);
               } else if((var15 & 1 << var21) != 0 && var1.isAirBlock(var2 + Direction.field_35871_a[var17] + Direction.field_35871_a[var21], var3, var4 + Direction.field_35870_b[var17] + Direction.field_35870_b[var21]) && this.canBePlacedOn(var1.getBlockId(var2 + Direction.field_35871_a[var21], var3, var4 + Direction.field_35870_b[var21]))) {
                  var1.setBlockAndMetadataWithNotify(var2 + Direction.field_35871_a[var17] + Direction.field_35871_a[var21], var3, var4 + Direction.field_35870_b[var17] + Direction.field_35870_b[var21], this.blockID, 1 << (var17 + 2 & 3));
               } else if((var15 & 1 << var23) != 0 && var1.isAirBlock(var2 + Direction.field_35871_a[var17] + Direction.field_35871_a[var23], var3, var4 + Direction.field_35870_b[var17] + Direction.field_35870_b[var23]) && this.canBePlacedOn(var1.getBlockId(var2 + Direction.field_35871_a[var23], var3, var4 + Direction.field_35870_b[var23]))) {
                  var1.setBlockAndMetadataWithNotify(var2 + Direction.field_35871_a[var17] + Direction.field_35871_a[var23], var3, var4 + Direction.field_35870_b[var17] + Direction.field_35870_b[var23], this.blockID, 1 << (var17 + 2 & 3));
               } else if(this.canBePlacedOn(var1.getBlockId(var2 + Direction.field_35871_a[var17], var3 + 1, var4 + Direction.field_35870_b[var17]))) {
                  var1.setBlockAndMetadataWithNotify(var2 + Direction.field_35871_a[var17], var3, var4 + Direction.field_35870_b[var17], this.blockID, 0);
               }
            }
         } else if(var3 > 1) {
            int var12 = var1.getBlockId(var2, var3 - 1, var4);
            if(var12 == 0) {
               int var13 = var1.rand.nextInt(16) & var15;
               if(var13 > 0) {
                  var1.setBlockAndMetadataWithNotify(var2, var3 - 1, var4, this.blockID, var13);
               }
            } else if(var12 == this.blockID) {
               int var20 = var1.rand.nextInt(16) & var15;
               int var14 = var1.getBlockMetadata(var2, var3 - 1, var4);
               if(var14 != (var14 | var20)) {
                  var1.setBlockMetadataWithNotify(var2, var3 - 1, var4, var14 | var20);
               }
            }
         }
      }

   }

   public void onBlockPlaced(World var1, int var2, int var3, int var4, int var5) {
      byte var6 = 0;
      switch(var5) {
      case 2:
         var6 = 1;
         break;
      case 3:
         var6 = 4;
         break;
      case 4:
         var6 = 8;
         break;
      case 5:
         var6 = 2;
      }

      if(var6 != 0) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
      }

   }

   public int idDropped(int var1, Random var2, int var3) {
      return 0;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      if(!var1.multiplayerWorld && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().itemID == Item.shears.shiftedIndex) {
         var2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
         this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(Block.vine, 1, 0));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5, var6);
      }

   }
}
