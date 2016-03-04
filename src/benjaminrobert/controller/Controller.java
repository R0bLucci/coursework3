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
		fantasy.addObserver(this);
		
		labels = new ArrayList<JLabel>();
		textFields = new ArrayList<JTextField>();
		
		cmbActionsListener = new CmbActionsListener();
		lblActionsListener = new LblActionsListener();
		txtActionsListener = new TxtActionsListener();
		getViewSelection();
	}
	
	private String capitalizeName(String name){
		return name.substring(0,1).toUpperCase() + name.substring(1);
	}
	
	private JLabel searchLabel(int panelName){
		for(JLabel label: labels){
			if(label.getName().equals(Integer.valueOf(panelName))){
				return label;
			}
		}
		return null;
	}
	
	
	private int parseFormation(String formation){
		formation = formation.replace("-", "");
		return Integer.parseInt(formation);
	}
	
	private void getViewSelection(){
		fantasy.getFormationSelection().addActionListener(cmbActionsListener);
	}
	
	
	private void setActionListenersToViewComponents(Observable subject, JComponent component, JTextField textField){
		
		if(component instanceof JLabel){
			JLabel label = ((JLabel)component);
			label.addMouseListener(lblActionsListener);
			labels.add(label);
		}
		textField.addActionListener(txtActionsListener);
		textField.getDocument().addDocumentListener(txtActionsListener);
		textFields.add(textField);
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
				fantasy.addBenchPlayer(benchGoalKeeper.getPlayerID(), benchGoalKeeper.getPlayerName(), "./src/Minor Piece of Coursework 3 Resources/squad/clyne.jpg");
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

	private void setLabel(JLabel label, String name){
		label.setText("");
		label.setIcon(new ImageIcon("./src/Minor Piece of Coursework 3 Resources/squad/"+ name + ".jpg"));
	}
	
	private String setPlayerNameOnTxtField(String path){
		for(String name: Player.getImagesName()){
			if(path.contains(name)){
				return name;
			}
		}
		return "";
	}
	
	private boolean checkDuplicate(Player player){
		for(Player p: squad.getSquad()){
			if(player.getImagePath().equals(p.getImagePath())){
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
				}
			}
		}

	}
		
		
		
	class LblActionsListener implements ActionListener, MouseListener{
		
		JFileChooser fileChooser;
		
		public LblActionsListener(){
			fileChooser = new JFileChooser();
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
				
				JLabel lbl = (JLabel) e.getSource();
				
				lbl.setText("");
				lbl.setIcon(new ImageIcon(file.getAbsolutePath()));
				
				Player player = squad.searchPlayerByID(Integer.parseInt(lbl.getName()));
				player.setPath(file.getAbsolutePath());
				player.setName(capitalizeName(setPlayerNameOnTxtField(file.getAbsolutePath())));
				
				if(checkDuplicate(player)){
					player.setPath("None");
					lbl.setIcon(null);
					lbl.setText("+");
					player.resetName();
					JOptionPane.showMessageDialog(fantasy, "Player already added.");
				}
				
				//debug
				/*for(Player player1: squad.getSquad()){
					System.out.println(player1.getImagePath() + " "+player1.getPlayerID());
				}*/
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
						JLabel lbl = getMatchingLabel();
						File file = new File("src/Minor Piece of Coursework 3 Resources/squad/"+ name + ".jpg");

						setLabel(lbl, name);
						
						player.setName(capitalizeName(name));
						player.setPath(file.getAbsolutePath());
						
						if(checkDuplicate(player)){
							player.setPath("None");
							lbl.setIcon(null);
							lbl.setText("+");
							player.resetName();
						}
						
						return;
						
					}else{
						JLabel lbl = getMatchingLabel();
						lbl.setText("+");
						lbl.setIcon(null);
						player.setPath("None");
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
