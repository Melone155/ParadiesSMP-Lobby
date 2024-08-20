package de.melone.Lobby.Listener;

import de.melone.Lobby.LobbyMain;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Join implements Listener {

    public static Map<UUID, FastBoard> boards = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.joinMessage(null);

        LobbyMain.player = player;

        FastBoard board = new FastBoard(player);
        board.updateTitle("DeinServer");
        boards.put(player.getUniqueId(), board);

        for (Player online : Bukkit.getOnlinePlayers()){
            new Scorbord().updateBoard(board, online);
        }

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
