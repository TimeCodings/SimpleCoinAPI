package dev.timecoding.coinapi.api;

import dev.timecoding.coinapi.CoinMain;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private File file = new File("plugins//CoinAPI", "data.yml");
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public DataManager(){
        init();
    }

    public void init(){
        //Init Config
        cfg.options().copyDefaults(true);
        saveConfig();
    }

    public void saveConfig(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean keyExists(String key){
        if(cfg.get(key) != null){
            return true;
        }
        return false;
    }

    public void setString(String key, String value){
        cfg.set(key, value);
        saveConfig();
    }

    public String getString(String key){
        if(keyExists(key)){
            return cfg.getString(key);
        }
        return null;
    }

    public void setInt(String key, Integer value){
        cfg.set(key, value);
        saveConfig();
    }

    public Integer getInt(String key){
        if(keyExists(key)){
            return cfg.getInt(key);
        }
        return null;
    }


}
