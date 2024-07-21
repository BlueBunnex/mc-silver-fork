package net.minecraft.src.entity;

import net.minecraft.src.DamageSource;
import net.minecraft.src.StatCollector;
import net.minecraft.src.item.ItemStack;

public class EntityDamageSource extends DamageSource {
	protected Entity damageSourceEntity;

	public EntityDamageSource(String var1, Entity var2) {
		super(var1);
		this.damageSourceEntity = var2;
	}

	public Entity getEntity() {
		return this.damageSourceEntity;
	}

	public String getDeathMessage(EntityLiving var1) {
		ItemStack var2 = this.damageSourceEntity instanceof EntityLiving ? ((EntityLiving)this.damageSourceEntity).getHeldItem() : null;
		String var3 = "death.attack." + this.damageType;
		String var4 = var3 + ".item";
		return var2 != null && var2.hasDisplayName() && StatCollector.func_94522_b(var4) ? StatCollector.translateToLocalFormatted(var4, new Object[]{var1.getTranslatedEntityName(), this.damageSourceEntity.getTranslatedEntityName(), var2.getDisplayName()}) : StatCollector.translateToLocalFormatted(var3, new Object[]{var1.getTranslatedEntityName(), this.damageSourceEntity.getTranslatedEntityName()});
	}

	public boolean isDifficultyScaled() {
		return this.damageSourceEntity != null && this.damageSourceEntity instanceof EntityLiving && !(this.damageSourceEntity instanceof EntityPlayer);
	}
}
