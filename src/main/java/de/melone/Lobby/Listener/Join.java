package de.melone.Lobby.Listener;

import de.melone.Lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.joinMessage(null);

        player.getInventory().clear();

        ItemStack spawn = new ItemStack(Material.RECOVERY_COMPASS);
        ItemMeta spawnmeta = spawn.getItemMeta();
        spawnmeta.setDisplayName(LobbyMain.messageyml.getString("Message.items.Navigator"));
        spawn.setItemMeta(spawnmeta);


        player.getPlayer().getInventory().setItem(4, spawn);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.quitMessage(null);
    }
}
