package phonebook;
import javax.swing.*;

import java.awt.event.*;

public class RemoveMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public RemoveMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Remove");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog("Enter name to remove from phone book:");
		
		if (phoneBook.remove(name)) {
			gui.setMessage(name + " successfully removed from the phone book");
		} else {
			gui.setMessage(name + " was not found in the phone book");
		}
	 }
}
