package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.entity.EntityZombie;
import net.minecraft.src.worldgen.World;

public class VillageSiege {
	private World worldObj;
	private boolean field_75535_b = false;
	private int field_75536_c = -1;
	private int field_75533_d;
	private int field_75534_e;
	private Village theVillage;
	private int field_75532_g;
	private int field_75538_h;
	private int field_75539_i;

	public VillageSiege(World var1) {
		this.worldObj = var1;
	}

	public void tick() {
		boolean var1 = false;
		if(var1) {
			if(this.field_75536_c == 2) {
				this.field_75533_d = 100;
				return;
			}
		} else {
			if(this.worldObj.isDaytime()) {
				this.field_75536_c = 0;
				return;
			}

			if(this.field_75536_c == 2) {
				return;
			}

			if(this.field_75536_c == 0) {
				float var2 = this.worldObj.getCelestialAngle(0.0F);
				if((double)var2 < 0.5D || (double)var2 > 0.501D) {
					return;
				}

				this.field_75536_c = this.worldObj.rand.nextInt(10) == 0 ? 1 : 2;
				this.field_75535_b = false;
				if(this.field_75536_c == 2) {
					return;
				}
			}
		}

		if(!this.field_75535_b) {
			if(!this.func_75529_b()) {
				return;
			}

			this.field_75535_b = true;
		}

		if(this.field_75534_e > 0) {
			--this.field_75534_e;
		} else {
			this.field_75534_e = 2;
			if(this.field_75533_d > 0) {
				this.spawnZombie();
				--this.field_75533_d;
			} else {
				this.field_75536_c = 2;
			}

		}
	}

	private boolean func_75529_b() {
		List var1 = this.worldObj.playerEntities;
		Iterator var2 = var1.iterator();

		Vec3 var10;
		do {
			do {
				do {
					do {
						do {
							if(!var2.hasNext()) {
								return false;
							}

							EntityPlayer var3 = (EntityPlayer)var2.next();
							this.theVillage = this.worldObj.villageCollectionObj.findNearestVillage((int)var3.posX, (int)var3.posY, (int)var3.posZ, 1);
						} while(this.theVillage == null);
					} while(this.theVillage.getNumVillageDoors() < 10);
				} while(this.theVillage.getTicksSinceLastDoorAdding() < 20);
			} while(this.theVillage.getNumVillagers() < 20);

			ChunkCoordinates var4 = this.theVillage.getCenter();
			float var5 = (float)this.theVillage.getVillageRadius();
			boolean var6 = false;

			for(int var7 = 0; var7 < 10; ++var7) {
				this.field_75532_g = var4.posX + (int)((double)(MathHelper.cos(this.worldObj.rand.nextFloat() * (float)Math.PI * 2.0F) * var5) * 0.9D);
				this.field_75538_h = var4.posY;
				this.field_75539_i = var4.posZ + (int)((double)(MathHelper.sin(this.worldObj.rand.nextFloat() * (float)Math.PI * 2.0F) * var5) * 0.9D);
				var6 = false;
				Iterator var8 = this.worldObj.villageCollectionObj.getVillageList().iterator();

				while(var8.hasNext()) {
					Village var9 = (Village)var8.next();
					if(var9 != this.theVillage && var9.isInRange(this.field_75532_g, this.field_75538_h, this.field_75539_i)) {
						var6 = true;
						break;
					}
				}

				if(!var6) {
					break;
				}
			}

			if(var6) {
				return false;
			}

			var10 = this.func_75527_a(this.field_75532_g, this.field_75538_h, this.field_75539_i);
		} while(var10 == null);

		this.field_75534_e = 0;
		this.field_75533_d = 20;
		return true;
	}

	private boolean spawnZombie() {
		Vec3 var1 = this.func_75527_a(this.field_75532_g, this.field_75538_h, this.field_75539_i);
		if(var1 == null) {
			return false;
		} else {
			EntityZombie var2;
			try {
				var2 = new EntityZombie(this.worldObj);
				var2.initCreature();
				var2.setVillager(false);
			} catch (Exception var4) {
				var4.printStackTrace();
				return false;
			}

			var2.setLocationAndAngles(var1.xCoord, var1.yCoord, var1.zCoord, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
			this.worldObj.spawnEntityInWorld(var2);
			ChunkCoordinates var3 = this.theVillage.getCenter();
			var2.setHomeArea(var3.posX, var3.posY, var3.posZ, this.theVillage.getVillageRadius());
			return true;
		}
	}

	private Vec3 func_75527_a(int var1, int var2, int var3) {
		for(int var4 = 0; var4 < 10; ++var4) {
			int var5 = var1 + this.worldObj.rand.nextInt(16) - 8;
			int var6 = var2 + this.worldObj.rand.nextInt(6) - 3;
			int var7 = var3 + this.worldObj.rand.nextInt(16) - 8;
			if(this.theVillage.isInRange(var5, var6, var7) && SpawnerAnimals.canCreatureTypeSpawnAtLocation(EnumCreatureType.monster, this.worldObj, var5, var6, var7)) {
				this.worldObj.getWorldVec3Pool().getVecFromPool((double)var5, (double)var6, (double)var7);
			}
		}

		return null;
	}
}
