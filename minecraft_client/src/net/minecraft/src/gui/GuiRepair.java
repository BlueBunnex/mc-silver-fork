package net.minecraft.src.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Container;
import net.minecraft.src.ContainerRepair;
import net.minecraft.src.ICrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.StatCollector;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiRepair extends GuiContainer implements ICrafting {
	private ContainerRepair repairContainer;
	private GuiTextField itemNameField;
	private InventoryPlayer field_82325_q;

	public GuiRepair(InventoryPlayer var1, World var2, int var3, int var4, int var5) {
		super(new ContainerRepair(var1, var2, var3, var4, var5, Minecraft.getMinecraft().thePlayer));
		this.field_82325_q = var1;
		this.repairContainer = (ContainerRepair)this.inventorySlots;
	}

	public void initGui() {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		int var1 = (this.width - this.xSize) / 2;
		int var2 = (this.height - this.ySize) / 2;
		this.itemNameField = new GuiTextField(this.fontRenderer, var1 + 62, var2 + 24, 103, 12);
		this.itemNameField.setTextColor(-1);
		this.itemNameField.setDisabledTextColour(-1);
		this.itemNameField.setEnableBackgroundDrawing(false);
		this.itemNameField.setMaxStringLength(30);
		this.inventorySlots.removeCraftingFromCrafters(this);
		this.inventorySlots.addCraftingToCrafters(this);
	}

	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
		this.inventorySlots.removeCraftingFromCrafters(this);
	}

	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		GL11.glDisable(GL11.GL_LIGHTING);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.repair"), 60, 6, 4210752);
		if(this.repairContainer.maximumCost > 0) {
			int var3 = 8453920;
			boolean var4 = true;
			String var5 = StatCollector.translateToLocalFormatted("container.repair.cost", new Object[]{Integer.valueOf(this.repairContainer.maximumCost)});
			if(this.repairContainer.maximumCost >= 40 && !this.mc.thePlayer.capabilities.isCreativeMode) {
				var5 = StatCollector.translateToLocal("container.repair.expensive");
				var3 = 16736352;
			} else if(!this.repairContainer.getSlot(2).getHasStack()) {
				var4 = false;
			} else if(!this.repairContainer.getSlot(2).canTakeStack(this.field_82325_q.player)) {
				var3 = 16736352;
			}

			if(var4) {
				int var6 = -16777216 | (var3 & 16579836) >> 2 | var3 & -16777216;
				int var7 = this.xSize - 8 - this.fontRenderer.getStringWidth(var5);
				byte var8 = 67;
				if(this.fontRenderer.getUnicodeFlag()) {
					drawRect(var7 - 3, var8 - 2, this.xSize - 7, var8 + 10, -16777216);
					drawRect(var7 - 2, var8 - 1, this.xSize - 8, var8 + 9, -12895429);
				} else {
					this.fontRenderer.drawString(var5, var7, var8 + 1, var6);
					this.fontRenderer.drawString(var5, var7 + 1, var8, var6);
					this.fontRenderer.drawString(var5, var7 + 1, var8 + 1, var6);
				}

				this.fontRenderer.drawString(var5, var7, var8, var3);
			}
		}

		GL11.glEnable(GL11.GL_LIGHTING);
	}

	protected void keyTyped(char var1, int var2) {
		if(this.itemNameField.textboxKeyTyped(var1, var2)) {
			this.repairContainer.updateItemName(this.itemNameField.getText());
			this.mc.thePlayer.sendQueue.addToSendQueue(new Packet250CustomPayload("MC|ItemName", this.itemNameField.getText().getBytes()));
		} else {
			super.keyTyped(var1, var2);
		}

	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		this.itemNameField.mouseClicked(var1, var2, var3);
	}

	public void drawScreen(int var1, int var2, float var3) {
		super.drawScreen(var1, var2, var3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/gui/repair.png");
		int var4 = (this.width - this.xSize) / 2;
		int var5 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
		this.drawTexturedModalRect(var4 + 59, var5 + 20, 0, this.ySize + (this.repairContainer.getSlot(0).getHasStack() ? 0 : 16), 110, 16);
		if((this.repairContainer.getSlot(0).getHasStack() || this.repairContainer.getSlot(1).getHasStack()) && !this.repairContainer.getSlot(2).getHasStack()) {
			this.drawTexturedModalRect(var4 + 99, var5 + 45, this.xSize, 0, 28, 21);
		}

	}

	public void sendContainerAndContentsToPlayer(Container var1, List var2) {
		this.sendSlotContents(var1, 0, var1.getSlot(0).getStack());
	}

	public void sendSlotContents(Container var1, int var2, ItemStack var3) {
		if(var2 == 0) {
			this.itemNameField.setText(var3 == null ? "" : var3.getDisplayName());
			this.itemNameField.setEnabled(var3 != null);
			if(var3 != null) {
				this.repairContainer.updateItemName(this.itemNameField.getText());
				this.mc.thePlayer.sendQueue.addToSendQueue(new Packet250CustomPayload("MC|ItemName", this.itemNameField.getText().getBytes()));
			}
		}

	}

	public void sendProgressBarUpdate(Container var1, int var2, int var3) {
	}
}
