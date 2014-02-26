package phonebook;
import javax.swing.*;

import java.awt.event.*;
import java.util.List;

@SuppressWarnings("serial")
public class FindNamesMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public FindNamesMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Find name(s)");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		String number = JOptionPane.showInputDialog("Find names with this phone number:");
		List<String> names = phoneBook.findNames(number);
		
		if (!names.isEmpty()) {
			gui.setMessage("People with the phone number " + number + ":\n");
			for (String n : names) {
				gui.appendMessage(n + "\n");
			}
		} else {
			gui.setMessage("Nobody with that phone number (" + number + ") in the phone book.");
		}
	 }
}