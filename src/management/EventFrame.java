package management;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class EventFrame extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel dog; 
    private JComboBox date; 
    private JComboBox month; 
    private JComboBox year; 
    private Container c; 
    private JLabel title;
    private JButton choose; 
    private JTextArea teams; 
    private JTextArea matchesR1;
    private JTextArea matchesR2;
    
    private String dates[] 
            = { "01", "02", "03", "04", "05", 
                "06", "07", "08", "09", "10", 
                "11", "12", "13", "14", "15", 
                "16", "17", "18", "19", "20", 
                "21", "22", "23", "24", "25", 
                "26", "27", "28", "29", "30", 
                "31" }; 
    
    private String months[] 
            = { "Jan","Dec" }; 
   
    private String years[] 
            = { "2020","2021"}; 
    
    public EventFrame() {
    	
    	setTitle("Event Details"); 
        setBounds(300, 90, 900, 600); 
        //setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false);
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        title = new JLabel("Event Details"); 
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
        
        choose = new JButton("Show details"); 
        choose.setFont(new Font("Arial", Font.PLAIN, 15)); 
        choose.setSize(150, 20); 
        choose.setLocation(420, 100); 
        choose.addActionListener(this); 
        c.add(choose); 
        
        
        teams = new JTextArea(); 
        teams.setFont(new Font("Arial", Font.PLAIN, 15)); 
        teams.setSize(200, 400); 
        teams.setLocation(70, 150); 
        teams.setLineWrap(true); 
        teams.setEditable(false); 
        c.add(teams); 
        
        matchesR1 = new JTextArea(); 
        matchesR1.setFont(new Font("Arial", Font.PLAIN, 15)); 
        matchesR1.setSize(200, 400); 
        matchesR1.setLocation(300, 150); 
        matchesR1.setLineWrap(true); 
        matchesR1.setEditable(false); 
        c.add(matchesR1); 
        
        matchesR2 = new JTextArea(); 
        matchesR2.setFont(new Font("Arial", Font.PLAIN, 15)); 
        matchesR2.setSize(200, 400); 
        matchesR2.setLocation(530, 150); 
        matchesR2.setLineWrap(true); 
        matchesR2.setEditable(false); 
        c.add(matchesR2); 
        
        setVisible(true); 

        
    }
    
    public void actionPerformed(ActionEvent e){ 
    	if(e.getSource() == choose) {
    		EventManagement em = new EventManagement();
    		
    		String dog = (String)date.getSelectedItem() 
                    	  + "/" + (String)month.getSelectedItem() 
                    	  + "/" + (String)year.getSelectedItem();
    		
    		MessageEvent me = em.showEvent(dog);
    		if(me.getEvent() == null) {
    			teams.setText(me.getMessage());
    			return;
    		}
    		System.out.println(me.getEvent());
    		teams.setText(me.getEvent().getTeams());
    		matchesR1.setText(me.getEvent().getMatchesR1().toString());
    		matchesR2.setText(me.getEvent().getMatchesR2().toString());

    		teams.setEditable(false);
    		
    	}
    }
}
