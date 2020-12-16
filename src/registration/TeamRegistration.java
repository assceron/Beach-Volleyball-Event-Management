package registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import databases.Database;

public class TeamRegistration {
	private Database db = new Database();


	private int registerNewPlayer(Player p) {
		db.createPlayersDB(); // TOMOVE
		
		int playerID = db.selectPlayerID(p);
		
		if(playerID < 0) 
			playerID = db.insertPlayer(p);
		
		
		return playerID;
	}
	
	public int registerNewTeam(String teamName, String gameNight, ArrayList<Player> players) {
		db.createTeamsDB(); //TO MOVE
		int teamID = -1;

		if(!this.teamExistent(teamName)) {
    		/*TEAM NOT EXISTENT*/	
			for(Player p:players) 
				p.setPlayerID(registerNewPlayer(p));	
			
	    	teamID = db.insertTeam(new Team(teamName,players,gameNight));			
		}
    	return teamID;
	}	
	
	public boolean teamExistent(String teamName) {
		return db.selectTeam(teamName);
	}
	
	/*
	public static void main(String[] args) {
		TeamRegistration tr = new TeamRegistration();
		System.out.println(tr.isValidPhone("0478483536"));
	}
	*/
}
