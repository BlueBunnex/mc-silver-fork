package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.entity.EntityRenderer;

public class CallableScreenName implements Callable {
	
	private final EntityRenderer entityRender;

	public CallableScreenName(EntityRenderer var1) {
		this.entityRender = var1;
	}

	public String callScreenName() {
		return EntityRenderer.getRendererMinecraft(this.entityRender).currentScreen.getClass().getCanonicalName();
	}

	public Object call() {
		return this.callScreenName();
	}
}
