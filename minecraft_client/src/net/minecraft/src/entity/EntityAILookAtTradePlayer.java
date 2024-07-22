package net.minecraft.src.entity;

public class EntityAILookAtTradePlayer extends EntityAIWatchClosest {
	private final EntityVillager theMerchant;

	public EntityAILookAtTradePlayer(EntityVillager var1) {
		super(var1, EntityPlayer.class, 8.0F);
		this.theMerchant = var1;
	}

	public boolean shouldExecute() {
		if(this.theMerchant.isTrading()) {
			this.closestEntity = this.theMerchant.getCustomer();
			return true;
		} else {
			return false;
		}
	}
}
