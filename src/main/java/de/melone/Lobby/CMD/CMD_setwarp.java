package de.melone.Lobby.CMD;

import de.melone.Lobby.LobbyMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CMD_setwarp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        String name = args[0];

        if (player.hasPermission("lobby.setwarp")){

            int X = (int) player.getLocation().getX();
            int Y = (int) player.getLocation().getY();
            int Z = (int) player.getLocation().getZ();

            double Yaw = player.getLocation().getYaw();
            double Pitch = player.getLocation().getPitch();

            double Xdouble = X + 0.5;
            double Ydouble = Y + 0.5;

            String World = player.getLocation().getWorld().toString();

            LobbyMain.warpyml.set(name + ".X", Xdouble);
            LobbyMain.warpyml.set(name + ".Y", Ydouble);
            LobbyMain.warpyml.set(name + ".Z", Z);
            LobbyMain.warpyml.set(name + ".Yaw", Yaw);
            LobbyMain.warpyml.set(name + ".Pitch", Pitch);
            LobbyMain.warpyml.set(name + ".World", World);

            try {
                LobbyMain.warpyml.save(LobbyMain.warpfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            player.sendMessage(LobbyMain.prefix + " " + LobbyMain.noperms);
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ArrayList<String> list = new ArrayList<>();

        list.add("caseopening");

        return list;
    }
}
