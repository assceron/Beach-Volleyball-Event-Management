package registration;

public class Captain extends Player{
	private int moneyPaid = 0;
	
	public Captain(String name, String surname, String email, String phone) {
		super(name, surname, email, phone);
		// TODO Auto-generated constructor stub
	}
	
	public int getMoneyPaid() {
		return moneyPaid;
	}

	public void setMoneyPaid(int moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	
	@Override
	public String toString() {
		return "Captain [playerID=" + playerID + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + "]";
	}
}
