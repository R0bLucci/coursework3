package benjaminrobert;

import java.awt.EventQueue;

import benjaminrobert.controller.Controller;
import benjaminrobert.model.Squad;
import benjaminrobert.view.Fantasy;

public class Main {

	public static void main(String[] args){
		
		
		EventQueue.invokeLater(new Runnable(){

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
