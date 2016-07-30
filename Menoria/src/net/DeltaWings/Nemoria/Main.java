package net.DeltaWings.Nemoria;

import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.DeltaWings.Nemoria.Commands.PlayerInfos;
import net.DeltaWings.Nemoria.Listeners.ChatListener;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener{
	//creation de l'instance
	public static Main instance;
	public static Main getInstance() {
		return instance;
	}
	public static Economy eco = null;
	
	public void onEnable(){
		//creation de l'instance
		instance = this;
		setupEconomy();
		//create chatlistener
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getCommand("PlayerInfos").setExecutor(new PlayerInfos());
		
		//plugin enabled
		System.out.println("[Player Imformation]Plugin enabled");
	}
	
	public void onDisable(){
		
		
		//plugin disabled
		System.out.println("[Player Imformation]Plugin disabled");
	}
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
        }

        return (eco != null);
    }
}


/* 
 * OBJECTIF:
 * quand le joueur se trouve a x coordonné dans x monde
 * ouvrir un chest
 */