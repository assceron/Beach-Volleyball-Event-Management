package databases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import registration.Player;
import registration.Team;  

public class Database {
	private static String dbName = "database.db";
	private static final String JDBC_URL = "jdbc:sqlite:";
    private Connection conn = null;  
    
	 /** 
     * Connect to a database 
     */  
    private Connection connect() {  
    	String url =  JDBC_URL + dbName;

        try {  
            this.conn = DriverManager.getConnection(url);  
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
    	System.out.println(team);
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
    
    public int selectPlayer(Player p) {
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
    
    /*
    public static void main(String[] args) {
    	Database db = new Database();
    	ArrayList<Player>players = new ArrayList<>();
    	players.add(new Player(1,"Michael","East","michael.east@gmail.com","012345678"));
    	players.add(new Player(2,"Assunta","Cerone","assuntacerone@gmail.com","04784836536"));
    	
    	
    	Team t = new Team("prova1", players, "tuesday", 0);
    	db.insertTeam(t);

    	Team t2 = new Team("prova2", players, "tuesday", 0);
    	db.insertTeam(t2);
    	

    	Team t3 = new Team("prova3", players, "tuesday", 0);
    	db.insertTeam(t3);

    	String teamName = "prova1";
    	if(db.selectTeam(teamName)) {
    		System.out.println("Team " + teamName + " existent");
    	}
    	else 
    		System.out.println("Team " + teamName + " not existent");
    }
    */

    /*
    public static void main(String[] args) {
    	Database db = new Database();
    	
		db.createPlayersDB();
		db.createTeamsDB();
    	
    	Player p1 = new Player("Michael","East","michael.east@gmail.com","012345678");
    	Player p2 = new Player("Assunta","Cerone","assuntacerone@gmail.com","04784836536");
    	
    	int p1ID = db.selectPlayer(p1);
    	if(p1ID<0) {
        	p1ID=db.insertPlayer(p1);
        	p1.setPlayerID(p1ID);
    	}

    	int p2ID=db.selectPlayer(p2);
    	if(p2ID < 0 ) {
    		p2ID=db.insertPlayer(p2);
    		p2.setPlayerID(p2ID);
    	}
    	System.out.println(p1ID);
    	System.out.println(p2ID);

    }
    */
    
    /*
    public static void main(String[] args) {
    	Database db = new Database();
    	Player p1 = new Player("m","e","me","123");
    	System.out.println(db.selectPlayer(p1));

    }
    */
}

