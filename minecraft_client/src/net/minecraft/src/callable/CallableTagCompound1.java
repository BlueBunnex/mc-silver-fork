package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;

public class CallableTagCompound1 implements Callable {
	
	private final String field_82585_a;
	private final NBTTagCompound theNBTTagCompound;

	public CallableTagCompound1(NBTTagCompound var1, String var2) {
		this.theNBTTagCompound = var1;
		this.field_82585_a = var2;
	}

	public String func_82583_a() {
		return NBTBase.NBTTypes[((NBTBase)NBTTagCompound.getTagMap(this.theNBTTagCompound).get(this.field_82585_a)).getId()];
	}

	public Object call() {
		return this.func_82583_a();
	}
}
