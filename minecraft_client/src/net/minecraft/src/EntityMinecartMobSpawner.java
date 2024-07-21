package net.minecraft.src;

import net.minecraft.src.block.Block;

public class EntityMinecartMobSpawner extends EntityMinecart {
	private final MobSpawnerBaseLogic mobSpawnerLogic = new EntityMinecartMobSpawnerLogic(this);

	public EntityMinecartMobSpawner(World var1) {
		super(var1);
	}

	public EntityMinecartMobSpawner(World var1, double var2, double var4, double var6) {
		super(var1, var2, var4, var6);
	}

	public int getMinecartType() {
		return 4;
	}

	public Block getDefaultDisplayTile() {
		return Block.mobSpawner;
	}

	protected void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.mobSpawnerLogic.readFromNBT(var1);
	}

	protected void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		this.mobSpawnerLogic.writeToNBT(var1);
	}

	public void handleHealthUpdate(byte var1) {
		this.mobSpawnerLogic.setDelayToMin(var1);
	}

	public void onUpdate() {
		super.onUpdate();
		this.mobSpawnerLogic.updateSpawner();
	}

	public MobSpawnerBaseLogic func_98039_d() {
		return this.mobSpawnerLogic;
	}
}
