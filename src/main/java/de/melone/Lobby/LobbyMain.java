package de.melone.Lobby;

import de.melone.Lobby.CMD.CMD_build;
import de.melone.Lobby.CMD.CMD_setspawn;
import de.melone.Lobby.CMD.CMD_setwarp;
import de.melone.Lobby.Listener.*;
import de.melone.Lobby.ulti.NoSQL;
import fr.mrmicky.fastboard.FastBoard;
import it.unimi.dsi.fastutil.ints.AbstractInt2BooleanMap;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LobbyMain extends JavaPlugin {

    File folder = new File("plugins/Lobby");

    public static File messagefile = new File("plugins//Lobby//Messages.yml");
    public static YamlConfiguration messageyml = YamlConfiguration.loadConfiguration(messagefile);

    public static File warpfile = new File("plugins//Lobby//warps.yml");
    public static YamlConfiguration warpyml = YamlConfiguration.loadConfiguration(warpfile);

    public static File sqlfile = new File("plugins//Lobby//nosql.yml");
    public static YamlConfiguration sqlyml = YamlConfiguration.loadConfiguration(sqlfile);

    public static String prefix = messageyml.getString("Message.prefix");
    public static String noperms = messageyml.getString("Message.noperms");
    public static String error = messageyml.getString("Message.error");
    public static String soon = messageyml.getString("Message.soon");

    public static String Username;
    public static String Password;
    public static String Host;
    public static String Port;
    public static String Database;
    public static String Collection;

    @Override
    public void onEnable() {

        CreateConfig();
        LoadSQL();
        registerCommand();
        registerlistener();

        NoSQL.Connection();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {

    }

    private void registerCommand(){
        getCommand("setwarp").setExecutor(new CMD_setwarp());
        getCommand("setspawn").setExecutor(new CMD_setspawn());
        getCommand("build").setExecutor(new CMD_build());
    }

    private void registerlistener() {
        final PluginManager pluginManager = super.getServer().getPluginManager();

        pluginManager.registerEvents(new Navigator(this), this);
        pluginManager.registerEvents(new Join(), this);
        pluginManager.registerEvents(new Buildevent(), this);
    }

    private void CreateConfig(){
        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!messagefile.exists()){
            try {
                messagefile.createNewFile();
                Message();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!warpfile.exists()){
            try {
                warpfile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!sqlfile.exists()){
            try {
                sqlfile.createNewFile();
                SQLFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void Message(){
        messageyml.set("Message.prefix", "[Prefix]");
        messageyml.set("Message.noperms", "Du hast keine rechts für diesen Befehl");
        messageyml.set("Message.error", "Hier scheint wohl etwas nicht zu");
        messageyml.set("Message.soon", "Diese Funktion kommt noch");
        messageyml.set("Message.nowarp", "Dieser Warp wurde nicht gesetzt");

        messageyml.set("Message.items.Navigator", "Navigator");

        messageyml.set("Message.item.Navigator.spawn", "Spawn");
        messageyml.set("Message.item.Navigator.build", "Build");
        messageyml.set("Message.item.Navigator.creative", "Creative");
        messageyml.set("Message.item.Navigator.dev", "Dev Server");
        messageyml.set("Message.item.Navigator.private", "Private Server");
        messageyml.set("Message.item.Navigator.gestrandet", "Gestrandet Server");
        messageyml.set("Message.item.Navigator.showcase", "Showcase Server");
        messageyml.set("Message.item.Navigator.smp", "SMP Wildnis");
        messageyml.set("Message.item.Navigator.citybuild", "SMP Wildnis");
        messageyml.set("Message.item.Navigator.paradies", "Paradies Sesson 2");
        messageyml.set("Message.item.Navigator.caseopening", "Paradies Sesson 2");
        messageyml.set("Message.item.Navigator.soon", "Paradies Sesson 2");

        messageyml.set("Message.build.help", "Benutze /build [Player]");
        messageyml.set("Message.build.off", "Du kannst jetzt nicht mehr bauen");
        messageyml.set("Message.build.on", "Du kannst bauen");
        messageyml.set("Message.build.seton", "Der Spieler %targetplayer% kann jetzt bauen");
        messageyml.set("Message.build.setoff", "Der Spieler %targetplayer% kann nicht mehr bauen");

        messageyml.set("Message.Scorbord.Titel", "DeinServer");
        messageyml.set("Message.Scorbord.Player", "Spieler:");
        messageyml.set("Message.Scorbord.Player2", "%player%");
        messageyml.set("Message.Scorbord.Rang", "Rang:");
        messageyml.set("Message.Scorbord.Playtime", "Spielzeit");
        messageyml.set("Message.Scorbord.Playtime2", "%playtime% Stunden");
        messageyml.set("Message.Scorbord.tiktok", "Tiktok:");
        messageyml.set("Message.Scorbord.tiktok2", "ParadiesSMPDE");
        messageyml.set("Message.Scorbord.Webseite", "Webseite");
        messageyml.set("Message.Scorbord.Webseite2", "Coming Soon!");

        try {
            messageyml.save(messagefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void SQLFile(){
        sqlyml.set("SQL.host", "127.0.0.1");
        sqlyml.set("SQL.Port", "27017");
        sqlyml.set("SQL.User", "root");
        sqlyml.set("SQL.Passwort", "passwort");
        sqlyml.set("SQL.Database", "db");
        sqlyml.set("SQL.Collection", "collection");

        try {
            sqlyml.save(sqlfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void LoadSQL(){
        Host = sqlyml.getString("SQL.host");
        Port = sqlyml.getString("SQL.Port");
        Username = sqlyml.getString("SQL.User");
        Password = sqlyml.getString("SQL.Passwort");
        Database = sqlyml.getString("SQL.Database");
        Collection = sqlyml.getString("SQL.Collection");
    }
}
