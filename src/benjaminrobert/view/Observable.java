package benjaminrobert.view;


import javax.swing.JComponent;
import javax.swing.JTextField;

import benjaminrobert.controller.Observer;

public interface Observable {

	public void notifyObservers(JComponent button, JTextField textField);

	public void addObserver(Observer observer);
}
