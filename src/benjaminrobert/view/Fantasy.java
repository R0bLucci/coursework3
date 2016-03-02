package benjaminrobert.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import benjaminrobert.controller.Observer;


public class Fantasy extends JFrame implements Observable{
	
	JComboBox<String> cmbFormations; // Selection Box to for the formation
	JPanel mainPanel; // Main panel for the frame
	JPanel formationSelectionPanel; // Panel for the combo box 
	JPanel pitchPanel; // The pith panel
	
	JPanel goalKeeperPanel;
	JPanel defendersPanel;
	JPanel midfieldersPanel;
	JPanel strikersPanel;
	
	JPanel footerBenchPanel; // bench panel 
	List<Observer> observers;
	
	
	
	public Fantasy(){
		// Call super class to set title of the frame
		super("Fantasy Football");
		
		// Initialize the components 
		init();
	}
	
	// Create temporary place holder for player panel
	private JPanel playerPlaceholderPanel(int id, String playerName, String path){
		// Main panel 
		
		JPanel playerPanel = new JPanel(new FlowLayout());
		playerPanel.setName(String.valueOf(id));
		
		JPanel tmpPlaceHolderPanel = new JPanel(new BorderLayout());
		tmpPlaceHolderPanel.setBounds(10, 10, 10, 10);
		
		
		JButton btnImgPlaceholder = new JButton("+");
		btnImgPlaceholder.setFont(new Font("Verdana", Font.BOLD, 15));
		
		btnImgPlaceholder.addMouseListener(null);
		
		JTextField txtPlayerRole = new JTextField(playerName, 10);
		txtPlayerRole.setBounds(10, 10, 10, 10);
		
		// Set text area properties
		txtPlayerRole.setBackground(null);
		txtPlayerRole.setHorizontalAlignment(JTextField.CENTER);
		txtPlayerRole.setFont(new Font("Verdana", Font.BOLD, 12));
		
		
		Border border = BorderFactory.createEmptyBorder();
		playerPanel.setBorder(border);
		
		
		tmpPlaceHolderPanel.add(btnImgPlaceholder, BorderLayout.CENTER);
		tmpPlaceHolderPanel.add(txtPlayerRole, BorderLayout.SOUTH);		
		
		playerPanel.add(tmpPlaceHolderPanel);
		
		notifyObservers(btnImgPlaceholder, txtPlayerRole);
		
		return playerPanel;
	}
	
	public void addGoalkeeper(int id, String playerName, String path){
		goalKeeperPanel.add(playerPlaceholderPanel(id, playerName, path));
		goalKeeperPanel.repaint();
		goalKeeperPanel.revalidate();
	}
	
	
	public void addDefender(int id, String playerName, String path){
		defendersPanel.add(playerPlaceholderPanel(id, playerName, path));
		defendersPanel.repaint();
		defendersPanel.revalidate();
	}
	
	public void addMidfielder(int id, String playerName, String path){
		midfieldersPanel.add(playerPlaceholderPanel(id, playerName, path));
		midfieldersPanel.repaint();
		midfieldersPanel.revalidate();
	}
	
	public void addStriker(int id, String playerName, String path){
		strikersPanel.add(playerPlaceholderPanel(id, playerName, path));
		strikersPanel.repaint();
		strikersPanel.revalidate();
	}
	
	public void addBenchPlayer(int id, String playerName, String path){
		footerBenchPanel.add(playerPlaceholderPanel(id, playerName, path));
		footerBenchPanel.repaint();
		footerBenchPanel.revalidate();
	}
	
	private void setGoalkeeperPanel(){
		goalKeeperPanel = new JPanel();
		goalKeeperPanel.setLayout(new BoxLayout(goalKeeperPanel, BoxLayout.X_AXIS));
		//goalKeeperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	}
	
	private void setDefendersPanel(){
		defendersPanel = new JPanel();
		defendersPanel.setLayout(new BoxLayout(defendersPanel, BoxLayout.X_AXIS));
		//defendersPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
	}
	
	private void setMidfieldersPanel(){
		midfieldersPanel = new JPanel();
		midfieldersPanel.setLayout(new BoxLayout(midfieldersPanel, BoxLayout.X_AXIS));
		//midfieldersPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
	}
	
	private void setStrikersPanel(){
		strikersPanel = new JPanel();
		strikersPanel.setLayout(new BoxLayout(strikersPanel, BoxLayout.X_AXIS));
		//strikersPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
	}
	
	private void setBenchPanel(){
		footerBenchPanel = new JPanel();
		footerBenchPanel.setLayout(new BoxLayout(footerBenchPanel, BoxLayout.X_AXIS));
		//footerBenchPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
	}
	
	private void init(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		pitchPanel = new JPanel(new GridLayout(4,1));
		
		observers = new ArrayList<Observer>();
		
		setGoalkeeperPanel();
		
		setDefendersPanel();
		
		setMidfieldersPanel();
		
		setStrikersPanel();
		
		pitchPanel.add(goalKeeperPanel);
		pitchPanel.add(defendersPanel);
		pitchPanel.add(midfieldersPanel);
		pitchPanel.add(strikersPanel);
		
		setBenchPanel();
		
		setFormationSelectionPanel();
		
		mainPanel.add(pitchPanel, BorderLayout.CENTER);
		mainPanel.add(footerBenchPanel, BorderLayout.SOUTH);
		
		
		add(mainPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(400, 500));
		pack();
	}
	
	public void clearPitch(){
			
		goalKeeperPanel.removeAll();
		defendersPanel.removeAll();
		midfieldersPanel.removeAll();
		strikersPanel.removeAll();
		footerBenchPanel.removeAll();
		
		goalKeeperPanel.revalidate();
		goalKeeperPanel.repaint();
		midfieldersPanel.revalidate();
		midfieldersPanel.repaint();
		
		midfieldersPanel.revalidate();
		midfieldersPanel.repaint();
		strikersPanel.revalidate();
		strikersPanel.repaint();
		
		footerBenchPanel.revalidate();
		footerBenchPanel.repaint();
	}
	
	private void setFormationSelectionPanel(){
		formationSelectionPanel = new JPanel();
		formationSelectionPanel.setLayout(new GridLayout(0,1));
		cmbFormations = new JComboBox<String>(defineFormations());
		Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		formationSelectionPanel.setBorder(padding);
		
		cmbFormations.addActionListener(null);
		
		formationSelectionPanel.add(cmbFormations);
		mainPanel.add(formationSelectionPanel, BorderLayout.NORTH);
		
	}

	private String[] defineFormations(){
		String[] formations = {"Select formation", "4-4-2", "4-3-3", "3-5-2", "5-3-2", "3-4-3", "4-5-1"};
		return formations;
	}
	
	public JComboBox getFormationSelection(){
		return cmbFormations;
	}

	public void notifyObservers(JButton button, JTextField textField) {
		if(observers != null){
			for(Observer observer: observers){
				observer.update(this, button, textField);
			}
		}
	}

	public void addObserver(Observer observer) {
		if(observers != null){
			observers.add(observer);
		}
	}
	
}
