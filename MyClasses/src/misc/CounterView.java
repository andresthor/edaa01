package misc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CounterView implements ActionListener{
	private int yesCount;
	private JLabel label;
	
	public CounterView() {
		yesCount = 0;
		
		JFrame frame = new JFrame("Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		
		JButton button = new JButton("Yes!");
		panel.add(button);
		button.addActionListener(this);
		
		label = new JLabel("Number yes: " + yesCount);
		panel.add(label);
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		yesCount++;
		label.setText("Number yes: " + yesCount);
	}
	
	public static void main(String[] args) {
		new CounterView();
	}
}