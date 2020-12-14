package management;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	private String eventID;
	private Date   eventDate;
	private String dateDay;
	private ArrayList<Match> matches;
	
	public Event(String eventID, Date eventDate, String dateDay, ArrayList<Match> matches) {
		super();
		this.eventID = eventID;
		this.eventDate = eventDate;
		this.dateDay = dateDay;
		this.matches = matches;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getDateDay() {
		return dateDay;
	}

	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}
	
	
}

