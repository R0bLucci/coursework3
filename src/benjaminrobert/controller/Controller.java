package benjaminrobert.controller;
import benjaminrobert.view.Fantasy;
import benjaminrobert.view.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import benjaminrobert.model.Defender;
import benjaminrobert.model.Goalkeeper;
import benjaminrobert.model.Midfielder;
import benjaminrobert.model.Player;
import benjaminrobert.model.Squad;
import benjaminrobert.model.Striker;


public class Controller implements Observer {

	private Squad squad;
	private Fantasy fantasy;
	private ActionsListener actionsListener;
	
	public Controller(Squad squad, Fantasy fantasy){
		this.squad = squad;
		this.fantasy = fantasy;
		squad.initSquad();
		fantasy.addObserver(this);
		actionsListener = new ActionsListener();
		getViewSelection();
	}
	
	private int parseFormation(String formation){
		formation = formation.replace("-", "");
		return Integer.parseInt(formation);
	}
	
	private void getViewSelection(){
		fantasy.getFormationSelection().addActionListener(actionsListener);
	}
	
	
	@Override
	public void update(Observable subject, JButton button, JTextField textField) {
		button.addActionListener(actionsListener);
		
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

	class ActionsListener implements ActionListener{
		
		public ActionsListener(){
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(fantasy.getFormationSelection().equals(e.getSource())){
				fantasy.clearPitch();
				JComboBox<String> cmb = (JComboBox<String>) e.getSource();
				String formation = (String) cmb.getSelectedItem();
				if(!formation.equalsIgnoreCase("select formation")){
					placePlayersOnPitch(parseFormation(formation));
				}
			}else{
				System.out.println(e.getSource());
			}
			
			
		}
	}

}
