package management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import databases.EventsDB;
import databases.TeamsDB;
import registration.Team;


public class EventManagement {
	
	private EventsDB eventDB = new EventsDB();
	private TeamsDB teamsDB = new TeamsDB();
	private Event event = null;
	
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
	
	public MessageEvent showEvent(String dateOfGame) {
		eventDB.createEventsDB();
		long diff = dateDifference(dateOfGame);
		
		if(diff < 0 ) 
			return new MessageEvent(null, "Event not available");
		
		if(diff > 7) 
			return new MessageEvent(null, "Registration still open. ");

		
		event = eventDB.selectEvent(dateOfGame);
			
		if(event != null) { // ALREADY IN DB
			return new MessageEvent(event, "Event details:\n + event");
		}
		
		else { //NOT IN DB
			HashMap<Integer,Team> teams = teamsDB.selectAllTeams(dateOfGame);
			if(teams.size()==0)
				return new MessageEvent(null, "No teams registered for this days");
			
			event = new Event(dateOfGame,teams);
			eventDB.insertEvent(event);
			return new MessageEvent(event, "Event details:\n + event");
		}
	}			
			
	
	
}
