package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableTagCompound1 implements Callable {
	final String field_82585_a;
	final NBTTagCompound theNBTTagCompound;

	CallableTagCompound1(NBTTagCompound var1, String var2) {
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
