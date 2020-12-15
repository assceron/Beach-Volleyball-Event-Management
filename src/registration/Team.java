package registration;

import java.util.ArrayList;
import java.util.Scanner;

import databases.Database;

public class Team {
	private int teamID;
	private String teamName;
	private ArrayList<Player> players;
	private String gameNight;
	
	public Team(String teamName, ArrayList<Player> players, String gameNight) {
		this.teamName = teamName;
		this.players = players;
		this.gameNight = gameNight;
	}

	public Team(int teamID, String teamName, ArrayList<Player> players, String gameNight) {
		this.teamID = teamID;
		this.teamName = teamName;
		this.players = players;
		this.gameNight = gameNight;
	}
	
	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public String getGameNight() {
		return gameNight;
	}

	public void setGameNight(String gameNight) {
		this.gameNight = gameNight;
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", players=" + players + ", gameNight=" + gameNight
				+ "]";
	}

	private static Team createTeam(String teamName, String gameNight) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Number of players (2-4): ");
		int numberOfPlayers = input.nextInt();
		//control number 2-4
		
		ArrayList<Player> players = new ArrayList<Player>();

		for(int i=0; i<numberOfPlayers; i++) {
			if(i==0) {
				System.out.println("Insert info Captain: ");
				players.add(Player.registerNewPlayer(true));	
			}
			else {
				System.out.println("Insert info Player: ");
				players.add(Player.registerNewPlayer(false));	
			}

		}
				
		input.close();
		
		return new Team(teamName, players, gameNight);
	}
	
	private static Team registerNewTeam() {
		Database db = new Database();
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
	
}
