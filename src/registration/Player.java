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
		return "Player: " + name  + " "+ surname + "\nemail: " + email + "\nphone: " + phone;
	} 
	
    public String validatePlayer() {
    	String toReturn = "";
    	if(!isValidName()) 
    		toReturn += "Invalid name: " + name + "\n";
    	
    	if(!isValidName())
    		toReturn += "Invalid surname: " + surname +"\n";
    	
    	if(!isValidEmailAddress())
    		toReturn+= "Invalid email address: " + email +"\n";
    	
    	if(!isValidPhone())
    		toReturn+="Invalid phone number: " + phone + "\n";
    	
    	return toReturn;
    }
    
    private boolean isValidName() {
    	if(name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$"))
    		return true;
    	
    	return false;
    	
    }
    
    private boolean isValidEmailAddress() {
    	String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    private boolean isValidPhone(){
    	if (phone.matches("\\d{10}"))
            return true;
    	
    	return false;
    }
    
}
