package com.mukri.muser.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mukri.muser.Muser;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 10:55:17 PM 
 */

public class SettingsData {

	File file;
	FileConfiguration config;
	
	public SettingsData() {
		file = new File(Muser.getIns().getDataFolder(), "settings.yml");
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public boolean isExists() {
		return file.exists();
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addDefault() {
		if(!isExists()) {
			try {
				
				if(!file.getParentFile().exists()) {	
					file.getParentFile().mkdirs();
				}
				
				file.createNewFile();
				
				config.set("Msg.Kick", "&c[Muser]&7Please register at\n &cwww.example.com");
				config.set("mySql.IP", "none");
				config.set("mySql.PORT", "3306");
				config.set("mySql.DATABASE", "database");
				config.set("mySql.USER", "username");
				config.set("mySql.PASSWORD", "password");
				
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public String getKickMsg() {
		return config.getString("Msg.Kick");
	}
	
	public String getIP() {
		return config.getString("mySql.IP");
	}
	
	public String getPort() {
		return config.getString("mySql.PORT");
	}
	
	public String getUser() {
		return config.getString("mySql.USER");
	}
	
	public String getPassword() {
		return config.getString("mySql.PASSWORD");
	}
	
	public String getDatabase() {
		return config.getString("mySql.DATABASE");
	}
	
}
