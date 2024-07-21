package net.minecraft.src.callable;

import java.util.concurrent.Callable;
import org.lwjgl.input.Mouse;

import net.minecraft.src.entity.EntityRenderer;

public class CallableMouseLocation implements Callable {
	
	private final int field_90026_a;
	private final int field_90024_b;
	private final EntityRenderer theEntityRenderer;

	public CallableMouseLocation(EntityRenderer var1, int var2, int var3) {
		this.theEntityRenderer = var1;
		this.field_90026_a = var2;
		this.field_90024_b = var3;
	}

	public String callMouseLocation() {
		return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[]{Integer.valueOf(this.field_90026_a), Integer.valueOf(this.field_90024_b), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY())});
	}

	public Object call() {
		return this.callMouseLocation();
	}
}
