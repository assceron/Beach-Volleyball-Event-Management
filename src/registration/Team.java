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
	
}
