package dev.timecoding.coinapi.api;

import dev.timecoding.coinapi.CoinMain;
import dev.timecoding.coinapi.enums.CoinType;
import org.bukkit.entity.Player;

public class CoinAPI {

    private String uuid;
    private DataManager cfg = new DataManager();;
    private ConfigManager config = CoinMain.config;

    public CoinAPI(Player p){
        this.uuid = p.getUniqueId().toString();
    }

    public CoinAPI(String uuid){
        this.uuid = uuid;
    }

    public boolean coinAccountExists(){
        if(cfg.keyExists(uuid+".gems")){
            return true;
        }
        return false;
    }

    public void createDefaultCoinAccount(){
        if(!coinAccountExists()){
            cfg.setInt(uuid+".gems", config.getInt("StartGems"));
            cfg.setInt(uuid+".ugems", config.getInt("StartUntradableGems"));
            cfg.saveConfig();
        }
    }

    public Integer getCoins(CoinType type){
        if(coinAccountExists()){
            switch (type){
                case DEFAULT:
                    return cfg.getInt(uuid+".gems");
                case UNTRADABLE:
                    return cfg.getInt(uuid+".ugems");
            }
        }
        return 0;
    }

    public void setCoins(CoinType type, Integer coins){
        switch (type){
            case DEFAULT:
                cfg.setInt(uuid+".gems", coins);
                break;
            case UNTRADABLE:
                cfg.setInt(uuid+".ugems", coins);
                break;
        }
        cfg.saveConfig();
    }

    public void addCoins(CoinType type, Integer coins){
        Integer finalcoins = (coins+getCoins(type));
        setCoins(type, finalcoins);
    }

    public void removeCoins(CoinType type, Integer coins){
        Integer finalcoins = (coins-getCoins(type));
        setCoins(type, finalcoins);
    }

}
