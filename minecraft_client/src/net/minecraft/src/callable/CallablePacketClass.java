package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.NetServerHandler;
import net.minecraft.src.Packet;

public class CallablePacketClass implements Callable {
	
	private final Packet thePacket;
	private final NetServerHandler theNetServerHandler;

	public CallablePacketClass(NetServerHandler var1, Packet var2) {
		this.theNetServerHandler = var1;
		this.thePacket = var2;
	}

	public String getPacketClass() {
		return this.thePacket.getClass().getCanonicalName();
	}

	public Object call() {
		return this.getPacketClass();
	}
}
