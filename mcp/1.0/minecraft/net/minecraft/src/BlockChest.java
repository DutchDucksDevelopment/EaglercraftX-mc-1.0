package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryLargeChest;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;

public class BlockChest extends BlockContainer {
   private Random random = new Random();

   protected BlockChest(int var1) {
      super(var1, Material.wood);
      this.blockIndexInTexture = 26;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 22;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      this.unifyAdjacentChests(var1, var2, var3, var4);
      int var5 = var1.getBlockId(var2, var3, var4 - 1);
      int var6 = var1.getBlockId(var2, var3, var4 + 1);
      int var7 = var1.getBlockId(var2 - 1, var3, var4);
      int var8 = var1.getBlockId(var2 + 1, var3, var4);
      if(var5 == this.blockID) {
         this.unifyAdjacentChests(var1, var2, var3, var4 - 1);
      }

      if(var6 == this.blockID) {
         this.unifyAdjacentChests(var1, var2, var3, var4 + 1);
      }

      if(var7 == this.blockID) {
         this.unifyAdjacentChests(var1, var2 - 1, var3, var4);
      }

      if(var8 == this.blockID) {
         this.unifyAdjacentChests(var1, var2 + 1, var3, var4);
      }

   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5) {
      int var6 = var1.getBlockId(var2, var3, var4 - 1);
      int var7 = var1.getBlockId(var2, var3, var4 + 1);
      int var8 = var1.getBlockId(var2 - 1, var3, var4);
      int var9 = var1.getBlockId(var2 + 1, var3, var4);
      byte var10 = 0;
      int var11 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      if(var11 == 0) {
         var10 = 2;
      }

      if(var11 == 1) {
         var10 = 5;
      }

      if(var11 == 2) {
         var10 = 3;
      }

      if(var11 == 3) {
         var10 = 4;
      }

      if(var6 != this.blockID && var7 != this.blockID && var8 != this.blockID && var9 != this.blockID) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var10);
      } else {
         if((var6 == this.blockID || var7 == this.blockID) && (var10 == 4 || var10 == 5)) {
            if(var6 == this.blockID) {
               var1.setBlockMetadataWithNotify(var2, var3, var4 - 1, var10);
            } else {
               var1.setBlockMetadataWithNotify(var2, var3, var4 + 1, var10);
            }

            var1.setBlockMetadataWithNotify(var2, var3, var4, var10);
         }

         if((var8 == this.blockID || var9 == this.blockID) && (var10 == 2 || var10 == 3)) {
            if(var8 == this.blockID) {
               var1.setBlockMetadataWithNotify(var2 - 1, var3, var4, var10);
            } else {
               var1.setBlockMetadataWithNotify(var2 + 1, var3, var4, var10);
            }

            var1.setBlockMetadataWithNotify(var2, var3, var4, var10);
         }
      }

   }

   public void unifyAdjacentChests(World var1, int var2, int var3, int var4) {
      if(!var1.multiplayerWorld) {
         int var5 = var1.getBlockId(var2, var3, var4 - 1);
         int var6 = var1.getBlockId(var2, var3, var4 + 1);
         int var7 = var1.getBlockId(var2 - 1, var3, var4);
         int var8 = var1.getBlockId(var2 + 1, var3, var4);
         byte var9 = 4;
         if(var5 != this.blockID && var6 != this.blockID) {
            if(var7 != this.blockID && var8 != this.blockID) {
               var9 = 3;
               if(Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6]) {
                  var9 = 3;
               }

               if(Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5]) {
                  var9 = 2;
               }

               if(Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8]) {
                  var9 = 5;
               }

               if(Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7]) {
                  var9 = 4;
               }
            } else {
               int var14 = var1.getBlockId(var7 == this.blockID?var2 - 1:var2 + 1, var3, var4 - 1);
               int var15 = var1.getBlockId(var7 == this.blockID?var2 - 1:var2 + 1, var3, var4 + 1);
               var9 = 3;
               int var17 = -1;
               if(var7 == this.blockID) {
                  var17 = var1.getBlockMetadata(var2 - 1, var3, var4);
               } else {
                  var17 = var1.getBlockMetadata(var2 + 1, var3, var4);
               }

               if(var17 == 2) {
                  var9 = 2;
               }

               if((Block.opaqueCubeLookup[var5] || Block.opaqueCubeLookup[var14]) && !Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var15]) {
                  var9 = 3;
               }

               if((Block.opaqueCubeLookup[var6] || Block.opaqueCubeLookup[var15]) && !Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var14]) {
                  var9 = 2;
               }
            }
         } else {
            int var10 = var1.getBlockId(var2 - 1, var3, var5 == this.blockID?var4 - 1:var4 + 1);
            int var11 = var1.getBlockId(var2 + 1, var3, var5 == this.blockID?var4 - 1:var4 + 1);
            var9 = 5;
            int var12 = -1;
            if(var5 == this.blockID) {
               var12 = var1.getBlockMetadata(var2, var3, var4 - 1);
            } else {
               var12 = var1.getBlockMetadata(var2, var3, var4 + 1);
            }

            if(var12 == 4) {
               var9 = 4;
            }

            if((Block.opaqueCubeLookup[var7] || Block.opaqueCubeLookup[var10]) && !Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var11]) {
               var9 = 5;
            }

            if((Block.opaqueCubeLookup[var8] || Block.opaqueCubeLookup[var11]) && !Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var10]) {
               var9 = 4;
            }
         }

         var1.setBlockMetadataWithNotify(var2, var3, var4, var9);
      }
   }

   public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(var5 == 1) {
         return this.blockIndexInTexture - 1;
      } else if(var5 == 0) {
         return this.blockIndexInTexture - 1;
      } else {
         int var6 = var1.getBlockId(var2, var3, var4 - 1);
         int var7 = var1.getBlockId(var2, var3, var4 + 1);
         int var8 = var1.getBlockId(var2 - 1, var3, var4);
         int var9 = var1.getBlockId(var2 + 1, var3, var4);
         if(var6 != this.blockID && var7 != this.blockID) {
            if(var8 != this.blockID && var9 != this.blockID) {
               byte var15 = 3;
               if(Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var7]) {
                  var15 = 3;
               }

               if(Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var6]) {
                  var15 = 2;
               }

               if(Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var9]) {
                  var15 = 5;
               }

               if(Block.opaqueCubeLookup[var9] && !Block.opaqueCubeLookup[var8]) {
                  var15 = 4;
               }

               return var5 == var15?this.blockIndexInTexture + 1:this.blockIndexInTexture;
            } else if(var5 != 4 && var5 != 5) {
               int var14 = 0;
               if(var8 == this.blockID) {
                  var14 = -1;
               }

               int var16 = var1.getBlockId(var8 == this.blockID?var2 - 1:var2 + 1, var3, var4 - 1);
               int var17 = var1.getBlockId(var8 == this.blockID?var2 - 1:var2 + 1, var3, var4 + 1);
               if(var5 == 3) {
                  var14 = -1 - var14;
               }

               byte var18 = 3;
               if((Block.opaqueCubeLookup[var6] || Block.opaqueCubeLookup[var16]) && !Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var17]) {
                  var18 = 3;
               }

               if((Block.opaqueCubeLookup[var7] || Block.opaqueCubeLookup[var17]) && !Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var16]) {
                  var18 = 2;
               }

               return (var5 == var18?this.blockIndexInTexture + 16:this.blockIndexInTexture + 32) + var14;
            } else {
               return this.blockIndexInTexture;
            }
         } else if(var5 != 2 && var5 != 3) {
            int var10 = 0;
            if(var6 == this.blockID) {
               var10 = -1;
            }

            int var11 = var1.getBlockId(var2 - 1, var3, var6 == this.blockID?var4 - 1:var4 + 1);
            int var12 = var1.getBlockId(var2 + 1, var3, var6 == this.blockID?var4 - 1:var4 + 1);
            if(var5 == 4) {
               var10 = -1 - var10;
            }

            byte var13 = 5;
            if((Block.opaqueCubeLookup[var8] || Block.opaqueCubeLookup[var11]) && !Block.opaqueCubeLookup[var9] && !Block.opaqueCubeLookup[var12]) {
               var13 = 5;
            }

            if((Block.opaqueCubeLookup[var9] || Block.opaqueCubeLookup[var12]) && !Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var11]) {
               var13 = 4;
            }

            return (var5 == var13?this.blockIndexInTexture + 16:this.blockIndexInTexture + 32) + var10;
         } else {
            return this.blockIndexInTexture;
         }
      }
   }

   public int getBlockTextureFromSide(int var1) {
      return var1 == 1?this.blockIndexInTexture - 1:(var1 == 0?this.blockIndexInTexture - 1:(var1 == 3?this.blockIndexInTexture + 1:this.blockIndexInTexture));
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      int var5 = 0;
      if(var1.getBlockId(var2 - 1, var3, var4) == this.blockID) {
         ++var5;
      }

      if(var1.getBlockId(var2 + 1, var3, var4) == this.blockID) {
         ++var5;
      }

      if(var1.getBlockId(var2, var3, var4 - 1) == this.blockID) {
         ++var5;
      }

      if(var1.getBlockId(var2, var3, var4 + 1) == this.blockID) {
         ++var5;
      }

      return var5 > 1?false:(this.isThereANeighborChest(var1, var2 - 1, var3, var4)?false:(this.isThereANeighborChest(var1, var2 + 1, var3, var4)?false:(this.isThereANeighborChest(var1, var2, var3, var4 - 1)?false:!this.isThereANeighborChest(var1, var2, var3, var4 + 1))));
   }

   private boolean isThereANeighborChest(World var1, int var2, int var3, int var4) {
      return var1.getBlockId(var2, var3, var4) != this.blockID?false:(var1.getBlockId(var2 - 1, var3, var4) == this.blockID?true:(var1.getBlockId(var2 + 1, var3, var4) == this.blockID?true:(var1.getBlockId(var2, var3, var4 - 1) == this.blockID?true:var1.getBlockId(var2, var3, var4 + 1) == this.blockID)));
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
      super.onNeighborBlockChange(var1, var2, var3, var4, var5);
      TileEntityChest var6 = (TileEntityChest)var1.getBlockTileEntity(var2, var3, var4);
      if(var6 != null) {
         var6.func_35144_b();
      }

   }

   public void onBlockRemoval(World var1, int var2, int var3, int var4) {
      TileEntityChest var5 = (TileEntityChest)var1.getBlockTileEntity(var2, var3, var4);
      if(var5 != null) {
         for(int var6 = 0; var6 < var5.getSizeInventory(); ++var6) {
            ItemStack var7 = var5.getStackInSlot(var6);
            if(var7 != null) {
               float var8 = this.random.nextFloat() * 0.8F + 0.1F;
               float var9 = this.random.nextFloat() * 0.8F + 0.1F;

               EntityItem var12;
               for(float var10 = this.random.nextFloat() * 0.8F + 0.1F; var7.stackSize > 0; var1.entityJoinedWorld(var12)) {
                  int var11 = this.random.nextInt(21) + 10;
                  if(var11 > var7.stackSize) {
                     var11 = var7.stackSize;
                  }

                  var7.stackSize -= var11;
                  var12 = new EntityItem(var1, (double)((float)var2 + var8), (double)((float)var3 + var9), (double)((float)var4 + var10), new ItemStack(var7.itemID, var11, var7.getItemDamage()));
                  float var13 = 0.05F;
                  var12.motionX = (double)((float)this.random.nextGaussian() * var13);
                  var12.motionY = (double)((float)this.random.nextGaussian() * var13 + 0.2F);
                  var12.motionZ = (double)((float)this.random.nextGaussian() * var13);
                  if(var7.func_40710_n()) {
                     var12.item.func_40706_d((NBTTagCompound)var7.func_40709_o().func_40195_b());
                  }
               }
            }
         }
      }

      super.onBlockRemoval(var1, var2, var3, var4);
   }

   public boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      Object var6 = (TileEntityChest)var1.getBlockTileEntity(var2, var3, var4);
      if(var6 == null) {
         return true;
      } else if(var1.isBlockNormalCube(var2, var3 + 1, var4)) {
         return true;
      } else if(var1.getBlockId(var2 - 1, var3, var4) == this.blockID && var1.isBlockNormalCube(var2 - 1, var3 + 1, var4)) {
         return true;
      } else if(var1.getBlockId(var2 + 1, var3, var4) == this.blockID && var1.isBlockNormalCube(var2 + 1, var3 + 1, var4)) {
         return true;
      } else if(var1.getBlockId(var2, var3, var4 - 1) == this.blockID && var1.isBlockNormalCube(var2, var3 + 1, var4 - 1)) {
         return true;
      } else if(var1.getBlockId(var2, var3, var4 + 1) == this.blockID && var1.isBlockNormalCube(var2, var3 + 1, var4 + 1)) {
         return true;
      } else {
         if(var1.getBlockId(var2 - 1, var3, var4) == this.blockID) {
            var6 = new InventoryLargeChest("Large chest", (TileEntityChest)var1.getBlockTileEntity(var2 - 1, var3, var4), (IInventory)var6);
         }

         if(var1.getBlockId(var2 + 1, var3, var4) == this.blockID) {
            var6 = new InventoryLargeChest("Large chest", (IInventory)var6, (TileEntityChest)var1.getBlockTileEntity(var2 + 1, var3, var4));
         }

         if(var1.getBlockId(var2, var3, var4 - 1) == this.blockID) {
            var6 = new InventoryLargeChest("Large chest", (TileEntityChest)var1.getBlockTileEntity(var2, var3, var4 - 1), (IInventory)var6);
         }

         if(var1.getBlockId(var2, var3, var4 + 1) == this.blockID) {
            var6 = new InventoryLargeChest("Large chest", (IInventory)var6, (TileEntityChest)var1.getBlockTileEntity(var2, var3, var4 + 1));
         }

         if(var1.multiplayerWorld) {
            return true;
         } else {
            var5.displayGUIChest((IInventory)var6);
            return true;
         }
      }
   }

   public TileEntity getBlockEntity() {
      return new TileEntityChest();
   }
}
