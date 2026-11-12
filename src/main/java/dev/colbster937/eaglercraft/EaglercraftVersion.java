package dev.colbster937.eaglercraft;

import net.lax1dude.eaglercraft.EagRuntime;

public class EaglercraftVersion {
	public static final String STORAGE_KEY = "_dev_colbster937_eaglercraft_1.2.5_";
	public static final String EAGLER_VERSION = "1.0";
	public static final String MINECRAFT_VERSION = "1.0";
	public static final String PROJECT_GITHUB = "https://github.com/DutchDucksDevelopment/EaglercraftX-mc-1.0";
	public static final String PROJECT_AUTHOR = "DutchDucksDevelopment";
	public static final String PROJECT_BRAND = "DutchDucksDevelopment";
	public static final String LANG_CONTEXT = "eaglercraft";
	
	public static final String[] getTitleString() {
		return new String[] {
			"Eaglercraft " + EAGLER_VERSION + " [" + EagRuntime.getPlatformType() + "]",
			"Made by " + PROJECT_AUTHOR
		};
	}
}
