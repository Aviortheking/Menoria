package net.DeltaWings.Nemoria.Listeners;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.DeltaWings.Nemoria.API.MinecraftToJSON;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

public class ChatListener implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent e){
		String msg = MinecraftToJSON.main(e.getMessage(), e.getPlayer());
		System.out.println(msg);
		e.setCancelled(true);
		IChatBaseComponent link = ChatSerializer.a(msg);
		PacketPlayOutChat packet = new PacketPlayOutChat(link);
		((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(packet);
	}
}