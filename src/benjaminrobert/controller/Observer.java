package benjaminrobert.controller;

import javax.swing.JButton;
import javax.swing.JTextField;

import benjaminrobert.view.Observable;

public interface Observer {

	public void update(Observable subject, JButton button, JTextField textField);
}
