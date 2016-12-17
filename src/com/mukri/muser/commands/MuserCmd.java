package com.mukri.muser.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mukri.muser.Muser;
import com.mukri.muser.mysql.MySql;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 10:54:15 PM 
 */

public class MuserCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}

		Player p = (Player) sender;

		if(cmd.getName().equalsIgnoreCase("muser")) {
			if(args.length < 1) {
				if(p.hasPermission("muser.admin")) {
					p.sendMessage("¤c/muser reconnect");
					p.sendMessage("¤c/muser reload");
				}
			}

			else if(args[0].equalsIgnoreCase("reconnect")) {
				if(p.hasPermission("muser.admin")) {
					p.sendMessage("¤c[Muser] ¤7Connecting to the mysql! Might be lagging for a tick!");
					Muser.getIns().sql = new MySql(Muser.getIns().settings.getIP(), Muser.getIns().settings.getPort(), Muser.getIns().settings.getDatabase(), Muser.getIns().settings.getUser(), Muser.getIns().settings.getPassword());
					p.sendMessage("¤c[Muser] ¤7Connected!");
				}
			}
			else if(args[0].equalsIgnoreCase("reload")) {
				if(p.hasPermission("muser.admin")) {
					Muser.getIns().settings.reloadConfig();
					p.sendMessage("¤c[Muser] ¤7Please reload your server because this plugin uses MySql DB!");
				}
			}
		}

		return false;
	}

}
