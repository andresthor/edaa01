package phonebook;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ShowAllMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public ShowAllMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Show all");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (phoneBook.isEmpty()) {
			gui.setMessage("The phone book is empty.");
		} else {
			gui.setMessage("Names contained in this phone book:\n");
			for (String n : phoneBook.names()) {
				gui.appendMessage(n + "\n");
			}
		}
		
	}
}