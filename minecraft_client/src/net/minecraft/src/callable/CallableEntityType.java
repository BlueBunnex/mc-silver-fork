package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityList;

public class CallableEntityType implements Callable {
	
	private final Entity theEntity;

	public CallableEntityType(Entity var1) {
		this.theEntity = var1;
	}

	public String callEntityType() {
		return EntityList.getEntityString(this.theEntity) + " (" + this.theEntity.getClass().getCanonicalName() + ")";
	}

	public Object call() {
		return this.callEntityType();
	}
}
