package net.minecraft.src.entity.tile;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet130UpdateSign;

public class TileEntitySign extends TileEntity {
	public String[] signText = new String[]{"", "", "", ""};
	public int lineBeingEdited = -1;
	private boolean isEditable = true;

	public void writeToNBT(NBTTagCompound var1) {
		super.writeToNBT(var1);
		var1.setString("Text1", this.signText[0]);
		var1.setString("Text2", this.signText[1]);
		var1.setString("Text3", this.signText[2]);
		var1.setString("Text4", this.signText[3]);
	}

	public void readFromNBT(NBTTagCompound var1) {
		this.isEditable = false;
		super.readFromNBT(var1);

		for(int var2 = 0; var2 < 4; ++var2) {
			this.signText[var2] = var1.getString("Text" + (var2 + 1));
			if(this.signText[var2].length() > 15) {
				this.signText[var2] = this.signText[var2].substring(0, 15);
			}
		}

	}

	public Packet getDescriptionPacket() {
		String[] var1 = new String[4];
		System.arraycopy(this.signText, 0, var1, 0, 4);
		return new Packet130UpdateSign(this.xCoord, this.yCoord, this.zCoord, var1);
	}

	public boolean isEditable() {
		return this.isEditable;
	}

	public void setEditable(boolean var1) {
		this.isEditable = var1;
	}
}
