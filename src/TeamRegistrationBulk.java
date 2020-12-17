import java.util.ArrayList;

import registration.Player;
import registration.TeamRegistration;

public class TeamRegistrationBulk {
	public static void main(String args[]) {
		TeamRegistration tr = new TeamRegistration();
		
		
		ArrayList<Player> players1 = new ArrayList<>();
		players1.add(new Player("Assunta","Cerone","assuntacerone@gmail.com","1234567890"));
		players1.add(new Player("Gaia","Mantovani","gamant@gmail.com","1243658790"));
		tr.registerNewTeam("test1","22/Dec/2020",players1);
		
		
		ArrayList<Player> players2 = new ArrayList<>();
		players2.add(new Player("Maria","Cerone","mariacerone@gmail.com","1234567890"));
		players2.add(new Player("Giorgia","Mantovani","gioamant@gmail.com","1243658790"));
		tr.registerNewTeam("team2","22/Dec/2020",players2);
		
		ArrayList<Player> players3 = new ArrayList<>();
		players3.add(new Player("Francesco","Cerone","francescocerone@gmail.com","1234567890"));
		players3.add(new Player("Mario","Mantovani","mariomant@gmail.com","1243658790"));
		tr.registerNewTeam("team3","22/Dec/2020",players3);
		
		ArrayList<Player> players4 = new ArrayList<>();
		players4.add(new Player("Pietro","Cerone","pietrocerone@gmail.com","1234567890"));
		players4.add(new Player("Valeria","Mantovani","valeriamant@gmail.com","1243658790"));
		tr.registerNewTeam("team4","22/Dec/2020",players4);
		
		ArrayList<Player> players5 = new ArrayList<>();
		players5.add(new Player("Gerardina","Cerone","gerardinacerone@gmail.com","1234567890"));
		players5.add(new Player("Paolo","Mantovani","paolomant@gmail.com","1243658790"));
		tr.registerNewTeam("team5","22/Dec/2020",players5);
		
		ArrayList<Player> players6 = new ArrayList<>();
		players6.add(new Player("Assunta","East","assuntaeast@gmail.com","1234567890"));
		players6.add(new Player("Gaia","Barbone","gabarbone@gmail.com","12436587890"));
		tr.registerNewTeam("team6","23/Dec/2020",players6);
		
		ArrayList<Player> players7 = new ArrayList<>();
		players7.add(new Player("Maria","east","mariaeast@gmail.com","1234567890"));
		players7.add(new Player("Giorgia","Mantovani","gioamant@gmail.com","1243658790"));
		tr.registerNewTeam("team7","23/Dec/2020",players7);
		
		ArrayList<Player> players8 = new ArrayList<>();
		players8.add(new Player("Francesco","east","francescoeast@gmail.com","1234567890"));
		players8.add(new Player("Mario","Mantovani","mariomant@gmail.com","1243658790"));
		tr.registerNewTeam("team8","23/Dec/2020",players8);
		
		ArrayList<Player> players9 = new ArrayList<>();
		players9.add(new Player("Pietro","east","pietroeast@gmail.com","1234567890"));
		players9.add(new Player("Valeria","Mantovani","valeriamant@gmail.com","1243658790"));
		tr.registerNewTeam("team9","23/Dec/2020",players9);
		
		ArrayList<Player> players10 = new ArrayList<>();
		players10.add(new Player("Gerardina","east","gerardinaeast@gmail.com","1234567890"));
		players10.add(new Player("Paolo","Mantovani","paolomant@gmail.com","1243658790"));
		tr.registerNewTeam("team10","23/Dec/2020",players10);
	}
}
