package dev.timecoding.coinapi.command;

import dev.timecoding.coinapi.CoinMain;
import dev.timecoding.coinapi.api.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GemTabCompleter implements TabCompleter {

    private ConfigManager cfg = CoinMain.config;

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("gems") || command.getName().equalsIgnoreCase("gem")){
            if(sender.hasPermission(cfg.getString("AdminPermission"))) {
                ArrayList<String> list = new ArrayList<>();
                if (args.length == 1) {
                    list.add("menü");
                    list.add("default");
                    list.add("untradable");
                } else if (args.length == 2) {
                    list.add("set");
                    list.add("add");
                    list.add("remove");
                } else if (args.length == 3) {
                    list.add("SPIELERNAME");
                } else if (args.length == 4) {
                    list.add("1");
                    list.add("10");
                    list.add("100");
                    list.add("1000");
                }
                return list;
            }
        }
        return null;
    }
}
