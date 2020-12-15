package management;

import java.util.ArrayList;
import java.util.Collections;

import databases.Database;
import registration.Team;

public class Event {
	private String gameNight;
	private ArrayList<Match> matchesR1;
	private ArrayList<Match> matchesR2;
	private ArrayList<Match> matchesR3;

	private ArrayList<Team> teams; 

	
	public Event(String gameNight) {
		Database db = new Database();
		teams = db.selectAllTeams(gameNight);
		matchesR1 = createRound(teams);
		matchesR2 = createRound(teams);
		matchesR3 = createRound(teams);
		
		this.gameNight = gameNight;
	}

	public String getgameNight() {
		return gameNight;
	}

	public void setgameNight(String dateDay) {
		this.gameNight = dateDay;
	}


	
	public ArrayList<Match> createRound(ArrayList<Team> teams){
		Collections.shuffle(teams);
		Team t1,t2;
		ArrayList<Match> matches = new ArrayList<>();
		int countMatches = 0;
		
		for (int i=0; i< teams.size(); i+=2) {	
			
			if(i+1 >= teams.size()) {
				/* The last team of the collection will be on a bye*/
				matches.add(new Match(++countMatches, teams.get(i), new Team(-1,"-",null,"-")));
				break;
			}
			
		    t1 = teams.get(i);
		    t2 = teams.get(i+1);
		    matches.add(new Match(++countMatches, t1,t2));
		}
		
		
		return matches;
	}

	@Override
	public String toString() {
		return "Event - " + gameNight + "\nround1\n" + matchesR1 + "\n round2 \n" + matchesR2
				+ "\n round3 \n" + matchesR3;
	}
	
	/*
	public static void main(String[] args) {
		Event e = new Event("tuesday");
		System.out.println(e);
	}
	*/
}