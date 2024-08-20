package de.melone.Lobby.Listener;

import de.melone.Lobby.CMD.CMD_build;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class Buildevent implements Listener {

    @EventHandler
    public void onBrack(BlockBreakEvent event) {
        Player p = event.getPlayer();

        event.setCancelled(!CMD_build.build.contains(p.getName()));

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();

        event.setCancelled(!CMD_build.build.contains(p.getName()));

    }

    @EventHandler
    public void ondrop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();

        event.setCancelled(!CMD_build.build.contains(p.getName()));

    }

    @EventHandler
    public void onsinge(SignChangeEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(!CMD_build.build.contains(player.getName()));
    }

    @EventHandler
    public void onArmor(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
            event.setCancelled(!CMD_build.build.contains(player.getName()));
        }
    }

    @EventHandler
    public void onleaves(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamge(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        event.setCancelled(true);
        player.setFoodLevel(event.getFoodLevel());
    }
}