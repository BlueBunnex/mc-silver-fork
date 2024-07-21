package net.minecraft.src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.src.block.Block;
import net.minecraft.src.block.BlockJukeBox;

public class ItemRecord extends Item {
	private static final Map records = new HashMap();
	public final String recordName;

	protected ItemRecord(int var1, String var2) {
		super(var1);
		this.recordName = var2;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabMisc);
		records.put(var2, this);
	}

	public Icon getIconFromDamage(int var1) {
		return this.itemIcon;
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
		if(var3.getBlockId(var4, var5, var6) == Block.jukebox.blockID && var3.getBlockMetadata(var4, var5, var6) == 0) {
			if(var3.isRemote) {
				return true;
			} else {
				((BlockJukeBox)Block.jukebox).insertRecord(var3, var4, var5, var6, var1);
				var3.playAuxSFXAtEntity((EntityPlayer)null, 1005, var4, var5, var6, this.itemID);
				--var1.stackSize;
				return true;
			}
		} else {
			return false;
		}
	}

	public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
		var3.add(this.getRecordTitle());
	}

	public String getRecordTitle() {
		return "C418 - " + this.recordName;
	}

	public EnumRarity getRarity(ItemStack var1) {
		return EnumRarity.rare;
	}

	public static ItemRecord getRecord(String var0) {
		return (ItemRecord)records.get(var0);
	}

	public void registerIcons(IconRegister var1) {
		this.itemIcon = var1.registerIcon("record_" + this.recordName);
	}
}
