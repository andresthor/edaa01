package phonebook;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AddMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public AddMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Add");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog("Enter name to add to phone book:");
		String number = JOptionPane.showInputDialog("Enter number to add to phone book:");
		
		if (phoneBook.put(name, number)) {
			gui.setMessage("Phone number added.\n" + name + " : " + number);
		} else {
			gui.setMessage("Failed to add number.\nNumber already exists for that person.");
		}
	}
}
