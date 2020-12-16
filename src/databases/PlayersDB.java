package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import registration.Captain;
import registration.Player;

public class PlayersDB {
	private String dbName = "players.db";
	private final String JDBC_URL = "jdbc:sqlite:";
    private Connection conn = null;  
    
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
     * Create Players table
     * */
    public void createPlayersDB() {  
          	
    	String query = "CREATE TABLE IF NOT EXISTS players(\n" 
    				  + "playerID 	INTEGER 	 PRIMARY KEY AUTOINCREMENT,\n"
    				  + "name 		VARCHAR(256) NOT NULL,\n"
    				  + "surname 	VARCHAR(256) NOT NULL,\n"
    				  + "email 		VARCHAR(256) NOT NULL,\n"
    				  + "phone 		VARCHAR(10)  NOT NULL\n"
    				  + ");";
    	
    	createTable(query);
    }  
    
    /**
     * Insert player
     */
    
    public int insertPlayer(Player p) {
    	String query = "INSERT INTO players(name,surname,email,phone)"
				   	   +"VALUES(?,?,?,?);";
    	
    	int playerID=-1;
    	try {
			 Connection conn = connect();
			
		     PreparedStatement pstmt = conn.prepareStatement(query);
		     pstmt.setString(1, p.getName());
		     pstmt.setString(2, p.getSurname());
		     pstmt.setString(3, p.getEmail());
		     pstmt.setString(4, p.getPhone());
		     
		     pstmt.executeUpdate();
		     System.out.println("Player " + p.getName() + " " + p.getSurname() + " inserted");
		     
		     ResultSet rs = pstmt.getGeneratedKeys();
		     playerID= rs.getInt(1);
	     
		} catch(SQLException e) {
	     System.out.println(e.getMessage());  
		}
		
    	
		return playerID;
    }
    
    /**
     * Select a player from the table
     * */
    
    public int selectPlayerID(Player p) {
    	String query = "SELECT playerID FROM players WHERE name=? AND surname = ? AND phone = ?";
    	int playerID=-1;
    	
    	try {
    		Connection conn = connect();
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, p.getName());
    		pstmt.setString(2, p.getSurname());
    		pstmt.setString(3, p.getPhone());
    		
    		
    		ResultSet rs = pstmt.executeQuery();  
    		
    		while(rs.next()) {
    			playerID=rs.getInt("playerID");
    		}
    		
    	} catch(SQLException e) {
            System.out.println(e.getMessage());  
    	  }
    	
    	return playerID;
    }
    
    public Player selectPlayer(int playerID, boolean isCaptain) {
    	String query = "SELECT * FROM players WHERE playerID=?";
		Player p = null;

    	try {
    		Connection conn = connect();
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, playerID);
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			String name=rs.getString("name");
    			String surname = rs.getString("surname");
    			String email = rs.getString("email");
    			String phone = rs.getString("phone");
    			
    			if(isCaptain)
    				p = new Captain(playerID,name,surname,email,phone);
    			else
    				p = new Player(playerID,name,surname,email,phone);
    		}
    		
    	}catch(SQLException e) {
            System.out.println(e.getMessage());  
    	  }
    	
    	return p;
    }    
    


}
