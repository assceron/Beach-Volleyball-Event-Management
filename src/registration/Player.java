package registration;

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
	
	
}
