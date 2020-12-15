package registration;

import java.util.Scanner;

import databases.Database;

public class Player {
	protected int playerID; 
	protected String name;
	protected String surname;
	protected String email;
	protected String phone;
	
	public Player(String name, String surname, String email, String phone){
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
	}
	
	public Player(int playerID, String name, String surname, String email, String phone){
		this.playerID = playerID;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
	}
	
	
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + "]";
	}
	
	private static Player createPlayer(boolean isCaptain) {		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Name: ");
		String name = input.next();
		
		System.out.print("Surname: ");
		String surname = input.next();
		//control surname
		
		System.out.print("Email: ");
		String email = input.next();
		//Control email
		
		System.out.print("Phone: ");
		String phone = input.next();
		//control phone
		
		//CREATE a function checkPlayerDetails() before creating Player
		if(isCaptain)
			return new Captain(name,surname,email,phone);
		
		return new Player(name,surname,email,phone);
		
	}
	
	protected static Player registerNewPlayer(boolean isCaptain) {
		Database db = new Database();
		db.createPlayersDB(); // TOMOVE
		Player p = createPlayer(isCaptain);
		int playerID = db.selectPlayerID(p);
		
		if(playerID < 0)
			playerID = db.insertPlayer(p);

		p.setPlayerID(playerID);

		return p;
	}
	
	
}
