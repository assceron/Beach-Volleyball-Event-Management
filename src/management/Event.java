package management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import registration.Team;

public class Event {
	private String dateOfGame;
	private ArrayList<Match> matchesR1;
	private ArrayList<Match> matchesR2;
	
	private String round1="";
	private String round2="";
	
	private HashMap<Integer,Team> teams; 
	
	public Event(String dateOfGame, HashMap<Integer,Team> teams) {
		this.dateOfGame = dateOfGame;
		this.teams = teams;
		
		this.matchesR1 = createRound(new ArrayList<Team>(teams.values()));
		this.matchesR2 = createRound(new ArrayList<Team>(teams.values()));	
		
		for(Match m: matchesR1)
			this.round1+=m.toStringID();
		
		for(Match m: matchesR2)
			this.round2+=m.toStringID();
	}
	
	public Event(String dateOfGame, String round1, String round2, HashMap<Integer,Team> teams) {
		
		this.dateOfGame = dateOfGame;
		this.round1 = round1;
		this.round2 = round2;
		this.teams = teams;
		
		this.matchesR1 = getRoundFromString(round1);
		this.matchesR2 = getRoundFromString(round2);
	}

	public String getDateOfGame() {
		return dateOfGame;
	}

	public void setDateOfGame(String dateOfGame) {
		this.dateOfGame = dateOfGame;
	}

	public void setTeams(HashMap<Integer,Team>teams) {
		this.teams = teams;
	}
	
	public String getTeams() {
		String teamsString="List of Teams:\n";
		ArrayList<Team> teamsArray = new ArrayList<>(teams.values());
		for(Team t : teamsArray) 
			teamsString += t.toString();
		return teamsString;
	}
	
	public String getRound1(){
		return round1;
	
	}
	
	public String getRound2(){
		return round2;
	}
	
	public ArrayList<Match> getMatchesR1(){
		return matchesR1;
	}
	
	public ArrayList<Match> getMatchesR2(){
		return matchesR2;
	}
	
	public ArrayList<Match> createRound(ArrayList<Team> teams){
		Collections.shuffle(teams);
		Team t1,t2;
		ArrayList<Match> matches = new ArrayList<>();
		int countMatches = 0;
		
		for (int i=0; i< teams.size(); i+=2) {	
			
			if(i+1 >= teams.size()) {
				/* The last team of the collection will be on a bye*/
				matches.add(new Match(++countMatches, teams.get(i), new Team(0,"-",null,"-")));
				break;
			}
			
		    t1 = teams.get(i);
		    t2 = teams.get(i+1);
		    matches.add(new Match(++countMatches, t1,t2));
		}
		
		return matches;
	}
	
	private ArrayList<Match> getRoundFromString(String round){
		//round="12345698";
		ArrayList<Match> matches = new ArrayList<>();
		int countMatches = 0;
				
		for(int i=0;i<round.length();i+=2) {
			Team t1 = teams.get(Character.getNumericValue(round.charAt(i)));
			Team t2;
			int secondTeam=Character.getNumericValue(round.charAt(i+1));
			
			if(secondTeam == 0) //If number of teams is odd
				t2 = new Team(0,"-",null,"-");
			else
				t2 = teams.get(secondTeam);
			
			matches.add(new Match(++countMatches,t1,t2));			
		}
		return matches;
	}

	@Override
	public String toString() {
		return "Event - " + dateOfGame + "\nround1\n" + matchesR1 + "\n round2 \n" + matchesR2;
	}
	
}