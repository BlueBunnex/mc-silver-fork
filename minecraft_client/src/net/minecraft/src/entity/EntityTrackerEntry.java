package net.minecraft.src.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.src.DataWatcher;
import net.minecraft.src.IAnimals;
import net.minecraft.src.MapData;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet17Sleep;
import net.minecraft.src.Packet20NamedEntitySpawn;
import net.minecraft.src.Packet23VehicleSpawn;
import net.minecraft.src.Packet24MobSpawn;
import net.minecraft.src.Packet25EntityPainting;
import net.minecraft.src.Packet26EntityExpOrb;
import net.minecraft.src.Packet28EntityVelocity;
import net.minecraft.src.Packet31RelEntityMove;
import net.minecraft.src.Packet32EntityLook;
import net.minecraft.src.Packet33RelEntityMoveLook;
import net.minecraft.src.Packet34EntityTeleport;
import net.minecraft.src.Packet35EntityHeadRotation;
import net.minecraft.src.Packet39AttachEntity;
import net.minecraft.src.Packet40EntityMetadata;
import net.minecraft.src.Packet41EntityEffect;
import net.minecraft.src.Packet5PlayerInventory;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemMap;
import net.minecraft.src.item.ItemStack;

public class EntityTrackerEntry {
	public Entity myEntity;
	public int blocksDistanceThreshold;
	public int updateFrequency;
	public int lastScaledXPosition;
	public int lastScaledYPosition;
	public int lastScaledZPosition;
	public int lastYaw;
	public int lastPitch;
	public int lastHeadMotion;
	public double motionX;
	public double motionY;
	public double motionZ;
	public int ticks = 0;
	private double posX;
	private double posY;
	private double posZ;
	private boolean isDataInitialized = false;
	private boolean sendVelocityUpdates;
	private int ticksSinceLastForcedTeleport = 0;
	private Entity field_85178_v;
	private boolean ridingEntity = false;
	public boolean playerEntitiesUpdated = false;
	public Set trackingPlayers = new HashSet();

	public EntityTrackerEntry(Entity var1, int var2, int var3, boolean var4) {
		this.myEntity = var1;
		this.blocksDistanceThreshold = var2;
		this.updateFrequency = var3;
		this.sendVelocityUpdates = var4;
		this.lastScaledXPosition = MathHelper.floor_double(var1.posX * 32.0D);
		this.lastScaledYPosition = MathHelper.floor_double(var1.posY * 32.0D);
		this.lastScaledZPosition = MathHelper.floor_double(var1.posZ * 32.0D);
		this.lastYaw = MathHelper.floor_float(var1.rotationYaw * 256.0F / 360.0F);
		this.lastPitch = MathHelper.floor_float(var1.rotationPitch * 256.0F / 360.0F);
		this.lastHeadMotion = MathHelper.floor_float(var1.getRotationYawHead() * 256.0F / 360.0F);
	}

	public boolean equals(Object var1) {
		return var1 instanceof EntityTrackerEntry ? ((EntityTrackerEntry)var1).myEntity.entityId == this.myEntity.entityId : false;
	}

	public int hashCode() {
		return this.myEntity.entityId;
	}

	public void sendLocationToAllClients(List var1) {
		this.playerEntitiesUpdated = false;
		if(!this.isDataInitialized || this.myEntity.getDistanceSq(this.posX, this.posY, this.posZ) > 16.0D) {
			this.posX = this.myEntity.posX;
			this.posY = this.myEntity.posY;
			this.posZ = this.myEntity.posZ;
			this.isDataInitialized = true;
			this.playerEntitiesUpdated = true;
			this.sendEventsToPlayers(var1);
		}

		if(this.field_85178_v != this.myEntity.ridingEntity || this.myEntity.ridingEntity != null && this.ticks % 60 == 0) {
			this.field_85178_v = this.myEntity.ridingEntity;
			this.sendPacketToAllTrackingPlayers(new Packet39AttachEntity(this.myEntity, this.myEntity.ridingEntity));
		}

		if(this.myEntity instanceof EntityItemFrame && this.ticks % 10 == 0) {
			EntityItemFrame var23 = (EntityItemFrame)this.myEntity;
			ItemStack var24 = var23.getDisplayedItem();
			if(var24 != null && var24.getItem() instanceof ItemMap) {
				MapData var26 = Item.map.getMapData(var24, this.myEntity.worldObj);
				Iterator var29 = var1.iterator();

				while(var29.hasNext()) {
					EntityPlayer var30 = (EntityPlayer)var29.next();
					EntityPlayerMP var31 = (EntityPlayerMP)var30;
					var26.updateVisiblePlayers(var31, var24);
					if(var31.playerNetServerHandler.packetSize() <= 5) {
						Packet var32 = Item.map.createMapDataPacket(var24, this.myEntity.worldObj, var31);
						if(var32 != null) {
							var31.playerNetServerHandler.sendPacketToPlayer(var32);
						}
					}
				}
			}

			DataWatcher var27 = this.myEntity.getDataWatcher();
			if(var27.hasChanges()) {
				this.sendPacketToAllAssociatedPlayers(new Packet40EntityMetadata(this.myEntity.entityId, var27, false));
			}
		} else if(this.ticks % this.updateFrequency == 0 || this.myEntity.isAirBorne || this.myEntity.getDataWatcher().hasChanges()) {
			int var2;
			int var3;
			if(this.myEntity.ridingEntity == null) {
				++this.ticksSinceLastForcedTeleport;
				var2 = this.myEntity.myEntitySize.multiplyBy32AndRound(this.myEntity.posX);
				var3 = MathHelper.floor_double(this.myEntity.posY * 32.0D);
				int var25 = this.myEntity.myEntitySize.multiplyBy32AndRound(this.myEntity.posZ);
				int var28 = MathHelper.floor_float(this.myEntity.rotationYaw * 256.0F / 360.0F);
				int var6 = MathHelper.floor_float(this.myEntity.rotationPitch * 256.0F / 360.0F);
				int var7 = var2 - this.lastScaledXPosition;
				int var8 = var3 - this.lastScaledYPosition;
				int var9 = var25 - this.lastScaledZPosition;
				Object var10 = null;
				boolean var11 = Math.abs(var7) >= 4 || Math.abs(var8) >= 4 || Math.abs(var9) >= 4 || this.ticks % 60 == 0;
				boolean var12 = Math.abs(var28 - this.lastYaw) >= 4 || Math.abs(var6 - this.lastPitch) >= 4;
				if(this.ticks > 0 || this.myEntity instanceof EntityArrow) {
					if(var7 >= -128 && var7 < 128 && var8 >= -128 && var8 < 128 && var9 >= -128 && var9 < 128 && this.ticksSinceLastForcedTeleport <= 400 && !this.ridingEntity) {
						if(var11 && var12) {
							var10 = new Packet33RelEntityMoveLook(this.myEntity.entityId, (byte)var7, (byte)var8, (byte)var9, (byte)var28, (byte)var6);
						} else if(var11) {
							var10 = new Packet31RelEntityMove(this.myEntity.entityId, (byte)var7, (byte)var8, (byte)var9);
						} else if(var12) {
							var10 = new Packet32EntityLook(this.myEntity.entityId, (byte)var28, (byte)var6);
						}
					} else {
						this.ticksSinceLastForcedTeleport = 0;
						var10 = new Packet34EntityTeleport(this.myEntity.entityId, var2, var3, var25, (byte)var28, (byte)var6);
					}
				}

				if(this.sendVelocityUpdates) {
					double var13 = this.myEntity.motionX - this.motionX;
					double var15 = this.myEntity.motionY - this.motionY;
					double var17 = this.myEntity.motionZ - this.motionZ;
					double var19 = 0.02D;
					double var21 = var13 * var13 + var15 * var15 + var17 * var17;
					if(var21 > var19 * var19 || var21 > 0.0D && this.myEntity.motionX == 0.0D && this.myEntity.motionY == 0.0D && this.myEntity.motionZ == 0.0D) {
						this.motionX = this.myEntity.motionX;
						this.motionY = this.myEntity.motionY;
						this.motionZ = this.myEntity.motionZ;
						this.sendPacketToAllTrackingPlayers(new Packet28EntityVelocity(this.myEntity.entityId, this.motionX, this.motionY, this.motionZ));
					}
				}

				if(var10 != null) {
					this.sendPacketToAllTrackingPlayers((Packet)var10);
				}

				DataWatcher var33 = this.myEntity.getDataWatcher();
				if(var33.hasChanges()) {
					this.sendPacketToAllAssociatedPlayers(new Packet40EntityMetadata(this.myEntity.entityId, var33, false));
				}

				if(var11) {
					this.lastScaledXPosition = var2;
					this.lastScaledYPosition = var3;
					this.lastScaledZPosition = var25;
				}

				if(var12) {
					this.lastYaw = var28;
					this.lastPitch = var6;
				}

				this.ridingEntity = false;
			} else {
				var2 = MathHelper.floor_float(this.myEntity.rotationYaw * 256.0F / 360.0F);
				var3 = MathHelper.floor_float(this.myEntity.rotationPitch * 256.0F / 360.0F);
				boolean var4 = Math.abs(var2 - this.lastYaw) >= 4 || Math.abs(var3 - this.lastPitch) >= 4;
				if(var4) {
					this.sendPacketToAllTrackingPlayers(new Packet32EntityLook(this.myEntity.entityId, (byte)var2, (byte)var3));
					this.lastYaw = var2;
					this.lastPitch = var3;
				}

				this.lastScaledXPosition = this.myEntity.myEntitySize.multiplyBy32AndRound(this.myEntity.posX);
				this.lastScaledYPosition = MathHelper.floor_double(this.myEntity.posY * 32.0D);
				this.lastScaledZPosition = this.myEntity.myEntitySize.multiplyBy32AndRound(this.myEntity.posZ);
				DataWatcher var5 = this.myEntity.getDataWatcher();
				if(var5.hasChanges()) {
					this.sendPacketToAllAssociatedPlayers(new Packet40EntityMetadata(this.myEntity.entityId, var5, false));
				}

				this.ridingEntity = true;
			}

			var2 = MathHelper.floor_float(this.myEntity.getRotationYawHead() * 256.0F / 360.0F);
			if(Math.abs(var2 - this.lastHeadMotion) >= 4) {
				this.sendPacketToAllTrackingPlayers(new Packet35EntityHeadRotation(this.myEntity.entityId, (byte)var2));
				this.lastHeadMotion = var2;
			}

			this.myEntity.isAirBorne = false;
		}

		++this.ticks;
		if(this.myEntity.velocityChanged) {
			this.sendPacketToAllAssociatedPlayers(new Packet28EntityVelocity(this.myEntity));
			this.myEntity.velocityChanged = false;
		}

	}

	public void sendPacketToAllTrackingPlayers(Packet var1) {
		Iterator var2 = this.trackingPlayers.iterator();

		while(var2.hasNext()) {
			EntityPlayerMP var3 = (EntityPlayerMP)var2.next();
			var3.playerNetServerHandler.sendPacketToPlayer(var1);
		}

	}

	public void sendPacketToAllAssociatedPlayers(Packet var1) {
		this.sendPacketToAllTrackingPlayers(var1);
		if(this.myEntity instanceof EntityPlayerMP) {
			((EntityPlayerMP)this.myEntity).playerNetServerHandler.sendPacketToPlayer(var1);
		}

	}

	public void informAllAssociatedPlayersOfItemDestruction() {
		Iterator var1 = this.trackingPlayers.iterator();

		while(var1.hasNext()) {
			EntityPlayerMP var2 = (EntityPlayerMP)var1.next();
			var2.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
		}

	}

	public void removeFromWatchingList(EntityPlayerMP var1) {
		if(this.trackingPlayers.contains(var1)) {
			var1.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
			this.trackingPlayers.remove(var1);
		}

	}

	public void tryStartWachingThis(EntityPlayerMP var1) {
		if(var1 != this.myEntity) {
			double var2 = var1.posX - (double)(this.lastScaledXPosition / 32);
			double var4 = var1.posZ - (double)(this.lastScaledZPosition / 32);
			if(var2 >= (double)(-this.blocksDistanceThreshold) && var2 <= (double)this.blocksDistanceThreshold && var4 >= (double)(-this.blocksDistanceThreshold) && var4 <= (double)this.blocksDistanceThreshold) {
				if(!this.trackingPlayers.contains(var1) && (this.isPlayerWatchingThisChunk(var1) || this.myEntity.field_98038_p)) {
					this.trackingPlayers.add(var1);
					Packet var6 = this.getPacketForThisEntity();
					var1.playerNetServerHandler.sendPacketToPlayer(var6);
					if(!this.myEntity.getDataWatcher().getIsBlank()) {
						var1.playerNetServerHandler.sendPacketToPlayer(new Packet40EntityMetadata(this.myEntity.entityId, this.myEntity.getDataWatcher(), true));
					}

					this.motionX = this.myEntity.motionX;
					this.motionY = this.myEntity.motionY;
					this.motionZ = this.myEntity.motionZ;
					if(this.sendVelocityUpdates && !(var6 instanceof Packet24MobSpawn)) {
						var1.playerNetServerHandler.sendPacketToPlayer(new Packet28EntityVelocity(this.myEntity.entityId, this.myEntity.motionX, this.myEntity.motionY, this.myEntity.motionZ));
					}

					if(this.myEntity.ridingEntity != null) {
						var1.playerNetServerHandler.sendPacketToPlayer(new Packet39AttachEntity(this.myEntity, this.myEntity.ridingEntity));
					}

					if(this.myEntity instanceof EntityLiving) {
						for(int var7 = 0; var7 < 5; ++var7) {
							ItemStack var8 = ((EntityLiving)this.myEntity).getCurrentItemOrArmor(var7);
							if(var8 != null) {
								var1.playerNetServerHandler.sendPacketToPlayer(new Packet5PlayerInventory(this.myEntity.entityId, var7, var8));
							}
						}
					}

					if(this.myEntity instanceof EntityPlayer) {
						EntityPlayer var10 = (EntityPlayer)this.myEntity;
						if(var10.isPlayerSleeping()) {
							var1.playerNetServerHandler.sendPacketToPlayer(new Packet17Sleep(this.myEntity, 0, MathHelper.floor_double(this.myEntity.posX), MathHelper.floor_double(this.myEntity.posY), MathHelper.floor_double(this.myEntity.posZ)));
						}
					}

					if(this.myEntity instanceof EntityLiving) {
						EntityLiving var11 = (EntityLiving)this.myEntity;
						Iterator var12 = var11.getActivePotionEffects().iterator();

						while(var12.hasNext()) {
							PotionEffect var9 = (PotionEffect)var12.next();
							var1.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.myEntity.entityId, var9));
						}
					}
				}
			} else if(this.trackingPlayers.contains(var1)) {
				this.trackingPlayers.remove(var1);
				var1.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
			}

		}
	}

	private boolean isPlayerWatchingThisChunk(EntityPlayerMP var1) {
		return var1.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(var1, this.myEntity.chunkCoordX, this.myEntity.chunkCoordZ);
	}

	public void sendEventsToPlayers(List var1) {
		for(int var2 = 0; var2 < var1.size(); ++var2) {
			this.tryStartWachingThis((EntityPlayerMP)var1.get(var2));
		}

	}

	private Packet getPacketForThisEntity() {
		if(this.myEntity.isDead) {
			this.myEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity");
		}

		if(this.myEntity instanceof EntityItem) {
			return new Packet23VehicleSpawn(this.myEntity, 2, 1);
		} else if(this.myEntity instanceof EntityPlayerMP) {
			return new Packet20NamedEntitySpawn((EntityPlayer)this.myEntity);
		} else if(this.myEntity instanceof EntityMinecart) {
			EntityMinecart var8 = (EntityMinecart)this.myEntity;
			return new Packet23VehicleSpawn(this.myEntity, 10, var8.getMinecartType());
		} else if(this.myEntity instanceof EntityBoat) {
			return new Packet23VehicleSpawn(this.myEntity, 1);
		} else if(!(this.myEntity instanceof IAnimals) && !(this.myEntity instanceof EntityDragon)) {
			if(this.myEntity instanceof EntityFishHook) {
				EntityPlayer var7 = ((EntityFishHook)this.myEntity).angler;
				return new Packet23VehicleSpawn(this.myEntity, 90, var7 != null ? var7.entityId : this.myEntity.entityId);
			} else if(this.myEntity instanceof EntityArrow) {
				Entity var6 = ((EntityArrow)this.myEntity).shootingEntity;
				return new Packet23VehicleSpawn(this.myEntity, 60, var6 != null ? var6.entityId : this.myEntity.entityId);
			} else if(this.myEntity instanceof EntitySnowball) {
				return new Packet23VehicleSpawn(this.myEntity, 61);
			} else if(this.myEntity instanceof EntityPotion) {
				return new Packet23VehicleSpawn(this.myEntity, 73, ((EntityPotion)this.myEntity).getPotionDamage());
			} else if(this.myEntity instanceof EntityExpBottle) {
				return new Packet23VehicleSpawn(this.myEntity, 75);
			} else if(this.myEntity instanceof EntityEnderPearl) {
				return new Packet23VehicleSpawn(this.myEntity, 65);
			} else if(this.myEntity instanceof EntityEnderEye) {
				return new Packet23VehicleSpawn(this.myEntity, 72);
			} else if(this.myEntity instanceof EntityFireworkRocket) {
				return new Packet23VehicleSpawn(this.myEntity, 76);
			} else {
				Packet23VehicleSpawn var2;
				if(this.myEntity instanceof EntityFireball) {
					EntityFireball var5 = (EntityFireball)this.myEntity;
					var2 = null;
					byte var3 = 63;
					if(this.myEntity instanceof EntitySmallFireball) {
						var3 = 64;
					} else if(this.myEntity instanceof EntityWitherSkull) {
						var3 = 66;
					}

					if(var5.shootingEntity != null) {
						var2 = new Packet23VehicleSpawn(this.myEntity, var3, ((EntityFireball)this.myEntity).shootingEntity.entityId);
					} else {
						var2 = new Packet23VehicleSpawn(this.myEntity, var3, 0);
					}

					var2.speedX = (int)(var5.accelerationX * 8000.0D);
					var2.speedY = (int)(var5.accelerationY * 8000.0D);
					var2.speedZ = (int)(var5.accelerationZ * 8000.0D);
					return var2;
				} else if(this.myEntity instanceof EntityEgg) {
					return new Packet23VehicleSpawn(this.myEntity, 62);
				} else if(this.myEntity instanceof EntityTNTPrimed) {
					return new Packet23VehicleSpawn(this.myEntity, 50);
				} else if(this.myEntity instanceof EntityEnderCrystal) {
					return new Packet23VehicleSpawn(this.myEntity, 51);
				} else if(this.myEntity instanceof EntityFallingSand) {
					EntityFallingSand var4 = (EntityFallingSand)this.myEntity;
					return new Packet23VehicleSpawn(this.myEntity, 70, var4.blockID | var4.metadata << 16);
				} else if(this.myEntity instanceof EntityPainting) {
					return new Packet25EntityPainting((EntityPainting)this.myEntity);
				} else if(this.myEntity instanceof EntityItemFrame) {
					EntityItemFrame var1 = (EntityItemFrame)this.myEntity;
					var2 = new Packet23VehicleSpawn(this.myEntity, 71, var1.hangingDirection);
					var2.xPosition = MathHelper.floor_float((float)(var1.xPosition * 32));
					var2.yPosition = MathHelper.floor_float((float)(var1.yPosition * 32));
					var2.zPosition = MathHelper.floor_float((float)(var1.zPosition * 32));
					return var2;
				} else if(this.myEntity instanceof EntityXPOrb) {
					return new Packet26EntityExpOrb((EntityXPOrb)this.myEntity);
				} else {
					throw new IllegalArgumentException("Don\'t know how to add " + this.myEntity.getClass() + "!");
				}
			}
		} else {
			this.lastHeadMotion = MathHelper.floor_float(this.myEntity.getRotationYawHead() * 256.0F / 360.0F);
			return new Packet24MobSpawn((EntityLiving)this.myEntity);
		}
	}

	public void removePlayerFromTracker(EntityPlayerMP var1) {
		if(this.trackingPlayers.contains(var1)) {
			this.trackingPlayers.remove(var1);
			var1.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
		}

	}
}
