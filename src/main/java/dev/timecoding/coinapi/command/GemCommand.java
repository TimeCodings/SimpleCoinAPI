package dev.timecoding.coinapi.command;

import dev.timecoding.coinapi.CoinMain;
import dev.timecoding.coinapi.api.CoinAPI;
import dev.timecoding.coinapi.api.ConfigManager;
import dev.timecoding.coinapi.enums.CoinType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GemCommand implements CommandExecutor {

    private ConfigManager cfg = CoinMain.config;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(cfg.getString("AdminPermission"))) {
            Player p = (Player) sender;
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
            //Create Inventory
            Inventory inv = Bukkit.createInventory(null, 9 * 3, ChatColor.BOLD + "► " + ChatColor.GREEN + ChatColor.BOLD + "Gems");
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta glassmeta = glass.getItemMeta();
                glassmeta.setDisplayName(" ");
                glass.setItemMeta(glassmeta);

                inv.setItem(i, glass);
            }

            CoinAPI api = new CoinAPI(p);

            ItemStack gem = new ItemStack(Material.EMERALD);
            ItemMeta gemm = gem.getItemMeta();

            gemm.setDisplayName("§a§l" + cfg.getString("GemsAnzeigeName") + ": " + ChatColor.DARK_GREEN + ChatColor.BOLD + api.getCoins(CoinType.DEFAULT));
            gem.setItemMeta(gemm);

            inv.setItem(10, gem);

            gemm.setDisplayName("§a§l" + cfg.getString("UntradableGemsAnzeigeName") + ": " + ChatColor.DARK_GREEN + ChatColor.BOLD + api.getCoins(CoinType.UNTRADABLE));
            gem.setItemMeta(gemm);
            gem.addUnsafeEnchantment(Enchantment.LUCK, 1);

            inv.setItem(16, gem);

            Integer gesamt = (api.getCoins(CoinType.DEFAULT) + api.getCoins(CoinType.UNTRADABLE));
            gemm.setDisplayName("§b§lGesamt: " + ChatColor.DARK_BLUE + ChatColor.BOLD + gesamt);
            gem.setType(Material.DIAMOND);
            gem.removeEnchantment(Enchantment.LUCK);
            gem.setItemMeta(gemm);

            inv.setItem(13, gem);

            p.openInventory(inv);
        } else {
            if (args.length == 4) {
                String type = args[0];
                String syntax = args[1];
                String name = args[2];
                String coins = args[3];

                System.out.println(type + syntax + name + coins);

                if (type.equalsIgnoreCase("default")) {
                    CoinType ctype = CoinType.DEFAULT;
                    if (Bukkit.getOfflinePlayer(name) != null) {
                        CoinAPI api = new CoinAPI(Bukkit.getOfflinePlayer(name).getUniqueId().toString());
                        String cname = cfg.getString("GemsAnzeigeName");
                        if (api.coinAccountExists()) {
                            if (syntax.equalsIgnoreCase("set")) {
                                api.setCoins(ctype, Integer.valueOf(coins));
                                sender.sendMessage("§aDie " + cname + " von §e" + name + " §awurden auf §e" + coins + " §agesetzt!");
                            } else if (syntax.equalsIgnoreCase("add")) {
                                api.addCoins(ctype, Integer.valueOf(coins));
                                sender.sendMessage("§aDem Spieler §e" + name + " §awurden §e" + coins + " §a" + cname + " §2hinzugefügt!");
                                sender.sendMessage("§aDie " + cname + " von §e" + name + " §awurden auf §e" + api.getCoins(ctype) + " §agesetzt!");
                            } else if (syntax.equalsIgnoreCase("remove")) {
                                api.removeCoins(ctype, Integer.valueOf(coins));
                                sender.sendMessage("§aDem Spieler §e" + name + " §awurden §e" + coins + " §a" + cname + " §centfernt!");
                                sender.sendMessage("§aDie " + cname + " von §e" + name + " §awurden auf §e" + api.getCoins(ctype) + " §agesetzt!");
                            }
                        } else {
                            sender.sendMessage("§cDieser Spieler hat den Server noch nie betreten!");
                        }
                    } else {
                        sender.sendMessage("§cDieser Spieler existiert nicht!");
                    }
                } else if (type.equalsIgnoreCase("untradable")) {
                    CoinType ctype = CoinType.UNTRADABLE;
                    if (Bukkit.getOfflinePlayer(name) != null) {
                        CoinAPI api = new CoinAPI(Bukkit.getOfflinePlayer(name).getUniqueId().toString());
                        String cname = cfg.getString("UntradableGemsAnzeigeName");
                        if (api.coinAccountExists()) {
                            if (syntax.equalsIgnoreCase("set")) {
                                api.setCoins(ctype, Integer.valueOf(coins));
                                sender.sendMessage("§aDie " + cname + " von §e" + name + " §awurden auf §e" + coins + " §agesetzt!");
                            } else if (syntax.equalsIgnoreCase("add")) {
                                api.addCoins(ctype, Integer.valueOf(coins));
                                sender.sendMessage("§aDem Spieler §e" + name + " §awurden §e" + coins + " §a" + cname + " §2hinzugefügt!");
                                sender.sendMessage("§aDie " + cname + " von §e" + name + " §awurden auf §e" + api.getCoins(ctype) + " §agesetzt!");
                            } else if (syntax.equalsIgnoreCase("remove")) {
                                api.removeCoins(ctype, Integer.valueOf(coins));
                                sender.sendMessage("§aDem Spieler §e" + name + " §awurden §e" + coins + " §a" + cname + " §centfernt!");
                                sender.sendMessage("§aDie " + cname + " von §e" + name + " §awurden auf §e" + api.getCoins(ctype) + " §agesetzt!");
                            }
                        } else {
                            sender.sendMessage("§cDieser Spieler hat den Server noch nie betreten!");
                        }
                    } else {
                        sender.sendMessage("§cDieser Spieler existiert nicht!");
                    }
                }else{
                    sender.sendMessage("§e§lCommands: \n §c/gems default/untradable set NAME COINS \n §c/gems default/untradable add NAME COINS \n §c/gems default/untradable remove NAME COINS \n §c/gems menu (ONLY PLAYERS)");
                }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("menu") && sender instanceof Player || args[0].equalsIgnoreCase("menü") && sender instanceof Player) {
                        Player p = (Player) sender;
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
                        //Create Inventory
                        Inventory inv = Bukkit.createInventory(null, 9 * 3, ChatColor.BOLD + "► " + ChatColor.GREEN + ChatColor.BOLD + "Gems");
                        for (int i = 0; i < inv.getSize(); i++) {
                            ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                            ItemMeta glassmeta = glass.getItemMeta();
                            glassmeta.setDisplayName(" ");
                            glass.setItemMeta(glassmeta);

                            inv.setItem(i, glass);
                        }

                        CoinAPI api = new CoinAPI(p);

                        ItemStack gem = new ItemStack(Material.EMERALD);
                        ItemMeta gemm = gem.getItemMeta();

                        gemm.setDisplayName("§a§l" + cfg.getString("GemsAnzeigeName") + ": " + ChatColor.DARK_GREEN + ChatColor.BOLD + api.getCoins(CoinType.DEFAULT));
                        gem.setItemMeta(gemm);

                        inv.setItem(10, gem);

                        gemm.setDisplayName("§a§l" + cfg.getString("UntradableGemsAnzeigeName") + ": " + ChatColor.DARK_GREEN + ChatColor.BOLD + api.getCoins(CoinType.UNTRADABLE));
                        gem.setItemMeta(gemm);
                        gem.addUnsafeEnchantment(Enchantment.LUCK, 1);

                        inv.setItem(16, gem);

                        Integer gesamt = (api.getCoins(CoinType.DEFAULT) + api.getCoins(CoinType.UNTRADABLE));
                        gemm.setDisplayName("§b§lGesamt: " + ChatColor.DARK_BLUE + ChatColor.BOLD + gesamt);
                        gem.setType(Material.DIAMOND);
                        gem.removeEnchantment(Enchantment.LUCK);
                        gem.setItemMeta(gemm);

                        inv.setItem(13, gem);

                        p.openInventory(inv);
                    } else {
                        sender.sendMessage("§e§lCommands: \n §c/gems default/untradable set NAME COINS \n §c/gems default/untradable add NAME COINS \n §c/gems default/untradable remove NAME COINS \n §c/gems menu (ONLY PLAYERS)");
                    }
                } else {
                    sender.sendMessage("§e§lCommands: \n §c/gems default/untradable set NAME COINS \n §c/gems default/untradable add NAME COINS \n §c/gems default/untradable remove NAME COINS \n §c/gems menu (ONLY PLAYERS)");
                }
            }
            return false;
        }
    }
