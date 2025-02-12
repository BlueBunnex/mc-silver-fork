package net.minecraft.src.gui;

import java.util.Random;
import org.lwjgl.input.Keyboard;

import net.minecraft.src.ChatAllowedCharacters;
import net.minecraft.src.EnumGameType;
import net.minecraft.src.ISaveFormat;
import net.minecraft.src.MathHelper;
import net.minecraft.src.StatCollector;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.worldgen.WorldInfo;
import net.minecraft.src.worldgen.WorldSettings;
import net.minecraft.src.worldgen.WorldType;

public class GuiCreateWorld extends GuiScreen {
	private GuiScreen parentGuiScreen;
	private GuiTextField textboxWorldName;
	private GuiTextField textboxSeed;
	private String folderName;
	private String gameMode = "survival";
	private boolean generateStructures = true;
	private boolean commandsAllowed = false;
	private boolean commandsToggled = false;
	private boolean bonusItems = false;
	private boolean isHardcore = false;
	private boolean createClicked;
	private boolean moreOptions;
	private GuiButton buttonGameMode;
	private GuiButton moreWorldOptions;
	private GuiButton buttonGenerateStructures;
	private GuiButton buttonBonusItems;
	private GuiButton buttonWorldType;
	private GuiButton buttonAllowCommands;
	private GuiButton buttonCustomize;
	private String gameModeDescriptionLine1;
	private String gameModeDescriptionLine2;
	private String seed;
	private String localizedNewWorldText;
	private int worldTypeId = 0;
	public String generatorOptionsToUse = "";
	private static final String[] ILLEGAL_WORLD_NAMES = new String[]{"CON", "COM", "PRN", "AUX", "CLOCK$", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};

	public GuiCreateWorld(GuiScreen var1) {
		this.parentGuiScreen = var1;
		this.seed = "";
		this.localizedNewWorldText = StatCollector.translateToLocal("selectWorld.newWorld");
	}

	public void updateScreen() {
		this.textboxWorldName.updateCursorCounter();
		this.textboxSeed.updateCursorCounter();
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, var1.translateKey("selectWorld.create")));
		this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, var1.translateKey("gui.cancel")));
		this.buttonList.add(this.buttonGameMode = new GuiButton(2, this.width / 2 - 75, 115, 150, 20, var1.translateKey("selectWorld.gameMode")));
		this.buttonList.add(this.moreWorldOptions = new GuiButton(3, this.width / 2 - 75, 187, 150, 20, var1.translateKey("selectWorld.moreWorldOptions")));
		this.buttonList.add(this.buttonGenerateStructures = new GuiButton(4, this.width / 2 - 155, 100, 150, 20, var1.translateKey("selectWorld.mapFeatures")));
		this.buttonGenerateStructures.drawButton = false;
		this.buttonList.add(this.buttonBonusItems = new GuiButton(7, this.width / 2 + 5, 151, 150, 20, var1.translateKey("selectWorld.bonusItems")));
		this.buttonBonusItems.drawButton = false;
		this.buttonList.add(this.buttonWorldType = new GuiButton(5, this.width / 2 + 5, 100, 150, 20, var1.translateKey("selectWorld.mapType")));
		this.buttonWorldType.drawButton = false;
		this.buttonList.add(this.buttonAllowCommands = new GuiButton(6, this.width / 2 - 155, 151, 150, 20, var1.translateKey("selectWorld.allowCommands")));
		this.buttonAllowCommands.drawButton = false;
		this.buttonList.add(this.buttonCustomize = new GuiButton(8, this.width / 2 + 5, 120, 150, 20, var1.translateKey("selectWorld.customizeType")));
		this.buttonCustomize.drawButton = false;
		this.textboxWorldName = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
		this.textboxWorldName.setFocused(true);
		this.textboxWorldName.setText(this.localizedNewWorldText);
		this.textboxSeed = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
		this.textboxSeed.setText(this.seed);
		this.func_82288_a(this.moreOptions);
		this.makeUseableName();
		this.updateButtonText();
	}

	private void makeUseableName() {
		this.folderName = this.textboxWorldName.getText().trim();
		char[] var1 = ChatAllowedCharacters.allowedCharactersArray;
		int var2 = var1.length;

		for(int var3 = 0; var3 < var2; ++var3) {
			char var4 = var1[var3];
			this.folderName = this.folderName.replace(var4, '_');
		}

		if(MathHelper.stringNullOrLengthZero(this.folderName)) {
			this.folderName = "World";
		}

		this.folderName = func_73913_a(this.mc.getSaveLoader(), this.folderName);
	}

	private void updateButtonText() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.buttonGameMode.displayString = var1.translateKey("selectWorld.gameMode") + " " + var1.translateKey("selectWorld.gameMode." + this.gameMode);
		this.gameModeDescriptionLine1 = var1.translateKey("selectWorld.gameMode." + this.gameMode + ".line1");
		this.gameModeDescriptionLine2 = var1.translateKey("selectWorld.gameMode." + this.gameMode + ".line2");
		this.buttonGenerateStructures.displayString = var1.translateKey("selectWorld.mapFeatures") + " ";
		if(this.generateStructures) {
			this.buttonGenerateStructures.displayString = this.buttonGenerateStructures.displayString + var1.translateKey("options.on");
		} else {
			this.buttonGenerateStructures.displayString = this.buttonGenerateStructures.displayString + var1.translateKey("options.off");
		}

		this.buttonBonusItems.displayString = var1.translateKey("selectWorld.bonusItems") + " ";
		if(this.bonusItems && !this.isHardcore) {
			this.buttonBonusItems.displayString = this.buttonBonusItems.displayString + var1.translateKey("options.on");
		} else {
			this.buttonBonusItems.displayString = this.buttonBonusItems.displayString + var1.translateKey("options.off");
		}

		this.buttonWorldType.displayString = var1.translateKey("selectWorld.mapType") + " " + var1.translateKey(WorldType.worldTypes[this.worldTypeId].getTranslateName());
		this.buttonAllowCommands.displayString = var1.translateKey("selectWorld.allowCommands") + " ";
		if(this.commandsAllowed && !this.isHardcore) {
			this.buttonAllowCommands.displayString = this.buttonAllowCommands.displayString + var1.translateKey("options.on");
		} else {
			this.buttonAllowCommands.displayString = this.buttonAllowCommands.displayString + var1.translateKey("options.off");
		}

	}

	public static String func_73913_a(ISaveFormat var0, String var1) {
		var1 = var1.replaceAll("[\\./\"]", "_");
		String[] var2 = ILLEGAL_WORLD_NAMES;
		int var3 = var2.length;

		for(int var4 = 0; var4 < var3; ++var4) {
			String var5 = var2[var4];
			if(var1.equalsIgnoreCase(var5)) {
				var1 = "_" + var1 + "_";
			}
		}

		while(var0.getWorldInfo(var1) != null) {
			var1 = var1 + "-";
		}

		return var1;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 1) {
				this.mc.displayGuiScreen(this.parentGuiScreen);
			} else if(var1.id == 0) {
				this.mc.displayGuiScreen((GuiScreen)null);
				if(this.createClicked) {
					return;
				}

				this.createClicked = true;
				long var2 = (new Random()).nextLong();
				String var4 = this.textboxSeed.getText();
				if(!MathHelper.stringNullOrLengthZero(var4)) {
					try {
						long var5 = Long.parseLong(var4);
						if(var5 != 0L) {
							var2 = var5;
						}
					} catch (NumberFormatException var7) {
						var2 = (long)var4.hashCode();
					}
				}

				EnumGameType var8 = EnumGameType.getByName(this.gameMode);
				WorldSettings var6 = new WorldSettings(var2, var8, this.generateStructures, this.isHardcore, WorldType.worldTypes[this.worldTypeId]);
				var6.func_82750_a(this.generatorOptionsToUse);
				if(this.bonusItems && !this.isHardcore) {
					var6.enableBonusChest();
				}

				if(this.commandsAllowed && !this.isHardcore) {
					var6.enableCommands();
				}

				this.mc.launchIntegratedServer(this.folderName, this.textboxWorldName.getText().trim(), var6);
			} else if(var1.id == 3) {
				this.func_82287_i();
			} else if(var1.id == 2) {
				if(this.gameMode.equals("survival")) {
					if(!this.commandsToggled) {
						this.commandsAllowed = false;
					}

					this.isHardcore = false;
					this.gameMode = "hardcore";
					this.isHardcore = true;
					this.buttonAllowCommands.enabled = false;
					this.buttonBonusItems.enabled = false;
					this.updateButtonText();
				} else if(this.gameMode.equals("hardcore")) {
					if(!this.commandsToggled) {
						this.commandsAllowed = true;
					}

					this.isHardcore = false;
					this.gameMode = "creative";
					this.updateButtonText();
					this.isHardcore = false;
					this.buttonAllowCommands.enabled = true;
					this.buttonBonusItems.enabled = true;
				} else {
					if(!this.commandsToggled) {
						this.commandsAllowed = false;
					}

					this.gameMode = "survival";
					this.updateButtonText();
					this.buttonAllowCommands.enabled = true;
					this.buttonBonusItems.enabled = true;
					this.isHardcore = false;
				}

				this.updateButtonText();
			} else if(var1.id == 4) {
				this.generateStructures = !this.generateStructures;
				this.updateButtonText();
			} else if(var1.id == 7) {
				this.bonusItems = !this.bonusItems;
				this.updateButtonText();
			} else if(var1.id == 5) {
				++this.worldTypeId;
				if(this.worldTypeId >= WorldType.worldTypes.length) {
					this.worldTypeId = 0;
				}

				while(WorldType.worldTypes[this.worldTypeId] == null || !WorldType.worldTypes[this.worldTypeId].getCanBeCreated()) {
					++this.worldTypeId;
					if(this.worldTypeId >= WorldType.worldTypes.length) {
						this.worldTypeId = 0;
					}
				}

				this.generatorOptionsToUse = "";
				this.updateButtonText();
				this.func_82288_a(this.moreOptions);
			} else if(var1.id == 6) {
				this.commandsToggled = true;
				this.commandsAllowed = !this.commandsAllowed;
				this.updateButtonText();
			} else if(var1.id == 8) {
				this.mc.displayGuiScreen(new GuiCreateFlatWorld(this, this.generatorOptionsToUse));
			}

		}
	}

	private void func_82287_i() {
		this.func_82288_a(!this.moreOptions);
	}

	private void func_82288_a(boolean var1) {
		this.moreOptions = var1;
		this.buttonGameMode.drawButton = !this.moreOptions;
		this.buttonGenerateStructures.drawButton = this.moreOptions;
		this.buttonBonusItems.drawButton = this.moreOptions;
		this.buttonWorldType.drawButton = this.moreOptions;
		this.buttonAllowCommands.drawButton = this.moreOptions;
		this.buttonCustomize.drawButton = this.moreOptions && WorldType.worldTypes[this.worldTypeId] == WorldType.FLAT;
		StringTranslate var2;
		if(this.moreOptions) {
			var2 = StringTranslate.getInstance();
			this.moreWorldOptions.displayString = var2.translateKey("gui.done");
		} else {
			var2 = StringTranslate.getInstance();
			this.moreWorldOptions.displayString = var2.translateKey("selectWorld.moreWorldOptions");
		}

	}

	protected void keyTyped(char var1, int var2) {
		if(this.textboxWorldName.isFocused() && !this.moreOptions) {
			this.textboxWorldName.textboxKeyTyped(var1, var2);
			this.localizedNewWorldText = this.textboxWorldName.getText();
		} else if(this.textboxSeed.isFocused() && this.moreOptions) {
			this.textboxSeed.textboxKeyTyped(var1, var2);
			this.seed = this.textboxSeed.getText();
		}

		if(var1 == 13) {
			this.actionPerformed((GuiButton)this.buttonList.get(0));
		}

		((GuiButton)this.buttonList.get(0)).enabled = this.textboxWorldName.getText().length() > 0;
		this.makeUseableName();
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		if(this.moreOptions) {
			this.textboxSeed.mouseClicked(var1, var2, var3);
		} else {
			this.textboxWorldName.mouseClicked(var1, var2, var3);
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		StringTranslate var4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, var4.translateKey("selectWorld.create"), this.width / 2, 20, 16777215);
		if(this.moreOptions) {
			this.drawString(this.fontRenderer, var4.translateKey("selectWorld.enterSeed"), this.width / 2 - 100, 47, 10526880);
			this.drawString(this.fontRenderer, var4.translateKey("selectWorld.seedInfo"), this.width / 2 - 100, 85, 10526880);
			this.drawString(this.fontRenderer, var4.translateKey("selectWorld.mapFeatures.info"), this.width / 2 - 150, 122, 10526880);
			this.drawString(this.fontRenderer, var4.translateKey("selectWorld.allowCommands.info"), this.width / 2 - 150, 172, 10526880);
			this.textboxSeed.drawTextBox();
		} else {
			this.drawString(this.fontRenderer, var4.translateKey("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
			this.drawString(this.fontRenderer, var4.translateKey("selectWorld.resultFolder") + " " + this.folderName, this.width / 2 - 100, 85, 10526880);
			this.textboxWorldName.drawTextBox();
			this.drawString(this.fontRenderer, this.gameModeDescriptionLine1, this.width / 2 - 100, 137, 10526880);
			this.drawString(this.fontRenderer, this.gameModeDescriptionLine2, this.width / 2 - 100, 149, 10526880);
		}

		super.drawScreen(var1, var2, var3);
	}

	public void func_82286_a(WorldInfo var1) {
		this.localizedNewWorldText = StatCollector.translateToLocalFormatted("selectWorld.newWorld.copyOf", new Object[]{var1.getWorldName()});
		this.seed = var1.getSeed() + "";
		this.worldTypeId = var1.getTerrainType().getWorldTypeID();
		this.generatorOptionsToUse = var1.getGeneratorOptions();
		this.generateStructures = var1.isMapFeaturesEnabled();
		this.commandsAllowed = var1.areCommandsAllowed();
		if(var1.isHardcoreModeEnabled()) {
			this.gameMode = "hardcore";
		} else if(var1.getGameType().isSurvivalOrAdventure()) {
			this.gameMode = "survival";
		} else if(var1.getGameType().isCreative()) {
			this.gameMode = "creative";
		}

	}
}
