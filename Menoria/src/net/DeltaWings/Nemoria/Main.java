package net.DeltaWings.Nemoria;

import net.DeltaWings.Nemoria.Listeners.PhysicListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.DeltaWings.Nemoria.Commands.DelTP;
import net.DeltaWings.Nemoria.Commands.PlayerInfos;
import net.DeltaWings.Nemoria.Commands.SetTP;
import net.DeltaWings.Nemoria.Listeners.PlayerPositionListener;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener{
	//creation de l'instance
	public static Main instance;
	public static Main getInstance() {
		return instance;
	}

	public static Main main;
	public static Economy eco = null;
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		//creation de l'instance
		instance = this;
		main = Main.getInstance();
		//create chatlistener
		/**getServer().getPluginManager().registerEvents(new ChatListener(), this);**/
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new PlayerPositionListener(),20,20);
		getCommand("PlayerInfos").setExecutor(new PlayerInfos());
		getCommand("SetTP").setExecutor(new SetTP());
		getCommand("DelTP").setExecutor(new DelTP());
		getServer().getPluginManager().registerEvents(new PhysicListener(), this);
		saveDefaultConfig();
		//plugin enabled
		System.out.println("[Menoria]Plugin enabled");
	}
	
	public void onDisable(){
		
		//plugin disabled
		System.out.println("[Menoria]Plugin disabled");
	}
	//reste chat
	/**private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
        }

        return (eco != null);
    }**/
}


/* 
 * OBJECTIF:
 * un systeme de kits
 * pour les kits ils peuvent être des commandes et des items ?
 * les gamemode juste les minimizer /gmc /gma /gms /gmsp
 * le tpa
 * les warps
 * ./enchantment /feed /xp /fly /give /god /hat /heal /kit /afk /msg /powertool /spawner /spawnmob /ban /banip /broadcast /enderchest /invsee /kick /kill /mute /tempban /vanish /setspawn /spawn /back /home /sethome /delhome /setwarp /warp /delwarp /tp /tpa /tphere /tpaccept /tpdeny
 * ./item /repair /remove /clearinventory / ignore /suicide /kickall /tpaall /tpall /tpahere
 */