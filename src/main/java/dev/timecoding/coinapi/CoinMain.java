package dev.timecoding.coinapi;

import dev.timecoding.coinapi.api.ConfigManager;
import dev.timecoding.coinapi.command.GemCommand;
import dev.timecoding.coinapi.command.GemTabCompleter;
import dev.timecoding.coinapi.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoinMain extends JavaPlugin {

    public static ConfigManager config;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW+"CoinAPI by TimeCoding"+ChatColor.GREEN+" wurde aktiviert!");
        //Create ConfigInstance
        config = new ConfigManager(this);
        //Register Listener
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        //Register Command
        getCommand("gems").setExecutor(new GemCommand());
        getCommand("gems").setTabCompleter(new GemTabCompleter());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW+"CoinAPI by TimeCoding"+ChatColor.RED+" wurde deaktiviert!");
    }
}
