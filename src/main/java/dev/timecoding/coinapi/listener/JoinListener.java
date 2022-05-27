package dev.timecoding.coinapi.listener;

import dev.timecoding.coinapi.api.CoinAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        //Init CoinAPI
        CoinAPI api = new CoinAPI(p);
        if(!api.coinAccountExists()){
            api.createDefaultCoinAccount();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "► " + ChatColor.GREEN + ChatColor.BOLD + "Gems")){
            e.setCancelled(true);
        }
    }

}
