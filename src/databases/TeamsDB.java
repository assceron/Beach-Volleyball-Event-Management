package databases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import registration.Captain;
import registration.Player;
import registration.Team;  

public class TeamsDB {
	private String dbName = "teams.db";
	private final String JDBC_URL = "jdbc:sqlite:";
    private Connection conn = null;  
    private PlayersDB playersDB = new PlayersDB();
    
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
     * Create Teams table
     * */
    public void createTeamsDB() {  
    	       	
    	String query = "CREATE TABLE IF NOT EXISTS teams(\n" 
    				  + "teamID 	INTEGER 		PRIMARY KEY AUTOINCREMENT,\n"
    				  + "teamName 	VARCHAR(256) 	NOT NULL,\n"
    				  + "gameNight 	VARCHAR(256) 	NOT NULL,\n"
    				  + "captainID 	INTEGER 		NOT NULL,\n"
    				  + "playerID2 	INTEGER 		NOT NULL,\n"
    				  + "playerID3 	INTEGER					,\n"
    				  + "playerID4 	INTEGER					 \n"
    				  + ");";
    	
    	createTable(query);
    }  
    
    private String prepareInsert(int numberOfPlayers) {
    	String query=null;
    	
    	switch(numberOfPlayers) {
    		case 2:
	    		query = "INSERT INTO teams(teamName,gameNight,captainID,playerID2)"
	 				   +"VALUES(?,?,?,?)";
	    		break;
	    	
    		case 3:
	    		query = "INSERT INTO teams(teamName,gameNight,captainID,playerID2,playerID3)"
	  				   +"VALUES(?,?,?,?,?)";
	    		break;
	    	
    		case 4:
    			query = "INSERT INTO teams(teamName,gameNight,captainID,playerID2,playerID3,playerID4)"
    					+"VALUES(?,?,?,?,?,?)";
    			break;
    	}
    	return query;
    }
    /**
     * Insert team into the table
     * */
    public int insertTeam(Team team) {
    	String query = prepareInsert(team.getPlayers().size());
    	int teamID = -1;
    	try {
    		Connection conn = connect();
    		
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, team.getTeamName());
            pstmt.setString(2, team.getGameNight());
            
            ArrayList<Player> players = team.getPlayers();
            
            for(int i=0;i<players.size();i++) {
            	int arg = 3+i;
            	pstmt.setInt(arg, players.get(i).getPlayerID());
            }

            pstmt.executeUpdate();
            System.out.println("Team " + team.getTeamName() + " inserted");
            
		    ResultSet rs = pstmt.getGeneratedKeys();
		    teamID= rs.getInt(1);
		     
    	} catch(SQLException e) {
            System.out.println(e.getMessage());  
    	}
    	
    	return teamID;
    }
    
    
    /**
     * Select a team from the table
     * */
    
    public boolean selectTeam(String teamName) {
    	String query = "SELECT * FROM teams WHERE teamName=?";
    	boolean teamExistent=false;
    	
    	try {
    		Connection conn = connect();
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, teamName);
    		
    		ResultSet rs = pstmt.executeQuery();  
    		teamExistent = rs.next();
    		
    	} catch(SQLException e) {
            System.out.println(e.getMessage());  
    	  }
    	
    	return teamExistent;
    }
    
    /**
     * Select all the teams
     */
    
    public HashMap<Integer,Team> selectAllTeams(String gameNight){
    	HashMap<Integer,Team> teams = new HashMap<>();
    	String query = "SELECT * FROM teams WHERE gameNight = ?";
    	
    	try {
    		Connection conn = connect();
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, gameNight);
    		ResultSet rs = pstmt.executeQuery();  
    		
    		
    		while(rs.next()) {
    			ArrayList<Player> players = new ArrayList<Player>();
    			String teamName = rs.getString("teamName");
    			int teamID = rs.getInt("teamID");
    			
    			int captainID = rs.getInt("captainID");
    			Captain captain = (Captain) playersDB.selectPlayer(captainID,true);
    			players.add(captain);
    			
    			int playerID2 = rs.getInt("playerID2");
    			Player p2 = playersDB.selectPlayer(playerID2,false);
    			players.add(p2);
    			
    			int playerID3 = rs.getInt("playerID3");
    			Player p3 = playersDB.selectPlayer(playerID3,false);
    			players.add(p3);
    			
    			int playerID4 = rs.getInt("playerID4");
    			Player p4 = playersDB.selectPlayer(playerID4,false);
    			players.add(p4);
    			
    			teams.put(teamID, new Team(teamID,teamName,players,gameNight));
    		}

    	}catch(SQLException e) {
            System.out.println(e.getMessage());  
    	  }
    	return teams;
    }
}