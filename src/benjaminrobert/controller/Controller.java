package benjaminrobert.controller;
import benjaminrobert.view.Fantasy;
import benjaminrobert.view.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import benjaminrobert.model.Defender;
import benjaminrobert.model.Goalkeeper;
import benjaminrobert.model.Midfielder;
import benjaminrobert.model.Player;
import benjaminrobert.model.Squad;
import benjaminrobert.model.Striker;

/**
 * 
 * @author Benjamin and Robert
 * 
 * The Controller class sets the event listeners for the view's component  
 *
 */
public class Controller implements Observer {

	private Squad squad; // model reference 
	private Fantasy fantasy; // view reference 
	private CmbActionsListener cmbActionsListener; // Listener for the comboBox view
	private LblActionsListener lblActionsListener; // Listener for the labels view
	private TxtActionsListener txtActionsListener; // Listener for the text Fields
	
	private List<JLabel> labels;  // List of label from the view
	private List<JTextField> textFields; //List of text field from the view
	
	/**
	 * 
	 * @param squad
	 * @param fantasy
	 * 
	 *  Constructor to instantiate  a controller object
	 *  A reference to the view and a reference to the model must be past
	 *  
	 */
	public Controller(Squad squad, Fantasy fantasy){
		this.squad = squad;
		this.fantasy = fantasy;
		squad.initSquad();
		
		labels = new ArrayList<JLabel>();
		textFields = new ArrayList<JTextField>();
		
		cmbActionsListener = new CmbActionsListener();
		lblActionsListener = new LblActionsListener();
		txtActionsListener = new TxtActionsListener();
	}
	
	/*------------------------------------ Utility method  -----------------------------------*/
	
	
	// Set Image to label 
	private void setImageOnLabel(JLabel label, String imagePath){
		label.setText("");
		label.setIcon(new ImageIcon(imagePath));
	}
	
	// Reset label to the standard place holder
	private void resetImageOnLabel(JLabel label){
		label.setText("+");
		label.setIcon(null);
	}
	
	// Set the name and the path for the player
	private void setNameAndPathImageOfPlayer(Player player, String name, String path){
		player.setName(capitalizeName(name));
		player.setPath(path);
	}
	
	// Reset the name to to the role and the image to the default value "None"
	private void resetNameAndPathImageOfPlayer(Player player){
		player.resetName();
		player.resetImagePath();
	}
	
	

	 // Check if there are players with the same image path
	 
	private boolean checkDuplicate(Player player){
		for(Player p: squad.getSquad()){
			
			if((player.getImagePath().equals(p.getImagePath())) && !(player.getImagePath().equals(Player.getDefaultImagePathValue()))){
				if(player.equals(p)){
					continue;
				}
				return true;
			}
		}
		return false;
	}



	// Upper case the first letter
	private String capitalizeName(String name){
		if(name.length() > 1){
			return (name.substring(0,1).toUpperCase() + name.substring(1)).trim();
		}
		return name.trim();
	}
	
	
	// 
	private JTextField searchTextField(int playerID){
		for(JTextField textField: textFields){
			if(textField.getName().equals(String.valueOf(playerID))){
				return textField;
			}
		}
		return null;
	}
	
	// Parse formation 
	private int parseFormation(String formation){
		formation = formation.replace("-", "");
		return Integer.parseInt(formation);
	}
	
	// Assign action listener for each component in the view 
	private void setActionListenersToViewComponents(Observable subject, JComponent component, JTextField textField){
		
		if(component instanceof JLabel){
			JLabel label = ((JLabel)component);
			label.addMouseListener(lblActionsListener);
			labels.add(label);
		}else if(component instanceof JComboBox){
			JComboBox comboBox = ((JComboBox) component);
			comboBox.addActionListener(cmbActionsListener);
		}
		
		if(textField != null){
			textField.addActionListener(txtActionsListener);
			textField.getDocument().addDocumentListener(txtActionsListener);
			textFields.add(textField);
		}
	}
	
	
	/*---------------- Observable implementation ----------------------*/
	@Override
	public void update(Observable subject, JComponent component, JTextField textField) {
		setActionListenersToViewComponents(subject, component, textField);	
	}
	
	
	private void placePlayersOnPitch(int formation){
		squad.setNumberOfDefenders(formation);
		squad.setNumberOfMidfielders(formation);
		squad.setNumberOfStrikers(formation);
		
		Player[] team = squad.getSquad();
		
		placeGoalkeeperOnPitch(team);
		
		placeDefendersOnPitch(team);
		
		placeMidfieldersOnPitch(team);
		
		placeStrikersOnPitch(team);
	}
	
	// Loop through the goal keepers and put the in the pitch
	private void placeGoalkeeperOnPitch(Player[] team){
		int add = 0;
		for(int i = 0; i < team.length; i++){
			if(add >= 1 && team[i] instanceof Goalkeeper){
				Goalkeeper benchGoalKeeper = (Goalkeeper) team[i];
				fantasy.addBenchPlayer(benchGoalKeeper.getPlayerID(), benchGoalKeeper.getPlayerName(),benchGoalKeeper.getImagePath() );
			}else if(team[i] instanceof Goalkeeper){
				add++;
				Goalkeeper goalkeeper = (Goalkeeper) team[i];
				fantasy.addGoalkeeper(goalkeeper.getPlayerID(), goalkeeper.getPlayerName(), goalkeeper.getImagePath());
			}
		}
	}
	
	// Loop through the defenders and put the in the pitch
	private void placeDefendersOnPitch(Player[] team){
		int add = 0;
		for(int i = 0; i < team.length; i++){
			if(add >= squad.getNumberOfDefenders() && team[i] instanceof Defender){
				Defender benchDefender = (Defender) team[i];
				fantasy.addBenchPlayer(benchDefender.getPlayerID(), benchDefender.getPlayerName(), benchDefender.getImagePath());
			}else if(team[i] instanceof Defender){
				add++;
				Defender defender = (Defender) team[i];
				fantasy.addDefender(defender.getPlayerID(), defender.getPlayerName(), defender.getImagePath());
			}
		}
	}
	
	// Loop through the mid fielders and put the in the pitch
	private void placeMidfieldersOnPitch(Player[] team){
		int add = 0;
		for(int i = 0; i < team.length; i++){
			if(add >= squad.getNumberOfMidfielders() && team[i] instanceof Midfielder){
				Midfielder benchMidfilders = (Midfielder) team[i];
				fantasy.addBenchPlayer(benchMidfilders.getPlayerID(), benchMidfilders.getPlayerName(), benchMidfilders.getImagePath());
			}else if(team[i] instanceof Midfielder){
				add++;
				Midfielder midfielder = (Midfielder) team[i];
				fantasy.addMidfielder(midfielder.getPlayerID(), midfielder.getPlayerName(), midfielder.getImagePath());
			}
		}
	}
	
	// Loop through the strikers and put the in the pitch
	private void placeStrikersOnPitch(Player[] team){
		int add = 0;
		for(int i = 0; i < team.length; i++){
			if(add >= squad.getNumberOfStrikers() && team[i] instanceof Striker){
				Striker benchStriker = (Striker) team[i];
				fantasy.addBenchPlayer(benchStriker.getPlayerID(), benchStriker.getPlayerName(), benchStriker.getImagePath());
			}else if(team[i] instanceof Striker){
				add++;
				Striker striker = (Striker) team[i];
				fantasy.addStriker(striker.getPlayerID(), striker.getPlayerName(), striker.getImagePath());
			}
		}
	}

	
	// Inner class used as a listener for the labels in the player panel 
	class CmbActionsListener implements ActionListener{
		
		public CmbActionsListener(){
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			labels.clear();
			textFields.clear();
	
			fantasy.clearPitch();
			JComboBox<String> cmb = (JComboBox<String>) e.getSource();
			String formation = (String) cmb.getSelectedItem();
			if(!formation.equalsIgnoreCase("select formation")){
				placePlayersOnPitch(parseFormation(formation));
			}else{
				for(Player player: squad.getSquad()){
					resetNameAndPathImageOfPlayer(player);
				}
			}
		}

	}	
		
	// Inner class used as a listener for the labels in the player panel 
	class LblActionsListener implements ActionListener, MouseListener{
		
		JFileChooser fileChooser;
		
		public LblActionsListener(){
			fileChooser = new JFileChooser();
		}
		
		private String removeFileExt(String fileWithExt){
			if(fileWithExt != null){
				return fileWithExt.substring(0, fileWithExt.lastIndexOf("."));
			}
			return fileWithExt;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		}


		
		@Override
		public void mouseClicked(MouseEvent e) {
			fileChooser.setCurrentDirectory(new File("."));
			
			int returnValue = fileChooser.showDialog(fantasy, "Select");
			
			if(returnValue == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				String name = capitalizeName(removeFileExt(file.getName()));
				JLabel lbl = (JLabel) e.getSource();
				
				Player player = squad.searchPlayerByID(Integer.parseInt(lbl.getName()));
				
				resetNameAndPathImageOfPlayer(player);
				JTextField txt = searchTextField(player.getPlayerID());
				txt.setText(player.getPlayerName());
				
				setImageOnLabel(lbl, file.getAbsoluteFile().toString());
				setNameAndPathImageOfPlayer(player,name,file.getAbsolutePath().toString());
				
				txt.setText(player.getPlayerName());
				
				if(checkDuplicate(player)){				
					resetNameAndPathImageOfPlayer(player);		
					resetImageOnLabel(lbl);
					txt.setText(player.getPlayerName());
					JOptionPane.showMessageDialog(fantasy, "The player " + name + " is already added.");
				}
			}
			
			// reset the file to null
			fileChooser.setSelectedFile(null);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	//Inner listener class for the text fields in the player panel
	class TxtActionsListener implements ActionListener, DocumentListener{
	
		JTextField textField;
		
		// Constructor 
		public TxtActionsListener(){
			textField = null;
		}
		
		// Return the player with the same id
		// of the text field name
		private Player getPlayer(){
			for(Player player: squad.getSquad()){
				if(player.getPlayerID() == Integer.parseInt((textField.getName()))){
					return player;
				}
			}
			return null;
		}
		
		// Return the label with the same name
		// of the player id and text name
		private JLabel getMatchingLabel(){
			for(JLabel lbl: labels){
				if(lbl.getName().equals(textField.getName())){
					return lbl;
				}
			}
			return null;
		}
		
		// Search for the player name  
		private void playerImageSearch(){
			if(textField != null){
				
				String name = textField.getText();
				
				Player player = getPlayer();
		
				for(String playerName: Player.getImagesName()){
					if(name.equalsIgnoreCase(playerName)){
						JLabel currentLabel = getMatchingLabel();
						File file = new File("src/Minor Piece of Coursework 3 Resources/squad/" + name + ".jpg");
						
						setNameAndPathImageOfPlayer(player, name, file.getAbsolutePath().toString());
						
						setImageOnLabel(currentLabel, file.getAbsolutePath());
						
						if(checkDuplicate(player)){	
							
							JLabel labelToReset = getMatchingLabel();
							
							resetImageOnLabel(labelToReset);
							
							resetNameAndPathImageOfPlayer(player);
							
							JOptionPane.showMessageDialog(fantasy, "The player " + name + " is already added.");
						}
						
						return;
						
					}else{
						
						JLabel lbl = getMatchingLabel();
						lbl = getMatchingLabel();
									
						resetImageOnLabel(lbl);
						
						resetNameAndPathImageOfPlayer(player);
					}
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			textField = (JTextField) e.getSource();
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			playerImageSearch();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			playerImageSearch();
		}
		
	}
	
}
