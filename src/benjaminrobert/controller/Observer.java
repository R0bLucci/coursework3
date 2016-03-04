package benjaminrobert.controller;

import javax.swing.JButton;
import javax.swing.JTextField;

import benjaminrobert.view.Observable;

/**
 * Interface - for the observer.
 * @author benjaminrobert
 * This method is called whenever the observed object is changed. 
 * @param subject This is the first paramter to observer interface
 * @param textField This is the second paramter to observer interface
 */
public interface Observer {

	public void update(Observable subject, JButton button, JTextField textField);
}
