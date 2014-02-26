package phonebook;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	public static void main(String[] args) {	
		PhoneBook pb = new PhoneBook();
		PhoneBookGUI gui;

		// Dialog som fr�gar om vi vill �ppna en sparad telefonkatalog
		int loadFileorNot = JOptionPane.showConfirmDialog(null,
				"Do you want to load a phone directory from file?", "Choose",
				JOptionPane.YES_NO_OPTION);
		
		// Om vi svarade ja s� �ppnas dialogf�nster d�r vi kan bl�ddra
		// efter fil
		if (loadFileorNot == JOptionPane.YES_OPTION) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"*.edaa01", "edaa01");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					ObjectInputStream in = new ObjectInputStream(
							new FileInputStream(chooser.getSelectedFile()
									.getName()));
					pb = (PhoneBook) in.readObject();			 
				} catch (Exception FileNotFoundException) {
					pb = new PhoneBook();
				}
			} else {
				pb = new PhoneBook();
			}
		}
		
		// Skapar grafiskt anv�ndargr�nssnitt
		gui = new PhoneBookGUI(pb);
		
		// Om vi laddade katalog fr�n fil s� meddelas det h�r
		if (!pb.isEmpty())
			gui.setMessage("Phone book successfully loaded!");
		
	}
	
}
