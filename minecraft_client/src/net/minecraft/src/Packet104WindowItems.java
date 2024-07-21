package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Packet104WindowItems extends Packet {
	public int windowId;
	public ItemStack[] itemStack;

	public Packet104WindowItems() {
	}

	public Packet104WindowItems(int var1, List var2) {
		this.windowId = var1;
		this.itemStack = new ItemStack[var2.size()];

		for(int var3 = 0; var3 < this.itemStack.length; ++var3) {
			ItemStack var4 = (ItemStack)var2.get(var3);
			this.itemStack[var3] = var4 == null ? null : var4.copy();
		}

	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.windowId = var1.readByte();
		short var2 = var1.readShort();
		this.itemStack = new ItemStack[var2];

		for(int var3 = 0; var3 < var2; ++var3) {
			this.itemStack[var3] = readItemStack(var1);
		}

	}

	public void writePacketData(DataOutputStream var1) throws IOException {
		var1.writeByte(this.windowId);
		var1.writeShort(this.itemStack.length);

		for(int var2 = 0; var2 < this.itemStack.length; ++var2) {
			writeItemStack(this.itemStack[var2], var1);
		}

	}

	public void processPacket(NetHandler var1) {
		var1.handleWindowItems(this);
	}

	public int getPacketSize() {
		return 3 + this.itemStack.length * 5;
	}
}
