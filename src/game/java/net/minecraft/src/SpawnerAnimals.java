package net.minecraft.src;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WeightedRandom;
import net.minecraft.src.World;

public final class SpawnerAnimals {
   private static HashMap eligibleChunksForSpawning = new HashMap();
   protected static final Class[] nightSpawnEntities = new Class[]{EntitySpider.class, EntityZombie.class, EntitySkeleton.class};

   protected static ChunkPosition getRandomSpawningPointInChunk(World var0, int var1, int var2) {
      int var3 = var1 + var0.rand.nextInt(16);
      int var4 = var0.rand.nextInt(var0.field_35472_c);
      int var5 = var2 + var0.rand.nextInt(16);
      return new ChunkPosition(var3, var4, var5);
   }

   public static final int performSpawning(World var0, boolean var1, boolean var2) {
      if(!var1 && !var2) {
         return 0;
      } else {
         eligibleChunksForSpawning.clear();

         for(int var3 = 0; var3 < var0.playerEntities.size(); ++var3) {
            EntityPlayer var4 = (EntityPlayer)var0.playerEntities.get(var3);
            int var5 = MathHelper.floor_double(var4.posX / 16.0D);
            int var6 = MathHelper.floor_double(var4.posZ / 16.0D);
            byte var7 = 8;

            for(int var8 = -var7; var8 <= var7; ++var8) {
               for(int var9 = -var7; var9 <= var7; ++var9) {
                  boolean var10 = var8 == -var7 || var8 == var7 || var9 == -var7 || var9 == var7;
                  ChunkCoordIntPair var11 = new ChunkCoordIntPair(var8 + var5, var9 + var6);
                  if(!var10) {
                     eligibleChunksForSpawning.put(var11, Boolean.valueOf(false));
                  } else if(!eligibleChunksForSpawning.containsKey(var11)) {
                     eligibleChunksForSpawning.put(var11, Boolean.valueOf(true));
                  }
               }
            }
         }

         int var31 = 0;
         ChunkCoordinates var32 = var0.getSpawnPoint();

         for(EnumCreatureType var36 : EnumCreatureType.values()) {
            if((!var36.getPeacefulCreature() || var2) && (var36.getPeacefulCreature() || var1) && var0.countEntities(var36.getCreatureClass()) <= var36.getMaxNumberOfCreature() * eligibleChunksForSpawning.size() / 256) {
               label337:
               for(java.util.Iterator var38_iter = (eligibleChunksForSpawning.keySet()).iterator(); var38_iter.hasNext();) {
                   ChunkCoordIntPair var38 = (ChunkCoordIntPair) var38_iter.next();
                  if(!((Boolean)eligibleChunksForSpawning.get(var38)).booleanValue()) {
                     ChunkPosition var39 = getRandomSpawningPointInChunk(var0, var38.chunkXPos * 16, var38.chunkZPos * 16);
                     int var12 = var39.x;
                     int var13 = var39.y;
                     int var14 = var39.z;
                     if(!var0.isBlockNormalCube(var12, var13, var14) && var0.getBlockMaterial(var12, var13, var14) == var36.getCreatureMaterial()) {
                        int var15 = 0;

                        for(int var16 = 0; var16 < 3; ++var16) {
                           int var17 = var12;
                           int var18 = var13;
                           int var19 = var14;
                           byte var20 = 6;
                           SpawnListEntry var21 = null;

                           for(int var22 = 0; var22 < 4; ++var22) {
                              var17 += var0.rand.nextInt(var20) - var0.rand.nextInt(var20);
                              var18 += var0.rand.nextInt(1) - var0.rand.nextInt(1);
                              var19 += var0.rand.nextInt(var20) - var0.rand.nextInt(var20);
                              if(canCreatureTypeSpawnAtLocation(var36, var0, var17, var18, var19)) {
                                 float var23 = (float)var17 + 0.5F;
                                 float var24 = (float)var18;
                                 float var25 = (float)var19 + 0.5F;
                                 if(var0.getClosestPlayer((double)var23, (double)var24, (double)var25, 24.0D) == null) {
                                    float var26 = var23 - (float)var32.posX;
                                    float var27 = var24 - (float)var32.posY;
                                    float var28 = var25 - (float)var32.posZ;
                                    float var29 = var26 * var26 + var27 * var27 + var28 * var28;
                                    if(var29 >= 576.0F) {
                                       if(var21 == null) {
                                          var21 = var0.func_40474_a(var36, var17, var18, var19);
                                          if(var21 == null) {
                                             break;
                                          }
                                       }

                                       EntityLiving var40 = null;

                                       try {
                                          var40 = (EntityLiving)var21.entityClass.createEntity(var0);
                                       } catch (Exception var30) {
                                          var30.printStackTrace();
                                          return var31;
                                       }

                                       var40.setLocationAndAngles((double)var23, (double)var24, (double)var25, var0.rand.nextFloat() * 360.0F, 0.0F);
                                       if(var40.getCanSpawnHere()) {
                                          ++var15;
                                          var0.entityJoinedWorld(var40);
                                          creatureSpecificInit(var40, var0, var23, var24, var25);
                                          if(var15 >= var40.getMaxSpawnedInChunk()) {
                                             continue label337;
                                          }
                                       }

                                       var31 += var15;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return var31;
      }
   }

   private static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType var0, World var1, int var2, int var3, int var4) {
      return var0.getCreatureMaterial() == Material.water?var1.getBlockMaterial(var2, var3, var4).getIsLiquid() && !var1.isBlockNormalCube(var2, var3 + 1, var4):var1.isBlockNormalCube(var2, var3 - 1, var4) && !var1.isBlockNormalCube(var2, var3, var4) && !var1.getBlockMaterial(var2, var3, var4).getIsLiquid() && !var1.isBlockNormalCube(var2, var3 + 1, var4);
   }

   private static void creatureSpecificInit(EntityLiving var0, World var1, float var2, float var3, float var4) {
      if(var0 instanceof EntitySpider && var1.rand.nextInt(100) == 0) {
         EntitySkeleton var5 = new EntitySkeleton(var1);
         var5.setLocationAndAngles((double)var2, (double)var3, (double)var4, var0.rotationYaw, 0.0F);
         var1.entityJoinedWorld(var5);
         var5.mountEntity(var0);
      } else if(var0 instanceof EntitySheep) {
         ((EntitySheep)var0).setFleeceColor(EntitySheep.getRandomFleeceColor(var1.rand));
      }

   }

   public static void func_35957_a(World var0, BiomeGenBase var1, int var2, int var3, int var4, int var5, Random var6) {
      List var7 = var1.getSpawnableList(EnumCreatureType.creature);
      if(!var7.isEmpty()) {
         while(var6.nextFloat() < var1.getSpawningChance()) {
            SpawnListEntry var8 = (SpawnListEntry)WeightedRandom.func_35733_a(var0.rand, var7);
            int var9 = var8.field_35591_b + var6.nextInt(1 + var8.field_35592_c - var8.field_35591_b);
            int var10 = var2 + var6.nextInt(var4);
            int var11 = var3 + var6.nextInt(var5);
            int var12 = var10;
            int var13 = var11;

            for(int var14 = 0; var14 < var9; ++var14) {
               boolean var15 = false;

               for(int var16 = 0; !var15 && var16 < 4; ++var16) {
                  int var17 = var0.getTopSolidOrLiquidBlock(var10, var11);
                  if(canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, var0, var10, var17, var11)) {
                     float var18 = (float)var10 + 0.5F;
                     float var19 = (float)var17;
                     float var20 = (float)var11 + 0.5F;

                     EntityLiving var21;
                     try {
                        var21 = (EntityLiving)var8.entityClass.createEntity(var0);
                     } catch (Exception var23) {
                        var23.printStackTrace();
                        continue;
                     }

                     var21.setLocationAndAngles((double)var18, (double)var19, (double)var20, var6.nextFloat() * 360.0F, 0.0F);
                     var0.entityJoinedWorld(var21);
                     creatureSpecificInit(var21, var0, var18, var19, var20);
                     var15 = true;
                  }

                  var10 += var6.nextInt(5) - var6.nextInt(5);

                  for(var11 += var6.nextInt(5) - var6.nextInt(5); var10 < var2 || var10 >= var2 + var4 || var11 < var3 || var11 >= var3 + var4; var11 = var13 + var6.nextInt(5) - var6.nextInt(5)) {
                     var10 = var12 + var6.nextInt(5) - var6.nextInt(5);
                  }
               }
            }
         }

      }
   }
}
