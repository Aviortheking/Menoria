package net.DeltaWings.Nemoria.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.DeltaWings.Nemoria.Main;
import net.DeltaWings.Nemoria.API.Files;

public class SetTP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("menoria.cmd.settp")){
				if(args.length==1){
					List<String> list = Files.getConfig().getStringList("list");
					list.add(args[0]);
					Files.getConfig().set("list", list);
					Files.getConfig().set("teleporteurs."+args[0]+".world", p.getLocation().getWorld().getName());
					Files.getConfig().set("teleporteurs."+args[0]+".x", p.getLocation().getBlockX());
					Files.getConfig().set("teleporteurs."+args[0]+".y", p.getLocation().getBlockY());
					Files.getConfig().set("teleporteurs."+args[0]+".z", p.getLocation().getBlockZ());
					p.sendMessage("Teleporteur crée");
				}
			}
		}
		Main.getInstance().saveConfig();
		return false;
	}

}
