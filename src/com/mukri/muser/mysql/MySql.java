package com.mukri.muser.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 5:16:13 PM 
 */

public class MySql {
	
	public Connection con;
	
	public MySql(String ip, String port,String database, String user, String password) {
		if(!isConnected()) {
			if(ip.equals("none") && database.equals("database") && user.equals("username") && password.equals("password")) {
				Bukkit.broadcast("¤c[Muser] ¤7MYSQL CONNECTION IS NOT PROPERLY SETUP! PLEASE DO CHECK YOUR SETTINGS.YML", "muser.admin");
				return;
			}
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database + "?autoReconnect=true", user, password);
				System.out.println("[Muser] Connected to mySQL!");
			} catch (SQLException e) {
				System.out.println("[Muser] Could not connect to the mySQL! Reason: " + e.getMessage());
			}
		}
	}
	
	public void close() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println("[Muser] Disconnected from the mySQL!");
			} catch (SQLException e) {
				System.out.println("[Muser] Could not disconnect from mySQL?! Reason: " + e.getMessage());
			}
		}
	}
	
	public boolean isConnected() {
		return con != null;
	}
	
	public void createTable() {
		if(isConnected()) {
			try {
				con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Muser (id INTEGER, user VARCHAR(16), PRIMARY KEY (id))");
				System.out.println("[Muser] Creating mySQL table if not exists!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isPlayerExists(String user) {
		if(isConnected()) {
			try {
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Muser WHERE user=?");
				ps.setString(1, user);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					ps.close();
					rs.close();
					return true;
				}
				
				ps.close();
				rs.close();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public int getId() {
		if(isConnected()) {
			try {
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Muser ORDER BY id DESC LIMIT 1");
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					return rs.getInt("id");
				}
				
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public void addPlayers(String name) {
		if(isConnected()) {
			if(!isPlayerExists(name)) {
				try {
					PreparedStatement ps = con.prepareStatement("INSERT INTO Muser values(?,?)");
					ps.setInt(1, getId() + 1);
					ps.setString(2, name);
					
					ps.execute();
					ps.close();
					
					System.out.println("[Muser] Added player " + name + " on the database!");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void removePlayer(String name) {
		if(isConnected()) {
			if(isPlayerExists(name)) {
				try {
					PreparedStatement ps = con.prepareStatement("DELETE FROM Muser WHERE user=?");
					ps.setString(1, name);
					
					ps.execute();
					ps.close();
					
					System.out.println("[Muser] Removed player " + name + " from the database!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
