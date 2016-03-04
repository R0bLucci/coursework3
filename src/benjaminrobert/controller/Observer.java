package benjaminrobert.controller;

import javax.swing.JComponent;
import javax.swing.JTextField;

import benjaminrobert.view.Observable;

public interface Observer {

	public void update(Observable subject, JComponent component, JTextField textField);
}
