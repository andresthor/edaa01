package phonebook;
import javax.swing.*;

import java.awt.event.*;
import java.util.List;

@SuppressWarnings("serial")
public class FindNumbersMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public FindNumbersMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Find number(s)");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		String name = JOptionPane
				.showInputDialog("Find phone numbers associated with this name:");
		List<String> numbers = phoneBook.findNumber(name);
		if (!numbers.isEmpty()) {
			gui.setMessage("Phone numbers belonging to " + name + ":\n");
			for (String n : numbers) {
				gui.appendMessage(n + "\n");
			}
		} else {
			gui.setMessage("Nobody with that phone number in the phone book.");
		}
	}
}