package de.melone.Lobby.CMD;

import de.melone.Lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CMD_setspawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("lobby.setspawn")){

            int X = (int) player.getLocation().getX();
            int Y = (int) player.getLocation().getY();
            int Z = (int) player.getLocation().getZ();

            double Yaw = player.getLocation().getYaw();
            double Pitch = player.getLocation().getPitch();

            double Xdouble = X + 0.5;
            double Ydouble = Y + 0.5;

            String World = player.getLocation().getWorld().toString();

            LobbyMain.warpyml.set("Spawn.X", Xdouble);
            LobbyMain.warpyml.set("Spawn.Y", Ydouble);
            LobbyMain.warpyml.set("Spawn.Z", Z);
            LobbyMain.warpyml.set("Spawn.Yaw", Yaw);
            LobbyMain.warpyml.set("Spawn.Pitch", Pitch);
            LobbyMain.warpyml.set( "Spawn.World", World);

            try {
                LobbyMain.warpyml.save(LobbyMain.warpfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.noperms));
        }

        return false;
    }
}
