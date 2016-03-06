package benjaminrobert.view;


import javax.swing.JComponent;
import javax.swing.JTextField;

import benjaminrobert.controller.Observer;
/**
 * 
 * @author Benjamin and Robert
 * 
 * Overrides the Java Observable 
 *
 */
public interface Observable {

	/**
	 * 
	 * @param button
	 * @param textField
	 * 
	 * Method used to call the update method in the Observer class
	 */
	public void notifyObservers(JComponent button, JTextField textField);

	/**
	 * 
	 * @param observer
	 * 
	 * Add Observer 
	 */
	public void addObserver(Observer observer);
}
