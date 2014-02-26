package phonebook;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class QuitButton extends JButton implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public QuitButton(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Quit");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		int saveFileorNot = JOptionPane.showConfirmDialog(null,
				"Do you want to save the phone directory to file?", "Choose",
				JOptionPane.YES_NO_OPTION);
		if (saveFileorNot == JOptionPane.YES_OPTION) {
			saveFile();
		}
		System.exit(1);
	 }
	 
	 private void saveFile() {
		 JFileChooser chooser = new JFileChooser();
		 FileNameExtensionFilter filter = new FileNameExtensionFilter(
				 "*.edaa01", "edaa01");
		 chooser.setFileFilter(filter);
		 int returnVal = chooser.showSaveDialog(null);
		 if(returnVal == JFileChooser.APPROVE_OPTION) {		 
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(chooser.getSelectedFile().getName()));
				out.writeObject(phoneBook);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		 }
	 }
}
