package com.mukri.muser;

import org.bukkit.plugin.java.JavaPlugin;

import com.mukri.muser.commands.MuserCmd;
import com.mukri.muser.files.SettingsData;
import com.mukri.muser.listeners.PreLogin;
import com.mukri.muser.mysql.MySql;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 5:15:53 PM 
 */

public class Muser extends JavaPlugin {
	
	public static Muser instance;
	public MySql sql;
	public SettingsData settings;
	
	public void onEnable() {
		instance = this;
		
		listen();
		commands();
		
		settings = new SettingsData();
		
		if(!settings.isExists()) {
			settings.addDefault();
		}
		
		sql = new MySql(settings.getIP(), settings.getPort(), settings.getDatabase(), settings.getUser(), settings.getPassword());
		sql.createTable();
	}
	
	public void onDisable() {
		sql.close();
	}
	
	public static Muser getIns() {
		return instance;
	}
	
	public void listen() {
		getServer().getPluginManager().registerEvents(new PreLogin(this), this);
	}
	
	public void commands() {
		getCommand("muser").setExecutor(new MuserCmd());
	}

}
