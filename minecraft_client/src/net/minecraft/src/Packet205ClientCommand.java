package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet205ClientCommand extends Packet {
	public int forceRespawn;

	public Packet205ClientCommand() {
	}

	public Packet205ClientCommand(int var1) {
		this.forceRespawn = var1;
	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.forceRespawn = var1.readByte();
	}

	public void writePacketData(DataOutputStream var1) throws IOException {
		var1.writeByte(this.forceRespawn & 255);
	}

	public void processPacket(NetHandler var1) {
		var1.handleClientCommand(this);
	}

	public int getPacketSize() {
		return 1;
	}
}
