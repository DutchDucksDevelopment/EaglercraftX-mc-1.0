package net.lax1dude.eaglercraft.profile;

import org.lwjgl.opengl.GL11;

import dev.colbster937.eaglercraft.utils.I18n;
import net.lax1dude.eaglercraft.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.RenderHelper;
import net.peyton.eagler.minecraft.TextureLocation;

/**
 * 1.0 port: cape selection screen, mirroring EaglercraftX 1.8's
 * GuiScreenEditCape (opened from the "Capes" link on the profile screen).
 */
public class GuiScreenEditCape extends GuiScreen {
	private GuiScreen parent;

	private boolean dropDownOpen = false;
	private String[] dropDownOptions;
	private int slotsVisible = 0;
	private int selectedSlot = 0;
	private int scrollPos = -1;
	private int capesHeight = 0;
	private boolean dragging = false;
	private int mousex = 0;
	private int mousey = 0;

	private int trans = 15;

	private static final TextureLocation gui = new TextureLocation("/eagler/gui/eagler_gui.png");

	private static ModelBiped playerModel = null;

	protected String screenTitle = "Edit Cape";

	public GuiScreenEditCape(GuiScreen parent) {
		this.parent = parent;
		this.dropDownOptions = DefaultCapes.capeNames;
		this.selectedSlot = EaglerProfile.presetCapeId + 1;
		if (this.selectedSlot < 0 || this.selectedSlot >= dropDownOptions.length) {
			this.selectedSlot = 0;
		}
	}

	public void initGui() {
		super.initGui();
		this.screenTitle = I18n.format("profile.editCape");
		this.controlList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
	}

	public void drawScreen(int mx, int my, float par3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 15, 16777215);
		this.drawString(this.fontRenderer, I18n.format("profile.cape"), this.width / 2 - 20, this.height / 6 + 8 + trans, 10526880);

		mousex = mx;
		mousey = my;

		int skinX = this.width / 2 - 120;
		int skinY = this.height / 6 + 8;
		int skinWidth = 80;
		int skinHeight = 130;

		drawRect(skinX, skinY, skinX + skinWidth, skinY + skinHeight, -6250336);
		drawRect(skinX + 1, skinY + 1, skinX + skinWidth - 1, skinY + skinHeight - 1, 0xff000015);

		if (dropDownOpen) {
			super.drawScreen(0, 0, par3);
		} else {
			super.drawScreen(mx, my, par3);
		}

		skinX = this.width / 2 - 20;
		skinY = this.height / 6 + 30 + trans;
		skinWidth = 140;
		skinHeight = 22;

		drawRect(skinX, skinY, skinX + skinWidth, skinY + skinHeight, -6250336);
		drawRect(skinX + 1, skinY + 1, skinX + skinWidth - 21, skinY + skinHeight - 1, -16777216);
		drawRect(skinX + skinWidth - 20, skinY + 1, skinX + skinWidth - 1, skinY + skinHeight - 1, -16777216);

		GL11.glColor4f(1f, 1f, 1f, 1f);
		gui.bindTexture();
		drawTexturedModalRect(skinX + skinWidth - 18, skinY + 3, 0, 0, 16, 16);

		this.fontRenderer.drawStringWithShadow(dropDownOptions[selectedSlot], skinX + 5, skinY + 7, 14737632);

		skinX = this.width / 2 - 20;
		skinY = this.height / 6 + 51 + trans;
		skinWidth = 140;
		skinHeight = (this.height - skinY - 10);
		slotsVisible = (skinHeight / 10);
		if (slotsVisible > dropDownOptions.length) slotsVisible = dropDownOptions.length;
		skinHeight = slotsVisible * 10 + 7;
		capesHeight = skinHeight;
		if (scrollPos == -1) {
			scrollPos = selectedSlot - 2;
		}
		if (scrollPos > (dropDownOptions.length - slotsVisible)) {
			scrollPos = (dropDownOptions.length - slotsVisible);
		}
		if (scrollPos < 0) {
			scrollPos = 0;
		}
		if (dropDownOpen) {
			drawRect(skinX, skinY, skinX + skinWidth, skinY + skinHeight, -6250336);
			drawRect(skinX + 1, skinY + 1, skinX + skinWidth - 1, skinY + skinHeight - 1, -16777216);
			for (int i = 0; i < slotsVisible; i++) {
				if (i + scrollPos < dropDownOptions.length) {
					if (selectedSlot == i + scrollPos) {
						drawRect(skinX + 1, skinY + i * 10 + 4, skinX + skinWidth - 1, skinY + i * 10 + 14, 0x77ffffff);
					} else if (mx >= skinX && mx < (skinX + skinWidth - 10) && my >= (skinY + i * 10 + 5) && my < (skinY + i * 10 + 15)) {
						drawRect(skinX + 1, skinY + i * 10 + 4, skinX + skinWidth - 1, skinY + i * 10 + 14, 0x55ffffff);
					}
					this.fontRenderer.drawStringWithShadow(dropDownOptions[i + scrollPos], skinX + 5, skinY + 5 + i * 10, 14737632);
				}
			}
			int scrollerSize = skinHeight * slotsVisible / dropDownOptions.length;
			int scrollerPos = skinHeight * scrollPos / dropDownOptions.length;
			drawRect(skinX + skinWidth - 4, skinY + scrollerPos + 1, skinX + skinWidth - 1, skinY + scrollerPos + scrollerSize, 0xff888888);
		}

		int xx = this.width / 2 - 80;
		int yy = this.height / 6 + 130;
		skinX = this.width / 2 - 120;
		skinY = this.height / 6 + 8;
		skinWidth = 80;
		skinHeight = 130;

		if (EaglerProfile.presetSkinId >= 0 && EaglerProfile.presetSkinId < GuiScreenEditProfile.defaultOptionsTextures.length) {
			GuiScreenEditProfile.defaultOptionsTextures[EaglerProfile.presetSkinId].bindTexture();
		} else if (EaglerProfile.customSkinId >= 0 && EaglerProfile.customSkinId < EaglerProfile.skins.size()) {
			Minecraft.getMinecraft().renderEngine.bindTexture(EaglerProfile.skins.get(EaglerProfile.customSkinId).glTex);
		} else {
			Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getTexture("/mob/char.png"));
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) xx, (float) (yy - 80), 100.0F);
		GL11.glScalef(50.0f, 50.0f, 50.0f);
		GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
		RenderHelper.enableStandardItemLighting();
		GL11.glScalef(1.0F, -1.0F, 1.0F);
		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(((yy - my) * -0.06f), 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(((xx - mx) * 0.06f), 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(0.0F, -1.0F, 0.0F);

		if (playerModel == null) {
			playerModel = new ModelBiped(0.0f);
		}

		playerModel.render(null, 0.0f, 0.0f, (float) (System.currentTimeMillis() % 100000) / 50f, ((xx - mx) * 0.06f), ((yy - my) * -0.1f), 0.0625F);

		int capeTex = DefaultCapes.getCapeTexture(selectedSlot - 1);
		if (capeTex != -1) {
			Minecraft.getMinecraft().renderEngine.bindTexture(capeTex);
			/* same cloak transform RenderPlayer uses in-game: the cloak box sits
			   inside the torso volume by default and must be pushed to the back
			   surface (translate 0.125 = 2px) + 6 degree drape tilt + 180 flip */
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, 0.125F);
			GL11.glRotatef(6.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			playerModel.renderCloak(0.0625F);
			GL11.glPopMatrix();
		}

		RenderHelper.disableStandardItemLighting();

		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor4f(1f, 1f, 1f, 1f);
	}

	public void handleMouseInput() {
		super.handleMouseInput();
		if (dropDownOpen) {
			int var1 = Mouse.getEventDWheel();
			if (var1 < 0) {
				scrollPos += 3;
			}
			if (var1 > 0) {
				scrollPos -= 3;
			}
			if (scrollPos < 0) {
				scrollPos = 0;
			}
			if (scrollPos > dropDownOptions.length - 1) {
				scrollPos = dropDownOptions.length - 1;
			}
		}
	}

	private void save() {
		EaglerProfile.presetCapeId = selectedSlot - 1;
		EaglerProfile.save();
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (!dropDownOpen) {
			if (par1GuiButton.id == 200) {
				save();
				this.mc.displayGuiScreen(parent);
			}
		}
	}

	public void updateScreen() {
		if (dropDownOpen) {
			if (Mouse.isButtonDown(0)) {
				int skinX = this.width / 2 - 20;
				int skinY = this.height / 6 + 51;
				int skinWidth = 140;
				if (mousex >= (skinX + skinWidth - 10) && mousex < (skinX + skinWidth) && mousey >= skinY && mousey < (skinY + capesHeight)) {
					dragging = true;
				}
				if (dragging) {
					int scrollerSize = capesHeight * slotsVisible / dropDownOptions.length;
					scrollPos = (mousey - skinY - (scrollerSize / 2)) * dropDownOptions.length / capesHeight;
				}
			} else {
				dragging = false;
			}
		} else {
			dragging = false;
		}
	}

	protected void keyTyped(char par1, int par2) {
		if (par2 == 200 && selectedSlot > 0) {
			--selectedSlot;
			scrollPos = selectedSlot - 2;
		}
		if (par2 == 208 && selectedSlot < (dropDownOptions.length - 1)) {
			++selectedSlot;
			scrollPos = selectedSlot - 2;
		}
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);

		if (par3 == 0) {
			int skinX = this.width / 2 + 140 - 40;
			int skinY = this.height / 6 + 30 + trans;

			if (par1 >= skinX && par1 < (skinX + 20) && par2 >= skinY && par2 < (skinY + 22)) {
				dropDownOpen = !dropDownOpen;
			}

			skinX = this.width / 2 - 20;
			int skinWidth = 140;
			int skinHeight = capesHeight;

			if (!(par1 >= skinX && par1 < (skinX + skinWidth) && par2 >= skinY && par2 < (skinY + skinHeight + 22))) {
				dropDownOpen = false;
				dragging = false;
			}

			skinY += 21;

			if (dropDownOpen && !dragging) {
				for (int i = 0; i < slotsVisible; i++) {
					if (i + scrollPos < dropDownOptions.length) {
						if (selectedSlot != i + scrollPos) {
							if (par1 >= skinX && par1 < (skinX + skinWidth - 10) && par2 >= (skinY + i * 10 + 5) && par2 < (skinY + i * 10 + 15)) {
								selectedSlot = i + scrollPos;
								dropDownOpen = false;
								dragging = false;
							}
						}
					}
				}
			}
		}
	}
}
