package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.Entity;

public class CallableEntityName implements Callable {
	
	private final Entity theEntity;

	public CallableEntityName(Entity var1) {
		this.theEntity = var1;
	}

	public String callEntityName() {
		return this.theEntity.getEntityName();
	}

	public Object call() {
		return this.callEntityName();
	}
}
