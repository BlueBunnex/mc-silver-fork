package net.minecraft.src.worldgen;

public class WorldType {
	public static final WorldType[] worldTypes = new WorldType[16];
	public static final WorldType DEFAULT = (new WorldType(0, "default", 1)).setVersioned();
	public static final WorldType FLAT = new WorldType(1, "flat");
	public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
	public static final WorldType DEFAULT_1_1 = (new WorldType(8, "default_1_1", 0)).setCanBeCreated(false);
	private final int worldTypeId;
	private final String worldType;
	private final int generatorVersion;
	private boolean canBeCreated;
	private boolean isWorldTypeVersioned;

	private WorldType(int var1, String var2) {
		this(var1, var2, 0);
	}

	private WorldType(int var1, String var2, int var3) {
		this.worldType = var2;
		this.generatorVersion = var3;
		this.canBeCreated = true;
		this.worldTypeId = var1;
		worldTypes[var1] = this;
	}

	public String getWorldTypeName() {
		return this.worldType;
	}

	public String getTranslateName() {
		return "generator." + this.worldType;
	}

	public int getGeneratorVersion() {
		return this.generatorVersion;
	}

	public WorldType getWorldTypeForGeneratorVersion(int var1) {
		return this == DEFAULT && var1 == 0 ? DEFAULT_1_1 : this;
	}

	private WorldType setCanBeCreated(boolean var1) {
		this.canBeCreated = var1;
		return this;
	}

	public boolean getCanBeCreated() {
		return this.canBeCreated;
	}

	private WorldType setVersioned() {
		this.isWorldTypeVersioned = true;
		return this;
	}

	public boolean isVersioned() {
		return this.isWorldTypeVersioned;
	}

	public static WorldType parseWorldType(String var0) {
		for(int var1 = 0; var1 < worldTypes.length; ++var1) {
			if(worldTypes[var1] != null && worldTypes[var1].worldType.equalsIgnoreCase(var0)) {
				return worldTypes[var1];
			}
		}

		return null;
	}

	public int getWorldTypeID() {
		return this.worldTypeId;
	}
}
