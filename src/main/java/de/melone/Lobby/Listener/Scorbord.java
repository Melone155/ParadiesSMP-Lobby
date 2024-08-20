package de.melone.Lobby.Listener;

import de.melone.Lobby.LobbyMain;
import fr.mrmicky.fastboard.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.getServer;

public class Scorbord {

    public void updateBoard(FastBoard board, Player player) {
        board.updateLines(
                LobbyMain.messageyml.getString("Message.Scorbord.Player"),
                playername(LobbyMain.messageyml.getString("Message.Scorbord.Player"), player),
                "",
                LobbyMain.messageyml.getString("Message.Scorbord.Rang"),
                " " + MakeColore(PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%")),
                "",
                LobbyMain.messageyml.getString("Message.Scorbord.Playtime"),
                playtime(LobbyMain.messageyml.getString("Message.Scorbord.Playtime2"))

        );
    }

    private static String MakeColore(String message) {
        if (message.contains("&")) {

            String part1 = extractFirstPart(message);
            message = message.substring(part1.length());

            String part2 = extractHexColor(message);
            message = message.substring(part2.length() + 1);

            String part3 = message;

            message = part1 + ChatColor.of(part2) + part3;

            return message.replace("&", "§");
        }
        return message;
    }

    private static String extractFirstPart(String input) {
        Pattern pattern = Pattern.compile("^&[0-9a-fk-or]\\[");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    private static String extractHexColor(String input) {
        Pattern pattern = Pattern.compile("#[0-9a-fA-F]{6}");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    private static String playtime(String message) {
        if (message.contains("%playtime%")) {

            return message.replace("%playtime%", "%no%");
        }
        return message;
    }

    private static String playername(String message, Player player){
        if (message.contains("%playtime%")) {

            return message.replace("%player%", player.getName());
        }
        return message;
    }
}