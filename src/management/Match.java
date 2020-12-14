package management;

import registration.Team;

public class Match {
	private String matchID;
	private Team team1;
	private Team team2;
	private int scoreT1;
	private int scoreT2;
	
	public Match(String matchID, Team team1, Team team2, int scoreT1, int scoreT2) {
		this.matchID = matchID;
		this.team1 = team1;
		this.team2 = team2;
		this.scoreT1 = scoreT1;
		this.scoreT2 = scoreT2;
	}

	public String getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
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

	public int getScoreT1() {
		return scoreT1;
	}

	public void setScoreT1(int scoreT1) {
		this.scoreT1 = scoreT1;
	}

	public int getScoreT2() {
		return scoreT2;
	}

	public void setScoreT2(int scoreT2) {
		this.scoreT2 = scoreT2;
	}

	@Override
	public String toString() {
		return "Match [matchID=" + matchID + ", team1=" + team1 + ", team2=" + team2 + ", scoreT1=" + scoreT1
				+ ", scoreT2=" + scoreT2 + "]";
	}
	
	
}
