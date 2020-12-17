package main;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import management.EventFrame;
import registration.TeamRegistrationFrame;

public class MainPageFrame extends JFrame implements ActionListener{
    private Container c; 
    private JLabel title; 
    private static final long serialVersionUID = 1L;
    private JButton reg;
    private JButton reg2;
    private JButton event;

	public MainPageFrame() {
		
		setTitle("Welcome in Vic Beach"); 
	    setBounds(300, 90, 900, 600); 
	    setDefaultCloseOperation(EXIT_ON_CLOSE); 
	    setResizable(false); 

	    c = getContentPane(); 
	    c.setLayout(null);

	    title = new JLabel("Welcome in Vic Beach"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(400, 50); 
        title.setLocation(250, 30); 
        c.add(title); 
        
        reg = new JButton("Register a new Team"); 
        reg.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reg.setSize(200, 50); 
        reg.setLocation(150, 100); 
        reg.addActionListener(this); 
        c.add(reg);
               
        reg2 = new JButton("Register an Existent Team"); 
        reg2.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reg2.setSize(300, 50); 
        reg2.setLocation(150, 300); 
        reg2.addActionListener(this); 
        c.add(reg2);
        
        event = new JButton("Event Details"); 
        event.setFont(new Font("Arial", Font.PLAIN, 15)); 
        event.setSize(200, 50); 
        event.setLocation(450, 100); 
        event.addActionListener(this); 
        c.add(event);
        
        //Display the window.
               
        setVisible(true);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void actionPerformed(ActionEvent e){
        if (e.getSource() == reg) {
    		TeamRegistrationFrame trf = new TeamRegistrationFrame(); 
        }
        
        if (e.getSource() == reg2) {
        	//TODO
        	//Retrieve data from database about TeamName
        }
        if(e.getSource() == event) {
        	EventFrame ev = new EventFrame();
        }
	}
	
}
