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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import benjaminrobert.controller.Observer;
/**
 * 
 * @author Benjamin and Robert
 *
 * Fantasy class extends JFrame to reproduce the pitch for the fantasy GUI
 */

public class Fantasy extends JFrame implements Observable{
	
	JComboBox<String> cmbFormations; // Selection Box to for the formation
	JPanel mainPanel; // Main panel for the frame
	JPanel formationSelectionPanel; // Panel for the combo box 
	JPanel pitchPanel; // The pith panel
	
	JPanel goalKeeperPanel; // Panel for the goal keeper 
	JPanel defendersPanel; // Panel for the defenders
	JPanel midfieldersPanel; // Panel for the mid fielder
	JPanel strikersPanel; // Pame; 
	
	JPanel footerBenchPanel; // bench panel 
	List<Observer> observers;
	
	public Fantasy(){
		// Call super class to set title of the frame
		super("Fantasy Football");
		// Initialise the components 
		init();
	}
	
	// Get 
	public void initObserver(Observer observer){
		addObserver(observer);
		notifyObservers(cmbFormations, null);
	}
	
	
	// Create temporary place holder for player panel
	private JPanel playerPlaceholderPanel(int id, String playerName, String path){
		// Main panel 
		
		JPanel playerPanel = new JPanel(new FlowLayout());
		playerPanel.setName(String.valueOf(id));
		
		JPanel tmpPlaceHolderPanel = new JPanel(new BorderLayout());
		tmpPlaceHolderPanel.setBounds(10, 10, 10, 10);
		
		JLabel lblImgPlaceholder = new JLabel("+");
		lblImgPlaceholder.setName(String.valueOf(id));
		lblImgPlaceholder.setFont(new Font("Verdana", Font.BOLD, 30));
		lblImgPlaceholder.setHorizontalAlignment(JLabel.CENTER);
        lblImgPlaceholder.setOpaque(false);
        
        // If the path is not None set the icon image with the image path path 
        if(!path.equals("None")){
        	lblImgPlaceholder.setText("");
        	lblImgPlaceholder.setIcon(new ImageIcon(path));
        }
		
		
		JTextField txtPlayerRole = new JTextField(playerName, 10);
		txtPlayerRole.setName(String.valueOf(id));
		txtPlayerRole.setBounds(10, 10, 10, 10);
		
		// Set text area properties
		txtPlayerRole.setBackground(null);
		txtPlayerRole.setHorizontalAlignment(JTextField.CENTER);
		txtPlayerRole.setFont(new Font("Verdana", Font.BOLD, 12));
		txtPlayerRole.setOpaque(false);
		
		
		Border border = BorderFactory.createEmptyBorder();
		playerPanel.setBorder(border);
		
		
		tmpPlaceHolderPanel.add(lblImgPlaceholder, BorderLayout.CENTER);
		tmpPlaceHolderPanel.add(txtPlayerRole, BorderLayout.SOUTH);		
		
		playerPanel.add(tmpPlaceHolderPanel);
		
		playerPanel.setName(String.valueOf(id));
		
		notifyObservers(lblImgPlaceholder, txtPlayerRole);
		
		playerPanel.setOpaque(false);
		return playerPanel;
	}
	
	
	/*------------------- Utility method ---------------------------*/
	
	
	
	/**
	 * 
	 * @param id
	 * @param playerName
	 * @param path
	 * 
	 * Add goal keeper to the pitch
	 */
	public void addGoalkeeper(int id, String playerName, String path){
		goalKeeperPanel.add(playerPlaceholderPanel(id, playerName, path));
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @param playerName
	 * @param path
	 * 
	 *  Add defender to the pitch
	 */
	public void addDefender(int id, String playerName, String path){
		defendersPanel.add(playerPlaceholderPanel(id, playerName, path));
	}
	
	/**
	 * 
	 * @param id
	 * @param playerName
	 * @param path
	 * 
	 * Add mid fielder to the pitch
	 */
	public void addMidfielder(int id, String playerName, String path){
		midfieldersPanel.add(playerPlaceholderPanel(id, playerName, path));
	}
	
	/**
	 * 
	 * @param id
	 * @param playerName
	 * @param path
	 * 
	 * Add mid fielder to the pitch
	 */
	public void addStriker(int id, String playerName, String path){
		strikersPanel.add(playerPlaceholderPanel(id, playerName, path));
	}
	
	/**
	 * 
	 * @param id
	 * @param playerName
	 * @param path
	 * 
	 * Add bench to the pitch
	 */
	public void addBenchPlayer(int id, String playerName, String path){
		footerBenchPanel.add(playerPlaceholderPanel(id, playerName, path));
	}
	
	// Initialise the goal keeper panel 
	private void setGoalkeeperPanel(){
		goalKeeperPanel = new JPanel();
		goalKeeperPanel.setLayout(new BoxLayout(goalKeeperPanel, BoxLayout.X_AXIS));
		goalKeeperPanel.setBackground(new Color(34,139,34));
	}
	
	// Initialise the defender panel 
	private void setDefendersPanel(){
		defendersPanel = new JPanel();
		defendersPanel.setLayout(new BoxLayout(defendersPanel, BoxLayout.X_AXIS));
		defendersPanel.setBackground(new Color(50,205,50));
	}
	
	// Initialise the mid fielder panel
	private void setMidfieldersPanel(){
		midfieldersPanel = new JPanel();
		midfieldersPanel.setLayout(new BoxLayout(midfieldersPanel, BoxLayout.X_AXIS));
		midfieldersPanel.setBackground(new Color(34,139,34));
	}
	
	// Initialise the striker panel
	private void setStrikersPanel(){
		strikersPanel = new JPanel();
		strikersPanel.setLayout(new BoxLayout(strikersPanel, BoxLayout.X_AXIS));
		strikersPanel.setBackground(new Color(50,205,50));
	}
	
	private void setBenchPanel(){
		footerBenchPanel = new JPanel();
		footerBenchPanel.setLayout(new BoxLayout(footerBenchPanel, BoxLayout.X_AXIS));
		footerBenchPanel.setBackground(new Color(190,190,190));
	}
	
	
	// Set component for the fantasy frame
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
		setPreferredSize(new Dimension(800, 500));
		pack();
	}
	
	// Clear pitch 
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
		
		strikersPanel.revalidate();
		strikersPanel.repaint();
		
		footerBenchPanel.revalidate();
		footerBenchPanel.repaint();
	}
	
	// Set the panel for the combo box 
	private void setFormationSelectionPanel(){
		formationSelectionPanel = new JPanel();
		formationSelectionPanel.setLayout(new GridLayout(0,1));
		cmbFormations = new JComboBox<String>(defineFormations());
		Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		formationSelectionPanel.setBorder(padding);
		
		formationSelectionPanel.add(cmbFormations);
		mainPanel.add(formationSelectionPanel, BorderLayout.NORTH);
	}

	// Set the options for the combo box
	private String[] defineFormations(){
		String[] formations = {"Select formation", "4-4-2", "4-3-3", "3-5-2", "5-3-2", "3-4-3", "4-5-1"};
		return formations;
	}

	/*--------------- Implements observable ---------------------*/
	public void notifyObservers(JComponent component, JTextField textField) {
		if(observers != null){
			for(Observer observer: observers){
				observer.update(this, component, textField);
			}
		}
	}

	// Add observer to 
	public void addObserver(Observer observer) {
		if(observers != null){
			observers.add(observer);
		}
	}
	
}
