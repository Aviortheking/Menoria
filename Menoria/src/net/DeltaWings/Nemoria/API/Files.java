package net.DeltaWings.Nemoria.API;

import org.bukkit.configuration.file.FileConfiguration;

import net.DeltaWings.Nemoria.Main;

public class Files {

	public static FileConfiguration getConfig(){
		return Main.getInstance().getConfig();
		
	}
	
	public static void saveFiles(){
		Main.getInstance().saveConfig();
	}
	
	public void yolo(){
		
	}
}