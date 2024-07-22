package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.NetServerHandler;
import net.minecraft.src.Packet;

public class CallablePacketID implements Callable {
	
	private final Packet thePacket;
	private final NetServerHandler theNetServerHandler;

	public CallablePacketID(NetServerHandler var1, Packet var2) {
		this.theNetServerHandler = var1;
		this.thePacket = var2;
	}

	public String callPacketID() {
		return String.valueOf(this.thePacket.getPacketId());
	}

	public Object call() {
		return this.callPacketID();
	}
}
