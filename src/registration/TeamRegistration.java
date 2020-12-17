package registration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import databases.PlayersDB;
import databases.TeamsDB;
import management.MessageEvent;

public class TeamRegistration {
	private TeamsDB teamsDB = new TeamsDB();
	private PlayersDB playersDB = new PlayersDB();

	private long dateDifference(String eventDate) {
		long diff = -1;
		try {
		    LocalDate now = LocalDate.now();
		    DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
		            .append(DateTimeFormatter.ofPattern("dd/MMM/yyyy")).toFormatter();
		 
		    LocalDate date2 = LocalDate.parse(eventDate, formatter);
		    diff = now.until(date2, ChronoUnit.DAYS);
		    
		} catch (DateTimeParseException e) {
	         // Exception handling message/mechanism/logging as per company standard
			System.out.println(e.getMessage());
		}
		return diff;
	}
	
	private int registerNewPlayer(Player p) {
		playersDB.createPlayersDB(); 
		
		int playerID = playersDB.selectPlayerID(p);
		
		if(playerID < 0) 
			playerID = playersDB.insertPlayer(p);
		
		
		return playerID;
	}
	
	public int registerNewTeam(String teamName, String dateOfGame, ArrayList<Player> players) {
		teamsDB.createTeamsDB(); //TO MOVE
		int teamID = -1;
		
		/*
		 * When register a new team, check for dateOfGame. The registration closes 7 days before the game.
		 * 		long diff = dateDifference(dateOfGame);
		 * 			if(diff < 7 ) {
		 * 				System.out.println("Registration are closed");
		 * 				return teamID;
		 * 			}
		 * */
		
		if(!this.teamExistent(teamName)) {
    		/*TEAM NOT EXISTENT*/	
			for(Player p:players) 
				p.setPlayerID(registerNewPlayer(p));	
			
	    	teamID = teamsDB.insertTeam(new Team(teamName,players,dateOfGame));			
		}
    	return teamID;
	}	
	
	public boolean teamExistent(String teamName) {
		return teamsDB.selectTeam(teamName);
	}
}