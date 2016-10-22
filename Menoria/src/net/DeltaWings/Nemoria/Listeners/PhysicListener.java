package net.DeltaWings.Nemoria.Listeners;

/**
 * Created by Floflo on 22/10/2016.
 */
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PhysicListener implements Listener {

    @EventHandler
    public void blockFallEvent(BlockPhysicsEvent event) {
        Block block = event.getBlock();
        if (!check(block)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void blockPlaceEvent(EntityChangeBlockEvent event) {
        Block block = event.getBlock();
        if (!check(block)) {
            return;
        }
        event.setCancelled(true);
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            Location loc = block.getLocation();
            double y = loc.getY();
            y -= 1.0D;
            loc.setY(y);

            player.sendBlockChange(loc, 0, (byte)0);
        }
    }

    private boolean check(Block block) {
        return (checkBlock(block));
    }

    private boolean checkBlock(Block block)
    {
        Material type = block.getType();
        return (type == Material.GRAVEL) || (type == Material.SAND || (type == Material.ANVIL));
    }
}
