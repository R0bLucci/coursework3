package benjaminrobert.view;

import javax.swing.JButton;
import javax.swing.JTextField;

import benjaminrobert.controller.Observer;


/**
 * Interface - for the observable.
 * @author benjaminrobert
 * This method is allows an object to be observed.. 
 * @param subject This is the first paramter to Observable interface
 * @param textField This is the second paramter to Observable interface
 */
public interface Observable {

	public void notifyObservers(JButton button, JTextField textField);

	public void addObserver(Observer observer);
}
