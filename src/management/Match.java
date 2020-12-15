package management;

import registration.Team;

public class Match {
	private int matchID;
	private Team team1;
	private Team team2;
	
	public Match(int matchID, Team team1, Team team2) {
		this.matchID = matchID;
		this.team1 = team1;
		this.team2 = team2;

	}

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	@Override
	public String toString() {
		return team1.getTeamName() + " VS " + team2.getTeamName() + "\n";
	}
	
	
}