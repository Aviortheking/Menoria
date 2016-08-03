package net.DeltaWings.Nemoria.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.DeltaWings.Nemoria.Main;
import net.DeltaWings.Nemoria.API.Files;

public class PlayerPositionListener extends BukkitRunnable {
	@Override
	public void run() {
		for(int a = 0; a<Files.getConfig() .getStringList("list").size(); a++){
			int x = Files.getConfig() .getInt("teleporteurs."+Files.getConfig() .getStringList("list").get(a)+".x");
			int y = Files.getConfig() .getInt("teleporteurs."+Files.getConfig() .getStringList("list").get(a)+".y");
			int z = Files.getConfig() .getInt("teleporteurs."+Files.getConfig() .getStringList("list").get(a)+".z");
			String world = Files.getConfig() .getString("teleporteurs."+Files.getConfig() .getStringList("list").get(a)+".world");
			for(Player player : Bukkit.getServer().getOnlinePlayers()){
				Boolean on;
				if(Files.getConfig() .isSet("players."+player.getName()+".on") == false){
					Files.getConfig() .set("players."+player.getName()+".on", false);
				}
				if(player.getLocation().getWorld().getName().equalsIgnoreCase(world)){
					if(player.getLocation().getBlockX() == x){
						if(player.getLocation().getBlockY() == y){
							if(player.getLocation().getBlockZ() == z){
								on = true;
								Main.getInstance().saveConfig();
							}else{
								on = false;
							}
						}else{
							on = false;
						}
					}else{
						on = false;
					}
				}else{
					on = false;
				}
				if(Files.getConfig().getBoolean("players."+player.getName()+".on") == false){
					if(on){
						//make player execute command //bs open Téléportation player
						Bukkit.dispatchCommand(player, "bs téléportation");
						Files.getConfig().set("players."+player.getName()+".on", true);
						Files.getConfig().set("players."+player.getName()+".latest", Files.getConfig() .getStringList("list").get(a));
					}
				}else{
					int lx = Files.getConfig() .getInt("teleporteurs."+Files.getConfig() .getString("players."+player.getName()+".latest")+".x");
					int ly = Files.getConfig() .getInt("teleporteurs."+Files.getConfig() .getString("players."+player.getName()+".latest")+".y");
					int lz = Files.getConfig() .getInt("teleporteurs."+Files.getConfig() .getString("players."+player.getName()+".latest")+".z");
					String lworld = Files.getConfig() .getString("teleporteurs."+Files.getConfig() .getString("players."+player.getName()+".latest")+".world");
					if(player.getLocation().getWorld().getName().equalsIgnoreCase(lworld)){
						if(player.getLocation().getBlockX() == lx){
							if(player.getLocation().getBlockY() == ly){
								if(player.getLocation().getBlockZ() == lz){
									
								}else{
									Files.getConfig().set("players."+player.getName()+".on", false);
								}
							}else{
								Files.getConfig().set("players."+player.getName()+".on", false);
							}
						}else{
							Files.getConfig().set("players."+player.getName()+".on", false);
						}
					}else{
						Files.getConfig().set("players."+player.getName()+".on", false);
					}
				}
				Main.getInstance().saveConfig();
			}
		}
	}

}