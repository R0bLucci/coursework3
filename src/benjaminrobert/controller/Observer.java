package benjaminrobert.controller;

import javax.swing.JComponent;
import javax.swing.JTextField;

import benjaminrobert.view.Observable;

/**
 * 
 * @author Benjamin and Robert
 * 
 *  Override Java observer to pass around a JCompoentna and a JTextField
 */
public interface Observer {

	/**
	 * 
	 * @param subject
	 * @param component
	 * @param textField
	 * 
	 * This methodo is called every time notifyObervers from an Observable object is invoked 
	 */
	public void update(Observable subject, JComponent component, JTextField textField);
}
