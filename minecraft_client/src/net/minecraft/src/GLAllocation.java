package net.minecraft.src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.lwjgl.opengl.GL11;

public class GLAllocation {
	private static final Map field_74531_a = new HashMap();
	private static final List field_74530_b = new ArrayList();

	public static synchronized int generateDisplayLists(int var0) {
		int var1 = GL11.glGenLists(var0);
		field_74531_a.put(Integer.valueOf(var1), Integer.valueOf(var0));
		return var1;
	}

	public static synchronized int generateTextureNames() {
		int var0 = GL11.glGenTextures();
		field_74530_b.add(Integer.valueOf(var0));
		return var0;
	}

	public static synchronized void deleteDisplayLists(int var0) {
		GL11.glDeleteLists(var0, ((Integer)field_74531_a.remove(Integer.valueOf(var0))).intValue());
	}

	public static synchronized void func_98302_b() {
		for(int var0 = 0; var0 < field_74530_b.size(); ++var0) {
			GL11.glDeleteTextures(((Integer)field_74530_b.get(var0)).intValue());
		}

		field_74530_b.clear();
	}

	public static synchronized void deleteTexturesAndDisplayLists() {
		Iterator var0 = field_74531_a.entrySet().iterator();

		while(var0.hasNext()) {
			Entry var1 = (Entry)var0.next();
			GL11.glDeleteLists(((Integer)var1.getKey()).intValue(), ((Integer)var1.getValue()).intValue());
		}

		field_74531_a.clear();
		func_98302_b();
	}

	public static synchronized ByteBuffer createDirectByteBuffer(int var0) {
		return ByteBuffer.allocateDirect(var0).order(ByteOrder.nativeOrder());
	}

	public static IntBuffer createDirectIntBuffer(int var0) {
		return createDirectByteBuffer(var0 << 2).asIntBuffer();
	}

	public static FloatBuffer createDirectFloatBuffer(int var0) {
		return createDirectByteBuffer(var0 << 2).asFloatBuffer();
	}
}
