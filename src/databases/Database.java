package databases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import registration.Captain;
import registration.Player;
import registration.Team;  

public class Database {
	private static String dbName = "database.db";
	private static final String JDBC_URL = "jdbc:sqlite:";
    private static Connection conn = null;  
    
	 /** 
     * Connect to a database 
     */  
    private static Connection connect() {  
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
    
    public ArrayList<Team> selectAllTeams(String gameNight){
    	ArrayList<Team> teams = new ArrayList<>();
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
    			Captain captain = (Captain) selectPlayer(captainID,true);
    			players.add(captain);
    			
    			int playerID2 = rs.getInt("playerID2");
    			Player p2 = selectPlayer(playerID2,false);
    			players.add(p2);
    			
    			int playerID3 = rs.getInt("playerID3");
    			Player p3 = selectPlayer(playerID3,false);
    			players.add(p3);
    			
    			int playerID4 = rs.getInt("playerID4");
    			Player p4 = selectPlayer(playerID4,false);
    			players.add(p4);
    			
    			teams.add(new Team(teamID,teamName,players,gameNight));
    		}

    	}catch(SQLException e) {
            System.out.println(e.getMessage());  
    	  }
    	return teams;
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
    
    public static Player selectPlayer(int playerID, boolean isCaptain) {
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
    	
    	ArrayList<Team> teams = db.selectAllTeams("tuesday");
    	System.out.println(teams);

    }
    */
    
}

