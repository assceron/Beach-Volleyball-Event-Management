package registration;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import databases.TeamsDB;

public class TeamRegistrationFrame extends JFrame implements ActionListener{
	
	private TeamRegistration teamreg = new TeamRegistration();
    // Components of the Form 
    private Container c; 
    private JLabel title; 
    private JLabel teamName; 
    private JTextField tname; 
    private JButton checkName; 
    
    private JLabel dog; 
    private JComboBox date; 
    private JComboBox month; 
    private JComboBox year; 
    
    private JLabel captainInfo;
    private JLabel captainName;
    private JTextField tcaptainName;
    private JLabel captainSurname;
    private JTextField tcaptainSurname;
    private JLabel captainEmail;
    private JTextField tcaptainEmail;
    private JLabel captainPhone;
    private JTextField tcaptainPhone;
    
    private JLabel playerInfo;
    private JLabel playerName;
    private JTextField tplayerName;
    private JLabel playerSurname;
    private JTextField tplayerSurname;
    private JLabel playerEmail;
    private JTextField tplayerEmail;
    private JLabel playerPhone;
    private JTextField tplayerPhone;
    
    private JCheckBox term; 
    private JButton sub; 
    private JTextArea tout; 
    private JLabel res; 
    private JTextArea resadd; 
    
    private String dates[] 
            = { "01", "02", "03", "04", "05", 
                "06", "07", "08", "09", "10", 
                "11", "12", "13", "14", "15", 
                "16", "17", "18", "19", "20", 
                "21", "22", "23", "24", "25", 
                "26", "27", "28", "29", "30", 
                "31" }; 
    
    private String months[] 
            = { "Jan", "Dec"}; 
   
    private String years[] 
            = { "2020","2021"}; 
        
    
 // constructor, to initialize the components 
    // with default values. 
    public TeamRegistrationFrame() 
    { 
        setTitle("Team Registration Form"); 
        setBounds(300, 90, 900, 600); 
        //setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false);
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        title = new JLabel("Team Registration Form"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(400, 50); 
        title.setLocation(300, 30); 
        c.add(title); 
        
        dog = new JLabel("Event Date"); 
        dog.setFont(new Font("Arial", Font.PLAIN, 20)); 
        dog.setSize(100, 20); 
        dog.setLocation(70, 100); 
        c.add(dog); 
  
        date = new JComboBox(dates); 
        date.setFont(new Font("Arial", Font.PLAIN, 15)); 
        date.setSize(50, 20); 
        date.setLocation(200, 100); 
        c.add(date); 
  
        month = new JComboBox(months); 
        month.setFont(new Font("Arial", Font.PLAIN, 15)); 
        month.setSize(60, 20); 
        month.setLocation(250, 100); 
        c.add(month); 
  
        year = new JComboBox(years); 
        year.setFont(new Font("Arial", Font.PLAIN, 15)); 
        year.setSize(60, 20); 
        year.setLocation(320, 100); 
        c.add(year);
  
        teamName = new JLabel("Team Name"); 
        teamName.setFont(new Font("Arial", Font.PLAIN, 20)); 
        teamName.setSize(150, 20); 
        teamName.setLocation(70, 150); 
        c.add(teamName); 
  
        tname = new JTextField(); 
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 20); 
        tname.setLocation(200, 150); 
        c.add(tname); 
        
        checkName = new JButton("Check");
        checkName.setFont(new Font("Arial", Font.PLAIN, 15));
        checkName.setSize(90,20);
        checkName.setLocation(400, 150);
        checkName.addActionListener(this); 
        c.add(checkName);
        
              
        term = new JCheckBox("Accept Terms And Conditions."); 
        term.setFont(new Font("Arial", Font.PLAIN, 15)); 
        term.setSize(250, 20); 
        term.setLocation(150, 450); 
        c.add(term); 
  
        sub = new JButton("Submit"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 20); 
        sub.setLocation(150, 480); 
        sub.addActionListener(this); 
        c.add(sub); 
  
        tout = new JTextArea(); 
        tout.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tout.setSize(300, 400); 
        tout.setLocation(520, 100); 
        tout.setLineWrap(true); 
        tout.setEditable(false); 
        c.add(tout); 
  
        res = new JLabel(""); 
        res.setFont(new Font("Arial", Font.PLAIN, 20)); 
        res.setSize(500, 25); 
        res.setLocation(100, 520); 
        c.add(res); 
  
        resadd = new JTextArea(); 
        resadd.setFont(new Font("Arial", Font.PLAIN, 15)); 
        resadd.setSize(200, 75); 
        resadd.setLocation(520, 175); 
        resadd.setLineWrap(true); 
        c.add(resadd); 
  
        setVisible(true); 
    } 
    
    // method actionPerformed() 
    // to get the action performed 
    // by the user and act accordingly 
    
    public void actionPerformed(ActionEvent e){ 
        if (e.getSource() == sub) {
        	submitButtonAction();
        }
        
        else if (e.getSource() == checkName) {
        	checkButtonAction();
        }
        
    }
    
  
    private void submitButtonAction() {
    	if (term.isSelected()) {
        	ArrayList<Player> players = this.retrievePlayersInformation();
        
        	String teamName = tname.getText();
        	String gameNight = (String)date.getSelectedItem() 
        						+ "/" + (String)month.getSelectedItem() 
        						+ "/" + (String)year.getSelectedItem();
        	
        	String playersValidationString = validatePlayers(players);
        	
        	if(!(playersValidationString.isEmpty())) {
        		tout.setText("\nRegistration failed.. Modify your information\n" + playersValidationString);
                tout.setEditable(false); 
        		return;
        	}
        	
        	int teamCreated = teamreg.registerNewTeam(teamName,gameNight,players);
        	if(teamCreated>=0) {
        		String data0 = "TEAM REGISTERED SUCCESSFULLY\n";
            	String data 
                    = "Team name : "
                      + tname.getText() + "\n";

                String data2 
                    = "Date of Game : "
                      + (String)date.getSelectedItem() 
                      + "/" + (String)month.getSelectedItem() 
                      + "/" + (String)year.getSelectedItem() 
                      + "\n\n"; 
                
                String data3 
                	= "Players:\n";
                
                for(Player p:players) 
                	data3 += p.toString() + "\n\n";
                
                tout.setText(data0 + data + data2 + data3); 
                tout.setEditable(false); 
                res.setText("Registration Successful.."); 
        	}
        	else {
        		tout.setText("\nRegistration failed. Team already existent");
        	}
        } 
        else { 
            tout.setText(""); 
            resadd.setText(""); 
            res.setText("Please accept the"
                        + " terms & conditions.."); 
        } 	
    }
    
    private void checkButtonAction() {
    	String text="";
    	
    	if(teamreg.teamExistent(tname.getText())) 
    		text = "A team with the same name already existent. Enter a new name.";
    	
    	else {
    		text = "Valid name. Continue with the form.";
    		playersForm();
    	}
    	
    	tout.setText(text);
        tout.setEditable(false); 
    }

        
    private ArrayList<Player> retrievePlayersInformation() {
    	 ArrayList<Player> players = new ArrayList<>();
  
    	 
    	 Captain c = new Captain(tcaptainName.getText(),
    			 				 tcaptainSurname.getText(),
    			 				 tcaptainEmail.getText(),
    			 				 tcaptainPhone.getText());

    	 Player  p = new Player(tplayerName.getText(),
    			 				tplayerSurname.getText(),
    			 				tplayerEmail.getText(), 
    			 				tplayerPhone.getText());

    	players.add(c);
        players.add(p);
    	
    	return players;
    	 
     }
    
    
    private String validatePlayers(ArrayList<Player> players) {
    	ArrayList<String> playersInfo = new ArrayList<String>();
    	for(Player p : players)
    		playersInfo.add(p.validatePlayer());
    		
    	String dataCheck="";
    	int count = 1;
    	for(String playerInfo : playersInfo) {
    		if(!(playerInfo.isEmpty()))
    			dataCheck += "Player "+ count + "\n" + playerInfo;
    		count++;
    	}
    	
    	return dataCheck;
    	
    }
    private void playersForm() {
    	 
    	 captainInfo = new JLabel("Captain Info"); 
    	 captainInfo.setFont(new Font("Arial", Font.BOLD, 17)); 
    	 captainInfo.setSize(150, 20); 
    	 captainInfo.setLocation(30, 200); 
         c.add(captainInfo); 
   
    	 captainName = new JLabel("Name"); 
         captainName.setFont(new Font("Arial", Font.PLAIN, 15)); 
         captainName.setSize(150, 20); 
         captainName.setLocation(30, 230); 
         c.add(captainName); 
   
         tcaptainName = new JTextField(); 
         tcaptainName.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tcaptainName.setSize(150, 20); 
         tcaptainName.setLocation(100, 230); 
         c.add(tcaptainName); 
         
         captainSurname = new JLabel("Surname"); 
         captainSurname.setFont(new Font("Arial", Font.PLAIN, 15)); 
         captainSurname.setSize(150, 20); 
         captainSurname.setLocation(280, 230); 
         c.add(captainSurname); 
   
         tcaptainSurname = new JTextField(); 
         tcaptainSurname.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tcaptainSurname.setSize(150, 20); 
         tcaptainSurname.setLocation(350, 230); 
         c.add(tcaptainSurname); 
         
         captainEmail = new JLabel("Email"); 
         captainEmail.setFont(new Font("Arial", Font.PLAIN, 15)); 
         captainEmail.setSize(150, 20); 
         captainEmail.setLocation(30, 280); 
         c.add(captainEmail); 
   
         tcaptainEmail = new JTextField(); 
         tcaptainEmail.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tcaptainEmail.setSize(150, 20); 
         tcaptainEmail.setLocation(100, 280); 
         c.add(tcaptainEmail); 
         
         captainPhone = new JLabel("Phone"); 
         captainPhone.setFont(new Font("Arial", Font.PLAIN, 15)); 
         captainPhone.setSize(150, 20); 
         captainPhone.setLocation(280, 280); 
         c.add(captainPhone); 
   
         tcaptainPhone = new JTextField(); 
         tcaptainPhone.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tcaptainPhone.setSize(150, 20); 
         tcaptainPhone.setLocation(350, 280); 
         c.add(tcaptainPhone); 
         
    	 playerInfo = new JLabel("Second player Info"); 
    	 playerInfo.setFont(new Font("Arial", Font.BOLD, 17)); 
    	 playerInfo.setSize(150, 20); 
    	 playerInfo.setLocation(30, 330); 
         c.add(playerInfo); 
   
    	 playerName = new JLabel("Name"); 
    	 playerName.setFont(new Font("Arial", Font.PLAIN, 15)); 
    	 playerName.setSize(150, 20); 
    	 playerName.setLocation(30, 360); 
         c.add(playerName); 
   
         tplayerName = new JTextField(); 
         tplayerName.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tplayerName.setSize(150, 20); 
         tplayerName.setLocation(100, 360); 
         c.add(tplayerName); 
         
         playerSurname = new JLabel("Surname"); 
         playerSurname.setFont(new Font("Arial", Font.PLAIN, 15)); 
         playerSurname.setSize(150, 20); 
         playerSurname.setLocation(280, 360); 
         c.add(playerSurname); 
   
         tplayerSurname = new JTextField(); 
         tplayerSurname.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tplayerSurname.setSize(150, 20); 
         tplayerSurname.setLocation(350, 360);
         c.add(tplayerSurname); 
         
         playerEmail = new JLabel("Email"); 
         playerEmail.setFont(new Font("Arial", Font.PLAIN, 15)); 
         playerEmail.setSize(150, 20); 
         playerEmail.setLocation(30, 410); 
         c.add(playerEmail); 
   
         tplayerEmail = new JTextField(); 
         tplayerEmail.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tplayerEmail.setSize(150, 20); 
         tplayerEmail.setLocation(100, 410); 
         c.add(tplayerEmail); 
         
         playerPhone = new JLabel("Phone"); 
         playerPhone.setFont(new Font("Arial", Font.PLAIN, 15)); 
         playerPhone.setSize(150, 20); 
         playerPhone.setLocation(280, 410); 
         c.add(playerPhone); 
   
         tplayerPhone = new JTextField(); 
         tplayerPhone.setFont(new Font("Arial", Font.PLAIN, 15)); 
         tplayerPhone.setSize(150, 20); 
         tplayerPhone.setLocation(350, 410); 
         c.add(tplayerPhone);
         
         c.validate();
         c.repaint();
         //setVisible(true); 
     }
}
