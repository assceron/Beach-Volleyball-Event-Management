package registration;

import java.util.Scanner;

import databases.Database;

import java.util.ArrayList;


public class TeamRegistration {
	private static Database db = new Database();
	private static PlayerRegistration pr = new PlayerRegistration();
	
	private Team createTeam(String teamName, String gameNight) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Number of players (2-4): ");
		int numberOfPlayers = input.nextInt();
		//control number 2-4
		
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i=0; i<numberOfPlayers; i++) {
			if(i==0) {
				System.out.println("Insert info Captain: ");
				players.add(pr.registerNewPlayer(true));	
			}
			else {
				System.out.println("Insert info Player: ");
				players.add(pr.registerNewPlayer(false));	
			}

		}
				
		input.close();
		
		return new Team(teamName, players, gameNight, 0);
	}
	
	private Team registerNewTeam() {
		db.createTeamsDB(); //TO MOVE
		Team team = null;
		Scanner input = new Scanner(System.in);
	
		
        System.out.print("When do you want to play? ");
		String gameNight = input.next();
		//control date
		
		
		/*Check that there are spot left for that night
		 * a. No spot --> I am sorry. There is no spot left this night
		 * 	  return;
		 * */
		
		int teamID = -1;
		boolean teamCreated=false;
		while (!teamCreated) {
			
	        System.out.print("Enter the team name: ");
	        String teamName = input.next();
	        //control 0...256
	        	    	
	    	if(!db.selectTeam(teamName)) {
	    		/*TEAM NOT EXISTENT*/
	    		team = createTeam(teamName, gameNight);
	    		teamID = db.insertTeam(team);
	    		team.setTeamID(teamID);
	    		teamCreated = true;
	    	}
	    	else {
	    		/*TEAM ALREADY EXISTENT*/
	          	System.out.println("A team with the same name already existent. Enter a new name.");
	    	}
		}
    	input.close();
    	return team;
	}	
	
	
	
	public static void main(String[] args) {
		TeamRegistration tr = new TeamRegistration();
		tr.registerNewTeam();
	}
	
	
}

