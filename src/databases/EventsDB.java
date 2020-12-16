package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import management.Event;
import registration.Team;

public class EventsDB {
	private String dbName = "events.db";
	private final String JDBC_URL = "jdbc:sqlite:";
    private Connection conn = null;  
    private TeamsDB teamsDB = new TeamsDB();
	 /** 
     * Connect to a database 
     */  
    private Connection connect() {  
    	String url =  JDBC_URL + dbName;

        try {  
            conn = DriverManager.getConnection(url);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return conn;    
    } 
    
    private void createTable(String query) {
    	
        try {  
            Connection conn = connect();
    		PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
    }
    /**
     * Create Events table
     * */
    public void createEventsDB() {  
          	
    	String query = "CREATE TABLE IF NOT EXISTS events(\n" 
    				  + "eventID 	INTEGER 	 PRIMARY KEY AUTOINCREMENT,\n"
    				  + "dateOfGame	VARCHAR(256) NOT NULL,\n"
    				  + "round1 	VARCHAR(256) NOT NULL,\n"
    				  + "round2 	VARCHAR(256) NOT NULL\n"
    				  + ");";
    	
    	createTable(query);
    }
    
    /**
     * Insert event
     */
    
    public int insertEvent(Event event) {
    	String query = "INSERT INTO events(dateOfGame,round1,round2)"
				   	   +"VALUES(?,?,?);";
    	
    	int eventID=-1;
    	try {
			 Connection conn = connect();
			
		     PreparedStatement pstmt = conn.prepareStatement(query);
		     pstmt.setString(1, event.getDateOfGame());
		     pstmt.setString(2, event.getRound1());
		     pstmt.setString(3, event.getRound2());
		     
		     pstmt.executeUpdate();
		     System.out.println("Event " + event.getDateOfGame() + " inserted");
		     
		     ResultSet rs = pstmt.getGeneratedKeys();
		     eventID= rs.getInt(1);
	     
		} catch(SQLException e) {
	     System.out.println(e.getMessage());  
		}
		return eventID;
    }
    
    /**
     * Select Event ID
     * */
    
    public Event selectEvent(String dateOfGame) {
    	String query = "SELECT * FROM events WHERE dateOfGame=?";
    	
    	try {
    		Connection conn = connect();
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, dateOfGame);
    		    		
    		ResultSet rs = pstmt.executeQuery();  
    
    		if(!rs.next())
    			return null;
    		
    		HashMap<Integer,Team> teams = teamsDB.selectAllTeams(dateOfGame);
    		
    		String round1="";
    		String round2="";
    		while(rs.next()) {
    			round1= rs.getString("round1");
    			round2= rs.getString("round2");
    		}
    		
			return new Event(dateOfGame,round1,round2,teams);
    	}catch(SQLException e) {
            System.out.println(e.getMessage());  
    	}
    	
    return null;
  }
}
