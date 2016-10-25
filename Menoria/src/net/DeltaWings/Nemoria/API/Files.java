package net.DeltaWings.Nemoria.API;

import org.bukkit.configuration.file.FileConfiguration;

import net.DeltaWings.Nemoria.Main;

public class Files {

	private static Main main = Main.getInstance();
	public static FileConfiguration getConfig(){
		return main.getConfig();
		
	}

	public static void saveConfig() {
		main.saveConfig();
	}

}