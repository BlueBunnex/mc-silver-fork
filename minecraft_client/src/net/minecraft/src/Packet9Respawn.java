package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet9Respawn extends Packet {
	public int respawnDimension;
	public int difficulty;
	public int worldHeight;
	public EnumGameType gameType;
	public WorldType terrainType;

	public Packet9Respawn() {
	}

	public Packet9Respawn(int var1, byte var2, WorldType var3, int var4, EnumGameType var5) {
		this.respawnDimension = var1;
		this.difficulty = var2;
		this.worldHeight = var4;
		this.gameType = var5;
		this.terrainType = var3;
	}

	public void processPacket(NetHandler var1) {
		var1.handleRespawn(this);
	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.respawnDimension = var1.readInt();
		this.difficulty = var1.readByte();
		this.gameType = EnumGameType.getByID(var1.readByte());
		this.worldHeight = var1.readShort();
		String var2 = readString(var1, 16);
		this.terrainType = WorldType.parseWorldType(var2);
		if(this.terrainType == null) {
			this.terrainType = WorldType.DEFAULT;
		}

	}

	public void writePacketData(DataOutputStream var1) throws IOException {
		var1.writeInt(this.respawnDimension);
		var1.writeByte(this.difficulty);
		var1.writeByte(this.gameType.getID());
		var1.writeShort(this.worldHeight);
		writeString(this.terrainType.getWorldTypeName(), var1);
	}

	public int getPacketSize() {
		return 8 + (this.terrainType == null ? 0 : this.terrainType.getWorldTypeName().length());
	}
}
