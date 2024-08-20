package de.melone.Lobby.CMD;

import de.melone.Lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CMD_build implements CommandExecutor, TabCompleter {

    public static ArrayList<UUID> build = new ArrayList<>();
    private static Player targetplayer;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("lobby.build")) {
            if (args.length == 0) {
                if (build.contains(player.getUniqueId())) {
                    build.remove(player.getUniqueId());
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.build.off")));
                } else {
                    build.add(player.getUniqueId());
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.build.on")));
                }
            } else if(args.length == 1){
                targetplayer = Bukkit.getPlayer(args[0]);

                if (build.contains(targetplayer.getUniqueId())) {
                    build.remove(targetplayer.getUniqueId());
                    targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.build.off")));
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + ConfigMessages(LobbyMain.messageyml.getString("Message.build.setoff"))));
                } else {
                    build.add(targetplayer.getUniqueId());
                    targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.build.on")));
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + ConfigMessages(LobbyMain.messageyml.getString("Message.build.seton"))));
                }
            }

        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + LobbyMain.noperms));
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                list.add(online.getName());
            }
        }
        return list;
    }

    private static String ConfigMessages(String message) {
        if (message.contains("%targetplayer%")) {
            return message.replace("%targetplayer%", targetplayer.getName());
        }
        return message;
    }
}
