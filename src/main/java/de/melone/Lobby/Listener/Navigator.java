package de.melone.Lobby.Listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.melone.Lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Navigator implements Listener {

    private final LobbyMain plugin;

    public Navigator(LobbyMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNav(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.hasItem()) {
                if (event.getItem().getType() == Material.RECOVERY_COMPASS) {

                    Inventory inv = Bukkit.createInventory(null, 54, "");

                    ItemStack leer = new ItemStack(Material.WHITE_STAINED_GLASS);
                    ItemMeta leermeta = leer.getItemMeta();
                    leermeta.setDisplayName(" ");
                    leer.setItemMeta(leermeta);

                    for (int i = 0; i < 54; i++) {
                        inv.setItem(i, leer);
                    }

                    ItemStack spawn = new ItemStack(Material.HONEYCOMB);
                    ItemMeta spawnmeta = spawn.getItemMeta();
                    spawnmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.spawn")));
                    spawn.setItemMeta(spawnmeta);

                    ItemStack build = new ItemStack(Material.GOLDEN_PICKAXE);
                    ItemMeta buildmeta = build.getItemMeta();
                    buildmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.build")));
                    build.setItemMeta(buildmeta);

                    ItemStack creative = new ItemStack(Material.DIAMOND_PICKAXE);
                    ItemMeta creativemeta = creative.getItemMeta();
                    creativemeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.creative")));
                    creative.setItemMeta(creativemeta);

                    ItemStack dev = new ItemStack(Material.LAVA_BUCKET);
                    ItemMeta devmeta = dev.getItemMeta();
                    devmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.dev")));
                    dev.setItemMeta(devmeta);

                    ItemStack privateserver = new ItemStack(Material.AMETHYST_SHARD);
                    ItemMeta privatemeta = privateserver.getItemMeta();
                    privatemeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.private")));
                    privateserver.setItemMeta(privatemeta);

                    ItemStack gestrandet = new ItemStack(Material.JUNGLE_SAPLING);
                    ItemMeta gestrandetmeta = gestrandet.getItemMeta();
                    gestrandetmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.gestrandet")));
                    gestrandet.setItemMeta(gestrandetmeta);

                    ItemStack show = new ItemStack(Material.SLIME_BALL);
                    ItemMeta showmeta = show.getItemMeta();
                    showmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.showcase")));
                    show.setItemMeta(showmeta);

                    ItemStack smp = new ItemStack(Material.TURTLE_SCUTE);
                    ItemMeta smpmeta = smp.getItemMeta();
                    smpmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.smp")));
                    smp.setItemMeta(smpmeta);

                    ItemStack citybuild = new ItemStack(Material.EMERALD);
                    ItemMeta citybuildmeta = citybuild.getItemMeta();
                    citybuildmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.citybuild")));
                    citybuild.setItemMeta(citybuildmeta);

                    ItemStack Paradies = new ItemStack(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
                    ItemMeta Paradiesmeta = Paradies.getItemMeta();
                    Paradiesmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.paradies")));
                    Paradies.setItemMeta(Paradiesmeta);

                    ItemStack caseopening = new ItemStack(Material.FIRE_CHARGE);
                    ItemMeta caseopeningmeta = caseopening.getItemMeta();
                    caseopeningmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.caseopening")));
                    caseopening.setItemMeta(caseopeningmeta);

                    ItemStack soon = new ItemStack(Material.BARRIER);
                    ItemMeta soonmeta = soon.getItemMeta();
                    soonmeta.setDisplayName(ColorMessage(LobbyMain.messageyml.getString("Message.item.Navigator.soon")));
                    soon.setItemMeta(soonmeta);

                    inv.setItem(4, spawn);

                    inv.setItem(19, build);
                    inv.setItem(28, creative);
                    inv.setItem(37, dev);

                    inv.setItem(21, privateserver);
                    inv.setItem(30, gestrandet);
                    inv.setItem(39, show);

                    inv.setItem(23, smp);
                    inv.setItem(32, citybuild);
                    inv.setItem(41, Paradies);

                    inv.setItem(25, caseopening);
                    inv.setItem(34, soon);
                    inv.setItem(43, soon);

                    player.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void onItem(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(LobbyMain.messageyml.getString("Message.items.Navigator"))) {
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.spawn"))) {

                if (!(LobbyMain.warpyml.getString("Spawn") == null)) {

                    Double X = LobbyMain.warpyml.getDouble("Spawn.X");
                    int Y = LobbyMain.warpyml.getInt("Spawn.Y");
                    Double Z = LobbyMain.warpyml.getDouble("Spawn.Z");
                    int Pitch = LobbyMain.warpyml.getInt("Spawn.Pitch");
                    int Yaw = LobbyMain.warpyml.getInt("Spawn.Yaw");
                    String Wold = LobbyMain.warpyml.getString("Spawn.World");

                    player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));
                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.nowarp")));
                }

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.Build"))) {

                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("bauserver");
                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.Creative"))) {

                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("creative");
                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.dev"))) {

                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("dev");
                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.private"))) {

                player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.soon));

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.gestrandet"))) {

                player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.soon));

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.showcase"))) {

                player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.soon));

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.smp"))) {

                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("SMP");
                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.citybuild"))) {

                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("citybuild");
                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.paradies"))) {

                player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.soon));

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.caseopening"))) {

                if (!(LobbyMain.warpyml.getString("caseopening") == null)) {

                    Double X = LobbyMain.warpyml.getDouble("caseopening.X");
                    int Y = LobbyMain.warpyml.getInt("caseopening.Y");
                    Double Z = LobbyMain.warpyml.getDouble("caseopening.Z");
                    int Pitch = LobbyMain.warpyml.getInt("caseopening.Pitch");
                    int Yaw = LobbyMain.warpyml.getInt("caseopening.Yaw");
                    String Wold = LobbyMain.warpyml.getString("caseopening.World");

                    player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));

                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.nowarp")));
                }

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.item.Navigator.soon"))) {

                player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.soon));
            }
        }
    }

    private static String ColorMessage(String message){
       String convertmessage = String.valueOf(MiniMessage.miniMessage().deserialize(message));
       return convertmessage;
    }
}
