package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;

public class CallableTagCompound2 implements Callable {
	
	private final int field_82588_a;
	private final NBTTagCompound theNBTTagCompound;

	public CallableTagCompound2(NBTTagCompound var1, int var2) {
		this.theNBTTagCompound = var1;
		this.field_82588_a = var2;
	}

	public String func_82586_a() {
		return NBTBase.NBTTypes[this.field_82588_a];
	}

	public Object call() {
		return this.func_82586_a();
	}
}
