package registration;

import java.util.Scanner;

import databases.Database;

public class PlayerRegistration {
	private static Database db = new Database();
	
	private Player createPlayer(boolean isCaptain) {		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Name: ");
		String name = input.next();
		//control name
		
		System.out.print("Surname: ");
		String surname = input.next();
		//control surname
		
		System.out.print("Email: ");
		String email = input.next();
		//Control email
		
		System.out.print("Phone: ");
		String phone = input.next();
		//control phone
		
		if(isCaptain)
			return new Captain(name,surname,email,phone);
		
		return new Player(name,surname,email,phone);
		
	}
	
	protected Player registerNewPlayer(boolean isCaptain) {
		db.createPlayersDB(); // TOMOVE
		Player p = createPlayer(isCaptain);
		int playerID = db.selectPlayer(p);
		
		if(playerID < 0)
			playerID = db.insertPlayer(p);

		p.setPlayerID(playerID);

		return p;
	}

	/*
	public static void main(String[] args) {
		PlayerRegistration pr = new PlayerRegistration();
		pr.registerNewPlayer();
	}
	*/
	
}
