package net.DeltaWings.Nemoria.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.JobProgression;

import net.DeltaWings.Nemoria.Main;

public class PlayerInfos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("playerinfos")){
				if(args.length == 1){
					for(Player player : Bukkit.getServer().getOnlinePlayers()){
						if(player.getName().equalsIgnoreCase(args[0])){
							p.sendMessage("§bNom: §6"+player.getName()+"§b\n Vie: §6"+player.getHealth()+"§b\nNiveau: §6"+player.getLevel()+"§b\nEcus: §6"+Main.eco.getBalance(player));
							List<JobProgression> jobs = Jobs.getPlayerManager().getJobsPlayer(player).getJobProgression();
							for (JobProgression OneJob : jobs) {
								p.sendMessage("§bJob actuel: §6"+OneJob.getJob().getName()+ "§b Niveau: §6"+OneJob.getLevel()+"§b Experience: §6"+OneJob.getExperience()+"§b/§6"+OneJob.getMaxExperience());
							}
						}
						
					}
				}
			}
			
		
		//get money 
		//get job
		//get job level
		//say  Comtniark et de m§tier mineur niveaux 13 poss§de 50 Cr§dit et actuellement lv 50(xp du joueur) vie actuel : 10 coeurs
		
		}
		return false;
	}

}
