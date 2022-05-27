package dev.timecoding.coinapi.api;

import dev.timecoding.coinapi.CoinMain;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private CoinMain plugin;
    private File file;
    private YamlConfiguration cfg;

    public ConfigManager(CoinMain plugin){
        this.plugin = plugin;
        init();

        this.file = new File("plugins//CoinAPI", "config.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void init(){
        //Init Config
        File file = new File("plugins//CoinAPI", "config.yml");
        if(!file.exists()) {
            plugin.saveResource("config.yml", false);
        }
    }

    public boolean keyExists(String key){
        if(cfg.get(key) != null){
            return true;
        }
        return false;
    }

    public String getString(String key){
        if(keyExists(key)){
            return cfg.getString(key);
        }
        return null;
    }

    public Integer getInt(String key){
        if(keyExists(key)){
            return cfg.getInt(key);
        }
        return null;
    }

}
