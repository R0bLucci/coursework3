package benjaminrobert.view;

import javax.swing.JButton;
import javax.swing.JTextField;

import benjaminrobert.controller.Observer;

public interface Observable {

	public void notifyObservers(JButton button, JTextField textField);

	public void addObserver(Observer observer);
}
