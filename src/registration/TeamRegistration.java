package registration;

import java.util.ArrayList;

import databases.PlayersDB;
import databases.TeamsDB;

public class TeamRegistration {
	private TeamsDB teamsDB = new TeamsDB();
	private PlayersDB playersDB = new PlayersDB();

	private int registerNewPlayer(Player p) {
		playersDB.createPlayersDB(); 
		
		int playerID = playersDB.selectPlayerID(p);
		
		if(playerID < 0) 
			playerID = playersDB.insertPlayer(p);
		
		
		return playerID;
	}
	
	public int registerNewTeam(String teamName, String gameNight, ArrayList<Player> players) {
		teamsDB.createTeamsDB(); //TO MOVE
		int teamID = -1;

		if(!this.teamExistent(teamName)) {
    		/*TEAM NOT EXISTENT*/	
			for(Player p:players) 
				p.setPlayerID(registerNewPlayer(p));	
			
	    	teamID = teamsDB.insertTeam(new Team(teamName,players,gameNight));			
		}
    	return teamID;
	}	
	
	public boolean teamExistent(String teamName) {
		return teamsDB.selectTeam(teamName);
	}
}