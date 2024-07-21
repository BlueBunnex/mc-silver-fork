package net.minecraft.src;

public final class StepSoundAnvil extends StepSound {
	
	public StepSoundAnvil(String var1, float var2, float var3) {
		super(var1, var2, var3);
	}

	public String getBreakSound() {
		return "dig.stone";
	}

	public String getPlaceSound() {
		return "random.anvil_land";
	}
}
