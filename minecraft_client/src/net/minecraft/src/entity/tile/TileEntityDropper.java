package net.minecraft.src.entity.tile;

public class TileEntityDropper extends TileEntityDispenser {
	public String getInvName() {
		return this.isInvNameLocalized() ? this.customName : "container.dropper";
	}
}
