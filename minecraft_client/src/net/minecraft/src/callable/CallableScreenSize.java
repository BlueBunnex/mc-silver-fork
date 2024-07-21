package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.EntityRenderer;
import net.minecraft.src.ScaledResolution;

public class CallableScreenSize implements Callable {
	
	private final ScaledResolution theScaledResolution;
	private final EntityRenderer theEntityRenderer;

	public CallableScreenSize(EntityRenderer var1, ScaledResolution var2) {
		this.theEntityRenderer = var1;
		this.theScaledResolution = var2;
	}

	public String callScreenSize() {
		return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", new Object[]{Integer.valueOf(this.theScaledResolution.getScaledWidth()), Integer.valueOf(this.theScaledResolution.getScaledHeight()), Integer.valueOf(EntityRenderer.getRendererMinecraft(this.theEntityRenderer).displayWidth), Integer.valueOf(EntityRenderer.getRendererMinecraft(this.theEntityRenderer).displayHeight), Integer.valueOf(this.theScaledResolution.getScaleFactor())});
	}

	public Object call() {
		return this.callScreenSize();
	}
}
