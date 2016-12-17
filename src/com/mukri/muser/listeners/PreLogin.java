package com.mukri.muser.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.mukri.muser.Muser;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 10:25:42 PM 
 */

public class PreLogin implements Listener {
	
	public Muser plugin;
	
	public PreLogin(Muser plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		
		if(Muser.getIns().settings.getIP().equals("none") && Muser.getIns().settings.getDatabase().equals("database") && Muser.getIns().settings.getUser().equals("username") && Muser.getIns().settings.getPassword().equals("password")) {
			Bukkit.broadcast("¤c[Muser] ¤7MYSQL CONNECTION IS NOT PROPERLY SETUP! PLEASE DO CHECK YOUR SETTINGS.YML", "muser.admin");
			return;
		}
		
		if(Muser.getIns().sql.isPlayerExists(p.getName())) {
			e.allow();
		} else {
			e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Muser.getIns().settings.getKickMsg().replace("&", "¤"));
		}
	}

}
