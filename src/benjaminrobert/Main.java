package benjaminrobert;

import java.awt.EventQueue;

import benjaminrobert.controller.Controller;
import benjaminrobert.model.Squad;
import benjaminrobert.view.Fantasy;

/**
 * 
 * @author Benjamin and Robert
 *
 *
 * Main class to start the fantasy game
 */
public class Main {

	/**
	 * 
	 * @param args
	 * 
	 * Entry point for the program
	 */
	public static void main(String[] args){
		
		// create thread to start the GUI
		EventQueue.invokeLater(new Runnable(){
			
			/**
			 * 
			 */
			@Override
			public void run() {
				Fantasy fantasy = new Fantasy();
				Squad squad = new Squad();
				Controller controller = new Controller(squad, fantasy);
				fantasy.initObserver(controller);
				fantasy.setVisible(true);
			}
		});
		
		
	}
		
		
}
