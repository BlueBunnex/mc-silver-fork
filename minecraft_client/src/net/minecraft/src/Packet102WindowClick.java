package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.item.ItemStack;

public class Packet102WindowClick extends Packet {
	public int window_Id;
	public int inventorySlot;
	public int mouseClick;
	public short action;
	public ItemStack itemStack;
	public int holdingShift;

	public Packet102WindowClick() {
	}

	public Packet102WindowClick(int var1, int var2, int var3, int var4, ItemStack var5, short var6) {
		this.window_Id = var1;
		this.inventorySlot = var2;
		this.mouseClick = var3;
		this.itemStack = var5 != null ? var5.copy() : null;
		this.action = var6;
		this.holdingShift = var4;
	}

	public void processPacket(NetHandler var1) {
		var1.handleWindowClick(this);
	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.window_Id = var1.readByte();
		this.inventorySlot = var1.readShort();
		this.mouseClick = var1.readByte();
		this.action = var1.readShort();
		this.holdingShift = var1.readByte();
		this.itemStack = readItemStack(var1);
	}

	public void writePacketData(DataOutputStream var1) throws IOException {
		var1.writeByte(this.window_Id);
		var1.writeShort(this.inventorySlot);
		var1.writeByte(this.mouseClick);
		var1.writeShort(this.action);
		var1.writeByte(this.holdingShift);
		writeItemStack(this.itemStack, var1);
	}

	public int getPacketSize() {
		return 11;
	}
}
