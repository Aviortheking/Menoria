package net.DeltaWings.Nemoria.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.DeltaWings.Nemoria.Main;
import net.DeltaWings.Nemoria.API.Files;

public class DelTP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("menoria.cmd.deltp")){
				if(args.length==1){
					List<String> list = Files.getConfig().getStringList("list");
					for( int a = 0;a<list.size();a++){
						if(list.get(a).equalsIgnoreCase(args[0])){
							list.remove(a);
						}
					}
					Files.getConfig().set("list", list);
					Files.getConfig().set("teleporteurs."+args[0]+".world", "");
					Files.getConfig().set("teleporteurs."+args[0]+".x", "");
					Files.getConfig().set("teleporteurs."+args[0]+".y", "");
					Files.getConfig().set("teleporteurs."+args[0]+".z", "");
                    Files.getConfig().set("teleporteurs."+args[0]+".command", "");
					Files.getConfig().set("teleporteurs."+args[0], "");
					p.sendMessage("teleporteur detruis !");
				}
			}
		}
		Files.saveConfig();
		return false;
	}

}
