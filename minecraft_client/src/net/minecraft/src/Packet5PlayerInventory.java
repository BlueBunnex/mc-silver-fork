package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.item.ItemStack;

public class Packet5PlayerInventory extends Packet {
	public int entityID;
	public int slot;
	private ItemStack itemSlot;

	public Packet5PlayerInventory() {
	}

	public Packet5PlayerInventory(int var1, int var2, ItemStack var3) {
		this.entityID = var1;
		this.slot = var2;
		this.itemSlot = var3 == null ? null : var3.copy();
	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.entityID = var1.readInt();
		this.slot = var1.readShort();
		this.itemSlot = readItemStack(var1);
	}

	public void writePacketData(DataOutputStream var1) throws IOException {
		var1.writeInt(this.entityID);
		var1.writeShort(this.slot);
		writeItemStack(this.itemSlot, var1);
	}

	public void processPacket(NetHandler var1) {
		var1.handlePlayerInventory(this);
	}

	public int getPacketSize() {
		return 8;
	}

	public ItemStack getItemSlot() {
		return this.itemSlot;
	}

	public boolean isRealPacket() {
		return true;
	}

	public boolean containsSameEntityIDAs(Packet var1) {
		Packet5PlayerInventory var2 = (Packet5PlayerInventory)var1;
		return var2.entityID == this.entityID && var2.slot == this.slot;
	}
}
