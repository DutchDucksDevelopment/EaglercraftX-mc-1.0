package net.lax1dude.eaglercraft.profile;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.opengl.ImageData;
import net.minecraft.client.Minecraft;

/**
 * 1.0 port: preset cape list, mirroring EaglercraftX 1.8's DefaultCapes.
 * The 32x32 preset cape PNGs are padded into a 64x32 texture on load so the
 * vanilla ModelBiped cloak UVs (10x16 region at offset 1,1 of a 64x32 atlas)
 * sample them correctly.
 */
public class DefaultCapes {

	public static final String[] capeNames = new String[] {
		"No Cape",
		"Minecon 2011",
		"Minecon 2012",
		"Minecon 2013",
		"Minecon 2015",
		"Minecon 2016",
		"Microsoft Account",
		"Realms Mapmaker",
		"Mojang Old",
		"Mojang New",
		"Jira Moderator",
		"Mojang Very Old",
		"Scrolls",
		"Cobalt",
		"Lang Translator",
		"Millionth Player",
		"Prismarine"
	};

	private static final String[] capeFiles = new String[] {
		null,
		"/eagler/capes/01.minecon_2011.png",
		"/eagler/capes/02.minecon_2012.png",
		"/eagler/capes/03.minecon_2013.png",
		"/eagler/capes/04.minecon_2015.png",
		"/eagler/capes/05.minecon_2016.png",
		"/eagler/capes/06.microsoft_account.png",
		"/eagler/capes/07.mapmaker.png",
		"/eagler/capes/08.mojang_old.png",
		"/eagler/capes/09.mojang_new.png",
		"/eagler/capes/10.jira_mod.png",
		"/eagler/capes/11.mojang_very_old.png",
		"/eagler/capes/12.scrolls.png",
		"/eagler/capes/13.cobalt.png",
		"/eagler/capes/14.translator.png",
		"/eagler/capes/15.millionth_account.png",
		"/eagler/capes/16.prismarine.png"
	};

	private static final int[] capeTextures = new int[capeNames.length];

	/**
	 * @param id EaglerProfile.presetCapeId (-1 = no cape)
	 * @return GL texture id of the padded cape texture, or -1 if none
	 */
	public static int getCapeTexture(int id) {
		if (id <= 0 || id >= capeNames.length) {
			return -1;
		}
		if (capeTextures[id] == 0) {
			byte[] data = EagRuntime.getResourceBytes(capeFiles[id]);
			if (data == null) {
				return -1;
			}
			ImageData img = ImageData.loadImageFile(data);
			if (img == null) {
				return -1;
			}
			ImageData tex = new ImageData(64, 32, true);
			for (int y = 0; y < img.height && y < 32; ++y) {
				for (int x = 0; x < img.width && x < 64; ++x) {
					tex.pixels[y * 64 + x] = img.pixels[y * img.width + x];
				}
			}
			capeTextures[id] = Minecraft.getMinecraft().renderEngine.allocateAndSetupTexture(tex);
		}
		return capeTextures[id];
	}
}
