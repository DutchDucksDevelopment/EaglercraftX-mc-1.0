package net.minecraft.src;

import net.minecraft.src.Block;
import net.minecraft.src.BlockEndPortalFrame;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.Direction;
import net.minecraft.src.EntityEnderEye;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;

public class ItemEnderEye extends Item {
   public ItemEnderEye(int var1) {
      super(var1);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
      int var8 = var3.getBlockId(var4, var5, var6);
      int var9 = var3.getBlockMetadata(var4, var5, var6);
      if(var2.func_35190_e(var4, var5, var6) && var8 == Block.endPortalFrame.blockID && !BlockEndPortalFrame.func_40212_d(var9)) {
         if(var3.multiplayerWorld) {
            return true;
         } else {
            var3.setBlockMetadataWithNotify(var4, var5, var6, var9 + 4);
            --var1.stackSize;

            for(int var10 = 0; var10 < 16; ++var10) {
               double var11 = (double)((float)var4 + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
               double var13 = (double)((float)var5 + 0.8125F);
               double var15 = (double)((float)var6 + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
               double var17 = 0.0D;
               double var19 = 0.0D;
               double var21 = 0.0D;
               var3.spawnParticle("smoke", var11, var13, var15, var17, var19, var21);
            }

            int var23 = var9 & 3;
            int var24 = 0;
            int var12 = 0;
            boolean var25 = false;
            boolean var14 = true;
            int var26 = Direction.field_35867_f[var23];

            for(int var16 = -2; var16 <= 2; ++var16) {
               int var30 = var4 + Direction.field_35871_a[var26] * var16;
               int var18 = var6 + Direction.field_35870_b[var26] * var16;
               int var41 = var3.getBlockId(var30, var5, var18);
               if(var41 == Block.endPortalFrame.blockID) {
                  int var20 = var3.getBlockMetadata(var30, var5, var18);
                  if(!BlockEndPortalFrame.func_40212_d(var20)) {
                     var14 = false;
                     break;
                  }

                  if(!var25) {
                     var24 = var16;
                     var12 = var16;
                     var25 = true;
                  } else {
                     var12 = var16;
                  }
               }
            }

            if(var14 && var12 == var24 + 2) {
               for(int var27 = var24; var27 <= var12; ++var27) {
                  int var31 = var4 + Direction.field_35871_a[var26] * var27;
                  int var35 = var6 + Direction.field_35870_b[var26] * var27;
                  var31 = var31 + Direction.field_35871_a[var23] * 4;
                  var35 = var35 + Direction.field_35870_b[var23] * 4;
                  int var42 = var3.getBlockId(var31, var5, var35);
                  int var47 = var3.getBlockMetadata(var31, var5, var35);
                  if(var42 != Block.endPortalFrame.blockID || !BlockEndPortalFrame.func_40212_d(var47)) {
                     var14 = false;
                     break;
                  }
               }

               for(int var28 = var24 - 1; var28 <= var12 + 1; var28 += 4) {
                  for(int var33 = 1; var33 <= 3; ++var33) {
                     int var37 = var4 + Direction.field_35871_a[var26] * var28;
                     int var43 = var6 + Direction.field_35870_b[var26] * var28;
                     var37 = var37 + Direction.field_35871_a[var23] * var33;
                     var43 = var43 + Direction.field_35870_b[var23] * var33;
                     int var48 = var3.getBlockId(var37, var5, var43);
                     int var49 = var3.getBlockMetadata(var37, var5, var43);
                     if(var48 != Block.endPortalFrame.blockID || !BlockEndPortalFrame.func_40212_d(var49)) {
                        var14 = false;
                        break;
                     }
                  }
               }

               if(var14) {
                  for(int var29 = var24; var29 <= var12; ++var29) {
                     for(int var34 = 1; var34 <= 3; ++var34) {
                        int var39 = var4 + Direction.field_35871_a[var26] * var29;
                        int var45 = var6 + Direction.field_35870_b[var26] * var29;
                        var39 = var39 + Direction.field_35871_a[var23] * var34;
                        var45 = var45 + Direction.field_35870_b[var23] * var34;
                        var3.setBlockWithNotify(var39, var5, var45, Block.endPortal.blockID);
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      MovingObjectPosition var4 = this.func_40402_a(var2, var3, false);
      if(var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE) {
         int var5 = var2.getBlockId(var4.blockX, var4.blockY, var4.blockZ);
         if(var5 == Block.endPortalFrame.blockID) {
            return var1;
         }
      }

      if(!var2.multiplayerWorld) {
         ChunkPosition var7 = var2.func_40477_b("Stronghold", (int)var3.posX, (int)var3.posY, (int)var3.posZ);
         if(var7 != null) {
            EntityEnderEye var6 = new EntityEnderEye(var2, var3.posX, var3.posY + 1.62D - (double)var3.yOffset, var3.posZ);
            var6.func_40090_a((double)var7.x, var7.y, (double)var7.z);
            var2.entityJoinedWorld(var6);
            var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            var2.playAuxSFXAtEntity((EntityPlayer)null, 1002, (int)var3.posX, (int)var3.posY, (int)var3.posZ, 0);
            if(!var3.capabilities.depleteBuckets) {
               --var1.stackSize;
            }
         }
      }

      return var1;
   }
}
