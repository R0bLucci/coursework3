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


public class Controller implements Observer {

	private Squad squad;
	private Fantasy fantasy;
	private CmbActionsListener cmbActionsListener;
	private LblActionsListener lblActionsListener;
	private TxtActionsListener txtActionsListener;
	
	private List<JLabel> labels;
	private List<JTextField> textFields;
	
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
	
	private String capitalizeName(String name){
		if(name.length() > 1){
			return (name.substring(0,1).toUpperCase() + name.substring(1)).trim();
		}
		return name.trim();
	}
	
	private JTextField searchTextField(int playerID){
		for(JTextField textField: textFields){
			if(textField.getName().equals(String.valueOf(playerID))){
				return textField;
			}
		}
		return null;
	}
	
	private int parseFormation(String formation){
		formation = formation.replace("-", "");
		return Integer.parseInt(formation);
	}
	
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

	private void setImageOnLabel(JLabel label, String imagePath){
		label.setText("");
		label.setIcon(new ImageIcon(imagePath));
	}
	
	private void resetImageOnLabel(JLabel label){
		label.setText("+");
		label.setIcon(null);
	}
	
	private void setNameAndPathImageOfPlayer(Player player, String name, String path){
		player.setName(capitalizeName(name));
		player.setPath(path);
	}
	
	
	private void resetNameAndPathImageOfPlayer(Player player){
		player.resetName();
		player.resetImagePath();
	}
	
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

	
	class CmbActionsListener implements ActionListener{
		
		public CmbActionsListener(){
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			labels.clear();
			textFields.clear();
			
			
			if(fantasy.getFormationSelection().equals(e.getSource())){
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

	}
		
		
		
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
	
	
	class TxtActionsListener implements ActionListener, DocumentListener{
	
		JTextField textField;
		
		
		public TxtActionsListener(){
			textField = null;
		}
		
		private Player getPlayer(){
			for(Player player: squad.getSquad()){
				if(player.getPlayerID() == Integer.parseInt((textField.getName()))){
					return player;
				}
			}
			return null;
		}
		
		private JLabel getMatchingLabel(){
			for(JLabel lbl: labels){
				if(lbl.getName().equals(textField.getName())){
					return lbl;
				}
			}
			return null;
		}
		
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
