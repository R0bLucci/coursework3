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
				Fantasy f = new Fantasy();
				f.setVisible(true);
				Squad s = new Squad();
				Controller c = new Controller(s, f);
			}
		});
		
		
	}
		
		
}
