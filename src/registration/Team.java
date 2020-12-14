package registration;

import java.util.ArrayList;

public class Team {
	private int teamID;
	private String teamName;
	private ArrayList<Player> players;
	private String gameNight;
	private int currentScore;
	
	public Team(String teamName, ArrayList<Player> players, String gameNight, int currentScore) {
		this.teamName = teamName;
		this.players = players;
		this.gameNight = gameNight;
		this.currentScore = currentScore;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
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
